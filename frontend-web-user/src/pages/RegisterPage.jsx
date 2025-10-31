import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import authService from '../services/authService';

export default function RegisterPage() {
  // Estados para cada campo del formulario
  const [nombreCompleto, setNombreCompleto] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [telefono, setTelefono] = useState('');

  // Estados para manejar la carga y los errores
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
      // Creamos el objeto con los datos del usuario para enviarlo a la API
      const userData = { nombreCompleto, email, password, telefono };
      
      // Llamamos a la función 'register' de nuestro servicio
      await authService.register(userData);
      
      setSuccess('¡Registro exitoso! Redirigiendo al login...');
      
      // Esperamos 2 segundos para que el usuario vea el mensaje y lo redirigimos
      setTimeout(() => {
        navigate('/login');
      }, 2000);

    } catch (err) {
      const errorMessage = err.response?.data || 'Error en el registro. Por favor, intenta de nuevo.';
      setError(errorMessage);
      console.error('Error de registro:', err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex min-h-screen">
      {/* Columna izquierda - Imagen */}
      <div className="hidden lg:flex flex-1 bg-primary-100">
        <div className="w-full h-full bg-cover bg-center bg-no-repeat" style={{
          backgroundImage: "url('/src/assets/images/pets-register.webp')",
          backgroundSize: 'cover',
          backgroundPosition: 'center'
        }}>
          <div className="w-full h-full bg-black bg-opacity-40 flex items-center justify-center p-12">
            <div className="text-white text-center">
              <h2 className="text-4xl font-bold mb-4">Únete a nuestra comunidad</h2>
              <p className="text-xl">Ayúdanos a conectar más mascotas con familias amorosas</p>
            </div>
          </div>
        </div>
      </div>

      {/* Columna derecha - Formulario */}
      <div className="flex-1 flex items-center justify-center p-8 bg-white">
        <div className="w-full max-w-md space-y-8">
          <div className="text-center">
            <h1 className="text-4xl font-bold text-gray-800">Crea tu Cuenta</h1>
            <p className="mt-2 text-gray-500">Únete a la comunidad de MatchPet</p>
          </div>

          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label htmlFor="nombreCompleto" className="block text-sm font-medium text-gray-700">
                Nombre Completo
              </label>
              <input
                id="nombreCompleto"
                type="text"
                required
                value={nombreCompleto}
                onChange={(e) => setNombreCompleto(e.target.value)}
                className="w-full px-3 py-2 mt-1 text-gray-900 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
              />
            </div>
            
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

            <div>
              <label htmlFor="telefono" className="block text-sm font-medium text-gray-700">
                Teléfono
              </label>
              <input
                id="telefono"
                type="tel"
                required
                value={telefono}
                onChange={(e) => setTelefono(e.target.value)}
                className="w-full px-3 py-2 mt-1 text-gray-900 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
              />
            </div>

            {error && <p className="text-sm text-red-600 bg-red-100 p-3 rounded-md text-center">{error}</p>}
            {success && <p className="text-sm text-green-600 bg-green-100 p-3 rounded-md text-center">{success}</p>}

            <div>
              <button
                type="submit"
                disabled={loading}
                className="w-full px-4 py-3 font-bold text-white bg-primary-600 rounded-md hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500 disabled:bg-primary-400 transition-colors"
              >
                {loading ? 'Creando cuenta...' : 'Registrarse'}
              </button>
            </div>
          </form>

          <p className="text-sm text-center text-gray-600">
            ¿Ya tienes una cuenta?{' '}
            <Link to="/login" className="font-medium text-primary-600 hover:text-primary-500">
              Inicia sesión
            </Link>
          </p>
        </div>
      </div>
    </div>
  );
}
