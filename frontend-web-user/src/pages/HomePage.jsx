import React from 'react';
import { Link } from 'react-router-dom';

export default function HomePage() {
  return (
    <div className="bg-sky-50">
      {/* Hero Section */}
      <div className="container mx-auto px-6 py-16 text-center">
        <div className="max-w-3xl mx-auto">
          <h1 className="text-5xl font-bold text-gray-800 mb-6">
            Encuentra a tu compañero ideal
          </h1>
          <p className="text-xl text-gray-600 mb-12">
            Nuestra plataforma conecta a compañeros de vida maravillosos con hogares amorosos, 
            utilizamos tecnología avanzada para hacer cada adopción una experiencia única y especial.
          </p>
          <div className="flex justify-center gap-4 mb-12">
            <Link
              to="/dashboard"
              className="px-8 py-3 bg-teal-700 text-white rounded-md hover:bg-teal-800 transition-colors"
            >
              Adoptar
            </Link>
            <Link
              to="/donar"
              className="px-8 py-3 bg-coral-500 text-white rounded-md hover:bg-coral-600 transition-colors"
            >
              Donar
            </Link>
            <Link
              to="/refugio"
              className="px-8 py-3 bg-white text-teal-700 border border-teal-700 rounded-md hover:bg-teal-50 transition-colors"
            >
              Soy Refugio
            </Link>
          </div>
        </div>
      </div>

      {/* Features Section */}
      <section className="container mx-auto px-6 py-16">
        <div className="text-center mb-12">
          <h2 className="text-3xl font-bold text-gray-800 mb-4">
            ¿Cómo te ayuda MatchPet?
          </h2>
          <p className="text-lg text-gray-600 max-w-2xl mx-auto">
            Descubre cómo nuestra plataforma facilita la adopción y ayuda a conectar mascotas con familias amorosas
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8 max-w-5xl mx-auto">
          <div className="flex flex-col items-center bg-white p-8 rounded-lg shadow-sm">
            <div className="w-16 h-16 bg-sky-100 rounded-full flex items-center justify-center mb-4">
              <svg className="w-8 h-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
              </svg>
            </div>
            <h3 className="text-xl font-semibold mb-2">Adopta con Amor</h3>
            <p className="text-gray-600 text-center">Encuentra tu compañero perfecto entre nuestras mascotas en adopción</p>
          </div>

          <div className="flex flex-col items-center bg-white p-8 rounded-lg shadow-sm">
            <div className="w-16 h-16 bg-sky-100 rounded-full flex items-center justify-center mb-4">
              <svg className="w-8 h-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M9.663 17h4.673M12 3v1m6.364 1.636l-.707.707M21 12h-1M4 12H3m3.343-5.657l-.707-.707m2.828 9.9a5 5 0 117.072 0l-.548.547A3.374 3.374 0 0014 18.469V19a2 2 0 11-4 0v-.531c0-.895-.356-1.754-.988-2.386l-.548-.547z" />
              </svg>
            </div>
            <h3 className="text-xl font-semibold mb-2">Recomendaciones con IA</h3>
            <p className="text-gray-600 text-center">Sistema inteligente que te ayuda a encontrar tu mascota ideal</p>
          </div>

          <div className="flex flex-col items-center bg-white p-8 rounded-lg shadow-sm">
            <div className="w-16 h-16 bg-sky-100 rounded-full flex items-center justify-center mb-4">
              <svg className="w-8 h-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" />
              </svg>
            </div>
            <h3 className="text-xl font-semibold mb-2">Apoyo a Refugios</h3>
            <p className="text-gray-600 text-center">Colaboramos con refugios verificados para garantizar adopciones seguras</p>
          </div>
        </div>

        {/* Galería de imágenes */}
        <div className="grid grid-cols-2 md:grid-cols-4 gap-4 max-w-5xl mx-auto mt-16">
          <img src="/src/assets/images/dog1.webp" alt="Perro feliz" className="rounded-lg object-cover w-full h-48" />
          <img src="/src/assets/images/cat1.webp" alt="Gato descansando" className="rounded-lg object-cover w-full h-48" />
          <img src="/src/assets/images/adoption1.webp" alt="Momento de adopción" className="rounded-lg object-cover w-full h-48" />
          <img src="/src/assets/images/pet2.webp" alt="Mascota jugando" className="rounded-lg object-cover w-full h-48" />
        </div>
      </section>
    </div>
  );
}