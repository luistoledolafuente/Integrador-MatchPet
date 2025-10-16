import React from 'react';
import { Navigate } from 'react-router-dom';
// ¡AQUÍ ESTÁ LA CORRECCIÓN!
// Cambiamos la ruta para que apunte al contexto, donde ahora vive el hook.
import { useAuth } from '../contexts/AuthContext';

// Este componente actúa como un guardia de seguridad para nuestras rutas.
const ProtectedRoute = ({ children }) => {
  const { isAuthenticated, loading } = useAuth();

  // Opcional: Mientras el contexto está verificando el token, podemos mostrar un loader.
  if (loading) {
    return <div>Cargando...</div>;
  }

  // Si el usuario NO está autenticado, lo redirigimos a la página de login.
  if (!isAuthenticated) {
    return <Navigate to="/login" />;
  }

  // Si está autenticado, mostramos el contenido de la página solicitada.
  return children;
};

export default ProtectedRoute;

