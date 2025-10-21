import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import authService from '../services/authService';

export default function RegisterPage() {
  const [nombreCompleto, setNombreCompleto] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [telefono, setTelefono] = useState('');

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError('');
    setSuccess('');
    setLoading(true);

    try {
      const userData = { nombreCompleto, email, password, telefono };
      await authService.register(userData);

      setSuccess('¡Registro exitoso! Redirigiendo al inicio de sesión...');
      setTimeout(() => {
        navigate('/login');
      }, 2000);
    } catch (err) {
      const errorMessage =
        err.response?.data || 'Error en el registro. Por favor, intenta de nuevo.';
      setError(errorMessage);
      console.error('Error de registro:', err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-b from-[#BAE6FD] to-[#FFF7E6] flex items-center justify-center">
      <div className="flex w-full max-w-[100rem] min-h-[80vh] rounded-xl overflow-hidden shadow-lg bg-white/90">
        
        {/* Columna del formulario */}
        <div className="flex-1 flex items-center justify-center p-10">
          <div className="w-full max-w-xl space-y-8">
            <div className="text-center">
              <h1 className="text-5xl font-bold text-[#316B7A]">Crea tu cuenta</h1>
              <p className="mt-4 text-gray-500 text-2xl">Únete a la comunidad MatchPet</p>
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
                <label htmlFor="nombreCompleto" className="block text-lg font-medium text-gray-700">
                  Nombre Completo
                </label>
                <input
                  id="nombreCompleto"
                  type="text"
                  required
                  value={nombreCompleto}
                  onChange={(e) => setNombreCompleto(e.target.value)}
                  className="w-full px-3 py-5 mt-1 text-gray-900 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
                />
              </div>

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

              <div>
                <label htmlFor="telefono" className="block text-lg font-medium text-gray-700">
                  Teléfono
                </label>
                <input
                  id="telefono"
                  type="tel"
                  required
                  value={telefono}
                  onChange={(e) => setTelefono(e.target.value)}
                  className="w-full px-3 py-5 mt-1 text-gray-900 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
                />
              </div>

              {error && (
                <p className="text-md text-red-600 bg-red-100 p-3 rounded-md text-center">
                  {error}
                </p>
              )}
              {success && (
                <p className="text-md text-green-600 bg-green-100 p-3 rounded-md text-center">
                  {success}
                </p>
              )}

              <div>
                <button
                  type="submit"
                  disabled={loading}
                  className="w-full text-xl px-4 py-5 font-bold text-white bg-[#316B7A] rounded-xl hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500 disabled:bg-primary-400 transition-colors"
                >
                  {loading ? 'Creando cuenta...' : 'Registrarse'}
                </button>
              </div>
            </form>
          </div>
        </div>

        {/* Columna con imagen (igual al LoginPage pero diferente texto) */}
        <div className="hidden lg:flex flex-1">
          <div
            className="w-full h-full bg-cover bg-center"
            style={{
              backgroundImage: "url('/src/assets/images/pets-register.webp')",
            }}
          >
            <div className="w-full h-full bg-black bg-opacity-40 flex items-center justify-center p-12">
              <div className="text-white text-center">
                <h2 className="text-4xl font-bold mb-4">Únete a nuestra comunidad</h2>
                <p className="text-xl">Ayúdanos a conectar más mascotas con familias amorosas</p>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
  );
}
