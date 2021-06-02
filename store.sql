-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2021 at 02:48 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `store`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(10000) NOT NULL,
  `giasanpham` int(11) NOT NULL,
  `soluongsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `madonhang`, `masanpham`, `tensanpham`, `giasanpham`, `soluongsanpham`) VALUES
(1, 19, 4, 'Sữa rửa mặt SenKa', 200000, 1),
(2, 19, 16, 'Sữa tắm Lamont cho phụ nữ', 660000, 2),
(3, 20, 4, 'Sữa rửa mặt SenKa', 800000, 4),
(4, 20, 3, 'Sữa tắm Hazeline MatCha Lựu đỏ', 600000, 3),
(5, 20, 10, 'Sữa tắm Bull Dog', 412111, 1),
(6, 21, 2, 'Sữa tắm cho nữ Love Beauty', 300000, 1),
(7, 22, 6, 'Sữa tắm Hatomugi đến từ Nhật Bản', 1200000, 3),
(8, 22, 17, 'Sữa rửa mặt Hasi đến từ Nhật Bản', 750000, 3),
(9, 23, 2, 'Sữa tắm cho nữ Love Beauty', 300000, 1),
(10, 23, 5, 'Sữa rửa mặt Medimix', 2500000, 5),
(11, 24, 3, 'Sữa tắm Hazeline MatCha Lựu đỏ', 800000, 4),
(12, 25, 1, 'Sữa rửa mặt BlackForce', 450000, 3),
(13, 25, 6, 'Sữa tắm Hatomugi đến từ Nhật Bản', 400000, 1),
(14, 26, 4, 'Sữa rửa mặt SenKa', 200000, 1),
(15, 27, 1, 'Sữa rửa mặt BlackForce', 150000, 1),
(16, 28, 3, 'Sữa tắm Hazeline MatCha Lựu đỏ', 200000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(255) NOT NULL,
  `sodienthoai` int(25) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `donhang`
--

INSERT INTO `donhang` (`id`, `tenkhachhang`, `sodienthoai`, `email`) VALUES
(19, 'dat', 123456789, 'dat@gmail.com'),
(20, 'tientrieu', 123456890, 'tientrieu@gmail.com'),
(21, 'minhduc', 1234356, 'duc@gmail.com'),
(22, 'superman', 397625171, 'sieuthamtu1@gmail.com'),
(23, 'dattien', 12345677, 'daika@gmail.com'),
(24, 'sieunhan', 3126556, 'dat@gmail.com'),
(25, 'minsut', 123456789, 'sut@gmail.com'),
(26, 'covananh', 397611111, 'va@gmail.com'),
(27, 'dat', 2147483647, '2222222'),
(28, 'ngoc trinh', 12345689, 'ngoctrinh@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(10) NOT NULL,
  `tensanpham` varchar(255) NOT NULL,
  `hinhanhsanpham` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tensanpham`, `hinhanhsanpham`) VALUES
(1, 'Sữa rửa mặt ', 'https://i0.wp.com/s1.uphinh.org/2021/05/19/cetaphil.jpg'),
(2, 'Sữa tắm', 'https://i0.wp.com/s1.uphinh.org/2021/05/19/hazeline.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(1000) NOT NULL,
  `giasanpham` int(15) NOT NULL,
  `hinhanhsanpham` varchar(1000) NOT NULL,
  `motasanpham` varchar(10000) NOT NULL,
  `idsanpham` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `motasanpham`, `idsanpham`) VALUES
(1, 'Sữa rửa mặt BlackForce', 150000, 'https://s1.uphinh.org/2021/05/19/blackforce.md.jpg', 'Sữa rửa mặt dành cho nam giới. \r\n\r\nCách dùng: Rửa khô\r\n\r\nRửa khô với sữa rửa mặt vô cùng đơn giản. Lúc này, bạn chỉ cần cho lượng sản phẩm vừa đủ lên mặt, đồng thời cũng massage nhẹ nhàng trong khoảng 2 – 3 phút. Sau đó, bạn dùng khăn ẩm để lau lại. ', 1),
(2, 'Sữa tắm cho nữ Love Beauty', 300000, 'https://s1.uphinh.org/2021/05/19/lovebeauty.md.jpg', 'Sữa tắm làm trắng da trị mụn cho nữ giới. Hương thơm dâu tay nhẹ nhàng\r\n\r\nLàm trắng da hiệu quả\r\n\r\nVới các loại sữa tắm trắng da sẽ là cứu tinh để bạn lấy lại được một làn da trắng trẻo, hồng hào hơn. Sữa tắm của mỹ phẩm Linh Hương được chiết xuất từ những thành phần tự nhiên nhất, thẩm thấu qua da cung cấp cho da những chất dưỡng trắng hiệu quả để bạn lấy lại một làn da trắng mịn hơn.\r\n\r\nSản phẩm có khả năng làm trắng da hiệu quả nhất cho bạn một làn da trắng lên theo từng ngày sử dụng. Tác dụng làm trắng là một tác dụng chính và tuyệt vời nhất của sữa tắm. ', 2),
(3, 'Sữa tắm Hazeline MatCha Lựu đỏ', 200000, 'https://s1.uphinh.org/2021/05/19/hazeline.md.jpg', 'Sữa tắm làm trắng da đem lại một làn da thơm mát\r\n\r\nDưỡng da mềm mịn\r\n\r\nHiện nay, không ít các sản phẩm sữa tắm có tích hợp các công dụng khác nhau. Ngoài làm sạch, sữa tắm còn giúp dưỡng da hiệu quả. Các chiết xuất từ thiên nhiên khiến làn da được chăm sóc, dưỡng trắng cực kỳ tốt.\r\n\r\nCác chất này không chỉ giúp dưỡng ẩm mà còn dưỡng trắng da cực tốt. Khi bề mặt da được làm sạch đúng cách thì ngay lập tức bạn sẽ cảm nhận được làn da mịn màng sau khi tắm. Hãy chọn cho mình một sản phẩm sữa tắm chất lượng để dưỡng da mềm mịn mỗi ngày nhé!', 2),
(4, 'Sữa rửa mặt SenKa', 200000, 'https://s1.uphinh.org/2021/05/19/perfect_whip.md.jpg', 'Sữa rửa mặt trị mụn dành cho da dầu \r\nSữa rửa mặt có tác dụng ngăn ngừa và hạn chế sự phát triển của mụn\r\nMột trong những tình trạng mà da sẽ gặp phải nếu như không được làm sạch hằng ngày chính là nổi mụn và thậm chí là mụn tái lại nhiều lần. Khi da của bạn bị đọng quá nhiều chất bẩn trên bề mặt cũng như trong lỗ chân lông mà không được làm sạch, điều đó sẽ gây bít tắc nang lông, viêm nhiễm và nổi mụn, thậm chí duy trì tình trạng trong thời gian dài sẽ rất khó để chữa. ', 1),
(5, 'Sữa rửa mặt Medimix', 500000, 'https://s1.uphinh.org/2021/05/23/medimix.md.jpg', 'Sữa rửa mặt trị mụn\r\n\r\nHạn chế nguy cơ nổi mụn\r\n\r\nDa tích tụ nhiều bụi bẩn, dầu nhờn, tế bào chết, mồ hôi, vi khuẩn... dễ gây ách tắc lỗ chân lông, viêm nhiễm và dẫn đến mụn hình thành. Hơn thế nữa, da quá bẩn cũng gây khó khăn trong việc điều trị mụn, khiến mụn tái đi tái lại mãi không hết.\r\n\r\nThế nên, khi bạn rửa mặt đều đặn 2 - 3 lần/ngày bằng sữa rửa mặt sẽ có tác dụng phòng ngừa mụn rất tốt, giúp da luôn khỏe mạnh và tránh bị viêm nhiễm do tác động của môi trường bên ngoài.', 1),
(6, 'Sữa tắm Hatomugi đến từ Nhật Bản', 400000, 'https://s1.uphinh.org/2021/05/23/hatomugi.jpg', 'Sữa tắm trị mụn trắng da\r\nCông dụng:\r\nPhòng chống viêm lỗ chân lông\r\n\r\nViệc mồ hôi, bụi bẩn không được làm sạch kỹ, lưu lại trên da lâu ngày sẽ dẫn đến tình trạng viêm lỗ chân lông. Nếu bạn chỉ tắm bằng nước sạch, bạn sẽ chỉ loại bỏ được phần nào bụi bẩn, bã nhờn mà không thẻ làm sạch hoàn toàn.\r\n\r\nSữa tắm có tính chất tạo bọt làm sạch sâu những lỗ chân lông trên cơ thể của bạn để loại bỏ những vi khuẩn và chất bã nhờn trên da của bạn một cách hiệu quả, phòng chống viêm nang lông trên da để làn da của bạn luôn mịn màng.', 2),
(7, 'Sữa rửa mặt StIves', 100000, 'https://s1.uphinh.org/2021/05/23/stives.md.jpg', 'Sữa rửa mặt trị mụn dành cho da nhạy cảm\r\nCông dùng:\r\nCung cấp dưỡng chất và cân bằng độ ẩm\r\n\r\nHiện nay rất nhiều sữa rửa mặt được các nhà sản xuất cho thêm vào thành phần là những vitamin, khoáng chất, dưỡng chất thiết yếu, nhằm cung cấp thêm chất dinh dưỡng làm da khỏe mạnh hơn, tăng cường sức đề kháng. Đặc biệt, sữa rửa mặt còn giúp cân bằng độ PH và độ ẩm trên bề mặt da.', 1),
(8, 'Sữa tắm OnTheBoDy ', 250000, 'https://s1.uphinh.org/2021/05/23/theboy.md.jpg', 'Sữa tắm từ thương hiệu đến từ Hàn Quốc\r\n\r\nTăng độ đàn hồi và phòng chống nhăn da\r\n\r\nTừ 30 tuổi trở đi, làn da bắt đầu gặp các dấu hiệu liên quan đến lão hóa, trở nên nhăn nheo cũng như mất đi độ đàn hồi vốn có. Nếu cảm thấy da kém sắc hơn, không còn mịn màng hơn trước thì hãy thử quan tâm về sữa tắm mình đang dùng xem sao nhé.\r\n\r\nNếu bạn có một sản phẩm sữa tắm phù hợp với làn da của mình, da của bạn sẽ được cung cấp độ ẩm vừa đủ. Ngoài ra, trong sữa tắm còn có các dưỡng chất nhất định để giúp da thêm căng mịn, tăng tính đàn hồi, chống nhăn da hiệu quả.', 2),
(9, 'Sữa rửa mặt Cerave', 169000, 'https://s1.uphinh.org/2021/05/23/theboy.md.jpg', 'Sữa rửa mặt trị mụn dành cho da thường và hỗn hợp\r\nCông dụng\r\n 1. Làm sạch da \r\n\r\nCông dụng thấy rõ nhất của sữa rửa mặt là làm sạch da. Điều đó dễ dàng thấy được ngay lần đầu sử dụng. Gương mặt đầy bụi bẩn, dầu nhờn tràn ngập sẽ lập tức sẽ trở nên tươi sáng, bừng lên sức sống chỉ sau vài giây rửa mặt. Thực vậy, những tạp chất đó sẽ làm da bạn trông kém tươi, nặng nề và bí bách. \r\n  2. Loại bỏ tạp chất mà nước thông thường không làm được\r\nNhiều người sợ kích ứng da, hoặc vì những nguyên do khác mà không dùng rửa mặt bằng sản phẩm chuyên biệt mà chỉ dùng nước thông thường. Thật ra, rửa mặt bằng nước thông thường sẽ lành tính vì chúng không có các hoạt chất làm sạch, hạn chế tối đa các kích ứng có thể gây ra. Tuy nhiên, nước thông thường không thể trung hòa dầu thừa và các bụi bẩn có kích thước siêu nhỏ đang ẩn lấp trong lỗ chân lông. ', 1),
(10, 'Sữa tắm Bull Dog', 350000, 'https://s1.uphinh.org/2021/05/23/bulldog.md.jpg', 'Sữa tắm dành cho nam giới\r\n\r\nNgăn ngừa và điều trị các tình trạng da\r\n\r\nNhững vấn đề liên quan đến vi khuẩn, viêm da như mụn thì việc sử dụng sữa rửa mặt sẽ đóng góp to lớn đến kết quả điều trị. Bởi lẽ, các hoạt chất có trong sữa rửa mặt như Salicylic acid sẽ tiêu diệt các loại vi khuẩn gây mụn, giúp da luôn sạch sẽ. Nếu không có sữa rửa mặt làm sạch đi các loại vi khuẩn đó ra khỏi da, thì các sản phẩm điều trị ở những bước sau sẽ không thể nào hoạt động được. ', 2),
(11, 'Sữa tắm Vaseline', 199000, 'https://s1.uphinh.org/2021/05/23/vaseline.md.jpg', 'Sữa tắm dưỡng da đến từ thương hiệu mỹ phẩm lâu đời\r\n\r\nPhòng chống viêm lỗ chân lông\r\n\r\nMột ngày làm việc mệt mỏi, cơ thể chúng ta toát rất nhiều mồ hôi và bám rất nhiều bụi bẩn, những nhân tố này có thể gây đến viêm lỗ chân lông, hay còn gọi là hiện tượng viêm nang lông trên da của bạn. Nếu chỉ tắm bằng nước không, những bụi bẩn và những chất bã nhờn trên da không thể làm sạch được hoàn toàn, chúng sẽ tồn đọng trên da của bạn và gây ra viêm da nghiêm trọng nếu bạn không có biện pháp làm sạch khoa học.\r\n\r\nSữa tắm có tính chất tạo bọt làm sạch sâu những lỗ chân lông trên cơ thể của bạn để loại bỏ những vi khuẩn và chất bã nhờn trên da của bạn một cách hiệu quả, phòng chống viêm nang lông trên da để làn da của bạn luôn mịn màng.', 2),
(12, 'Sữa Tắm Naive', 220000, 'https://s1.uphinh.org/2021/05/23/naive.md.jpg', 'Sữa tắm trắng da từ bột ngọc trai đến từ Nhật Bản', 2),
(13, 'Sữa rửa mặt Cerave', 189000, 'https://s1.uphinh.org/2021/05/23/cerave.md.jpg', 'Sữa rửa mặt trị mụn tốt nhất được các bác sĩ da liễu khuyên dùng', 1),
(14, 'Sữa rửa mặt trị mụn Murad', 450000, 'https://s1.uphinh.org/2021/05/23/murad.md.jpg', 'Sữa rửa mặt trị mụn dành cho da nhạy cảm\r\nGiúp các sản phẩm dưỡng da hoạt động tốt nhất vào da\r\nKhi làn da sạch sẽ không còn vi khuẩn, lỗ chân lông thông thoáng thì dưỡng chất mới có thể thẩm thấu vào da sâu và trọn vẹn nhất. Khi bạn vẫn chưa làm sạch được da, các tạp chất tạo thành lớp màng chắn không cho những sản phẩm dưỡng thẩm thấu được. Đồng thời, da vẫn chưa sạch mà phải chịu thêm tầng tầng lớp lớp các chất dưỡng, sẽ tạo ra hiện tượng bít tắc gây ra mụn ẩn và các vấn đề khác của da. \r\n\r\n', 1),
(15, 'Sữa rửa mặt OMC', 250000, 'https://s1.uphinh.org/2021/05/23/omc.jpg', 'Sữa rửa mặt trị mụn nuôi dưỡng làn da khoẻ mạnh\r\n\r\nKhông làm khô da \r\nMặc dù sữa rửa mặt làm sạch là thế nhưng cũng không làm khô da. Vì khi da quá khô, đồng nghĩa với việc lớp lipid tự nhiên- hàng rào bảo vệ tự nhiên của da - đã bị loại bỏ hoàn toàn. Khi da không còn lớp màng lipid bạn sẽ cảm thấy da khô và căng kích vì độ ẩm đã bị bốc hơi ra khỏi da. Đồng thời, thiếu đi lớp màng bảo vệ da tự nhiên, sức đề kháng và miễn nhiễm với vi khuẩn và tác hại của môi trường cũng giảm đi đáng kể, khiến nhiều vấn đề da sẽ sớm xuất hiện.', 1),
(16, 'Sữa tắm Lamont cho phụ nữ', 330000, 'https://s1.uphinh.org/2021/05/23/lamont.md.jpg', 'Sữa tắm hương hoa hồng cho làn da mịn màng thơm mát\r\nKhông làm kích ứng da và đem lại cảm giác dễ chịu khi sử dụng\r\nTất nhiên, nếu sản phẩm nào khi tiếp xúc lên da mà tạo ra những cảm giác châm chích, đau rát, khó chịu kéo dài thì bạn cũng không nên kiên trì “cắn răng chịu đựng’ sử dụng thêm. Sữa rửa mặt tạo nào đem đến cảm giác căng kích, bỏng rát khi vừa chạm vào da, bạn không nên tiếp tục sử dụng chúng nữa mà hãy tìm sản phẩm khác phù hợp hơn. Tuy nhiên, những nốt mụn bị vỡ và sưng viêm, khi tiếp xúc với các sản phẩm sữa rửa mặt có hoạt chất Salicylic acid thường gây ra cảm giác rát nhẹ nhưng sẽ chóng khỏi. Và điều đó hoàn toàn bình thường.', 2),
(17, 'Sữa rửa mặt Hasi đến từ Nhật Bản', 250000, 'https://s1.uphinh.org/2021/05/23/hasi.png', 'Sữa rửa mặt làm trắng da , ngăn ngữa lão hoá\r\nLàm sạch da hoàn hảo\r\nCông dụng chính và khi nghĩ đến sữa rửa mặt là phải làm sạch da. Nếu sản phẩm không thể giúp loại bỏ các bụi bẩn, tạp chất, dầu thừa ra khỏi da thì bạn nên suy nghĩ việc đổi sản phẩm khác. Có nhiều cách để kiểm tra độ sạch của da sau khi rửa mặt. Đó có thể là nhờ đến các biện pháp có sự can thiệp của máy móc, hoặc đơn giản hơn sau khi rửa mặt bạn thấy làn da tươi sáng, từng lỗ chân lông được làm sạch, mịn màng và thông thoáng. ', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
