import React, { createContext, useState, useEffect, useContext } from 'react';
import authService from '../services/authService';

// 1. Creamos el contexto con un "molde" o valor por defecto.
//    Esto previene errores si un componente intenta usar el contexto sin un proveedor.
const AuthContext = createContext({
  token: null,
  isAuthenticated: false,
  loading: false,
  error: null,
  login: () => Promise.reject('Función de login no disponible fuera del AuthProvider'),
  logout: () => console.error('Función de logout no disponible fuera del AuthProvider'),
});

// 2. Exportamos nuestro hook personalizado desde este mismo archivo.
export const useAuth = () => {
  return useContext(AuthContext);
};

// 3. Hacemos que AuthProvider sea la exportación por defecto.
export default function AuthProvider({ children }) {
  const [token, setToken] = useState(localStorage.getItem('userToken'));
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (token) {
      localStorage.setItem('userToken', token);
    } else {
      localStorage.removeItem('userToken');
    }
  }, [token]);

  const login = async (email, password) => {
    setLoading(true);
    setError(null);
    try {
      const receivedToken = await authService.login(email, password);
      setToken(receivedToken);
      return receivedToken;
    } catch (err) {
      const errorMessage = err.response?.data || 'Error al iniciar sesión.';
      setError(errorMessage);
      throw err; // Relanzamos el error para que el componente que llama sepa que falló
    } finally {
      setLoading(false);
    }
  };

  const logout = () => {
    authService.logout();
    setToken(null);
  };

  // El valor que compartiremos con toda la aplicación
  const value = {
    token,
    isAuthenticated: !!token,
    loading,
    error,
    login,
    logout,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}