import React from "react";

export default function Footer() {
  return (
    <footer style={{ backgroundColor: 'rgba(64, 117, 131, 0.23)' }} className="text-gray-700">
      <div className="max-w-6xl mx-auto px-6 py-10">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8 items-start">
          {/* Left: Logo + description + social */}
          <div>
            <div className="flex items-center gap-3 mb-4">
              <span className="text-xl font-semibold text-[#2F7C8A]">🐾MatchPet</span>
            </div>
            <p className="text-sm text-gray-600 mb-4">
              Encuentra a tu compañero ideal.
            </p>

            <div className="flex gap-3">
              <a href="#" aria-label="Facebook" className="w-9 h-9 flex items-center justify-center rounded-full bg-white border border-gray-200 hover:shadow-sm">
                <svg className="w-4 h-4 text-blue-600" viewBox="0 0 24 24" fill="currentColor" aria-hidden>
                  <path d="M22 12a10 10 0 10-11.5 9.9v-7h-2.2v-2.9h2.2V9.2c0-2.2 1.3-3.4 3.3-3.4.95 0 1.95.17 1.95.17v2.15h-1.1c-1.1 0-1.44.68-1.44 1.38v1.64h2.45l-.39 2.9h-2.06v7A10 10 0 0022 12z"/>
                </svg>
              </a>

              <a href="#" aria-label="Instagram" className="w-9 h-9 flex items-center justify-center rounded-full bg-white border border-gray-200 hover:shadow-sm">
                <svg className="w-4 h-4 text-pink-500" viewBox="0 0 24 24" fill="currentColor" aria-hidden>
                  <path d="M7 2h10a5 5 0 015 5v10a5 5 0 01-5 5H7a5 5 0 01-5-5V7a5 5 0 015-5zm5 6.5A4.5 4.5 0 1016.5 13 4.5 4.5 0 0012 8.5zm6.8-2.4a1.1 1.1 0 11-1.1-1.1 1.1 1.1 0 011.1 1.1z"/>
                </svg>
              </a>
            </div>
          </div>

          {/* Middle: Navegación */}
          <div>
            <h4 className="text-sm font-semibold text-[#316B7A] mb-4 tracking-wider">NAVEGACIÓN</h4>

            <ul className="space-y-3 text-sm text-gray-600">
              <li><a href="#" className="hover:underline">Sobre nosotros</a></li>
              <li><a href="#" className="hover:underline">Blog</a></li>
              <li><a href="#" className="hover:underline">Contacto</a></li>
            </ul>
          </div>

          {/* Right: Legal */}
          <div>
            <h4 className="text-sm font-semibold text-[#316B7A] mb-4 tracking-wider">LEGAL</h4>
            <ul className="space-y-3 text-sm text-gray-600">
              <li><a href="#" className="hover:underline">Política de Privacidad</a></li>
              <li><a href="#" className="hover:underline">Términos de Servicio</a></li>
            </ul>
          </div>
        </div>

        {/* Divider */}
        <div className="border-t border-gray-200 mt-8 pt-6">
          <p className="text-center text-sm text-gray-500">
            © {new Date().getFullYear()} MatchPet. Todos los derechos reservados.
          </p>
        </div>
      </div>
    </footer>
  );
}