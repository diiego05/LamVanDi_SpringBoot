# AloTra Website — Đồ án Lập trình Web

Một ứng dụng web được xây dựng cho môn Đồ án Lập trình Web, tập trung vào trải nghiệm người dùng thân thiện, dễ sử dụng và dễ mở rộng.

![Status](https://img.shields.io/badge/status-active-success)
![Made with Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=javascript&logoColor=black)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white)

---

## Tổng quan

AloTra Website là một dự án web với nền tảng backend viết bằng Java kết hợp giao diện web HTML/CSS/JavaScript, hướng đến việc xây dựng một ứng dụng sạch, rõ ràng, dễ bảo trì và dễ phát triển thêm các tính năng mới.

- Mã nguồn: [diiego05/DoAnLapTrinhWeb_AloTra](https://github.com/diiego05/DoAnLapTrinhWeb_AloTra)
- Nhánh mặc định: `main`

---

## Thành phần công nghệ

Tỷ lệ ngôn ngữ (ước tính theo số dòng mã):

- Java: ~46.8%
- HTML: ~27.9%
- JavaScript: ~22.1%
- CSS: ~3.2%
- Dockerfile: ~0.0%

Thư mục chính:
- `AloTraWebsite/` — thư mục dự án ứng dụng web
- `README.md` — tài liệu này

---

## Tính năng (hiện có/định hướng)

- [ ] Trang giao diện thân thiện, bố cục rõ ràng
- [ ] Quản lý người dùng (đăng ký/đăng nhập/đăng xuất)
- [ ] Quản lý nội dung/dữ liệu (CRUD)
- [ ] Tìm kiếm/lọc dữ liệu
- [ ] Phân quyền cơ bản (người dùng/quản trị)
- [ ] Triển khai với Docker để chạy nhanh

Lưu ý: Hạng mục sẽ được cập nhật dần theo tiến độ dự án.

---

## Yêu cầu hệ thống

- Java Development Kit (JDK) 17 hoặc mới hơn
- Git
- Tùy chọn:
  - Docker 24+ (nếu chạy bằng Docker)
  - IDE (IntelliJ IDEA/Eclipse/VS Code) để phát triển

---

## Hướng dẫn chạy nhanh

Bạn có thể chạy theo 1 trong 2 cách sau.

### Cách A — Chạy bằng Docker (khuyến nghị nếu đã có Dockerfile)

1) Build image:
```bash
docker build -t alotra .
```

2) Chạy container:
```bash
docker run -d -p 8080:8080 --name alotra-app \
  -e DB_URL="jdbc:postgresql://localhost:5432/alotra" \
  -e DB_USERNAME="username" \
  -e DB_PASSWORD="password" \
  alotra
```

3) Mở trình duyệt:
```
http://localhost:8080
```

### Cách B — Chạy cục bộ bằng IDE

Vì dự án Java có thể dùng nhiều cách đóng gói/chạy khác nhau (Tomcat/Servlet, Spring Boot, v.v.), bạn có thể:

- Mở dự án trong IDE (IntelliJ/Eclipse)
- Cấu hình SDK Java (JDK 17+)
- Nếu là Spring Boot: chạy class `main` (Application)
- Nếu là ứng dụng Servlet/Tomcat:
  - Cấu hình Application Server (Tomcat)
  - Deploy module web lên server và run

Nếu bạn cần mình tùy biến hướng dẫn theo đúng stack (Spring Boot/Maven/Gradle/Tomcat), hãy cho mình biết file build bạn đang dùng.

---

## Cấu hình môi trường

Thiết lập biến môi trường khi chạy (ví dụ):

- `DB_URL` — JDBC URL, ví dụ: `jdbc:postgresql://localhost:5432/alotra`
- `DB_USERNAME` — tài khoản DB
- `DB_PASSWORD` — mật khẩu DB
- `PORT` — cổng ứng dụng (nếu hỗ trợ thay đổi)

Ví dụ file `.env` (không commit lên Git):
```
DB_URL=jdbc:postgresql://localhost:5432/alotra
DB_USERNAME=your_user
DB_PASSWORD=your_password
PORT=8080
```

---

## Cấu trúc thư mục (rút gọn)

```text
/
├─ AloTraWebsite/
└─ README.md
```

Gợi ý:
- Thêm tài liệu kỹ thuật vào `docs/`
- Lưu ảnh demo vào `docs/screenshots/`

---

## Bộ quy tắc chất lượng

- Đặt tên biến/hàm/class rõ nghĩa
- Format code nhất quán (EditorConfig/IDE Code Style)
- Commit theo Conventional Commits (ví dụ: `feat:`, `fix:`, `chore:`, `docs:`)

---

## Đóng góp

Rất hoan nghênh đóng góp:
1) Fork repository
2) Tạo nhánh mới: `git checkout -b feature/ten-tinh-nang`
3) Commit thay đổi: `git commit -m "feat: mo-ta-ngan"`
4) Push lên nhánh của bạn: `git push origin feature/ten-tinh-nang`
5) Mở Pull Request

---

## Giấy phép

Chưa khai báo giấy phép. Nếu cần, bạn có thể thêm file `LICENSE` (ví dụ MIT) cho dự án.

---

## Tác giả & Liên hệ

- Tác giả: [@diiego05](https://github.com/diiego05)
- Vấn đề/trao đổi: tạo Issue trong repo hoặc liên hệ qua GitHub

Cảm ơn bạn đã quan tâm đến dự án! 💚
