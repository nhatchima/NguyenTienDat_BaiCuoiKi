# NguyenTienDat_BaiCuoiKi
- Tiêu đề :Bài Cuối Kì môn Lập trình Android
- Họ tên: Nguyễn Tiến Đạt 
- Mã SV: B17DCAT035 
- Nhóm: 06
-> Hướng dẫn sử dụng
- Và để chạy được code hoàn chỉnh thì cần phải thêm import csdl sql vào Databse MySQL để ứng dụng có thể lấy dữ liệu từ server về
- + Cho các file php để lấy dữ liệu từ MySQL vào trong thư mục server( tên thư mục có thể đăt tuỳ ý): Vào đường dẫn sau xampp/htdocs/ -> copy thư mục server để vào trong htdocs 
- Nếu gặp lỗi khi chạy ứng dụng mà không hiển thị được sản phẩm. Tức là kết nối tới CSDL của bạn có vấn để.
-	GIẢI QUYẾT LỖI:
-	Chúng ta cần kết nối tới CSDL localhost bằng cách sử dụng xampp. Tải xampp về và thiết lập port để tránh bị xung cổng với các phần mềm khác
 
-	Tiến hành khởi chạy server Apache và MySQL
-	Import CSDL vào database MySQL
-	Vậy nên em có đính kèm theo source code Android các file sau: 
+  Import file sql vào CSDL trên xampp: tiến hành mở localhost chọn New-> chọn Import -> Browse đến file CSDL là store.sql 

 
+  Vào package util tìm tới file Server.java -> Để thay đổi đường dẫn tới localhost và IP lưu ý:
IP thay bằng IP của máy localhost (cmd -> ipconfig)
Đường dẫn localhost ở đây là cổng 8888 -> nên phải đổi cổng phù hợp với cổng đc thiết lập 
 
