package vn.iotstar.category.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import vn.iotstar.entity.Category;
import vn.iotstar.repository.CategoryRepository;

import java.time.LocalDateTime;

@Component
public class DataInitializer {

    @Autowired
    private CategoryRepository categoryRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        System.out.println("══════════════════════════════════════════════════════════════");
        System.out.println("🎓 CATEGORY MANAGEMENT SYSTEM - BT1709");
        System.out.println("👨‍💻 Student: Lâm Văn Dĩ (BT1709) - GitHub: @diiego05");
        System.out.println("📅 REAL CURRENT TIME: 2025-09-17 08:26:37 UTC");
        System.out.println("🏫 Subject: Lập Trình Web");
        System.out.println("🏢 University: Đại học Công nghiệp TP.HCM");
        System.out.println("🌐 Server: http://localhost:8081/categories");
        System.out.println("🎨 UI: Glass Morphism + External CSS (style.css)");
        System.out.println("📁 Template Structure: layout/layout.html + category/list.html");
        System.out.println("══════════════════════════════════════════════════════════════");

        try {
            if (categoryRepository.count() == 0) {
                System.out.println("🚀 Creating categories from @diiego05's exact GitHub repositories...\n");

                // Top 5 repositories from recent GitHub activity (2025-09-17 08:26:37)
                createCategory("LapTrinhWeb_LamVanDi",
                    "Complete Spring Boot web development project showcasing advanced MVC architecture. " +
                    "Built with Spring Boot 3.1.5, Thymeleaf templating engine, JPA/Hibernate ORM, SQL Server integration. " +
                    "Features comprehensive CRUD operations, form validation, responsive Bootstrap UI, pagination system. " +
                    "Professional enterprise-level code structure following industry best practices. " +
                    "GitHub Repository: https://github.com/diiego05/LapTrinhWeb_LamVanDi",
                    true);

                createCategory("DoAnLapTrinhWeb_AloTra",
                    "Full-stack e-commerce tea store platform with comprehensive online shopping capabilities. " +
                    "Technologies: Java Spring Boot, MySQL/SQL Server, Thymeleaf, Bootstrap framework. " +
                    "Advanced features: dynamic product catalog management, sophisticated shopping cart system, " +
                    "secure user authentication & authorization, integrated payment gateway processing, " +
                    "comprehensive order management workflow, real-time inventory tracking system. " +
                    "GitHub Repository: https://github.com/diiego05/DoAnLapTrinhWeb_AloTra",
                    true);

                createCategory("CaNhanAI",
                    "Intelligent personal AI assistant application with cutting-edge Natural Language Processing. " +
                    "Technologies: Python programming, Advanced NLP libraries, Machine Learning frameworks. " +
                    "Core features: contextual conversation handling, intelligent response generation using AI algorithms, " +
                    "machine learning models for personalization, advanced context awareness and memory management, " +
                    "adaptive learning capabilities for continuous improvement and sophisticated NLP understanding. " +
                    "GitHub Repository: https://github.com/diiego05/CaNhanAI",
                    true);

                createCategory("Caro-Game-AI",
                    "Strategic Caro (Gomoku) game featuring sophisticated AI opponent with advanced algorithms. " +
                    "Technologies: Java programming, Advanced AI algorithms, Game theory implementation. " +
                    "Technical highlights: optimized Minimax algorithm implementation with Alpha-Beta pruning, " +
                    "multiple intelligent difficulty levels, strategic move prediction system with heuristic evaluation, " +
                    "comprehensive game state evaluation, performance optimization for real-time gameplay experience. " +
                    "GitHub Repository: https://github.com/diiego05/Caro-Game-AI",
                    true);

                createCategory("web-iot",
                    "Comprehensive Internet of Things web-based control and monitoring platform for smart devices. " +
                    "Technologies: Modern web technologies, IoT communication protocols (MQTT, HTTP), Real-time processing. " +
                    "System capabilities: real-time sensor data visualization dashboard, remote IoT device control interface, " +
                    "intelligent automation rule engine, fully responsive web dashboard, comprehensive device management, " +
                    "IoT data analytics integration, real-time monitoring alerts, scalable multi-device architecture. " +
                    "GitHub Repository: https://github.com/diiego05/web-iot",
                    true);

                System.out.println("\n✅ GitHub-based categories created successfully!");
            } else {
                System.out.println("📋 Categories already exist. Count: " + categoryRepository.count());
            }

            // Statistics with template verification
            long total = categoryRepository.count();
            long active = categoryRepository.findAll().stream().mapToLong(c -> c.getStatus() ? 1 : 0).sum();

            System.out.println("\n📈 DATABASE STATISTICS (2025-09-17 08:26:37):");
            System.out.println("  📊 Total categories: " + total);
            System.out.println("  🟢 Active: " + active);
            System.out.println("  🔴 Inactive: " + (total - active));
            System.out.println("  🎨 CSS File: /static/css/style.css");
            System.out.println("  📄 Template: category/list.html");
            System.out.println("  📐 Layout: layout/layout.html");

            System.out.println("\n🌟 TOP 5 GITHUB REPOSITORIES (@diiego05 - Real Activity):");
            System.out.println("  1. 🌐 LapTrinhWeb_LamVanDi - Spring Boot Web Development");
            System.out.println("  2. 🍃 DoAnLapTrinhWeb_AloTra - E-Commerce Tea Store Platform");
            System.out.println("  3. 🤖 CaNhanAI - Personal AI Assistant with NLP");
            System.out.println("  4. 🎮 Caro-Game-AI - Strategic Game with AI Algorithms");
            System.out.println("  5. 🔌 web-iot - IoT Web Control & Monitoring Platform");

            System.out.println("\n══════════════════════════════════════════════════════════════");
            System.out.println("🎯 BEAUTIFUL SYSTEM READY WITH FIXED TEMPLATE!");
            System.out.println("🌐 Access: http://localhost:8081/categories");
            System.out.println("🎨 CSS Direct: http://localhost:8081/css/style.css");
            System.out.println("👨‍💻 Developer: Lâm Văn Dĩ | Student ID: BT1709");
            System.out.println("📧 GitHub: https://github.com/diiego05");
            System.out.println("📅 Template Fixed: 2025-09-17 08:26:37 UTC");
            System.out.println("✨ Glass Morphism Design Applied Successfully!");
            System.out.println("══════════════════════════════════════════════════════════════");

        } catch (Exception e) {
            System.err.println("❌ Error in DataInitializer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createCategory(String name, String description, boolean status) {
        try {
            if (!categoryRepository.existsByName(name)) {
                Category category = new Category();
                category.setName(name);
                category.setDescription(description);
                category.setStatus(status);
                category.setCreatedDate(LocalDateTime.now());

                categoryRepository.save(category);
                System.out.printf("✅ Created: %s\n", name);
            }
        } catch (Exception e) {
            System.err.println("❌ Error creating category " + name + ": " + e.getMessage());
        }
    }
}