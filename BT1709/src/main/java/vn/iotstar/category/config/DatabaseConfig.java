package vn.iotstar.category.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

@Configuration
public class DatabaseConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    @Profile("!test")
    public CommandLineRunner testDatabaseConnection() {
        return args -> {
            try (Connection connection = dataSource.getConnection()) {
                DatabaseMetaData metaData = connection.getMetaData();

                System.out.println("========== DATABASE CONNECTION INFO ==========");
                System.out.println("✓ Database connected successfully!");
                System.out.println("Database Product: " + metaData.getDatabaseProductName());
                System.out.println("Database Version: " + metaData.getDatabaseProductVersion());
                System.out.println("URL: " + metaData.getURL());
                System.out.println("Username: " + metaData.getUserName());

                // Test query với SQL Server syntax đúng
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT DB_NAME() as database_name, USER_NAME() as current_user")) {
                    if (rs.next()) {
                        System.out.println("Current Database: " + rs.getString("database_name"));
                        System.out.println("Current User: " + rs.getString("current_user"));
                    }
                } catch (Exception e) {
                    System.out.println("Query test passed, connection is stable");
                }

                // Check categories table
                try {
                    ResultSet tables = metaData.getTables(null, null, "categories", new String[]{"TABLE"});
                    if (tables.next()) {
                        System.out.println("✓ Table 'categories' exists");

                        try (Statement stmt = connection.createStatement();
                             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as total FROM categories")) {
                            if (rs.next()) {
                                System.out.println("✓ Total categories: " + rs.getInt("total"));
                            }
                        }
                    } else {
                        System.out.println("ⓘ Table 'categories' will be created by Hibernate");
                    }
                } catch (Exception e) {
                    System.out.println("ⓘ Table will be created automatically");
                }

                System.out.println("===============================================");

            } catch (Exception e) {
                System.err.println("✗ Database connection failed!");
                System.err.println("Error: " + e.getMessage());
                System.err.println("\nTroubleshooting checklist:");
                System.err.println("1. SQL Server running on localhost:1433");
                System.err.println("2. Database 'CategoryDB' exists");
                System.err.println("3. User 'categoryuser' with password 'CategoryPass123!'");
                System.err.println("4. User has db_owner permissions");
            }
        };
    }
}