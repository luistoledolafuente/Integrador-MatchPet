import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

export default function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const { login, isAuthenticated, loading, error } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (isAuthenticated) {
      navigate('/');
    }
  }, [isAuthenticated, navigate]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    await login(email, password);
  };

  return (
    <div className="min-h-screen bg-gradient-to-b from-[#BAE6FD] to-[#FFF7E6] flex items-center justify-center">
      <div className="flex w-full max-w-[100rem] min-h-[80vh] rounded-xl overflow-hidden shadow-lg bg-white/90">
        <div className="flex-1 flex items-center justify-center p-10">
          <div className="w-full max-w-xl space-y-8">
            <div className="text-center">
              <h1 className="text-5xl font-bold text-[#316B7A]">Bienvenido a MatchPet</h1>
              <p className="mt-4 text-gray-500 text-2xl">Únete a nuestra comunidad MatchPet</p>
            </div>

            {/* Tabs visuales */}
            <div className="flex border-b border-gray-300 mb-8">
              <Link
                to="/login"
                className={`flex-1 py-3 text-center text-xl font-semibold transition-all duration-300 ${
                  window.location.pathname === '/login'
                    ? 'border-b-4 border-[#316B7A] text-[#316B7A]'
                    : 'text-gray-500 hover:text-[#316B7A]'
                }`}
              >
                Inicio de Sesión
              </Link>

              <Link
                to="/registro"
                className={`flex-1 py-3 text-center text-xl font-semibold transition-all duration-300 ${
                  window.location.pathname === '/registro'
                    ? 'border-b-4 border-[#316B7A] text-[#316B7A]'
                    : 'text-gray-500 hover:text-[#316B7A]'
                }`}
              >
                Registro
              </Link>
            </div>

            <form onSubmit={handleSubmit} className="space-y-6">
              <div>
                <label htmlFor="email" className="block text-lg font-medium text-gray-700">
                  Correo Electrónico
                </label>
                <input
                  id="email"
                  type="email"
                  required
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="w-full px-3 py-5 mt-1 text-gray-900 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
                />
              </div>

              <div>
                <label htmlFor="password" className="block text-lg font-medium text-gray-700">
                  Contraseña
                </label>
                <input
                  id="password"
                  type="password"
                  required
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="w-full px-3 py-5 mt-1 text-gray-900 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
                />
              </div>
            
              {error && (
                <p className="text-md text-red-600 bg-red-100 p-3 rounded-md text-center">
                  {error}
                </p>
              )}
              <p className="text-lg text-end text-gray-600">
                ¿Olvidaste la contraseña?
              </p>

              <div>
                <button
                  type="submit"
                  disabled={loading}
                  className="w-full text-xl px-4 py-5 font-bold text-white bg-[#316B7A] rounded-xl hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500 disabled:bg-primary-400 transition-colors"
                >
                  {loading ? 'Iniciando sesión...' : 'Iniciar Sesión'}
                </button>
              </div>
              <div className="flex items-center gap-4 my-6">
                <div className="flex-grow border-t border-gray-300" />
                  <span className="text-gray-500 text-lg">O inicia sesión con</span>
                <div className="flex-grow border-t border-gray-300" />
              </div>
              <button
                type="button"
                  className="w-full flex items-center justify-center gap-3 px-4 py-5 border border-gray-300 rounded-xl bg-white text-xl font-semibold text-gray-700 hover:bg-gray-50 transition-colors">
                  <img
                    src="https://www.svgrepo.com/show/475656/google-color.svg"
                    alt="Google logo"
                    className="w-5 h-5"
                  />
                  Inicia sesión con Google
              </button>


            </form>
          </div>
        </div>
        <div className="hidden lg:flex flex-1">
          <div
            className="w-full h-full bg-cover bg-center"
            style={{
              backgroundImage: "url('/src/assets/images/pets-login.webp')",
            }}
          >
            <div className="w-full h-full bg-black bg-opacity-40 flex items-center justify-center p-12">
              <div className="text-white text-center">
                <h2 className="text-4xl font-bold mb-4">Encuentra tu compañero perfecto</h2>
                <p className="text-xl">Conectamos mascotas con familias amorosas</p>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
  );
}
