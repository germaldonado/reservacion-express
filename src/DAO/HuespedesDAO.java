package DAO;

import modelo.Huespedes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HuespedesDAO {

    private Connection con;

    public HuespedesDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Huespedes huesped) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            try (statement) {
                ejecutarRegistro(huesped, statement);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void ejecutarRegistro(Huespedes huesped, PreparedStatement statement) throws SQLException {

        statement.setString(1, huesped.getNombre());
        statement.setString(2, huesped.getApellido());
        statement.setDate(3, huesped.getFecha_nacimiento());
        statement.setString(4, huesped.getNacionalidad());
        statement.setString(5, huesped.getTelefono());
        statement.setInt(6, huesped.getId_reserva());

        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();

        try (resultSet) {
            while (resultSet.next()) {
                huesped.setId(resultSet.getInt(1));
                ;
                System.out.println(String.format("Huesped guardado correctamente!! %s", huesped));
            }
        }

    }

    public int eliminarHuesped(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM huespedes WHERE id = ?");
            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huespedes> findAll() throws SQLException {
        List<Huespedes> huespedes = new ArrayList<>();

        String sql = "SELECT * FROM huespedes";
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Huespedes huesped = new Huespedes();
            huesped.setId(resultSet.getInt("id"));
            huesped.setNombre(resultSet.getString("nombre"));
            huesped.setApellido(resultSet.getString("apellido"));
            huesped.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
            huesped.setNacionalidad(resultSet.getString("nacionalidad"));
            huesped.setTelefono(resultSet.getString("telefono"));
            huesped.setId_reserva(resultSet.getInt("id_reserva"));

            huespedes.add(huesped);
        }

        return huespedes;
    }

    public List<Huespedes> findByApellido(String apellido) throws SQLException {
        String sql = "SELECT * FROM huespedes WHERE apellido LIKE BINARY ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, apellido);
        ResultSet resultSet = statement.executeQuery();

        List<Huespedes> huespedes = new ArrayList<>();
        while (resultSet.next()) {
            Huespedes huesped = new Huespedes();
            huesped.setId(resultSet.getInt("id"));
            huesped.setNombre(resultSet.getString("nombre"));
            huesped.setApellido(resultSet.getString("apellido"));
            huesped.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
            huesped.setNacionalidad(resultSet.getString("nacionalidad"));
            huesped.setTelefono(resultSet.getString("telefono"));
            huesped.setId_reserva(resultSet.getInt("id_reserva"));

            huespedes.add(huesped);
        }

        return huespedes;
    }

    public Huespedes buscarPorId(Integer id) {

        try {

            final PreparedStatement statement = con.prepareStatement("SELECT * FROM huespedes WHERE id = ?");
            statement.setInt(1, id);

            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                final Huespedes huespedes = new Huespedes();
                huespedes.setId(resultSet.getInt("id"));
                huespedes.setNombre(resultSet.getString("nombre"));
                huespedes.setApellido(resultSet.getString("apellido"));
                huespedes.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
                huespedes.setNacionalidad(resultSet.getString("nacionalidad"));
                huespedes.setTelefono(resultSet.getString("telefono"));
                huespedes.setId_reserva(resultSet.getInt("id_reserva"));

                return huespedes;
            }

            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void modificarHuesped(Huespedes huespedes) {

        try {

            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE huespedes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ?, id_reserva = ? WHERE id = ?",
                    Statement.RETURN_GENERATED_KEYS);
            try(statement){
                statement.setString(1, huespedes.getNombre());
                statement.setString(2, huespedes.getApellido());
                statement.setDate(3, huespedes.getFecha_nacimiento());
                statement.setString(4, huespedes.getNacionalidad());
                statement.setString(5, huespedes.getTelefono());
                statement.setInt(6, huespedes.getId_reserva());
                statement.setInt(7, huespedes.getId());
                statement.execute();

                int updateCount = statement.getUpdateCount();

                if (updateCount == 0) {
                    throw new SQLException("No se pudo actualizar el huesped");
                }

                System.out.println(String.format("Huesped modificado con exito! %s", huespedes));
            }
        }catch(SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
