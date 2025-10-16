import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
// ¡AQUÍ ESTÁ LA CORRECCIÓN!
// Apuntamos al contexto en lugar de la carpeta 'hooks' que ya no existe.
import { useAuth } from '../contexts/AuthContext';

export default function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  
  // Obtenemos todo lo que necesitamos del AuthContext
  const { login, isAuthenticated, loading, error } = useAuth();
  
  const navigate = useNavigate();

  // Redirigimos al usuario si ya está autenticado
  useEffect(() => {
    if (isAuthenticated) {
      navigate('/');
    }
  }, [isAuthenticated, navigate]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    // La lógica de la llamada a la API ahora está en el contexto.
    await login(email, password);
  };

  return (
    <div className="flex min-h-screen">
      {/* Columna izquierda - Formulario */}
      <div className="flex-1 flex items-center justify-center p-8 bg-white">
        <div className="w-full max-w-md space-y-8">
          <div className="text-center">
            <h1 className="text-4xl font-bold text-gray-800">Bienvenido a MatchPet</h1>
            <p className="mt-2 text-gray-500">Inicia sesión para encontrar a tu compañero ideal</p>
          </div>

          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label htmlFor="email" className="block text-sm font-medium text-gray-700">
                Correo Electrónico
              </label>
              <input
                id="email"
                type="email"
                required
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="w-full px-3 py-2 mt-1 text-gray-900 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
              />
            </div>

            <div>
              <label htmlFor="password" className="block text-sm font-medium text-gray-700">
                Contraseña
              </label>
              <input
                id="password"
                type="password"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="w-full px-3 py-2 mt-1 text-gray-900 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
              />
            </div>

            {error && (
              <p className="text-sm text-red-600 bg-red-100 p-3 rounded-md text-center">
                {error}
              </p>
            )}

            <div>
              <button
                type="submit"
                disabled={loading}
                className="w-full px-4 py-3 font-bold text-white bg-primary-600 rounded-md hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500 disabled:bg-primary-400 transition-colors"
              >
                {loading ? 'Iniciando sesión...' : 'Iniciar Sesión'}
              </button>
            </div>
          </form>

          <p className="text-sm text-center text-gray-600">
            ¿No tienes una cuenta?{' '}
            <Link to="/registro" className="font-medium text-primary-600 hover:text-primary-500">
              Regístrate aquí
            </Link>
          </p>
        </div>
      </div>

      {/* Columna derecha - Imagen */}
      <div className="hidden lg:flex flex-1 bg-primary-100">
        <div className="w-full h-full bg-cover bg-center bg-no-repeat" style={{
          backgroundImage: "url('/src/assets/images/pets-login.webp')",
          backgroundSize: 'cover',
          backgroundPosition: 'center'
        }}>
          <div className="w-full h-full bg-black bg-opacity-40 flex items-center justify-center p-12">
            <div className="text-white text-center">
              <h2 className="text-4xl font-bold mb-4">Encuentra tu compañero perfecto</h2>
              <p className="text-xl">Conectamos mascotas con familias amorosas</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

