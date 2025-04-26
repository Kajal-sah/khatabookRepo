package com.khatabook.khatabook_backend.security;

import com.khatabook.khatabook_backend.service.Impl.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String email = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                email = jwtUtil.extractEmail(jwt);
                logger.info("🔐 JWT Token received: {}", jwt);
                logger.info("👤 Email extracted from token: {}", email);
            } catch (ExpiredJwtException e) {
                logger.warn("⚠️ Token expired: {}", e.getMessage());
            } catch (Exception e) {
                logger.error("❌ Token parsing error: {}", e.getMessage());
            }
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email); // Loading by email
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken token =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token);
                    logger.info("✅ Authenticated user: {}", userDetails.getUsername());
                } else {
                    logger.warn("⚠️ Invalid JWT token for email: {}", email);
                }
            } catch (Exception e) {
                logger.error("❌ Error authenticating user: {}", e.getMessage());
            }
        } else {
            if (email == null) {
                logger.warn("⚠️ No email extracted from token.");
            } else {
                logger.debug("ℹ️ User already authenticated: {}", email);
            }
        }

        filterChain.doFilter(request, response);
    }
}
