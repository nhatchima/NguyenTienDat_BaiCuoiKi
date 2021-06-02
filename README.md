# NguyenTienDat_BaiCuoiKi
Bài Cuối Kì Nguyễn Tiến Đạt Mã SV B17DCAT035 Nhóm 6
	
Nếu gặp lỗi khi chạy ứng dụng mà không hiển thị được sản phẩm. Tức là kết nối tới CSDL của bạn có vấn để.
	GIẢI QUYẾT LỖI:
-	Chúng ta cần kết nối tới CSDL localhost bằng cách sử dụng xampp. Tải xampp về và thiết lập port để tránh bị xung cổng với các phần mềm khác
 
-	Tiến hành khởi chạy server Apache và MySQL
-	Import CSDL vào database MySQL
	Vậy nên em có đính kèm theo source code Android các file sau: 
+  Import file sql vào CSDL trên xampp: tiến hành mở localhost chọn New-> chọn Import -> Browse đến file CSDL là store.sql 
+ Các file php để lấy dữ liệu từ MySQL được để trong thư mục server: Vào đường dẫn sau xampp/htdocs/ -> copy thư mục server để vào trong htdocs 
 
+  Vào package util tìm tới file Server.java -> Để thay đổi đường dẫn tới localhost và IP lưu ý:
IP thay bằng IP của máy localhost (cmd -> ipconfig)
Đường dẫn localhost ở đây là cổng 8888 -> nên phải đổi cổng phù hợp với cổng đc thiết lập 
 
