-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 15, 2019 lúc 01:58 PM
-- Phiên bản máy phục vụ: 10.1.36-MariaDB
-- Phiên bản PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `shopthucung`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL,
  `categoryName` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`categoryId`, `categoryName`, `isDelete`) VALUES
(1, 'Thú cưng', 0),
(2, 'Thức ăn thú cưng', 0),
(3, 'Đồ chơi thú cưng', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `contact`
--

CREATE TABLE `contact` (
  `contactId` int(11) NOT NULL,
  `content` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `userId` int(11) NOT NULL,
  `createDay` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `coupon`
--

CREATE TABLE `coupon` (
  `couponId` int(11) NOT NULL,
  `createDay` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `money` int(11) NOT NULL,
  `supplierid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `coupon_product`
--

CREATE TABLE `coupon_product` (
  `coupon_productId` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `coupontId` int(11) NOT NULL,
  `productId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `fooddetail`
--

CREATE TABLE `fooddetail` (
  `FoodDetailId` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  `origin` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `images`
--

CREATE TABLE `images` (
  `imageId` int(11) NOT NULL,
  `imageName` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `imageLink` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `images`
--

INSERT INTO `images` (`imageId`, `imageName`, `imageLink`, `isDelete`) VALUES
(1, 'resource/images/pet.jpg', '', 0),
(2, 'resource/images/pet1.png', '', 0),
(3, 'resource/images/pet3.jpg', '', 0),
(4, 'resource/images/pet4.jpg', '', 0),
(5, 'resource/images/pet5.jpg', '', 0),
(6, 'resource/images/pet6.jpg', '', 0),
(7, 'resource/images/pet7.jpg', '', 0),
(8, 'resource/images/pet8.jpg', '', 0),
(9, 'resource/images/pet9.jpg', '', 0),
(10, 'resource/images/pet10.jpg', '', 0),
(11, 'resource/images/pet11.jpg', '', 0),
(12, 'resource/images/thuc-an.jpg', '', 0),
(13, 'resource/images/thuc-an1.jpg', '', 0),
(14, 'resource/images/thuc-an2.jpg', '', 0),
(15, 'resource/images/thuc-an3.jpg', '', 0),
(16, 'resource/images/thuc-an4.jpg', '', 0),
(17, 'resource/images/thuc-an5.jpg', '', 0),
(18, 'resource/images/thuc-an6.jpg', '', 0),
(19, 'resource/images/thuc-an7.png', '', 0),
(20, 'resource/images/thuc-an8.jpg', '', 0),
(21, 'resource/images/thuc-an9.jpg', '', 0),
(22, 'resource/images/thuc-an10.jpg', '', 0),
(23, 'resource/images/thuc-an11.jpg', '', 0),
(24, 'resource/images/pet2.jpg', '', 0),
(25, 'resource/images/do-choi.jpg', '', 0),
(26, 'resource/images/do-choi1.jpg', '', 0),
(27, 'resource/images/do-choi2.jpg', '', 0),
(28, 'resource/images/do-choi3.jpg', '', 0),
(29, 'resource/images/do-choi4.png', '', 0),
(30, 'resource/images/do-choi5.jpg', '', 0),
(31, 'resource/images/do-choi6.jpg', '', 0),
(32, 'resource/images/do-choi7.jpg', '', 0),
(33, 'resource/images/do-choi8.jpg', '', 0),
(34, 'resource/images/do-choi9.jpg', '', 0),
(35, 'resource/images/do-choi10.jpg', '', 0),
(36, 'resource/images/do-choi11.jpg', '', 0),
(37, '', '', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `news`
--

CREATE TABLE `news` (
  `newsId` int(11) NOT NULL,
  `titles` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `imageId` int(11) NOT NULL,
  `dateSubmitted` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `newsdetail`
--

CREATE TABLE `newsdetail` (
  `newsDetailId` int(11) NOT NULL,
  `content` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `newsId` int(11) NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `oder`
--

CREATE TABLE `oder` (
  `oderId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `fullName` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phoneNumber` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tradingAddress` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `totalMoney` int(11) NOT NULL,
  `createDay` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `oder`
--

INSERT INTO `oder` (`oderId`, `userId`, `fullName`, `email`, `phoneNumber`, `tradingAddress`, `status`, `totalMoney`, `createDay`) VALUES
(46, 1, '', '', '', '', 'Chưa thanh toán', 2011, '2019-06-15'),
(47, 1, 'Hồ Văn Tiên', 'ohayotien@gmail.com', '0898183251', 'Gia Lai', 'Chưa thanh toán', 2011, '2019-06-15');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `oderdetail`
--

CREATE TABLE `oderdetail` (
  `oderdetailId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `oderId` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `price` double NOT NULL,
  `createDay` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `oderdetail`
--

INSERT INTO `oderdetail` (`oderdetailId`, `productId`, `oderId`, `count`, `price`, `createDay`) VALUES
(65, 38, 46, 4, 200, '2019-06-15'),
(66, 3, 46, 5, 32, '2019-06-15'),
(67, 2, 46, 3, 31, '2019-06-15'),
(68, 1, 46, 5, 30, '2019-06-15'),
(69, 7, 46, 4, 36, '2019-06-15'),
(70, 6, 46, 3, 35, '2019-06-15'),
(71, 5, 46, 3, 34, '2019-06-15'),
(72, 8, 46, 7, 37, '2019-06-15'),
(73, 4, 46, 6, 33, '2019-06-15'),
(74, 38, 47, 4, 200, '2019-06-15'),
(75, 3, 47, 5, 32, '2019-06-15'),
(76, 2, 47, 3, 31, '2019-06-15'),
(77, 1, 47, 5, 30, '2019-06-15'),
(78, 7, 47, 4, 36, '2019-06-15'),
(79, 6, 47, 3, 35, '2019-06-15'),
(80, 5, 47, 3, 34, '2019-06-15'),
(81, 8, 47, 7, 37, '2019-06-15'),
(82, 4, 47, 6, 33, '2019-06-15');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `petdetail`
--

CREATE TABLE `petdetail` (
  `productDetailPetId` int(11) NOT NULL,
  `height` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `weight` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lifeExpectancy` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `furColor` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `petPersonality` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `takeCareOf` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL,
  `origin` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `productId` int(11) NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `petdetail`
--

INSERT INTO `petdetail` (`productDetailPetId`, `height`, `weight`, `lifeExpectancy`, `furColor`, `petPersonality`, `takeCareOf`, `origin`, `productId`, `isDelete`) VALUES
(1, 'Chiều dài: 30cm', 'Cân nặng: 2kg', 'Tuổi thọ: 20 năm', 'Màu lông: Nâu', 'Tính cách: Hoạt bát', 'Chăm sóc: Một ngày cho ăn 3 pữa, mỗi pữa 200g thức ăn. Chiều chiều dắt pé đi dạo. Một tháng đi khám định kỳ một lần.', 'Nguồn gốc: Việt Nam', 3, 0),
(2, 'Chiều dài: 10cm', 'Cân nặng: 800g', 'Tuổi thọ: 1-2 năm', 'Màu lông: Trắng đen, mầu vàng nâu, màu trắng', 'Tính cách: Hiền hòa', 'Chắm sóc: Cho ăn mỗi ngày, dắt pé đi chơi.', 'Nguồn gốc: Việt Nam', 1, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `productId` int(11) NOT NULL,
  `productName` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `oldPrice` double NOT NULL,
  `currentPrice` double NOT NULL,
  `productDescription` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `count` int(11) NOT NULL,
  `imageId` int(11) NOT NULL,
  `categoryId` int(11) NOT NULL,
  `createDay` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`productId`, `productName`, `oldPrice`, `currentPrice`, `productDescription`, `count`, `imageId`, `categoryId`, `createDay`, `isDelete`) VALUES
(1, 'Thú cưng ', 45, 30, 'Kawaii', 1000, 1, 1, '2019-06-14', 0),
(2, 'Thú cưng1 ', 45, 31, 'Kawaii', 100, 2, 1, '2019-05-21', 0),
(3, 'Thú cưng2 ', 45, 32, 'Kawaii', 100, 3, 1, '2019-05-21', 0),
(4, 'Thú cưng3 ', 45, 33, 'Kawaii', 100, 4, 1, '2019-05-21', 0),
(5, 'Thú cưng4 ', 45, 34, 'Kawaii', 100, 5, 1, '2019-05-21', 0),
(6, 'Thú cưng5 ', 45, 35, 'Kawaii', 100, 6, 1, '2019-05-21', 0),
(7, 'Thú cưng6 ', 45, 36, 'Kawaii', 100, 7, 1, '2019-05-21', 0),
(8, 'Thú cưng7', 45, 37, 'Kawaii', 100, 8, 1, '2019-05-21', 0),
(9, 'Thú cưng8', 45, 38, 'Kawaii', 100, 9, 1, '2019-05-21', 0),
(10, 'Thú cưng9', 45, 39, 'Kawaii', 100, 10, 1, '2019-05-21', 0),
(11, 'Thú cưng10', 45, 40, 'Kawaii', 100, 11, 1, '2019-05-21', 0),
(12, 'Thú cưng11', 45, 41, 'Kawaii', 100, 12, 1, '2019-05-21', 0),
(13, 'Đồ chơi thú cưng', 45, 30, 'Hot', 100, 13, 2, '2019-05-21', 0),
(14, 'Đồ chơi thú cưng 1', 45, 31, 'Hot', 100, 14, 2, '2019-05-21', 0),
(15, 'Đồ chơi thú cưng 2', 45, 32, 'Hot', 100, 15, 2, '2019-05-21', 0),
(16, 'Đồ chơi thú cưng 3', 45, 33, 'Hot', 100, 16, 2, '2019-05-21', 0),
(17, 'Đồ chơi thú cưng 4', 45, 34, 'Hot', 100, 17, 2, '2019-05-21', 0),
(18, 'Đồ chơi thú cưng 5', 45, 35, 'Hot', 100, 18, 2, '2019-05-21', 0),
(19, 'Đồ chơi thú cưng 6', 45, 36, 'Hot', 100, 19, 2, '2019-05-21', 0),
(20, 'Đồ chơi thú cưng 7', 45, 37, 'Hot', 100, 20, 2, '2019-05-21', 0),
(21, 'Đồ chơi thú cưng 8', 45, 38, 'Hot', 100, 21, 2, '2019-05-21', 0),
(22, 'Đồ chơi thú cưng 9', 45, 39, 'Hot', 100, 22, 2, '2019-05-21', 0),
(23, 'Đồ chơi thú cưng 10', 45, 40, 'Hot', 100, 23, 2, '2019-05-21', 0),
(24, 'Đồ chơi thú cưng 11', 45, 41, 'Hot', 100, 24, 2, '2019-05-21', 0),
(25, 'Thức ăn thú cưng', 45, 30, 'Good', 100, 25, 3, '2019-05-21', 0),
(26, 'Thức ăn thú cưng 1', 45, 31, 'Good', 100, 26, 3, '2019-05-21', 0),
(27, 'Thức ăn thú cưng 2', 45, 32, 'Good', 100, 27, 3, '2019-05-21', 0),
(28, 'Thức ăn thú cưng 3', 45, 33, 'Good', 100, 28, 3, '2019-05-21', 0),
(29, 'Thức ăn thú cưng 4', 45, 34, 'Good', 100, 29, 3, '2019-05-21', 0),
(30, 'Thức ăn thú cưng 5', 45, 35, 'Good', 100, 30, 3, '2019-05-21', 0),
(31, 'Thức ăn thú cưng 6', 45, 36, 'Good', 100, 31, 3, '2019-05-21', 0),
(32, 'Thức ăn thú cưng 7', 45, 37, 'Good', 100, 32, 3, '2019-05-21', 0),
(33, 'Thức ăn thú cưng 8', 45, 38, 'Good', 100, 33, 3, '2019-05-21', 0),
(34, 'Thức ăn thú cưng 9', 45, 39, 'Good', 100, 34, 3, '2019-05-21', 0),
(35, 'Thức ăn thú cưng 10', 45, 40, 'Good', 100, 35, 3, '2019-05-21', 0),
(36, 'Thức ăn thú cưng 11', 45, 41, 'Good', 100, 36, 3, '2019-05-21', 0),
(38, 'Thú cưng aaa', 300, 200, 'Kawaii', 100, 1, 1, '2019-05-28', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_change`
--

CREATE TABLE `product_change` (
  `product_ChangeId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `dateOfPurchase` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dateOfProductReturn` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `createDay` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotion`
--

CREATE TABLE `promotion` (
  `promotionId` int(11) NOT NULL,
  `promotionOfTypeId` int(11) NOT NULL,
  `promotionDate` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `promotionEndDate` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `promotionOfDescription` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `createDay` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `promotion`
--

INSERT INTO `promotion` (`promotionId`, `promotionOfTypeId`, `promotionDate`, `promotionEndDate`, `promotionOfDescription`, `createDay`, `isDelete`) VALUES
(3, 3, '2019-05-21', '2019-06-02', 'Tưng bừng khuyến mãi 1', '2019-05-28', 0),
(4, 4, '2019-05-21', '2019-06-02', 'Tưng bừng khuyến mãi mừng quốc tế thiếu nhi', '2019-05-21', 0),
(5, 5, '2019-05-21', '2019-06-02', 'Tưng bừng khuyến mãi mừng quốc tế thiếu nhi', '2019-05-21', 0),
(9, 2, '2019-05-21', '2019-06-02', 'Tưng bừng khuyến mãi mừng quốc tế thiếu nhi', '2019-05-21', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotionoftype`
--

CREATE TABLE `promotionoftype` (
  `promotionOfTypeId` int(11) NOT NULL,
  `percent` int(11) NOT NULL,
  `promotionProductId` int(11) NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `promotionoftype`
--

INSERT INTO `promotionoftype` (`promotionOfTypeId`, `percent`, `promotionProductId`, `isDelete`) VALUES
(2, 0, 2, 1),
(3, 0, 1, 0),
(4, 7, 1, 0),
(5, 10, 2, 0),
(8, 3, 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotionproduct`
--

CREATE TABLE `promotionproduct` (
  `promotionProductId` int(11) NOT NULL,
  `promotionProductName` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `count` int(11) NOT NULL,
  `categoryId` int(11) NOT NULL,
  `imageId` int(11) NOT NULL,
  `createDay` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `promotionproduct`
--

INSERT INTO `promotionproduct` (`promotionProductId`, `promotionProductName`, `count`, `categoryId`, `imageId`, `createDay`, `isDelete`) VALUES
(1, 'Đồ ăn cho pet', 100, 2, 13, '2018-12-08', 0),
(2, 'Đồ chơi cho pet', 100, 3, 32, '2018-12-08', 0),
(4, 'THú cưng s', 100, 2, 4, '2019-05-28', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotion_product`
--

CREATE TABLE `promotion_product` (
  `promotion_ProductId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `promotionId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `roleId` int(11) NOT NULL,
  `roleName` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`roleId`, `roleName`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `service`
--

CREATE TABLE `service` (
  `serviceId` int(11) NOT NULL,
  `serviceName` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `servicePrice` double NOT NULL,
  `serviceDescribe` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `createDay` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `service`
--

INSERT INTO `service` (`serviceId`, `serviceName`, `servicePrice`, `serviceDescribe`, `createDay`, `isDelete`) VALUES
(1, 'Tắm & Khô', 30, 'Chúng tôi có những kinh nghiệm, giải pháp dành cho tất cả các giống thú cưng khác nhau, sử dụng những sản phẩm sữa tắm chất lượng tốt nhất để đảm bảo cho chú thú cưng của bạn cảm thấy an tâm, sạch sẽ, khỏe mạnh và thơm mát lâu dài.', '2019-05-21', 0),
(2, 'Hoạt động vui chơi', 20, 'Thú cưng chỉ muốn vui chơi. Thú cưng của bạn luôn sẵn sàng để chơi và có thể làm cho một trò chơi trong bất cứ điều gì.', '2019-05-21', 0),
(3, 'Tạo kiểu', 50, 'Bộ lông xoăn, dày và chắc của thú cưng tạo điều kiện cho chủ nhân thoải mái sáng tạo các kiểu cắt tỉa lông cho thú cưng.', '2019-05-21', 0),
(4, 'Thú y', 100, 'Với đội ngũ bác sĩ thú y tại nhà tốt nhất, giàu kinh nghiệm. PetClub cung cấp tới khách hàng dịch vụ chăm sóc sức khỏe toàn diện cho thú cưng.', '2019-05-21', 0),
(5, 'Chăm sóc toàn diện', 300, 'PetClub là một phòng khám dành cho thú cưng, chúng tôi muốn mang đến giải pháp toàn diện và an toàn chăm sóc sức khỏe thú cưng bằng những dịch vụ y tế chuyên nghiệp, thái độ chăm sóc nhiệt tình và thân thiện', '2019-05-21', 0),
(6, 'Vắc xin', 100, 'Tiêm vacxin là cách tốt nhất để giúp thú cưng của bạn phòng chống được bệnh tật nguy hiểm, bệnh không có thuốc chữa, bệnh truyền nhiễm.', '2019-05-21', 0),
(7, 'Phòng tắm y tế', 80, '1. Tắm trị liệu khử mùi hôi, dưỡng dầu xả mềm mượt \r\n2. Vệ sinh tai\r\n3. Sấy và Chải tơi lông\r\n4.Cắt mài móng \r\n5. Dịch vụ gỡ rối và chải lông rụng ', '2019-05-21', 0),
(8, 'Đào tạo', 200, 'Bạn muốn thú cưng của mình biết cách vệ sinh đúng chỗ nhưng chưa biết phải làm thế nào hay bạn muốn thú cưng của bạn nghe lời bạn. PetClub chuyên huấn luyện thú cưng giúp bạn có một thú cưng tuyệt vời hơn', '2019-05-21', 0),
(9, 'Combo chăm sóc', 250, 'Bao gồm: Vắc xin, thú ý và phòng tắm ý tế.', '2019-05-21', 0),
(10, 'Combo làm đẹp', 70, 'Bao gồm: Tạo kiểu, tắm & khô,', '2019-05-21', 0),
(11, 'Combo đặc biệt', 320, 'Bao gồm: Chăm sóc toàn diện, hoạt động vui chơi, tắm & khô', '2019-05-21', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `serviceoder`
--

CREATE TABLE `serviceoder` (
  `serviceOderId` int(11) NOT NULL,
  `serviceId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `supplier`
--

CREATE TABLE `supplier` (
  `supplierid` int(11) NOT NULL,
  `supplierName` int(11) NOT NULL,
  `phoneNumber` int(11) NOT NULL,
  `address` int(11) NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `toysdetail`
--

CREATE TABLE `toysdetail` (
  `productDetailToysId` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `color` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `origin` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `weight` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `username` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullName` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthday` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phoneNumber` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `createDay` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `enabled` int(11) NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`userId`, `username`, `password`, `fullName`, `birthday`, `address`, `gender`, `email`, `phoneNumber`, `createDay`, `enabled`, `isDelete`) VALUES
(1, 'admin', '$2a$10$GBeVzlHfLFfuVBPPyJOPYOXSTBuTProh2MXBDgf.krw1ENMRVuStm', 'Hồ Văn Tiên', '21-08-1996', 'Gia Lai', 'Nam', 'ohayotien@gmail.com', '0898183251', '2019-05-27', 1, 0),
(2, 'emp', 'emp', 'Tiên Lười', '21-08-1996', 'Đà Nẵng', 'Nam', 'tienlazy.darksoul@gmail.com', '0348282622', '2019-05-27', 1, 0),
(3, 'null', 'null', 'null', 'null', 'null', 'null', 'null', 'null', '2019-05-28', 0, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users_roles`
--

CREATE TABLE `users_roles` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  `isDelete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users_roles`
--

INSERT INTO `users_roles` (`id`, `userId`, `roleId`, `isDelete`) VALUES
(1, 1, 2, 0),
(2, 2, 1, 0),
(3, 2, 2, 1),
(4, 1, 1, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryId`);

--
-- Chỉ mục cho bảng `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`contactId`),
  ADD KEY `userId` (`userId`);

--
-- Chỉ mục cho bảng `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`couponId`),
  ADD KEY `supplierid` (`supplierid`);

--
-- Chỉ mục cho bảng `coupon_product`
--
ALTER TABLE `coupon_product`
  ADD PRIMARY KEY (`coupon_productId`),
  ADD KEY `coupontId` (`coupontId`),
  ADD KEY `productId` (`productId`);

--
-- Chỉ mục cho bảng `fooddetail`
--
ALTER TABLE `fooddetail`
  ADD PRIMARY KEY (`FoodDetailId`),
  ADD KEY `productId` (`productId`);

--
-- Chỉ mục cho bảng `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`imageId`);

--
-- Chỉ mục cho bảng `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`newsId`),
  ADD KEY `imageId` (`imageId`);

--
-- Chỉ mục cho bảng `newsdetail`
--
ALTER TABLE `newsdetail`
  ADD PRIMARY KEY (`newsDetailId`),
  ADD KEY `newsId` (`newsId`);

--
-- Chỉ mục cho bảng `oder`
--
ALTER TABLE `oder`
  ADD PRIMARY KEY (`oderId`),
  ADD KEY `userId` (`userId`);

--
-- Chỉ mục cho bảng `oderdetail`
--
ALTER TABLE `oderdetail`
  ADD PRIMARY KEY (`oderdetailId`),
  ADD KEY `oderId` (`oderId`),
  ADD KEY `productId` (`productId`);

--
-- Chỉ mục cho bảng `petdetail`
--
ALTER TABLE `petdetail`
  ADD PRIMARY KEY (`productDetailPetId`),
  ADD KEY `productId` (`productId`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productId`),
  ADD KEY `categoryId` (`categoryId`),
  ADD KEY `imageId` (`imageId`);

--
-- Chỉ mục cho bảng `product_change`
--
ALTER TABLE `product_change`
  ADD PRIMARY KEY (`product_ChangeId`),
  ADD KEY `productId` (`productId`),
  ADD KEY `userId` (`userId`);

--
-- Chỉ mục cho bảng `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`promotionId`),
  ADD KEY `promontionOfTypeId` (`promotionOfTypeId`);

--
-- Chỉ mục cho bảng `promotionoftype`
--
ALTER TABLE `promotionoftype`
  ADD PRIMARY KEY (`promotionOfTypeId`),
  ADD KEY `promontionProductId` (`promotionProductId`);

--
-- Chỉ mục cho bảng `promotionproduct`
--
ALTER TABLE `promotionproduct`
  ADD PRIMARY KEY (`promotionProductId`),
  ADD KEY `imageId` (`imageId`),
  ADD KEY `categoryId` (`categoryId`);

--
-- Chỉ mục cho bảng `promotion_product`
--
ALTER TABLE `promotion_product`
  ADD PRIMARY KEY (`promotion_ProductId`),
  ADD KEY `productId` (`productId`),
  ADD KEY `promontionId` (`promotionId`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`roleId`);

--
-- Chỉ mục cho bảng `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`serviceId`);

--
-- Chỉ mục cho bảng `serviceoder`
--
ALTER TABLE `serviceoder`
  ADD PRIMARY KEY (`serviceOderId`),
  ADD KEY `serviceId` (`serviceId`),
  ADD KEY `userId` (`userId`);

--
-- Chỉ mục cho bảng `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`supplierid`);

--
-- Chỉ mục cho bảng `toysdetail`
--
ALTER TABLE `toysdetail`
  ADD PRIMARY KEY (`productDetailToysId`),
  ADD KEY `productId` (`productId`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`);

--
-- Chỉ mục cho bảng `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `roleId` (`roleId`),
  ADD KEY `userId` (`userId`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `contact`
--
ALTER TABLE `contact`
  MODIFY `contactId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `oder`
--
ALTER TABLE `oder`
  MODIFY `oderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT cho bảng `oderdetail`
--
ALTER TABLE `oderdetail`
  MODIFY `oderdetailId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `productId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT cho bảng `promotion`
--
ALTER TABLE `promotion`
  MODIFY `promotionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `promotionoftype`
--
ALTER TABLE `promotionoftype`
  MODIFY `promotionOfTypeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `promotionproduct`
--
ALTER TABLE `promotionproduct`
  MODIFY `promotionProductId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `roleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `service`
--
ALTER TABLE `service`
  MODIFY `serviceId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `serviceoder`
--
ALTER TABLE `serviceoder`
  MODIFY `serviceOderId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `users_roles`
--
ALTER TABLE `users_roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);

--
-- Các ràng buộc cho bảng `coupon`
--
ALTER TABLE `coupon`
  ADD CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`supplierid`) REFERENCES `supplier` (`supplierid`);

--
-- Các ràng buộc cho bảng `coupon_product`
--
ALTER TABLE `coupon_product`
  ADD CONSTRAINT `coupon_product_ibfk_1` FOREIGN KEY (`coupontId`) REFERENCES `coupon` (`couponId`),
  ADD CONSTRAINT `coupon_product_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`);

--
-- Các ràng buộc cho bảng `fooddetail`
--
ALTER TABLE `fooddetail`
  ADD CONSTRAINT `fooddetail_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`);

--
-- Các ràng buộc cho bảng `news`
--
ALTER TABLE `news`
  ADD CONSTRAINT `news_ibfk_1` FOREIGN KEY (`imageId`) REFERENCES `images` (`imageId`);

--
-- Các ràng buộc cho bảng `newsdetail`
--
ALTER TABLE `newsdetail`
  ADD CONSTRAINT `newsdetail_ibfk_1` FOREIGN KEY (`newsId`) REFERENCES `news` (`newsId`);

--
-- Các ràng buộc cho bảng `oder`
--
ALTER TABLE `oder`
  ADD CONSTRAINT `oder_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);

--
-- Các ràng buộc cho bảng `oderdetail`
--
ALTER TABLE `oderdetail`
  ADD CONSTRAINT `oderdetail_ibfk_4` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`),
  ADD CONSTRAINT `oderdetail_ibfk_5` FOREIGN KEY (`oderId`) REFERENCES `oder` (`oderId`);

--
-- Các ràng buộc cho bảng `petdetail`
--
ALTER TABLE `petdetail`
  ADD CONSTRAINT `petdetail_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`),
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`imageId`) REFERENCES `images` (`imageId`);

--
-- Các ràng buộc cho bảng `product_change`
--
ALTER TABLE `product_change`
  ADD CONSTRAINT `product_change_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`),
  ADD CONSTRAINT `product_change_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);

--
-- Các ràng buộc cho bảng `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `promotion_ibfk_1` FOREIGN KEY (`promotionOfTypeId`) REFERENCES `promotionoftype` (`promotionOfTypeId`);

--
-- Các ràng buộc cho bảng `promotionoftype`
--
ALTER TABLE `promotionoftype`
  ADD CONSTRAINT `promotionoftype_ibfk_1` FOREIGN KEY (`promotionProductId`) REFERENCES `promotionproduct` (`promotionProductId`);

--
-- Các ràng buộc cho bảng `promotionproduct`
--
ALTER TABLE `promotionproduct`
  ADD CONSTRAINT `promotionproduct_ibfk_1` FOREIGN KEY (`imageId`) REFERENCES `images` (`imageId`),
  ADD CONSTRAINT `promotionproduct_ibfk_2` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`);

--
-- Các ràng buộc cho bảng `promotion_product`
--
ALTER TABLE `promotion_product`
  ADD CONSTRAINT `promotion_product_ibfk_3` FOREIGN KEY (`promotionId`) REFERENCES `promotion` (`promotionId`),
  ADD CONSTRAINT `promotion_product_ibfk_4` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`);

--
-- Các ràng buộc cho bảng `serviceoder`
--
ALTER TABLE `serviceoder`
  ADD CONSTRAINT `serviceoder_ibfk_1` FOREIGN KEY (`serviceId`) REFERENCES `service` (`serviceId`),
  ADD CONSTRAINT `serviceoder_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);

--
-- Các ràng buộc cho bảng `toysdetail`
--
ALTER TABLE `toysdetail`
  ADD CONSTRAINT `toysdetail_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`);

--
-- Các ràng buộc cho bảng `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `roles` (`roleId`),
  ADD CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
