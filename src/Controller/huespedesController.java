package Controller;

import DAO.HuespedesDAO;
import factory.ConnectionDB;
import modelo.Huespedes;
import modelo.Reserva;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class huespedesController {

    private HuespedesDAO huespedesDAO;

    public huespedesController() {
        Connection con = new ConnectionDB().connectAndExecuteSQL();
        this.huespedesDAO = new HuespedesDAO(con);
    }

    public int eliminarHuesped(Integer id) {
        return huespedesDAO.eliminarHuesped(id);
    }

    public void guardar(Huespedes huesped) {
        validarHuespedes(huesped);
        huespedesDAO.guardar(huesped);
    }

    private void validarHuespedes(Huespedes huespedes) {
        // Validar el nombre
        if (huespedes.getNombre() == null || huespedes.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre del huesped debe ser especificado.");
        }

        // Validar el apellido
        if (huespedes.getApellido() == null || huespedes.getApellido().isEmpty()) {
            throw new RuntimeException("El apellido del huesped debe ser especificado.");
        }

        // Validar la fecha de nacimiento
        if (huespedes.getFecha_nacimiento() == null) {
            throw new RuntimeException("La fecha de nacimiento no puede estar vacía.");
        }

        // Validar el nacionalidad
        if (huespedes.getNacionalidad() == null || huespedes.getNacionalidad().isEmpty()) {
            throw new RuntimeException("La nacionalidad del huesped debe ser especificado.");
        }

        // Validar el telefono
        if (huespedes.getTelefono() == null || huespedes.getTelefono().isEmpty()) {
            throw new RuntimeException("El telefono del huesped debe ser especificado.");
        }
    }

    public List<Huespedes> findAll() throws SQLException {
        return huespedesDAO.findAll();
    }

    public List<Huespedes> findByApellido(String apellido) throws SQLException {
        return huespedesDAO.findByApellido(apellido);
    }

    public Huespedes buscarPorId(Integer id) {
        return huespedesDAO.buscarPorId(id);

    }

    public void modificarHuespedes(Huespedes huespedes) {
        validarHuespedes(huespedes);
        huespedesDAO.modificarHuesped(huespedes);
    }
}
