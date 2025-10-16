import React from 'react';

export default function Footer() {
  return (
    <footer className="bg-white border-t mt-8">
      <div className="container mx-auto px-6 py-6 text-center text-sm text-gray-600">
        © {new Date().getFullYear()} MatchPet. Todos los derechos reservados.
      </div>
    </footer>
  );
}
