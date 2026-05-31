import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    
    private static final String CONFIG_PATH = "nbproject/private/config.properties";

    static {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_PATH)) {
            prop.load(input);
            System.setProperty("DB_URL", prop.getProperty("db.url", ""));
            System.setProperty("DB_USER", prop.getProperty("db.user", ""));
            System.setProperty("DB_PASSWORD", prop.getProperty("db.password", ""));
        } catch (IOException e) {
            System.err.println("Warning: Could not load " + CONFIG_PATH + ". Falling back to command line flags.");
        }
    }

    private static final String URL = System.getProperty("DB_URL"); 
    private static final String USER = System.getProperty("DB_USER");
    private static final String PASSWORD = System.getProperty("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver missing from classpath! Check your lib folder.", e);
        }

        // Safety check to ensure variables are present
        if (URL == null || URL.isEmpty() || USER == null || USER.isEmpty() || PASSWORD == null) {
            throw new SQLException("Database properties (DB_URL, DB_USER, DB_PASSWORD) are not set!");
        }
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        System.out.println("Attempting to connect to the database...");
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Success! Connected to the MySQL database perfectly.");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
    }
}