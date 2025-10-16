import React from 'react';
import { NavLink, Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext'; // Corregido para apuntar al contexto

// Íconos simples para el menú
const HomeIcon = () => <span role="img" aria-label="home">🏠</span>;
const PawIcon = () => <span role="img" aria-label="paw">🐾</span>;
const HeartIcon = () => <span role="img" aria-label="heart">❤️</span>;
const UserIcon = () => <span role="img" aria-label="user">👤</span>;
const LogoutIcon = () => <span role="img" aria-label="logout">🚪</span>;

export default function Sidebar() {
  const { logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login'); // Redirige al login después de cerrar sesión
  };

  // Estilos base para los enlaces de navegación
  const linkStyles = "flex items-center px-4 py-3 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors duration-200";
  // Estilos para el enlace activo
  const activeLinkStyles = "bg-blue-600 text-white shadow-lg";

  return (
    <aside className="w-64 bg-white shadow-xl flex flex-col h-screen p-4">
      <div className="px-2 py-4 border-b border-gray-200">
        <Link to="/" className="text-2xl font-bold text-blue-600 text-center block">MatchPet</Link>
      </div>
      <nav className="flex-grow mt-6 space-y-2">
        <NavLink 
          to="/dashboard" 
          end 
          className={({ isActive }) => `${linkStyles} ${isActive ? activeLinkStyles : ''}`}
        >
          <HomeIcon />
          <span className="ml-4 font-medium">Inicio</span>
        </NavLink>
        <NavLink 
          to="/dashboard/mascotas" 
          className={({ isActive }) => `${linkStyles} ${isActive ? activeLinkStyles : ''}`}
        >
          <PawIcon />
          <span className="ml-4 font-medium">Mascotas</span>
        </NavLink>
        <NavLink 
          to="/dashboard/favoritos" 
          className={({ isActive }) => `${linkStyles} ${isActive ? activeLinkStyles : ''}`}
        >
          <HeartIcon />
          <span className="ml-4 font-medium">Mis Favoritos</span>
        </NavLink>
        <NavLink 
          to="/dashboard/perfil" 
          className={({ isActive }) => `${linkStyles} ${isActive ? activeLinkStyles : ''}`}
        >
          <UserIcon />
          <span className="ml-4 font-medium">Mi Perfil</span>
        </NavLink>
      </nav>
      <div className="py-4 border-t border-gray-200">
        <button 
          onClick={handleLogout} 
          className={`${linkStyles} w-full text-left text-red-500 hover:bg-red-50 hover:text-red-600 font-semibold`}
        >
          <LogoutIcon />
          <span className="ml-4">Cerrar Sesión</span>
        </button>
      </div>
    </aside>
  );
}

