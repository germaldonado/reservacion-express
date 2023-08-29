CREATE TABLE IF NOT EXISTS huespedes(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    nacionalidad VARCHAR(50) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    id_reserva INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_reserva) REFERENCES reservas(id)
)