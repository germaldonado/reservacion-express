CREATE TABLE IF NOT EXISTS reservas(
    id INT NOT NULL AUTO_INCREMENT,
    fecha_entrada DATE NOT NULL,
    fecha_salida DATE NOT NULL,
    valor VARCHAR(50),
    forma_de_pago VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
)