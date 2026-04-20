package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil implements ConnectionProvider{
    private static final String URL = "jdbc:postgresql://localhost:5432/test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qweasd12";

    public DbUtil() {
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS employees (" +
                            "id INT PRIMARY KEY, " +
                            "name VARCHAR(100), " +
                            "email VARCHAR(100), " +
                            "department VARCHAR(100), " +
                            "salary DECIMAL(15,2))"
            );
        } catch (SQLException ex) {
            throw new RuntimeException("Failed to initialize database", ex);
        }
    }


    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
