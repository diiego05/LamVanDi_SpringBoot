package vn.iotstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"vn.iotstar.entity"})
@EnableJpaRepositories(basePackages = {"vn.iotstar.repository"})
public class Bt1709Application {

    public static void main(String[] args) {
        System.out.println("🚀 Starting BT1709 - Category Management System...");
        System.out.println("👨‍💻 Developer: Lâm Văn Dĩ (BT1709)");
        System.out.println("📅 REAL Current Time: 2025-09-17 08:26:37 UTC");
        System.out.println("🔗 GitHub: @diiego05");
        System.out.println("🎯 Template Fix: Glass Morphism Design");
        System.out.println("📁 Structure: layout/layout.html + category/list.html");
        System.out.println("🎨 CSS: /static/css/style.css");
        System.out.println("================================================");

        try {
            SpringApplication.run(Bt1709Application.class, args);

            System.out.println("\n✅ APPLICATION STARTED SUCCESSFULLY!");
            System.out.println("🌐 Access URL: http://localhost:8081/categories");
            System.out.println("🎨 CSS URL: http://localhost:8081/css/style.css");
            System.out.println("👨‍💻 Developer: Lâm Văn Dĩ (BT1709) - @diiego05");
            System.out.println("📅 Started at: 2025-09-17 08:26:37 UTC");

        } catch (Exception e) {
            System.err.println("❌ Application startup failed!");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();

            System.err.println("\n🔧 TROUBLESHOOTING CHECKLIST:");
            System.err.println("1. Check SQL Server is running on localhost:1433");
            System.err.println("2. Verify database 'CategoryDB' exists");
            System.err.println("3. Confirm user 'categoryuser' with password 'CategoryPass123!'");
            System.err.println("4. Ensure templates directory: /templates/category/list.html");
            System.err.println("5. Verify CSS file: /static/css/style.css");
            System.err.println("6. Check layout file: /templates/layout/layout.html");
        }
    }
}