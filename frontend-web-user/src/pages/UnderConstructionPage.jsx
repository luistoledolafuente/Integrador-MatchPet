import React from 'react';
import { Link } from 'react-router-dom';

export default function UnderConstructionPage() {
  return (
    <div className="min-h-[60vh] flex items-center justify-center bg-gray-50">
      <div className="text-center p-8">
        <div className="mb-8">
          <span className="text-6xl">🚧</span>
        </div>
        <h1 className="text-4xl font-bold text-gray-800 mb-4">
          Página en Construcción
        </h1>
        <p className="text-xl text-gray-600 mb-8">
          Estamos trabajando para brindarte una mejor experiencia.
          ¡Vuelve pronto!
        </p>
        <Link
          to="/"
          className="inline-block bg-primary-600 text-white px-6 py-3 rounded-md hover:bg-primary-700 transition-colors"
        >
          Volver al Inicio
        </Link>
      </div>
    </div>
  );
}