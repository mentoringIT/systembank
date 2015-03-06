--
-- Base de datos: systembank
--
CREATE DATABASE systembank DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE systembank;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla banco
--

CREATE TABLE IF NOT EXISTS banco (
  idBanco int(11) NOT NULL,
  nombre varchar(50) NOT NULL,
  PRIMARY KEY (idBanco)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla banco
--

INSERT INTO banco (idBanco, nombre) VALUES
(1, 'BANAMEX'),
(2, 'BANORTE'),
(3, 'SANTANDER'),
(4, 'BANREGIO'),
(5, 'BANCOMER'),
(6, 'AZTECA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla cliente
--

CREATE TABLE IF NOT EXISTS cliente (
  idCliente int(11) NOT NULL AUTO_INCREMENT,
  nombre varchar(50) NOT NULL,
  apaterno varchar(30) NOT NULL,
  amaterno varchar(50) NOT NULL,
  edad int(11) NOT NULL,
  idBanco int(11) NOT NULL,
  PRIMARY KEY (idCliente),
  KEY RefBanco2 (idBanco)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla cuenta
--

CREATE TABLE IF NOT EXISTS cuenta (
  idCuenta int(11) NOT NULL AUTO_INCREMENT,
  numeroCuenta int(11) NOT NULL,
  idTipoCuenta int(11) NOT NULL,
  idCliente int(11) NOT NULL,
  saldo decimal(11,2) NOT NULL,
  PRIMARY KEY (idCuenta),
  KEY RefTipoCuenta3 (idTipoCuenta),
  KEY RefCliente4 (idCliente)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla movimiento
--

CREATE TABLE IF NOT EXISTS movimiento (
  idMovimiento int(11) NOT NULL AUTO_INCREMENT,
  fecha datetime NOT NULL,
  monto decimal(9,2) NOT NULL,
  idCuenta int(11) NOT NULL,
  idTipoMovimiento int(11) NOT NULL,
  PRIMARY KEY (idMovimiento),
  KEY RefCuenta7 (idCuenta),
  KEY RefTipoMovimiento9 (idTipoMovimiento)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla tipocuenta
--

CREATE TABLE IF NOT EXISTS tipocuenta (
  idTipoCuenta int(11) NOT NULL,
  nombre varchar(20) NOT NULL,
  PRIMARY KEY (idTipoCuenta)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

--
-- Volcado de datos para la tabla tipocuenta
--

INSERT INTO tipocuenta (idTipoCuenta, nombre) VALUES
(1, 'Debito'),
(2, 'Credito'),
(3, 'Fondos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla tipomovimiento
--

CREATE TABLE IF NOT EXISTS tipomovimiento (
  idTipoMovimiento int(11) NOT NULL,
  nombre varchar(30) NOT NULL,
  PRIMARY KEY (idTipoMovimiento)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla tipomovimiento
--

INSERT INTO tipomovimiento (idTipoMovimiento, nombre) VALUES
(1, 'Cargo'),
(2, 'Abono');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla usuario
--

CREATE TABLE IF NOT EXISTS usuario (
  idUsuario int(11) NOT NULL AUTO_INCREMENT,
  nombre varchar(30) NOT NULL,
  apaterno varchar(30) DEFAULT NULL,
  amaterno varchar(30) DEFAULT NULL,
  usuario varchar(10) NOT NULL,
  password varchar(10) NOT NULL,
  PRIMARY KEY (idUsuario)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla cliente
--
ALTER TABLE cliente
  ADD CONSTRAINT RefBanco2 FOREIGN KEY (idBanco) REFERENCES banco (idBanco);

--
-- Filtros para la tabla cuenta
--
ALTER TABLE cuenta
  ADD CONSTRAINT RefCliente4 FOREIGN KEY (idCliente) REFERENCES cliente (idCliente),
  ADD CONSTRAINT RefTipoCuenta3 FOREIGN KEY (idTipoCuenta) REFERENCES tipocuenta (idTipoCuenta);

--
-- Filtros para la tabla movimiento
--
ALTER TABLE movimiento
  ADD CONSTRAINT RefCuenta7 FOREIGN KEY (idCuenta) REFERENCES cuenta (idCuenta),
  ADD CONSTRAINT RefTipoMovimiento9 FOREIGN KEY (idTipoMovimiento) REFERENCES tipomovimiento (idTipoMovimiento);
