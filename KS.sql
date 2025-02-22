CREATE DATABASE KhachSan
GO
USE KhachSan
GO
CREATE TABLE KhachHang (
    maKH INT IDENTITY(1,1) PRIMARY KEY,   -- Mã khách hàng
    tenKH NVARCHAR(100),                    -- Tên khách hàng
    gioiTinh NVARCHAR(10),                  -- Giới tính
    soDT NVARCHAR(15),                      -- Số điện thoại
    email VARCHAR(100),                    -- Email
    ngaySinh DATE,                         -- Ngày sinh
    loaiKH NVARCHAR(20),                    -- Loại khách hàng (mới, cũ, vip,...)
    matKhau VARCHAR(50),                    -- Mật khẩu
	imagePath NVARCHAR(255)                 -- Hình
);
GO
CREATE TABLE NhanVien (
    maNV INT IDENTITY(1,1) PRIMARY KEY,   -- Mã nhân viên
    tenNV NVARCHAR(100),                    -- Tên nhân viên
    gioiTinh NVARCHAR(10),                  -- Giới tính
    soDT VARCHAR(15),                      -- Số điện thoại
    email VARCHAR(100),                    -- Email
    ngaySinh DATE,                         -- Ngày sinh
    chucVu NVARCHAR(50),                    -- Chức vụ
    luong DECIMAL(15, 2),                  -- Lương
    ngayVaoLam DATE,                       -- Ngày vào làm
    trangThaiCV NVARCHAR(20),               -- Trạng thái công việc
    matKhau VARCHAR(50),                    -- Mật khẩu
	imagePath NVARCHAR(255)                 -- Hình
);
GO
CREATE TABLE Phong (
    maPhong INT IDENTITY(1,1) PRIMARY KEY, -- Mã phòng
    loaiPhong NVARCHAR(50),                  -- Loại phòng
    trangThaiPhong NVARCHAR(20),             -- Trạng thái phòng (đã đặt, chưa đặt, đang bảo trì)
    thoiGianCapNhat DATETIME DEFAULT CURRENT_TIMESTAMP, -- Thời gian cập nhật
    moTa NVARCHAR(250),                              -- Mô tả phòng
    giaPhong DECIMAL(15, 2),                 -- Giá phòng
	imagePath NVARCHAR(250)					-- Hình ảnh phòng
);
GO
CREATE TABLE DichVu (
    maDV INT IDENTITY(1,1) PRIMARY KEY,   -- Mã dịch vụ
    tenDV NVARCHAR(100),                    -- Tên dịch vụ
    giaDV DECIMAL(15, 2),                   -- Giá dịch vụ
    moTa NVARCHAR(255),                              -- Mô tả dịch vụ
    thoiGianThemDV DATETIME DEFAULT CURRENT_TIMESTAMP, -- Thời gian thêm dịch vụ
);
GO
CREATE TABLE ThanhToan (
    maTT INT IDENTITY(1,1) PRIMARY KEY,    -- Mã thanh toán
    soTien DECIMAL(15, 2),                  -- Số tiền
    ngayTT DATE,                            -- Ngày thanh toán
    phuongThucTT NVARCHAR(50)               -- Phương thức thanh toán
);
GO
CREATE TABLE DatPhong (
    maDP INT IDENTITY(1,1) PRIMARY KEY,    -- Mã đặt phòng
    soNguoi INT,                           -- Số người
    ngayDat DATE,                          -- Ngày đặt
    ngayTra DATE,                          -- Ngày trả
    maNV INT,                              -- Mã nhân viên (liên kết với NhanVien)
    maKH INT,                              -- Mã khách hàng (liên kết với KhachHang)
    maPhong INT,                           -- Mã phòng (liên kết với Phong)
    maDV INT,                              -- Mã dịch vụ (liên kết với DichVu)
    maTT INT,                              -- Mã thanh toán (liên kết với ThanhToan)
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV),      -- FK liên kết với bảng NhanVien
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),       -- FK liên kết với bảng KhachHang
    FOREIGN KEY (maPhong) REFERENCES Phong(maPhong),     -- FK liên kết với bảng Phong
    FOREIGN KEY (maDV) REFERENCES DichVu(maDV),          -- FK liên kết với bảng DichVu
    FOREIGN KEY (maTT) REFERENCES ThanhToan(maTT)        -- FK liên kết với bảng ThanhToan
);
GO
CREATE TABLE DanhGia (
    maDG INT IDENTITY(1,1) PRIMARY KEY,          -- Mã đánh giá
    maKH INT NOT NULL,                            -- Mã khách hàng (liên kết với KhachHang)
    diemDanhGia INT,                              -- Điểm đánh giá (ví dụ: từ 1 đến 5)
    nhanXet NVARCHAR(500),                        -- Nhận xét của khách hàng
    ngayDanhGia DATETIME DEFAULT CURRENT_TIMESTAMP,-- Ngày đánh giá
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);
GO
CREATE TABLE ThongKe (
    maBC INT IDENTITY(1,1) PRIMARY KEY,    -- Mã báo cáo thống kê
    loaiBaoCao NVARCHAR(50),                 -- Loại báo cáo (doanh thu, khách hàng, dịch vụ, v.v.)
    noiDung TEXT,                           -- Nội dung báo cáo thống kê
    giaTri DECIMAL(15, 2),                  -- Giá trị (số tiền, số lượng, v.v.)
    ngayBaoCao DATE                         -- Ngày báo cáo
);
GO
INSERT INTO KhachHang (tenKH, gioiTinh, soDT, email, ngaySinh, loaiKH, matKhau, imagePath)
VALUES 
    (N'Nguyễn Văn A', N'Nam', '0987654321', 'nguyenvana@gmail.com', '1990-01-01', N'VIP', '123456', '1.jpg'),
    (N'Lê Thị B', N'Nữ', '0912345678', 'lethib@yahoo.com', '1995-05-15', N'Mới', 'password1',  '1.jpg'),
    (N'Phạm Văn C', N'Nam', '0978567890', 'phamvanc@hotmail.com', '1988-08-10', N'Cũ', 'qwerty',  '1.jpg'),
    (N'Trần Minh D', N'Nam', '0909090909', 'tranminhd@gmail.com', '2000-12-25', N'VIP', 'hello123',  '1.jpg'),
    (N'Hoàng Thị E', N'Nữ', '0934567890', 'hoangthie@gmail.com', '1993-07-20', N'Cũ', 'hoang123',  '1.jpg');
GO
INSERT INTO NhanVien (tenNV, gioiTinh, soDT, email, ngaySinh, chucVu, luong, ngayVaoLam, trangThaiCV, matKhau, imagePath)
VALUES 
    (N'Nguyễn Thị Mai', N'Nữ', '0981234567', 'nguyenthimai@hotel.com', '1990-03-10', N'Lễ tân', 7000000, '2022-01-01', N'Đang làm việc', 'pass123', '1.jpg'),
    (N'Hoàng Văn Tú', N'Nam', '0932123456', 'hoangvantu@hotel.com', '1992-09-20', N'Quản lý', 15000000, '2021-05-01', N'Đang làm việc', 'manager1', '1.jpg'),
    (N'Trần Thị Hồng', N'Nữ', '0912345678', 'tranthihong@hotel.com', '1998-06-15', N'Lễ tân', 7000000, '2023-03-01', N'Đang làm việc', 'hongtran', '1.jpg'),
    (N'Phạm Quang Huy', N'Nam', '0965432109', 'phamquanghuy@hotel.com', '1987-11-30', N'Kỹ thuật', 8000000, '2020-07-15', N'Đang làm việc', 'tech123', '1.jpg'),
    (N'Lê Văn Tùng', N'Nam', '0923456789', 'levantung@hotel.com', '1995-01-05', N'Nhân viên dọn phòng', 6000000, '2022-09-01', N'Đang làm việc', 'tung123', '1.jpg');
GO
INSERT INTO Phong (loaiPhong, trangThaiPhong, thoiGianCapNhat, moTa, giaPhong, imagePath)
VALUES 
    (N'Phòng đơn', N'Chưa đặt', DEFAULT, N'Phòng đơn 1 giường', 500000, '1.jpg'),
    (N'Phòng đôi', N'Đã đặt', DEFAULT, N'Phòng 2 giường', 800000, '1.jpg'),
    (N'Phòng VIP', N'Chưa đặt', DEFAULT, N'Phòng VIP cao cấp', 1500000, '1.jpg'),
    (N'Phòng gia đình', N'Đang bảo trì', DEFAULT, N'Phòng lớn dành cho gia đình', 1200000, '1.jpg'),
    (N'Phòng đôi', N'Chưa đặt', DEFAULT, N'Phòng 2 giường view biển', 850000, '1.jpg'),
	(N'Phòng đơn', N'Chưa đặt', DEFAULT, N'Phòng 3 giường view đẹp', 749500, '1.jpg');
GO
INSERT INTO DichVu (tenDV, giaDV, moTa, thoiGianThemDV)
VALUES 
    (N'Nước suối', 10000, N'1 chai nước suối', DEFAULT),
    (N'Bữa sáng', 50000, N'Bữa sáng buffet', DEFAULT),
    (N'Xe đưa đón sân bay', 200000, N'Dịch vụ đưa đón sân bay', DEFAULT),
    (N'Trái cây', 30000, N'1 đĩa trái cây tươi', DEFAULT),
    (N'Spa thư giãn', 500000, N'Dịch vụ spa tại khách sạn', DEFAULT);
GO
INSERT INTO ThanhToan (soTien, ngayTT, phuongThucTT)
VALUES
    (1500000, '2025-01-02', N'Tiền mặt'),
    (2000000, '2025-01-06', N'Chuyển khoản'),
    (500000, '2025-01-10', N'Thẻ tín dụng'),
    (1200000, '2025-01-13', N'Tiền mặt'),
    (800000, '2025-01-15', N'Thẻ tín dụng');
GO
INSERT INTO DatPhong (soNguoi, ngayDat, ngayTra, maNV, maKH, maPhong, maDV, maTT)
VALUES 
    (2, '2025-01-01', '2025-01-03', 1, 1, 2, 1, 1),
    (4, '2025-01-05', '2025-01-07', 2, 3, 3, 2, 1),
    (1, '2025-01-10', '2025-01-11', 3, 4, 1, 3, 2),
    (2, '2025-01-12', '2025-01-13', 4, 2, 5, 4, 2),
    (3, '2025-01-14', '2025-01-16', 5, 5, 4, 5, 3);
GO
INSERT INTO DanhGia (maKH, diemDanhGia, nhanXet)
VALUES 
    (1, 5, N'Khách sạn rất sạch sẽ và nhân viên nhiệt tình.'),
    (2, 4, N'Phòng đẹp nhưng dịch vụ cần cải thiện.'),
    (3, 3, N'Chỗ ở ổn, nhưng cần thêm nhiều tiện nghi hơn.'),
    (4, 5, N'Dịch vụ tuyệt vời, chắc chắn sẽ quay lại!'),
    (5, 2, N'Không hài lòng với chất lượng phòng và dịch vụ.');
GO
-- 1. Tổng doanh thu từ đặt phòng
INSERT INTO ThongKe (loaiBaoCao, noiDung, giaTri, ngayBaoCao)
SELECT 
    N'Doanh thu', 
    N'Tổng doanh thu từ đặt phòng', 
    SUM(giaPhong) AS giaTri, 
    GETDATE() AS ngayBaoCao
FROM 
    Phong
WHERE 
    maPhong IN (SELECT maPhong FROM DatPhong);
-- 2. Tổng doanh thu từ dịch vụ
INSERT INTO ThongKe (loaiBaoCao, noiDung, giaTri, ngayBaoCao)
SELECT 
    N'Doanh thu', 
    N'Tổng doanh thu từ dịch vụ', 
    SUM(giaDV) AS giaTri, 
    GETDATE() AS ngayBaoCao
FROM 
    DichVu;
-- 3. Tổng số lượt khách hàng đã đặt phòng
INSERT INTO ThongKe (loaiBaoCao, noiDung, giaTri, ngayBaoCao)
SELECT 
    N'Thống kê khách hàng', 
    N'Tổng số lượt đặt phòng của khách hàng', 
    COUNT(maDP) AS giaTri, 
    GETDATE() AS ngayBaoCao
FROM 
    DatPhong;
