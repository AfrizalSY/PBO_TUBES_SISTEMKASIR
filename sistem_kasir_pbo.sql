-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2022 at 04:00 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistem_kasir_pbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `item_id` int(11) NOT NULL,
  `item_name` varchar(50) NOT NULL,
  `item_price` double NOT NULL,
  `item_jenis` varchar(50) NOT NULL,
  `volume_or_weight` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `item_name`, `item_price`, `item_jenis`, `volume_or_weight`) VALUES
(1, 'Nasi Goreng', 15000, 'Makanan', 140),
(2, 'Es Teh', 3000, 'Minuman', 500);

-- --------------------------------------------------------

--
-- Table structure for table `kasir`
--

CREATE TABLE `kasir` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `username` varchar(16) NOT NULL,
  `password` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kasir`
--

INSERT INTO `kasir` (`id`, `name`, `username`, `password`) VALUES
(1, 'Afrizal', 'asy123', 'asy123');

-- --------------------------------------------------------

--
-- Table structure for table `keranjang`
--

CREATE TABLE `keranjang` (
  `keranjang_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  `jumlah_item` int(11) NOT NULL,
  `harga_item_perjumlah` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `keranjang`
--

INSERT INTO `keranjang` (`keranjang_id`, `order_id`, `id_item`, `jumlah_item`, `harga_item_perjumlah`) VALUES
(1, 1, 1, 1, 0),
(2, 1, 2, 1, 0),
(3, 8, 1, 1, 15000),
(4, 9, 2, 1, 3000),
(5, 9, 1, 1, 15000),
(6, 10, 1, 2, 30000),
(7, 11, 1, 2, 30000),
(8, 12, 2, 2, 6000),
(9, 13, 1, 2, 30000),
(10, 13, 2, 2, 6000),
(11, 14, 2, 1, 3000),
(12, 14, 2, 1, 3000),
(13, 15, 2, 2, 6000),
(15, 16, 2, 2, 6000),
(19, 19, 2, 1, 3000),
(20, 20, 1, 1, 15000),
(21, 21, 1, 2, 30000),
(23, 22, 1, 1, 15000),
(24, 23, 1, 1, 15000),
(25, 24, 1, 1, 15000),
(26, 25, 1, 1, 15000),
(27, 26, 1, 1, 15000),
(28, 27, 1, 1, 15000),
(29, 28, 1, 1, 15000),
(30, 29, 1, 1, 15000),
(31, 30, 1, 1, 15000),
(32, 31, 1, 1, 15000),
(33, 32, 1, 1, 15000);

-- --------------------------------------------------------

--
-- Table structure for table `order_table`
--

CREATE TABLE `order_table` (
  `id_order` int(11) NOT NULL,
  `no_table` int(11) NOT NULL,
  `jenis_pembayaran` varchar(50) DEFAULT NULL,
  `nama_bank` varchar(50) DEFAULT NULL,
  `total_pembayaran` double DEFAULT NULL,
  `uang_yang_diberikan` double DEFAULT NULL,
  `uang_kembalian` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_table`
--

INSERT INTO `order_table` (`id_order`, `no_table`, `jenis_pembayaran`, `nama_bank`, `total_pembayaran`, `uang_yang_diberikan`, `uang_kembalian`) VALUES
(1, 12, 'Cash', '', 18000, 20000, 2000),
(2, 1, NULL, NULL, 20, NULL, NULL),
(3, 1, NULL, NULL, NULL, NULL, NULL),
(4, 1, NULL, NULL, NULL, NULL, NULL),
(5, 1, NULL, NULL, NULL, NULL, NULL),
(6, 1, NULL, NULL, NULL, NULL, NULL),
(7, 1, NULL, NULL, NULL, NULL, NULL),
(8, 1, NULL, NULL, NULL, NULL, NULL),
(9, 2, NULL, NULL, NULL, NULL, NULL),
(10, 1, NULL, NULL, NULL, NULL, NULL),
(11, 1, NULL, NULL, NULL, NULL, NULL),
(12, 2, NULL, NULL, NULL, NULL, NULL),
(13, 1, NULL, NULL, NULL, NULL, NULL),
(14, 1, NULL, NULL, NULL, NULL, NULL),
(15, 1, NULL, NULL, NULL, NULL, NULL),
(16, 1, NULL, NULL, NULL, NULL, NULL),
(17, 1, NULL, NULL, NULL, NULL, NULL),
(18, 1, NULL, NULL, NULL, NULL, NULL),
(19, 1, NULL, NULL, NULL, NULL, NULL),
(20, 1, NULL, NULL, NULL, NULL, NULL),
(21, 1, NULL, NULL, 30000, NULL, NULL),
(22, 1, NULL, NULL, 15000, NULL, NULL),
(23, 1, NULL, NULL, 15000, NULL, NULL),
(24, 1, NULL, NULL, 15000, NULL, NULL),
(25, 1, NULL, NULL, 15000, NULL, NULL),
(26, 1, NULL, NULL, 15000, NULL, NULL),
(27, 1, NULL, NULL, 15000, NULL, NULL),
(28, 1, NULL, NULL, 15000, NULL, NULL),
(29, 1, NULL, NULL, 15000, NULL, NULL),
(30, 1, 'bank', 'BNI', 16500, 20000, 3500),
(31, 1, NULL, NULL, 15000, NULL, NULL),
(32, 1, NULL, NULL, 15000, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`item_id`),
  ADD UNIQUE KEY `item_name` (`item_name`);

--
-- Indexes for table `kasir`
--
ALTER TABLE `kasir`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD PRIMARY KEY (`keranjang_id`);

--
-- Indexes for table `order_table`
--
ALTER TABLE `order_table`
  ADD PRIMARY KEY (`id_order`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `kasir`
--
ALTER TABLE `kasir`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `keranjang`
--
ALTER TABLE `keranjang`
  MODIFY `keranjang_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `order_table`
--
ALTER TABLE `order_table`
  MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
