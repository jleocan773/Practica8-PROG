DROP DATABASE IF EXISTS instituto;
CREATE DATABASE IF NOT EXISTS instituto;

create table if not exists clase_daw1(
    id int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    participacion TINYINT UNSIGNED DEFAULT 0,
    fechaParticipacion DATE
    );