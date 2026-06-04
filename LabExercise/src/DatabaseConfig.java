import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {

    private static final String URL = "jdbc:sqlite:database.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite Driver missing from classpath! Check your lib folder.", e);
        }
        return DriverManager.getConnection(URL);
    }

    public static void main(String[] args) {
        System.out.println("Attempting to connect to the database...");
        try (Connection conn = getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON");
            }
            if (conn != null) {
                System.out.println("Success! Connected to SQLite database.");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
    }
}