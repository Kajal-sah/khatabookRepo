import { Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import DashboardPage from './pages/DashboardPage';
import BusinessDetailPage from './pages/BusinessDetailPage';
import CustomerDetailPage from './pages/CustomerDetailPage';

function App() {
  return (
    <Routes>
      <Route path="/" element={<LoginPage />} />
    {/*        
      <Route path="/register" element={<RegisterPage />} />
       
       <Route path="/dashboard" element={
        <ProtectedRoute>
          <DashboardPage />
        </ProtectedRoute>
      } />

      <Route path="/business/:businessId" element={
        <ProtectedRoute>
          <BusinessDetailPage />
        </ProtectedRoute>
      } />

      <Route path="/customer/:customerId" element={
        <ProtectedRoute>
          <CustomerDetailPage />
        </ProtectedRoute>
      } />  */}
    </Routes>
  );
}

export default App;
