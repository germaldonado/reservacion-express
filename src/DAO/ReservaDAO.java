package DAO;

import factory.ConnectionDB;
import modelo.Reserva;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    final private Connection con;

    public ReservaDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Reserva reserva) {

        try {

            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_de_pago) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            try(statement){
                ejecutarRegisto(reserva, statement);
            }
        }catch(SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    private void ejecutarRegisto(Reserva reserva, PreparedStatement statement) throws SQLException {
        statement.setDate(1, reserva.getFecha_entrada());
        statement.setDate(2, reserva.getFecha_salida());
        statement.setString(3, reserva.getValor());
        statement.setString(4, reserva.getForma_de_pago());

        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();

        try (resultSet){

            while(resultSet.next()) {
                reserva.setId(resultSet.getInt(1));
                System.out.println(String.format("Reserva guardada con exito! %s", reserva));
            }
        }


    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE id = ?");
            try(statement){
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No puede eliminar una reserva que tenga Huespedes");
            return 0;
        }
    }

    public void modificar(Reserva reserva) {

        try {

            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_de_pago = ? WHERE id = ?",
                    Statement.RETURN_GENERATED_KEYS);
            try(statement){
                statement.setDate(1, reserva.getFecha_entrada());
                statement.setDate(2, reserva.getFecha_salida());
                statement.setString(3, reserva.getValor());
                statement.setString(4, reserva.getForma_de_pago());
                statement.setInt(5, reserva.getId());
                statement.execute();

                int updateCount = statement.getUpdateCount();

                if (updateCount == 0) {
                    throw new SQLException("No se pudo actualizar la reserva");
                }

                System.out.println(String.format("Reserva modificada con exito! %s", reserva));
            }
        }catch(SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<Reserva> getAll() {

        try {

            final PreparedStatement statement = con.prepareStatement("SELECT * FROM reservas");
            final ResultSet resultSet = statement.executeQuery();

            final List<Reserva> reservas = new ArrayList<>();

            while (resultSet.next()) {
                final Reserva reserva = new Reserva();
                reserva.setId(resultSet.getInt("id"));
                reserva.setFecha_entrada(resultSet.getDate("fecha_entrada"));
                reserva.setFecha_salida(resultSet.getDate("fecha_salida"));
                reserva.setValor(resultSet.getString("valor"));
                reserva.setForma_de_pago(resultSet.getString("forma_de_pago"));

                reservas.add(reserva);
            }

            return reservas;
        }catch(SQLException ex) {
            throw new RuntimeException(ex);
        }

    }



    public Reserva buscarPorId(Integer id) {

        try {

            final PreparedStatement statement = con.prepareStatement("SELECT * FROM reservas WHERE id = ?");
            statement.setInt(1, id);

            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                final Reserva reserva = new Reserva();
                reserva.setId(resultSet.getInt("id"));
                reserva.setFecha_entrada(resultSet.getDate("fecha_entrada"));
                reserva.setFecha_salida(resultSet.getDate("fecha_salida"));
                reserva.setValor(resultSet.getString("valor"));
                reserva.setForma_de_pago(resultSet.getString("forma_de_pago"));

                return reserva;
            }

            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
