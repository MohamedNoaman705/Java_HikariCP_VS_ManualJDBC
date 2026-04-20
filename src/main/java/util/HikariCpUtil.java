package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCpUtil implements ConnectionProvider {
    private static final String URL = "jdbc:postgresql://localhost:5432/test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qweasd12";

    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        dataSource = new HikariDataSource(config);
    }

    public HikariCpUtil() {
        try (Connection con = getConnection();
             java.sql.Statement stmt = con.createStatement()) {
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
        return dataSource.getConnection();
    }
}