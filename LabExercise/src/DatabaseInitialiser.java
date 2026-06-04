import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseInitialiser {
    private static final String SCHEMA_PATH = "db/schema.sql";

    public static void initialiseDatabase() {
        System.out.println("Initializing Database System...");
        File schemaFile = new File(SCHEMA_PATH);
        if (!schemaFile.exists()) {
            System.err.println("Error: 'schema.sql' file not found at project root!");
            return;
        }

        try (Connection conn = DatabaseConfig.getConnection();
            Statement stmt = conn.createStatement();
            Scanner scanner = new Scanner(schemaFile)){
            
            String createDatabase = "CREATE DATABASE IF NOT EXISTS hospital_system;";
            stmt.executeUpdate(createDatabase);
            System.out.println("Database 'hospital_system' verified/created.");
            String useDatabase = "USE hospital_system;";
            stmt.executeUpdate(useDatabase);
            
            scanner.useDelimiter(";");
            int commandCount = 0;

            while (scanner.hasNext()) {
                String rawCommand = scanner.next();
                String cleanCommand = rawCommand.trim();

                if (cleanCommand.isEmpty() || cleanCommand.startsWith("--")) {
                    continue;
                }

                try {
                    stmt.executeUpdate(cleanCommand);
                    commandCount++;
                } catch (SQLException ex) {
                    System.err.println("Failed to execute statement: " + cleanCommand);
                    ex.printStackTrace();
                    return;
                }
            }

            System.out.println("Success! Successfully processed and executed " + commandCount + " SQL instructions.");
            
            } catch (FileNotFoundException e) {
            System.err.println("Failed to locate schema file.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failure encountered during schema execution, initialization failed");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        initialiseDatabase();
    }
}
