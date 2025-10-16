import { Routes, Route } from 'react-router-dom';

// Importación de Páginas
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import HomePage from './pages/HomePage';
import DashboardHomePage from './pages/DashboardHomePage';
import MascotasPage from './pages/MascotasPage';

// Importación de Layouts (Plantillas de Diseño)
import MainLayout from './layouts/MainLayout';
import DashboardLayout from './layouts/DashboardLayout';

// Importación de Componentes de Seguridad
import ProtectedRoute from './components/ProtectedRoute';

function App() {
  return (
    <Routes>
      {/* --- Rutas Públicas --- */}
      {/* Estas rutas usan el MainLayout que tiene Navbar y Footer */}
      <Route element={<MainLayout />}>
        <Route path="/" element={<HomePage />} />
        {/* Aquí podrías añadir otras páginas públicas como /sobre-nosotros, /contacto, etc. */}
      </Route>

      {/* --- Rutas de Autenticación --- */}
      {/* Estas rutas son a pantalla completa, por lo que no usan ningún layout */}
      <Route path="/login" element={<LoginPage />} />
      <Route path="/registro" element={<RegisterPage />} />

      {/* --- Rutas Protegidas del Dashboard --- */}
      {/* Todas las rutas que empiecen con /dashboard estarán protegidas */}
      <Route 
        path="/dashboard" 
        element={
          <ProtectedRoute>
            <DashboardLayout />
          </ProtectedRoute>
        }
      >
        {/* Estas son las sub-rutas que se mostrarán dentro del DashboardLayout */}
        <Route index element={<DashboardHomePage />} />
        <Route path="mascotas" element={<MascotasPage />} />
        {/* Futuras rutas del dashboard irían aquí, por ejemplo: */}
        {/* <Route path="favoritos" element={<FavoritosPage />} /> */}
        {/* <Route path="perfil" element={<PerfilPage />} /> */}
      </Route>

    </Routes>
  );
}

export default App;

