CREATE DATABASE DB_PRO1041_BanHang;
GO

USE DB_PRO1041_BanHang;
GO
-- Phần 1 tạo bảng
-- 1. Bảng ChucVu
CREATE TABLE ChucVu (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma NVARCHAR(20),
    Ten NVARCHAR(50)
);

-- 2. Bảng NhanVien
CREATE TABLE NhanVien (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma NVARCHAR(20),
    Ten NVARCHAR(30),
    TenDem NVARCHAR(30),
    Ho NVARCHAR(30),
    GioiTinh NVARCHAR(10),
    NgaySinh DATE,
    DiaChi NVARCHAR(100),
    Sdt NVARCHAR(15),
    MatKhau NVARCHAR(50),
    IdCV INT,
    FOREIGN KEY (IdCV) REFERENCES ChucVu(Id)
);

-- 3. Bảng KhachHang
CREATE TABLE KhachHang (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma NVARCHAR(20),
    Ten NVARCHAR(30),
    TenDem NVARCHAR(30),
    Ho NVARCHAR(30),
    NgaySinh DATE,
    Sdt NVARCHAR(15),
    DiaChi NVARCHAR(100),
    ThanhPho NVARCHAR(50),
    QuocGia NVARCHAR(50),
    MatKhau NVARCHAR(50),
    TrangThai INT
);
-- 4. Bảng NSX 
CREATE TABLE NSX (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma NVARCHAR(20),
    Ten NVARCHAR(50)
);

-- 5. Bảng DongSP 
CREATE TABLE DongSP (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma NVARCHAR(20),
    Ten NVARCHAR(50)
);
-- 6. Bảng SanPham
CREATE TABLE SanPham (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma NVARCHAR(20),
    Ten NVARCHAR(50),
    IdDongSP INT,
    IdNSX INT,
    FOREIGN KEY (IdDongSP) REFERENCES DongSP(Id),
    FOREIGN KEY (IdNSX) REFERENCES NSX(Id)
);



-- 7. Bảng MauSac
CREATE TABLE MauSac (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma NVARCHAR(20),
    Ten NVARCHAR(50)
);

-- 8. Bảng KichThuoc (mới)
CREATE TABLE KichThuoc (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Ma NVARCHAR(20),
    Ten NVARCHAR(50)
);

-- 9. Bảng ChiTietSP
CREATE TABLE ChiTietSP (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    IdSP INT,
    IdMauSac INT,
    IdKichThuoc INT,
    NamBH INT,
    MoTa NVARCHAR(255),
    SoLuongTon INT,
    GiaNhap DECIMAL(18,2),
    GiaBan DECIMAL(18,2),
    FOREIGN KEY (IdSP) REFERENCES SanPham(Id),
    FOREIGN KEY (IdMauSac) REFERENCES MauSac(Id),
    FOREIGN KEY (IdKichThuoc) REFERENCES KichThuoc(Id)
);

-- 10. Bảng HoaDon
CREATE TABLE HoaDon (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    IdKH INT,
    IdNV INT,
    Ma NVARCHAR(20),
    NgayTao DATETIME,
    NgayThanhToan DATETIME,
    NgayShip DATETIME,
    NgayNhan DATETIME,
    TinhTrang INT,
    TenNguoiNhan NVARCHAR(100),
    DiaChi NVARCHAR(255),
    Sdt NVARCHAR(15),
    FOREIGN KEY (IdKH) REFERENCES KhachHang(Id),
    FOREIGN KEY (IdNV) REFERENCES NhanVien(Id)
);

-- 11. Bảng HoaDonChiTiet
CREATE TABLE HoaDonChiTiet (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    IdHoaDon INT,
    IdChiTietSP INT,
    SoLuong INT,
    DonGia DECIMAL(18,2),
    FOREIGN KEY (IdHoaDon) REFERENCES HoaDon(Id),
    FOREIGN KEY (IdChiTietSP) REFERENCES ChiTietSP(Id)
);
--Phần 2: insert dữ liệu
--1. insert bảng Chức vụ
INSERT INTO ChucVu (Ma, Ten)
VALUES 
('CV01', 'Quản lý'),
('CV02', 'Nhân viên bán hàng');
--2. inset bảng Nhân viên
INSERT INTO NhanVien (Ma, Ten, TenDem, Ho, GioiTinh, NgaySinh, DiaChi, Sdt, MatKhau, IdCV)
VALUES 
('NV01', 'An', 'Văn', 'Nguyễn', 'Nam', '1990-05-01', 'Hà Nội', '0901234567', '123', 1),
('NV02', 'Lan', 'Thị', 'Trần', 'Nữ', '1995-03-10', 'Hồ Chí Minh', '0912345678', '123', 2);
--3. inset bảng Khách hàng
INSERT INTO KhachHang (Ma, Ten, TenDem, Ho, NgaySinh, Sdt, DiaChi, ThanhPho, QuocGia, MatKhau, TrangThai)
VALUES 
('KH01', 'Tú', 'Hồng', 'Lê', '1998-11-20', '0981112233', '123 Lê Lợi', 'Huế', 'Việt Nam', 'abc', 1),
('KH02', 'Minh', 'Gia', 'Phạm', '1993-07-12', '0973344556', '99 Trần Hưng Đạo', 'Đà Nẵng', 'Việt Nam', 'xyz', 1);

--4. insert bảng NSX 
INSERT INTO NSX (Ma, Ten)
VALUES 
('NSX01', 'Công ty A'),
('NSX02', 'Công ty B');

--5. insert bảng DongSP 
INSERT INTO DongSP (Ma, Ten)
VALUES 
('DSP01', 'Dòng phổ thông'),
('DSP02', 'Dòng cao cấp');
--6. inset bảng Sản phẩm
-- Giả sử DongSP: ID = 1 (phổ thông), 2 (cao cấp)
-- NSX: ID = 1 (Cty A), 2 (Cty B)

INSERT INTO SanPham (Ma, Ten, IdDongSP, IdNSX)
VALUES 
('SP01', 'Áo thun', 1, 1),
('SP02', 'Quần jeans', 2, 2);
-- insert bảng Màu sắc
INSERT INTO MauSac (Ma, Ten)
VALUES 
('MS01', 'Đỏ'),
('MS02', 'Xanh');
-- insert bảng Kích thước
INSERT INTO KichThuoc (Ma, Ten)
VALUES 
('KT01', 'M'),
('KT02', 'L');
-- insert bảng chi tiết SP
INSERT INTO ChiTietSP (IdSP, IdMauSac, IdKichThuoc, NamBH, MoTa, SoLuongTon, GiaNhap, GiaBan)
VALUES 
(1, 1, 1, 1, 'Sản phẩm 1 đỏ size M', 100, 50000, 100000),
(2, 2, 2, 1, 'Sản phẩm 2 xanh size L', 50, 120000, 220000);
-- insert bảng hóa đơn
INSERT INTO HoaDon (IdKH, IdNV, Ma, NgayTao, NgayThanhToan, NgayShip, NgayNhan, TinhTrang, TenNguoiNhan, DiaChi, Sdt)
VALUES 
(1, 1, 'HD01', GETDATE(), GETDATE(), GETDATE(), GETDATE(), 1, 'Nguyễn Hồng Tú', '123 Lê Lợi, Huế', '0981112233'),
(2, 2, 'HD02', GETDATE(), GETDATE(), GETDATE(), GETDATE(), 0, 'Phạm Gia Minh', '99 Trần Hưng Đạo, Đà Nẵng', '0973344556');
-- insert bảng hóa đơn chi tiết
INSERT INTO HoaDonChiTiet (IdHoaDon, IdChiTietSP, SoLuong, DonGia)
VALUES 
(1, 1, 2, 100000),
(2, 2, 1, 220000);
