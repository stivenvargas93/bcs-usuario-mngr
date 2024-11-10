# bcs-usuario-mngr

## Paso a paso para levantar el proyecto

### 1. Crear una base de datos PostgreSQL con Docker

1. Asegúrate de tener Docker instalado en tu máquina.
2. Crea un contenedor de PostgreSQL con las siguientes características:

```sh
docker run --name postgres-db -e POSTGRES_DB=postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
```

### 2. Configurar la base de datos
Conéctate a la base de datos PostgreSQL:

```sh
docker exec -it postgres-db psql -U postgres
```

Ejecuta el script `create-tables.sql` para crear las tablas necesarias:

```sh
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    numero_documento VARCHAR(50) NOT NULL,
    tipo_documento VARCHAR(20) NOT NULL,
    contraseña VARCHAR(255) NOT NULL
);

INSERT INTO usuario (numero_documento, tipo_documento, contraseña) 
VALUES ('123456789', 'CC', '$2a$10$Wors0WVSEcoSMK432PEsJ.dQAnls4uE/QavQLlLgtLKZC13Fi/u6C');

CREATE TABLE cuenta (
    id SERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(50) NOT NULL,
    tipo_cuenta VARCHAR(20) NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL,
    usuario_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo, usuario_id) VALUES
('1234567890', 'Ahorros', 100000000.00, 1),
('0987654321', 'Corriente', 100000000.00, 1);

CREATE TABLE condiciones_cdt (
    id SERIAL PRIMARY KEY,
    monto_min DECIMAL(15, 2) NOT NULL,
    monto_max DECIMAL(15, 2) NOT NULL,
    plazo_min INT NOT NULL,
    plazo_max INT NOT NULL,
    tasa DECIMAL(5, 2) NOT NULL
);

INSERT INTO condiciones_cdt (monto_min, monto_max, plazo_min, plazo_max, tasa) VALUES
(500000, 9999999, 60, 89, 0.70),
(500000, 9999999, 90, 119, 9.35),
(500000, 9999999, 120, 149, 9.35),
(500000, 9999999, 150, 179, 9.35),
(500000, 9999999, 180, 359, 10.00),
(500000, 9999999, 360, 539, 9.25),
(500000, 9999999, 540, 540, 9.30),
(10000000, 39999999, 60, 89, 0.70),
(10000000, 39999999, 90, 119, 9.50),
(10000000, 39999999, 120, 149, 9.50),
(10000000, 39999999, 150, 179, 9.50),
(10000000, 39999999, 180, 359, 10.10),
(10000000, 39999999, 360, 539, 9.35),
(10000000, 39999999, 540, 540, 9.40),
(40000000, 99999999, 60, 89, 0.70),
(40000000, 99999999, 90, 119, 9.50),
(40000000, 99999999, 120, 149, 9.50),
(40000000, 99999999, 150, 179, 9.50),
(40000000, 99999999, 180, 359, 10.10),
(40000000, 99999999, 360, 539, 9.35),
(40000000, 99999999, 540, 540, 9.40),
(100000000, 199999999, 60, 89, 0.70),
(100000000, 199999999, 90, 119, 9.70),
(100000000, 199999999, 120, 149, 9.70),
(100000000, 199999999, 150, 179, 9.70),
(100000000, 199999999, 180, 359, 10.50),
(100000000, 199999999, 360, 539, 9.80),
(100000000, 199999999, 540, 540, 9.85),
(200000000, 499999999, 60, 89, 0.70),
(200000000, 499999999, 90, 119, 9.70),
(200000000, 499999999, 120, 149, 9.70),
(200000000, 499999999, 150, 179, 9.70),
(200000000, 499999999, 180, 359, 10.50),
(200000000, 499999999, 360, 539, 9.80),
(200000000, 499999999, 540, 540, 9.85),
(500000000, 1000000000, 60, 89, 0.70),
(500000000, 1000000000, 90, 119, 9.75),
(500000000, 1000000000, 120, 149, 9.75),
(500000000, 1000000000, 150, 179, 9.75),
(500000000, 1000000000, 180, 359, 10.55),
(500000000, 1000000000, 360, 539, 9.90),
(500000000, 1000000000, 540, 540, 9.95);
```

### 3. Configurar el proyecto Spring Boot
Clona el repositorio del proyecto:
    
```sh
git clone https://github.com/stivenvargas93/bcs-usuario-mngr.git
cd bcs-usuario-mngr
```

Configura las propiedades de la aplicación en src/main/resources/application.properties según sea necesario.

### 4. Ejecutar el proyecto
Ejecuta el proyecto con Maven:

```sh
mvn spring-boot:run
```