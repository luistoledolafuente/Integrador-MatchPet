CREATE DATABASE IF NOT EXISTS matchpet_db;
USE matchpet_db;

CREATE TABLE `Especies` (
  `especie_id` int PRIMARY KEY AUTO_INCREMENT,
  `nombre_especie` varchar(50) UNIQUE NOT NULL
);

CREATE TABLE `Temperamentos` (
  `temperamento_id` int PRIMARY KEY AUTO_INCREMENT,
  `nombre_temperamento` varchar(100) UNIQUE NOT NULL
);

CREATE TABLE `Refugios` (
  `refugio_id` int PRIMARY KEY AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `direccion` text NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `telefono` varchar(20),
  `email` varchar(255) UNIQUE NOT NULL,
  `persona_contacto` varchar(255),
  `fecha_registro` timestamp DEFAULT (now())
);

CREATE TABLE `Adoptantes` (
  `adoptante_id` int PRIMARY KEY AUTO_INCREMENT,
  `nombre_completo` varchar(255) NOT NULL,
  `email` varchar(255) UNIQUE NOT NULL,
  `telefono` varchar(20),
  `fecha_nacimiento` date,
  `direccion` text,
  `ciudad` varchar(100),
  `hash_contraseña` text NOT NULL,
  `fecha_creacion_perfil` timestamp DEFAULT (now())
);

CREATE TABLE `Razas` (
  `raza_id` int PRIMARY KEY AUTO_INCREMENT,
  `nombre_raza` varchar(100) UNIQUE NOT NULL,
  `especie_id` int NOT NULL
);

CREATE TABLE `Animales` (
  `animal_id` int PRIMARY KEY AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `especie_id` int NOT NULL,
  `raza_id` int NOT NULL,
  `refugio_id` int NOT NULL,
  `fecha_nacimiento_aprox` date,
  `genero` ENUM ('Macho', 'Hembra') NOT NULL,
  `tamaño` ENUM ('Pequeño', 'Mediano', 'Grande'),
  `descripcion_personalidad` text,
  `nivel_energia` ENUM ('Bajo', 'Medio', 'Alto'),
  `compatible_niños` bool DEFAULT false,
  `compatible_otras_mascotas` bool DEFAULT false,
  `foto_principal_url` text,
  `estado_adopcion` ENUM ('Disponible', 'En proceso', 'Adoptado') DEFAULT 'Disponible',
  `fecha_ingreso_refugio` date DEFAULT (now())
);

CREATE TABLE `Animal_Temperamentos` (
  `animal_id` int,
  `temperamento_id` int,
  PRIMARY KEY (`animal_id`, `temperamento_id`)
);

CREATE TABLE `Solicitudes_Adopcion` (
  `solicitud_id` int PRIMARY KEY AUTO_INCREMENT,
  `adoptante_id` int NOT NULL,
  `animal_id` int NOT NULL,
  `fecha_solicitud` timestamp DEFAULT (now()),
  `estado_solicitud` ENUM ('Enviada', 'En revisión', 'Aprobada', 'Rechazada') NOT NULL DEFAULT 'Enviada',
  `notas_del_refugio` text
);

CREATE TABLE `Donantes` (
  `donante_id` int PRIMARY KEY AUTO_INCREMENT,
  `nombre_completo` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `adoptante_id` int UNIQUE,
  `fecha_creacion` timestamp DEFAULT (now())
);

CREATE TABLE `Donaciones` (
  `donacion_id` int PRIMARY KEY AUTO_INCREMENT,
  `donante_id` int NOT NULL,
  `refugio_id` int,
  `animal_id` int,
  `monto` decimal(10,2) NOT NULL,
  `moneda` varchar(3) NOT NULL,
  `fecha_donacion` timestamp DEFAULT (now()),
  `estado_pago` ENUM ('Pendiente', 'Completado', 'Fallido', 'Reembolsado') NOT NULL DEFAULT 'Pendiente',
  `gateway_transaccion_id` varchar(255) UNIQUE,
  `mensaje_donante` text
);

-- RESTRICCIONES DE CLAVES FORÁNEAS (Foreign Keys)

ALTER TABLE `Razas` ADD FOREIGN KEY (`especie_id`) REFERENCES `Especies` (`especie_id`);
ALTER TABLE `Animales` ADD FOREIGN KEY (`especie_id`) REFERENCES `Especies` (`especie_id`);
ALTER TABLE `Animales` ADD FOREIGN KEY (`raza_id`) REFERENCES `Razas` (`raza_id`);
ALTER TABLE `Animales` ADD FOREIGN KEY (`refugio_id`) REFERENCES `Refugios` (`refugio_id`);
ALTER TABLE `Animal_Temperamentos` ADD FOREIGN KEY (`animal_id`) REFERENCES `Animales` (`animal_id`);
ALTER TABLE `Animal_Temperamentos` ADD FOREIGN KEY (`temperamento_id`) REFERENCES `Temperamentos` (`temperamento_id`);
ALTER TABLE `Solicitudes_Adopcion` ADD FOREIGN KEY (`adoptante_id`) REFERENCES `Adoptantes` (`adoptante_id`);
ALTER TABLE `Solicitudes_Adopcion` ADD FOREIGN KEY (`animal_id`) REFERENCES `Animales` (`animal_id`);
ALTER TABLE `Donantes` ADD FOREIGN KEY (`adoptante_id`) REFERENCES `Adoptantes` (`adoptante_id`);
ALTER TABLE `Donaciones` ADD FOREIGN KEY (`donante_id`) REFERENCES `Donantes` (`donante_id`);
ALTER TABLE `Donaciones` ADD FOREIGN KEY (`refugio_id`) REFERENCES `Refugios` (`refugio_id`);
ALTER TABLE `Donaciones` ADD FOREIGN KEY (`animal_id`) REFERENCES `Animales` (`animal_id`);
