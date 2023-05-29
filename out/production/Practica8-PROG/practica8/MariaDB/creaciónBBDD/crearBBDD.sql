DROP DATABASE IF EXISTS estudiantes;
CREATE DATABASE IF NOT EXISTS estudiantes;

create table if not exists clase_daw1(
    id int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    participacion TINYINT UNSIGNED DEFAULT 0,
    fechaParticipacion DATE
    );