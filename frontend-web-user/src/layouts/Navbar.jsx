import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

export default function Navbar() {
  const { isAuthenticated, logout } = useAuth();

  return (
    <header className="bg-white shadow-md">
      <nav className="container mx-auto px-6 py-4 flex justify-between items-center">
        <Link to="/" className="text-xl font-bold text-blue-600">
          🐾 MatchPet
        </Link>
        <div className="flex items-center space-x-6">
          <Link to="/#about" className="text-gray-600 hover:text-blue-600">Sobre Nosotros</Link>
          <Link to="/#contact" className="text-gray-600 hover:text-blue-600">Contacto</Link>
          {isAuthenticated ? (
            <>
              <Link to="/dashboard" className="text-gray-600 hover:text-blue-600">Dashboard</Link>
              <button
                onClick={logout}
                className="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600"
              >
                Cerrar Sesión
              </button>
            </>
          ) : (
            <Link to="/login" className="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700">
              Iniciar Sesión
            </Link>
          )}
        </div>
      </nav>
    </header>
  );
}
