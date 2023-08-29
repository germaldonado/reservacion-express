package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class testConnectionDB {

    public static void main(String[] args) throws SQLException {
        ConnectionDB connectionDB = new ConnectionDB();
        Connection conn = connectionDB.connectAndExecuteSQL();

        System.out.println("Conectado");
        conn.close();

        System.out.println("Cerrado");
    }
}
