import React from 'react';
import { Navigate } from 'react-router-dom';
// ¡AQUÍ ESTÁ LA CORRECCIÓN!
// Cambiamos la ruta para que apunte al contexto, donde ahora vive el hook.
import { useAuth } from '../contexts/AuthContext';

// Este componente actúa como un guardia de seguridad para nuestras rutas.
const ProtectedRoute = ({ children }) => {
  const { isAuthenticated, loading } = useAuth();

  // Mientras se verifica la autenticación, mostramos un loader
  if (loading) {
    return (
      <div className="flex items-center justify-center min-h-screen">
        <div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-primary-600"></div>
      </div>
    );
  }

  // Si el usuario NO está autenticado, lo redirigimos a la página de login
  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  // Si está autenticado, mostramos el contenido de la página solicitada
  return children;
};

export default ProtectedRoute;

