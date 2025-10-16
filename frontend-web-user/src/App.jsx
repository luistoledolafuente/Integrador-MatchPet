import { Routes, Route } from 'react-router-dom';

// Importación de Páginas
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import HomePage from './pages/HomePage';
import DashboardHomePage from './pages/DashboardHomePage';
import MascotasPage from './pages/MascotasPage';
import UnderConstructionPage from './pages/UnderConstructionPage';

// Importación de Layouts (Plantillas de Diseño)
import MainLayout from './layouts/MainLayout';
import DashboardLayout from './layouts/DashboardLayout';

// Importación de Componentes de Seguridad
import ProtectedRoute from './components/ProtectedRoute';

function App() {
  return (
    <Routes>
      {/* --- Rutas Públicas --- */}
      <Route element={<MainLayout />}>
        <Route path="/" element={<HomePage />} />
        <Route path="/sobre-nosotros" element={<UnderConstructionPage />} />
        <Route path="/contacto" element={<UnderConstructionPage />} />
        <Route path="/blog" element={<UnderConstructionPage />} />
        <Route path="/adoptar" element={<UnderConstructionPage />} />
        <Route path="/donar" element={<UnderConstructionPage />} />
        <Route path="/refugio" element={<UnderConstructionPage />} />
      </Route>

      {/* --- Rutas de Autenticación --- */}
      <Route path="/login" element={<LoginPage />} />
      <Route path="/registro" element={<RegisterPage />} />

      {/* --- Rutas Protegidas del Dashboard --- */}
      <Route 
        path="/dashboard" 
        element={
          <ProtectedRoute>
            <DashboardLayout />
          </ProtectedRoute>
        }
      >
        <Route index element={<DashboardHomePage />} />
        <Route path="mascotas" element={<MascotasPage />} />
        <Route path="favoritos" element={<UnderConstructionPage />} />
        <Route path="perfil" element={<UnderConstructionPage />} />
        <Route path="mensajes" element={<UnderConstructionPage />} />
        <Route path="configuracion" element={<UnderConstructionPage />} />
      </Route>

      {/* Ruta para cualquier URL no definida */}
      <Route path="*" element={
        <MainLayout>
          <UnderConstructionPage />
        </MainLayout>
      } />

    </Routes>
  );
}

export default App;

