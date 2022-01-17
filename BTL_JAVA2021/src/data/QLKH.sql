CREATE DATABASE QLKH

GO
USE QLKH

GO
CREATE TABLE Accounts
(
	accountID CHAR(10) PRIMARY KEY,
	userName VARCHAR(15) NOT NULL UNIQUE,
	password VARCHAR(15) NOT NULL,
	isAdmin BIT NOT NULL,
	fullName NVARCHAR(25) NOT NULL,
	balance DECIMAL(10,2) NOT NULL
)

GO
CREATE TABLE Courses
(
    courseID CHAR(5) PRIMARY KEY,
    name NVARCHAR(30) NOT NULL,
    price DECIMAL(7,2) NOT NULL,
    introduce NVARCHAR(500) NOT NULL
)

GO
CREATE TABLE Lessons
(
    lessonID CHAR(10) PRIMARY KEY,
    courseId CHAR(5) NOT NULl,
    name NVARCHAR(35) NOT NULL,
    content NVARCHAR(50) NOT NULL,
    CONSTRAINT FK_Lessons FOREIGN KEY (courseID)
    REFERENCES Courses(courseID),
)

GO
CREATE TABLE Bills
(
    billID CHAR(5) PRIMARY KEY,
    courseID CHAR(5) NOT NULL,
    accountID CHAR(10) NOT NULL,
    buyAt VARCHAR(20) NOT NULL,
    CONSTRAINT FK_Bills_courseID FOREIGN KEY (courseID)
    REFERENCES Courses(courseID),
    CONSTRAINT FK_Bills_accountID FOREIGN KEY (accountID)
    REFERENCES Accounts(accountID)
)

GO
CREATE TABLE Rates
(
    courseID CHAR(5) NOT NULL,
    accountID CHAR(10) NOT NULL,
    rateValue DECIMAL(1) NOT NULL,
    comment NVARCHAR(200) NOT NULL
	CONSTRAINT PK_Rate PRIMARY KEY (courseID, accountID),
    CONSTRAINT FK_Rates_courseID FOREIGN KEY (courseID)
    REFERENCES Courses(courseID),
    CONSTRAINT FK_Rates_accountID FOREIGN KEY (accountID)
    REFERENCES Accounts(accountID)
)

GO
INSERT INTO Accounts VALUES
('AC00000001', 'admin', 'admmin', 1, N'Nguyễn Trung Thành',  388.44),
('AC00000002', 'userName0002', 'Security@0002', 0, N'Nguyễn Quang Hải',  60.01),
('AC00000003', 'userName0003', 'Security@0003', 0, N'Nguyễn Văn Toàn',  120),
('AC00000004', 'userName0004', 'Security@0004', 0, N'Hà Đức Chinh',  200),
('AC00000005', 'userName0005', 'Security@0005', 0, N'Nguyễn Tiến Linh',  22),
('AC00000006', 'userName0006', 'Security@0006', 0, N'Nguyễn Công Phượng',  72.01),
('AC00000007', 'userName0007', 'Security@0007', 0, N'Nguyễn Phong Hồng Duy',  36.75)

GO
INSERT INTO Courses VALUES
('C0001', N'Làm quen với SQL', 99.99, N'Khóa học này sẽ giúp các lập trình viên nắm được nguyên tắc, cú pháp và cách thức hoạt động của SQL (Structured Query Language).'),
('C0002', N'C cơ bản', 49.99, N'Khóa học lập trình C cho người mới bắt đầu. Khóa học này sẽ cung cấp những kiến thức cơ bản và là nền tảng để bạn đi xa hơn trên con đường lập trình..'),
('C0003', N'Thuật toán cơ bản', 78.00, N'Với khóa học thuật toán cơ bản, bạn sẽ học được cách tư duy và giải quyết các bài toán lập trình cơ bản mà một lập trình viên cần có.'),
('C0004', N'Java cơ bản', 65.15, N'Khóa học lập trình Java cơ bản cho người mới bắt đầu, khóa học này sẽ là nền tảng cho khóa Java nâng cao để tiến tới Java Web hay lập trình Android, ...'),
('C0005', N'OOP trong java', 100.00, N'Lập trình hướng đối tượng (Object-Oriented-Programming) là phương pháp lập trình dựa trên đối tượng để tìm ra bản chất của vấn đề. Khóa học Java OOP giúp các lập trình viên học được kỹ thuật lập trình mà tất cả logic,...')

GO
INSERT INTO Bills VALUES
('B0001', 'C0003', 'AC00000005', '07/04/2021 17:21:32'),
('B0002', 'C0003', 'AC00000006', '15/09/2020 09:00:00'),
('B0003', 'C0002', 'AC00000004', '08/03/2020 02:59:22'),
('B0004', 'C0004', 'AC00000003', '01/01/2022 23:59:20'),
('B0005', 'C0004', 'AC00000005', '01/02/2021 02:59:59'),
('B0006', 'C0004', 'AC00000002', '13/11/2021 16:34:22'),
('B0007', 'C0004', 'AC00000004', '24/02/2021 14:23:23'),
('B0008', 'C0005', 'AC00000005', '10/06/2021 14:05:16')

INSERT INTO Rates VALUES
('C0003', 'AC00000005', 3, N'Cảm ơn vì đã tạo ra khóa học này nhờ vậy mà tôi cảm thấy thích học SQL'),
('C0003', 'AC00000006', 4, N'Nội dung bài dễ hiểu'),
('C0004', 'AC00000005', 4, N'Mình thấy đây là 1 khóa học tuyệt vời, giải thích hầu hết dễ hiểu, các ví dụ, bài tập nhiều tha hồ làm "nhuyễn như cháo" :D'),
('C0004', 'AC00000003', 4, N'Bạn nào học nhanh hoặc đã biết chút chút thì chừng 20h có thể học xong rồi. Xin cám ơn bạn mentor đã thiết kế ra 1 khóa học thật xịn, mình sẽ giới thiệu đến bạn bè cùng học.'),
('C0004', 'AC00000006', 3, N'Cảm ơn vì đã tạo ra khóa học này'),
('C0005', 'AC00000005', 5, N'Tôi thấy giá của nó khá rẻ so với lợi ích của nó, Tôi vẫn sẽ mua nó dù là miễn phí :)'),
('C0005', 'AC00000004', 4, N'Tôi chưa mua khóa học nhưng cảm thấy nó khá hay, mở mang kiến thức'),
('C0005', 'AC00000003', 3, N'Tôi đã tự học phần này nhưng nó khá khó, có lẽ tôi nên mua khóa học của OWBcode'),
('C0005', 'AC00000002', 2, N'Thật tiếc khi tôi chưa mua nó'),
('C0002', 'AC00000004', 5, N'Quyết định mua khóa học này của tôi thật đúng đắn, đáng đồng tiền, tôi sẽ tiếp tục đợi khóa học tiếp theo'),
('C0002', 'AC00000003', 5, N'Giá như khóa học này rẻ hơn thì tôi sẽ cân nhắc để mua nó, có lẽ phải đợi đến khi nó giảm giá')

GO

INSERT INTO Lessons VALUES
('LS00000001', 'C0001', N'Giới thiệu về cơ sở dữ liệu', 'https://codepro.io/sql/introdce'),
('LS00000002', 'C0001', N'Khởi tạo', 'https://codepro.io/sql/create'),
('LS00000003', 'C0001', N'Hiểu biết về những ràng buộc', 'https://codepro.io/sql/constraints'),
('LS00000004', 'C0001', N'Sửa đổi dữ liệu', 'https://codepro.io/sql/updating'),
('LS00000005', 'C0001', N'Lọc dữ liệu, truy vấn con', 'https://codepro.io/sql/seleting-subquery'),
('LS00000006', 'C0001', N'Gộp nhiều bảng', 'https://codepro.io/sql/merge'),
('LS00000007', 'C0002', N'Chương trình C đầu tiên', 'https://codepro.io/c/first-c-program'),
('LS00000008', 'C0002', N'Biến', 'https://codepro.io/c/variable'),
('LS00000009', 'C0002', N'Vòng lặp', 'https://codepro.io/c/loops'),
('LS00000010', 'C0002', N'Câu lệnh điều khiển', 'https://codepro.io/c/selection'),
('LS00000011', 'C0002', N'Mảng và chuỗi', 'https://codepro.io/c/array-and-string'),
('LS00000012', 'C0002', N'Hàm trong C', 'https://codepro.io/c/funtion'),
('LS00000013', 'C0003', N'Xử lý sâu, Số học', 'https://codepro.io/NumericalAndString'),
('LS00000014', 'C0003', N'Đếm, Ma trận', 'https://codepro.io/CountingAndMatrix'),
('LS00000015', 'C0003', N'Hình học, Dãy số', 'https://codepro.io/Geometry-Sequence'),
('LS00000016', 'C0003', N'Một số loại đồ thị', 'https://codepro.io/Graph'),
('LS00000017', 'C0003', N'Sắp xếp và tìm kiếm', 'https://codepro.io/SortingAndSearching'),
('LS00000018', 'C0004', N'Tổng quan', 'https://codepro.io/java/Overview'),
('LS00000019', 'C0004', N'Biến và kiểu dữ liệu', 'https://codepro.io/java/variables'),
('LS00000020', 'C0004', N'Câu lệnh rẽ nhánh', 'https://codepro.io/java/selection'),
('LS00000021', 'C0004', N'Vòng lặp', 'https://codepro.io/java/Loops'),
('LS00000022', 'C0004', N'Phương thức', 'https://codepro.io/java/Method'),
('LS00000023', 'C0005', N'Lớp và đối tượng', 'https://codepro.io/oopJava/classes'),
('LS00000024', 'C0005', N'Phương thức và biến static', 'https://codepro.io/oopJava/static'),
('LS00000025', 'C0005', N'Tính đóng gói - Encapsulation', 'https://codepro.io/oopJava/encapsulation'),
('LS00000026', 'C0005', N'Tính kế thừa - Inheritance', 'https://codepro.io/oopJava/inheritance'),
('LS00000027', 'C0005', N'Tính đa hình, trừu tượng', 'https://codepro.io/oopJava/polyAndAbstract'),
('LS00000028', 'C0005', N'Quan hệ kết tập(HAS-A)', 'https://codepro.io/oopJava/hasa-relationship')

SELECT * FROM Accounts
SELECT * FROM Courses
SELECT * FROM Lessons
SELECT * FROM Rates
SELECT * FROM Bills

