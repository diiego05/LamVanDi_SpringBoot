-- Data initialization for SQL Server
-- Insert sample categories if they don't exist

-- Check and insert categories
IF NOT EXISTS (SELECT 1 FROM categories WHERE name = N'Công nghệ thông tin')
    INSERT INTO categories (name, description, created_date, status)
    VALUES (N'Công nghệ thông tin', N'Các sản phẩm và dịch vụ liên quan đến công nghệ thông tin, phần mềm, phần cứng máy tính', GETDATE(), 1);

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = N'Giáo dục và Đào tạo')
    INSERT INTO categories (name, description, created_date, status)
    VALUES (N'Giáo dục và Đào tạo', N'Tài liệu học tập, khóa học online, sách giáo khoa và các dịch vụ giáo dục', GETDATE(), 1);

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = N'Thể thao và Giải trí')
    INSERT INTO categories (name, description, created_date, status)
    VALUES (N'Thể thao và Giải trí', N'Dụng cụ thể thao, đồ tập gym, các hoạt động giải trí và thể thao', GETDATE(), 1);

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = N'Ẩm thực và Đồ uống')
    INSERT INTO categories (name, description, created_date, status)
    VALUES (N'Ẩm thực và Đồ uống', N'Món ăn đặc sản, đồ uống các loại, nguyên liệu nấu ăn và dụng cụ bếp', GETDATE(), 1);

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = N'Lập trình Web')
    INSERT INTO categories (name, description, created_date, status)
    VALUES (N'Lập trình Web', N'Khóa học lập trình web, framework, công cụ phát triển website và ứng dụng', GETDATE(), 1);

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = N'Trí tuệ nhân tạo')
    INSERT INTO categories (name, description, created_date, status)
    VALUES (N'Trí tuệ nhân tạo', N'Khóa học AI, machine learning, chatbot và các ứng dụng trí tuệ nhân tạo', GETDATE(), 1);

-- Log completion
PRINT 'Sample data initialization completed!';