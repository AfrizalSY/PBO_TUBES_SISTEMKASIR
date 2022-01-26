-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 26, 2022 at 04:31 AM
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
(2, 'Es Teh', 3000, 'Minuman', 200),
(3, 'Jus Jeruk', 8000, 'Minuman', 120),
(4, 'Cheeseburger', 25000, 'Makanan', 150),
(5, 'Pizza', 70000, 'Makanan', 200),
(6, 'Lemon Tea', 10000, 'Minuman', 200),
(7, 'Lasagna', 35000, 'Makanan', 230);

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
(1, 'Afrizal', 'asy123', 'asy123'),
(2, 'admin', 'admin', 'admin');

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
(1, 1, 1, 2, 30000),
(2, 1, 2, 2, 6000),
(3, 2, 4, 1, 25000),
(4, 2, 6, 1, 10000),
(5, 3, 7, 1, 35000),
(6, 3, 5, 1, 70000),
(7, 3, 6, 1, 10000),
(8, 3, 3, 1, 8000);

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
(1, 21, 'cash', '-', 39600, 50000, 10400),
(2, 24, 'bank', 'BNI', 38500, 40000, 1500),
(3, 5, 'bank', 'Mandiri', 135300, 150000, 14700);

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
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `kasir`
--
ALTER TABLE `kasir`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `keranjang`
--
ALTER TABLE `keranjang`
  MODIFY `keranjang_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `order_table`
--
ALTER TABLE `order_table`
  MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
