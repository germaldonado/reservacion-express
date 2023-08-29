package factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import io.github.cdimascio.dotenv.Dotenv;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

    public DataSource dataSource;

    public ConnectionDB() {
        Dotenv dotenv = Dotenv.load();
        String URL = dotenv.get("JDBC_URL");
        String ROOT = dotenv.get("ROOT");
        String PASSWORD = dotenv.get("PASSWORD");

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(URL);
        comboPooledDataSource.setUser(ROOT);
        comboPooledDataSource.setPassword(PASSWORD);
        this.dataSource = comboPooledDataSource;
    }

    public Connection connectAndExecuteSQL() {
        try {
            Connection conn = dataSource.getConnection();

            File folder = new File("db");
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    String sql = readFile(file.getPath());
                    Statement stmt = conn.createStatement();
                    stmt.execute(sql);
                }
            }

            return conn;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}