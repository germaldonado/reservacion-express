package Controller;

import DAO.ReservaDAO;
import factory.ConnectionDB;
import modelo.Reserva;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class reservaController {

    private ReservaDAO reservaDAO;

    public reservaController() {
        Connection con = new ConnectionDB().connectAndExecuteSQL();
        this.reservaDAO = new ReservaDAO(con);
    }

    public int eliminar(Integer id) {
        return reservaDAO.eliminar(id);
    }

    public void guardar(Reserva reserva) {
        validarReserva(reserva);
        reservaDAO.guardar(reserva);
    }

    private void validarReserva(Reserva reserva) {
        // Validar la fecha de entrada
        if (reserva.getFecha_entrada().compareTo(new Date()) < 0) {
            throw new RuntimeException("La fecha de entrada debe ser posterior a la fecha actual.");
        }

        // Validar la fecha de salida
        if (reserva.getFecha_salida().compareTo(reserva.getFecha_entrada()) < 0) {
            throw new RuntimeException("La fecha de salida debe ser posterior a la fecha de entrada.");
        }

        // Validar el valor
        if (reserva.getValor() == null || reserva.getValor().isEmpty()) {
            throw new RuntimeException("El valor de la reserva debe ser especificado.");
        }

        // Validar la forma de pago
        if (reserva.getForma_de_pago() == null || reserva.getForma_de_pago().isEmpty()) {
            throw new RuntimeException("La forma de pago de la reserva debe ser especificada.");
        }
    }

    public Reserva buscarPorId(Integer id) {
        return reservaDAO.buscarPorId(id);

    }

    public List<Reserva> getAll() {
        return reservaDAO.getAll();
    }

    public void modificar(Reserva reserva) {
        validarReserva(reserva);
        reservaDAO.modificar(reserva);
    }
}

