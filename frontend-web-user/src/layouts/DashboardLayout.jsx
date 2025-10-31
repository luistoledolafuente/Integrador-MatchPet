import React from 'react';
import { Outlet } from 'react-router-dom';
import Sidebar from '../components/Sidebar';

export default function DashboardLayout() {
  return (
    <div className="flex bg-gray-100 min-h-screen">
      <Sidebar />
      <div className="flex-1 p-8">
        {/* Aquí se renderizará el contenido de la página del dashboard actual */}
        <Outlet />
      </div>
    </div>
  );
}
