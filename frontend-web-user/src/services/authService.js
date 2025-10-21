import axios from 'axios';

// La URL base de tu API de backend.
// Apunta al servicio que está corriendo (sea en local o en Docker).
const API_URL = 'http://localhost:8081/api/auth';

/**
 * Función para iniciar sesión.
 * Envía las credenciales al endpoint /api/auth/login.
 * @param {string} email - El correo electrónico del usuario.
 * @param {string} password - La contraseña del usuario.
 * @returns {Promise<string>} Una promesa que se resuelve con el token JWT si el login es exitoso.
 */
const login = async (email, password) => {
  const response = await axios.post(`${API_URL}/login`, {
    email,
    password,
  });

  // Si la respuesta contiene un token, lo guardamos en el almacenamiento local del navegador.
  // Esto nos permitirá "recordar" que el usuario está autenticado.
  if (response.data.token) {
    localStorage.setItem('userToken', response.data.token);
  }

  return response.data.token;
};

/**
 * Función para registrar un nuevo usuario.
 * @param {object} userData - Un objeto con { nombreCompleto, email, password, telefono }.
 * @returns {Promise<any>} Una promesa que se resuelve con la respuesta del servidor.
 */
const register = (userData) => {
  return axios.post(`${API_URL}/register`, userData);
};

/**
 * Función para cerrar sesión.
 * Simplemente elimina el token del almacenamiento local.
 */
const logout = () => {
  localStorage.removeItem('userToken');
};

// Exportamos un objeto con todas nuestras funciones de autenticación.
const authService = {
  login,
  register,
  logout,
};

export default authService;
