-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-05-2021 a las 15:18:13
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestionordenadores`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `dni` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `apellidos` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `codigo_postal` int(5) NOT NULL,
  `poblacion` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `provincia` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `telefono` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `imagen_cliente` varchar(200) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`dni`, `nombre`, `apellidos`, `direccion`, `codigo_postal`, `poblacion`, `provincia`, `telefono`, `imagen_cliente`) VALUES
('04124125H', 'Jorge', 'Aragón Moreno', 'Calle Alarcones, 61', 14850, 'Baena', 'Cordoba', '666885566', 'jorge'),
('04187548F', 'Juan', 'Ramirez Gómez', 'Avda. de Villalonga, 34', 46700, 'Gandía', 'Valencia', '668547144', 'juan'),
('04342525X', 'Andrés', 'Sánchez González', 'Avda. de Alcoy, 254', 3700, 'Denia', 'Alicante', '687548755', 'andres'),
('04353576K', 'Felipe', 'Rey Leonardo', 'Avda. de la Luz, 145', 4638, 'Mojácar', 'Almería', '666112244', 'felipe'),
('04356235N', 'Luis', 'Peña Rodriguez', 'Avda. de Los Jinetes, 52', 41410, 'Carmona', 'Sevilla', '666875558', 'luis'),
('04464623R', 'Joaquín', 'Sabina Morales', 'Avda. de las Lomas, 354', 30205, 'Cartagena', 'Murcia', '668875498', 'joaquin'),
('04823852Q', 'Alfonso', 'Ramirez Hernández', 'Calle Arquímedes, 40', 29780, 'Nerja', 'Málaga', '666222211', 'alfonso'),
('04839359K', 'Fernando', 'Gudiel Martínez', 'Calle Pintor Rosales, 3', 23700, 'Linares', 'Jaén', '666333321', 'fernando'),
('04839568T', 'Jesus', 'Montero Pérez', 'Avda. de Salobreña, 32', 18600, 'Motril', 'Granada', '666711188', 'jesus'),
('08754587M', 'Antonio', 'Moreno Blanco', 'Avda. la Parreta, 450', 12560, 'Benicasim', 'Castellón', '666777888', 'antonio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `componentes`
--

CREATE TABLE `componentes` (
  `id` int(10) NOT NULL,
  `nombre` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `marca` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `precio` decimal(8,2) NOT NULL,
  `cif_proveedor` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `id_tipo` int(10) NOT NULL,
  `imagen_componente` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `componentes`
--

INSERT INTO `componentes` (`id`, `nombre`, `marca`, `descripcion`, `precio`, `cif_proveedor`, `id_tipo`, `imagen_componente`, `estado`) VALUES
(1, 'Caja Turret Mesh', 'Cougar', 'Cristal Templado / Negro', '59.96', 'B12650719', 1, 'turretmesh', 0),
(2, 'Caja Hummer TGM', 'NOX', 'RGB Cristal Templado', '64.95', 'B12650719', 1, 'hummertgm', 0),
(3, 'Caja NZXT H710i', 'NZXT', ' Cristal Templado / Blanco', '179.95', 'B12650719', 1, 'nzxth710i', 1),
(4, 'Caja MasterBox MB530P', 'Cooler Master', 'Cristal Templado', '104.96', 'B12650719', 1, 'masterboxmb530p', 1),
(5, 'Fuente Acrux Series', 'Zalman', 'ZM1200-ARX 80 Plus', '170.44', 'B78076395', 2, 'acruxseries', 0),
(6, 'Fuente Urano VX 80', 'NOX', 'Plus Bronze 750W - Fuente/PSU', '54.95', 'B78076395', 2, 'uranovx80', 0),
(7, 'Fuente RM650X 80', 'Corsair', 'Plus Gold 650W', '109.95', 'B78076395', 2, 'rm650x80', 0),
(8, 'Fuente E650 80', 'NZXT', 'Plus Gold 650W Digital Modular', '129.95', 'B78076395', 2, 'e65080', 1),
(9, 'Placa Socket 1151', 'Asus', 'ROG Strix Z390-H Gaming', '198.95', 'B82766452', 3, 'asusrogxtrixz390h', 0),
(10, 'Placa Socket 1151', 'Gigabyte', 'Z390 AORUS Pro', '209.95', 'B82766452', 3, 'gigaz390aoruspro', 0),
(11, 'Placa Socket sTRX4', 'Asus', 'ROG Zenith II Extreme Alpha', '999.94', 'B82766452', 3, 'asusrogzenithii', 1),
(12, 'Placa Socket sTRX4', 'Gigabyte', 'TRX40 Designare', '704.95', 'B82766452', 3, 'gigatrx40desi', 1),
(13, 'Procesador Socket 1151', 'Intel', 'Core i9-9900K 5.0 GHz', '491.95', 'B58728585', 4, 'i9-9900K', 0),
(14, 'Procesador Socket 1151', 'Intel', 'Core i7-9700KF 4.9 Ghz', '419.95', 'B58728585', 4, 'i7-9700KF', 0),
(15, 'Procesador Socket sTRX4', 'AMD', 'Ryzen Threadripper 3990X 4.3Ghz', '4269.94', 'B58728585', 4, '3990X', 1),
(16, 'Procesador Socket sTRX4', 'AMD', 'Ryzen Threadripper 3970X 4.5GHz', '2138.95', 'B58728585', 4, '3970X', 1),
(17, 'Disipador', 'Corsair', 'Hydro Series H100i Pro - Kit Líquida', '119.95', 'B84443985', 5, 'H100i', 0),
(18, 'Disipador', 'Cougar', 'AQUA 360 - Kit Líquida', '109.94', 'B84443985', 5, 'aqua360', 0),
(19, 'Disipador', 'Cooler Master', 'Wraith Ripper Socket AMD TR4', '99.95', 'B84443985', 5, 'wrariptr4', 1),
(20, 'Disipador', 'Noctua', 'NH-U14S TR4-SP3', '83.95', 'B84443985', 5, 'nh-u14s', 1),
(21, 'Memoria DDR4', 'Corsair', 'Vengeance RGB Pro Black 16GB (2x8GB) 3200MHz (PC4-', '99.95', 'B30542849', 6, 'corven2x8g', 0),
(22, 'Memoria DDR4', 'G.Skill', 'Trident Z Black/Black 16GB (2x8GB) 4400Mhz', '345.95', 'B30542849', 6, 'zblack2x8g', 0),
(23, 'Memoria DDR4', 'Corsair', 'Vengeance LPX Black 64GB (8x8GB) 4200Mhz', '1008.55', 'B30542849', 6, 'corven8x8g', 1),
(24, 'Memoria DDR4', 'Team Group', 'Xtreem \"8Pack Edition\" 32GB (4x8GB) 4000Mhz', '402.95', 'B30542849', 6, 'tegrxt4x8g', 1),
(25, 'Disco Duro', 'Western Digital', 'Xtreem \"Caviar Black 2TB 3.5\" HDD SATA3', '132.95', 'B32307738', 7, 'wdcb2tb35', 0),
(26, 'Disco Duro', 'Seagate', 'BarraCuda Compute HDD 2TB 3.5\" HDD SATA3', '47.95', 'B32307738', 7, 'sbc2tb35', 0),
(27, 'Disco Duro', 'Samsung', '970 PRO 512GB NVMe SSD SSD M.2', '218.95', 'B32307738', 7, 's970pro512g', 1),
(28, 'Disco Duro', 'Kingston', 'KC600 512GB 2.5\" SSD SATA3 NAND TLC 3D', '83.95', 'B32307738', 7, 'kc600512g', 1),
(29, 'Tarjeta Gráfica', 'Gigabyte', 'AORUS GeForce® RTX 2080 Ti 11GB GDDR6', '1347.95', 'B30701924', 8, 'GV-N208TAORUS-11GC', 0),
(30, 'Tarjeta Gráfica', 'Asus', 'ROG Strix GeForce® RTX 2080 Ti Advanced', '1389.95', 'B30701924', 8, '90YV0CC1-M0NM00', 0),
(31, 'Tarjeta Gráfica', 'MSI', 'GeForce® RTX 2080 SUPER GAMING X TRIO 8GB', '819.94', 'B30701924', 8, '912-V372-248', 1),
(32, 'Tarjeta Gráfica', 'Gigabyte', 'GeForce® RTX 2070 SUPER Windforce OC 3X 8GB', '550.95', 'B30701924', 8, 'GV-N207SWF3OC-8GD', 1),
(33, 'Tarjeta Sonido', 'Creative', 'Labs Sound Blaster ZxR', '196.95', 'B18314112', 9, '70SB151000001', 0),
(34, 'Tarjeta Sonido', 'Creative', 'Labs Sound Blaster Z', '69.95', 'B18314112', 9, '70SB150000001', 0),
(35, 'Tarjeta Sonido', 'Asus', 'Xonar Essence STX II 5.1 PCIe', '213.95', 'B18314112', 9, '90YA00MN-M0UA00', 1),
(36, 'Tarjeta Sonido', 'Asus', 'Essence STX II 7.1', '241.95', 'B18314112', 9, '90YA00NN-M0UA00', 1),
(37, 'Modding Pc', 'Lamptron', 'FlexLight Triple RF RGB Tira LED', '29.95', 'B79484564', 10, 'LAMP-LEDFM1006', 1),
(38, 'Modding Pc', 'BitFenix', 'Alchemy 3.0 Magnetic Addressable RGB', '23.95', 'B79484564', 10, 'BFA-ADD-30MK15N-RP', 1),
(39, 'Modding Pc', 'Corsair', 'iCUE LS100 Smart Lighting Tiras LED', '77.95', 'B79484564', 10, 'CD-9010001-EU', 1),
(40, 'Modding Pc', 'NZXT', 'Hue 2 Underglow 300mm Tira Led', '29.95', 'B79484564', 10, 'AH-2UGKK-A1', 1),
(41, 'Regrabadora', 'Asus', 'BLU-RAY Interna BW-16D1HT/G', '81.95', 'B04427936', 11, '90DD01E0-B20000', 0),
(42, 'Regrabadora', 'LG', 'BLU-RAY Interna BH16 Super Multi BDXL 16x', '95.95', 'B04427936', 11, 'BH16NS55.AHLR10B', 0),
(43, 'Regrabadora', 'Asus', 'DRW-24D5MT 24x SATA Negra', '13.95', 'B04427936', 11, '90DD01Y0-B10010', 1),
(44, 'Regrabadora', 'LG', 'DVD-RW Interna 24x SATA Negro', '14.96', 'B04427936', 11, 'GH24NSD1AUAA10B', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `montajes`
--

CREATE TABLE `montajes` (
  `id_ordenador` int(10) NOT NULL,
  `id_componente` int(10) NOT NULL,
  `id_operario` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `montajes`
--

INSERT INTO `montajes` (`id_ordenador`, `id_componente`, `id_operario`) VALUES
(1, 1, 1),
(1, 5, 1),
(1, 9, 1),
(1, 13, 1),
(1, 17, 1),
(1, 21, 1),
(1, 25, 1),
(1, 29, 1),
(1, 33, 1),
(1, 41, 1),
(2, 7, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operarios`
--

CREATE TABLE `operarios` (
  `id` int(10) NOT NULL,
  `nombre` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `apellidos` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `imagen_operario` varchar(200) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `operarios`
--

INSERT INTO `operarios` (`id`, `nombre`, `apellidos`, `imagen_operario`) VALUES
(1, 'Alejandro', 'Bolaños Sánchez', 'alejandro'),
(2, 'Alfredo', 'Bautista Mejía', 'alfredo'),
(3, 'Humberto', 'Blanco Velasco', 'humberto'),
(4, 'Marcela', 'Aguilar Pérez', 'marcela'),
(5, 'Francisco', 'Alarcón Loranca', 'francisco'),
(6, 'Israel', 'Alcoverde Martínez', 'israel'),
(7, 'Rocío', 'Alonso Ibarra', 'rocio'),
(8, 'Federíco', 'Díaz Salgado', 'federico'),
(9, 'Manuel', 'Vivaldo Barrios', 'manuel'),
(10, 'Vicente', 'Duarte Domínguez', 'vicente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenadores`
--

CREATE TABLE `ordenadores` (
  `id` int(10) NOT NULL,
  `modelo` varchar(80) COLLATE utf8_spanish2_ci NOT NULL,
  `precio` decimal(8,2) NOT NULL,
  `dni_cliente` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha_montaje` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `ordenadores`
--

INSERT INTO `ordenadores` (`id`, `modelo`, `precio`, `dni_cliente`, `fecha_montaje`) VALUES
(1, 'GutPC Gamer I i9 9900K/64GB/Asus', '4500.00', '08754587M', '2020-03-12'),
(2, 'GutPC Gamer II i7 9700KF/32GB/Giga', '3800.00', '04187548F', '2020-03-12'),
(3, 'GutPC Gamer III i9 9900K/16GB/Asus', '3400.00', '04342525X', '2020-03-12'),
(4, 'GutPC Gamer IV i7 9700KF/16GB/Giga', '3675.00', '04464623R', '2020-03-13'),
(5, 'GutPC Gamer V i7 9700KF/32GB/Asus', '3780.00', '04356235N', '2020-03-13'),
(6, 'GutPC Gamer VI Threadripper 3990X/64GB/A', '7500.00', '04124125H', '2020-03-13'),
(7, 'GutPC Gamer VII Threadripper 3970X/64GB/', '5500.00', '04839359K', '2020-03-14'),
(8, 'GutPC Gamer VIII Threadripper 3990X/32GB', '6500.00', '04823852Q', '2020-03-14'),
(9, 'GutPC Gamer IX Threadripper 3970X/32GB/G', '4500.00', '04839568T', '2020-03-14'),
(10, 'GutPC Gamer X Threadripper 3970X/16GB/A', '3600.00', '04353576K', '2020-03-16');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `cif` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `codigo_postal` int(5) NOT NULL,
  `poblacion` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `provincia` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `telefono` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `imagen_proveedor` varchar(200) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`cif`, `nombre`, `direccion`, `codigo_postal`, `poblacion`, `provincia`, `telefono`, `imagen_proveedor`) VALUES
('B04427936', 'Globomatik', 'C/ Sierra de Segura, 17', 4240, 'Almería', 'Almería', '950081876', 'globomatik'),
('B12650719', 'Coolmod', 'Carrer de L Històric Regne de Valéncia, Nave 2', 12550, 'Almazora', 'Castellón', '964256677', 'coolmod'),
('B18314112', 'Megasur', 'C/ Los Visos, nº14', 18130, 'Escúzar', 'Granada', '958509010', 'megasur'),
('B30542849', 'Hispamicro', 'Avda. de las Américas, 1-16 Nave C6', 30820, 'Alcantarilla', 'Murcia', '968350123', 'hispamicro'),
('B30701924', 'Depau', 'Avda. del Carbono, Nº 46 Parc.95', 30369, 'Los Camachos', 'Murcia', '968506619', 'depau'),
('B32307738', 'Supercomp', 'C/ Ricardo Martín Esperanza Sector C1', 32901, 'Pgno. San Cibrao das Viñas', 'Ourense', '988383828', 'supercomp'),
('B58728585', 'Techdata', 'Avda. de la Vega, 1', 28108, 'Alcobendas', 'Madrid', '666112244', 'techdata'),
('B78076395', 'Ingram Micro', 'C/ Cantabria, 2 - 3ª Planta Módulo B1', 28108, 'Alcobendas', 'Madrid', '902506210', 'ingram'),
('B79484564', 'Vinzeo', 'Avenida de Bruselas, 36 – Bajo', 28108, 'Alcobendas', 'Madrid', '902380480', 'vinzeo'),
('B82766452', 'MCRs', 'C/ Gutenberg 123', 28904, 'Getafe', 'Madrid', '914400701', 'mcr'),
('B84443985', 'Esprinet', 'C/ Osca, n° 2, Plaza', 50197, 'Zaragoza', 'Zaragona', '‎976766110', 'esprinet');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reparaciones`
--

CREATE TABLE `reparaciones` (
  `id_ordenador` int(10) NOT NULL,
  `id_componente` int(10) NOT NULL,
  `id_operario` int(10) NOT NULL,
  `fecha_reparacion` date NOT NULL,
  `precio` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `reparaciones`
--

INSERT INTO `reparaciones` (`id_ordenador`, `id_componente`, `id_operario`, `fecha_reparacion`, `precio`) VALUES
(1, 2, 1, '2020-02-25', '64.95'),
(2, 6, 2, '2020-02-25', '54.95'),
(3, 10, 3, '2020-02-25', '209.95'),
(4, 14, 4, '2020-02-25', '419.95'),
(5, 18, 5, '2020-02-25', '109.94'),
(6, 22, 6, '2020-03-01', '345.95'),
(7, 26, 7, '2020-03-01', '47.95'),
(8, 30, 8, '2020-03-01', '1389.95'),
(9, 34, 9, '2020-03-01', '69.95'),
(10, 42, 10, '2020-03-01', '95.95');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos`
--

CREATE TABLE `tipos` (
  `id` int(10) NOT NULL,
  `tipo` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `tipos`
--

INSERT INTO `tipos` (`id`, `tipo`) VALUES
(1, 'Caja/Torre'),
(2, 'Fuente Alimentación'),
(3, 'Placa Base'),
(4, 'Procesador'),
(5, 'Disipador Ventilador'),
(6, 'Memoria RAM'),
(7, 'Disco Duro'),
(8, 'Tarjeta Gráfica'),
(9, 'Tarjeta Sonido'),
(10, 'Modding Pc'),
(11, 'Otros');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(10) NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `apellidos` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `usuario` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `password` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(100) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apellidos`, `usuario`, `password`, `direccion`) VALUES
(1, 'Yo', 'mismo', 'admin', '1234', 'C/ Java, 11'),
(2, 'Usuario', 'Programa', 'usuario', 'usuario', 'C/ Java, 11');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `componentes`
--
ALTER TABLE `componentes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cif_proveedor` (`cif_proveedor`),
  ADD KEY `id_tipo` (`id_tipo`);

--
-- Indices de la tabla `montajes`
--
ALTER TABLE `montajes`
  ADD PRIMARY KEY (`id_ordenador`,`id_componente`),
  ADD KEY `id_componente` (`id_componente`),
  ADD KEY `id_operario` (`id_operario`);

--
-- Indices de la tabla `operarios`
--
ALTER TABLE `operarios`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ordenadores`
--
ALTER TABLE `ordenadores`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dni_cliente` (`dni_cliente`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`cif`);

--
-- Indices de la tabla `reparaciones`
--
ALTER TABLE `reparaciones`
  ADD PRIMARY KEY (`id_ordenador`,`id_componente`),
  ADD KEY `id_componente` (`id_componente`),
  ADD KEY `id_operario` (`id_operario`);

--
-- Indices de la tabla `tipos`
--
ALTER TABLE `tipos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `componentes`
--
ALTER TABLE `componentes`
  ADD CONSTRAINT `componentes_ibfk_1` FOREIGN KEY (`cif_proveedor`) REFERENCES `proveedores` (`cif`),
  ADD CONSTRAINT `componentes_ibfk_2` FOREIGN KEY (`id_tipo`) REFERENCES `tipos` (`id`);

--
-- Filtros para la tabla `montajes`
--
ALTER TABLE `montajes`
  ADD CONSTRAINT `montajes_ibfk_1` FOREIGN KEY (`id_ordenador`) REFERENCES `ordenadores` (`id`),
  ADD CONSTRAINT `montajes_ibfk_2` FOREIGN KEY (`id_componente`) REFERENCES `componentes` (`id`),
  ADD CONSTRAINT `montajes_ibfk_3` FOREIGN KEY (`id_operario`) REFERENCES `operarios` (`id`);

--
-- Filtros para la tabla `ordenadores`
--
ALTER TABLE `ordenadores`
  ADD CONSTRAINT `ordenadores_ibfk_1` FOREIGN KEY (`dni_cliente`) REFERENCES `clientes` (`dni`);

--
-- Filtros para la tabla `reparaciones`
--
ALTER TABLE `reparaciones`
  ADD CONSTRAINT `reparaciones_ibfk_1` FOREIGN KEY (`id_ordenador`) REFERENCES `ordenadores` (`id`),
  ADD CONSTRAINT `reparaciones_ibfk_2` FOREIGN KEY (`id_componente`) REFERENCES `componentes` (`id`),
  ADD CONSTRAINT `reparaciones_ibfk_3` FOREIGN KEY (`id_operario`) REFERENCES `operarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
