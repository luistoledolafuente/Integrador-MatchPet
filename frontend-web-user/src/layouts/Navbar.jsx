import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

export default function Navbar() {
  const { isAuthenticated, logout } = useAuth();

  return (
    <header className="bg-white shadow-md">
      <nav className="container mx-auto px-6 py-4 flex justify-between items-center">
        <Link to="/" className="text-xl font-bold text-[#407581]">
          🐾 MatchPet
        </Link>
        <div className="flex items-center space-x-6">
          <Link to="/#about" className="text-gray-600 hover:text-[#407581]">¿Cómo Funciona?</Link>
          <Link to="/#contact" className="text-gray-600 hover:text-[#407581]">Contacto</Link>
          {isAuthenticated ? (
            <>
              <Link to="/dashboard" className="text-gray-600 hover:text-[#407581]">Dashboard</Link>
              <button
                onClick={logout}
                className="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600"
              >
                Cerrar Sesión
              </button>
            </>
          ) : (
            <Link to="/login" style={{ backgroundColor: 'rgba(64, 117, 131, 0.23)' }} className="bg-blue-600 text-[#407581] font-bold px-4 py-2 rounded-xl hover:bg-blue-700">
              Iniciar Sesión
            </Link>
          )}
        </div>
      </nav>
    </header>
  );
}
