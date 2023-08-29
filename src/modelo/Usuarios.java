package modelo;

import factory.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuarios {


    private String nombre;
    private String password;

    public Usuarios(String nombre, String contraseña) {
        this.nombre = nombre;
        this.password = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean validarUsuario(String nombre, String password){
        ConnectionDB connectionDB = new ConnectionDB();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = connectionDB.connectAndExecuteSQL();
            stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND password = ?");
            stmt.setString(1, nombre);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if(rs != null)
                    rs.close();
                if(stmt != null)
                    stmt.close();
                if(conn != null)
                    conn.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }
}
