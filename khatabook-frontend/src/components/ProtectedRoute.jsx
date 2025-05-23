import React from 'react';
import { Navigate } from 'react-router-dom';

function ProtectedRoute({ children }) {
  const token = localStorage.getItem('token');

  if (!token) {
    // If no token, redirect to login page
    return <Navigate to="/" replace />;
  }

  return children;
}

export default ProtectedRoute;
