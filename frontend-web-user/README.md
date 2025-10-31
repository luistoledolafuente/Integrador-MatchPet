# 🐾 MatchPet - Frontend Web Usuario

![MatchPet Logo](./src/assets/images/paw-logo.png)

MatchPet es una plataforma innovadora diseñada para conectar mascotas en adopción con familias amorosas, utilizando tecnología avanzada para hacer el proceso de adopción más eficiente y exitoso.

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Uso](#-uso)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Contribución](#-contribución)

## ✨ Características

- 🔐 Sistema de autenticación seguro
- 🏠 Interfaz de usuario moderna y responsive
- 🤖 Sistema de recomendaciones con IA
- 🏥 Integración con refugios verificados
- 📱 Diseño adaptable a diferentes dispositivos
- 🔍 Búsqueda avanzada de mascotas

## 🛠 Tecnologías

- **React 18** - Biblioteca principal de UI
- **Vite** - Build tool y dev server
- **React Router v6** - Enrutamiento
- **Tailwind CSS** - Framework de estilos
- **Context API** - Manejo de estado
- **Axios** - Cliente HTTP
- **ESLint** - Linting de código

## 📋 Requisitos Previos

- Node.js (versión 16 o superior)
- npm o yarn
- Git

## 🚀 Instalación

1. Clona el repositorio:
\`\`\`bash
git clone https://github.com/luistoledolafuente/Integrador-MatchPet.git
cd Integrador-MatchPet/frontend-web-user
\`\`\`

2. Instala las dependencias:
\`\`\`bash
npm install
# o
yarn install
\`\`\`

3. Crea un archivo .env en la raíz del proyecto:
\`\`\`env
VITE_API_URL=http://localhost:3000/api
\`\`\`

4. Inicia el servidor de desarrollo:
\`\`\`bash
npm run dev
# o
yarn dev
\`\`\`

## 🖥 Uso

Una vez iniciado el servidor de desarrollo, puedes acceder a la aplicación en:
\`http://localhost:5173\`

### Rutas Principales:

- \`/\` - Página de inicio
- \`/login\` - Inicio de sesión
- \`/registro\` - Registro de usuario
- \`/dashboard\` - Panel de usuario (requiere autenticación)
- \`/mascotas\` - Listado de mascotas disponibles

## 📁 Estructura del Proyecto

\`\`\`
frontend-web-user/
├── public/
├── src/
│   ├── assets/         # Imágenes y recursos estáticos
│   ├── components/     # Componentes reutilizables
│   ├── contexts/       # Contextos de React (Auth, etc.)
│   ├── hooks/         # Hooks personalizados
│   ├── layouts/       # Layouts y plantillas
│   ├── pages/         # Componentes de página
│   ├── services/      # Servicios y API calls
│   ├── utils/         # Utilidades y helpers
│   ├── App.jsx        # Componente principal
│   └── main.jsx       # Punto de entrada
├── .env               # Variables de entorno
├── .eslintrc.js      # Configuración de ESLint
├── package.json
├── tailwind.config.js # Configuración de Tailwind
└── vite.config.js     # Configuración de Vite
\`\`\`

## 🤝 Contribución

1. Crea un fork del proyecto
2. Crea una rama para tu característica (\`git checkout -b feature/AmazingFeature\`)
3. Haz commit de tus cambios (\`git commit -m 'Add some AmazingFeature'\`)
4. Push a la rama (\`git push origin feature/AmazingFeature\`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](./LICENSE) para más detalles.

## ✨ Agradecimientos

- A todos los contribuidores que participan en este proyecto
- A los refugios de animales que confían en nuestra plataforma
- A la comunidad de desarrolladores que mantiene las librerías que usamos

---
Desarrollado con ❤️ por el equipo de MatchPet
