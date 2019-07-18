-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 18, 2019 at 06:34 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toko_cat`
--

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `kode_kategori` varchar(9) NOT NULL,
  `nama_kategori` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`kode_kategori`, `nama_kategori`) VALUES
('001', 'CAT KAYU'),
('002', 'CAT DINDING'),
('003', 'CAT LUKIS'),
('004', 'CAT BESI'),
('005', 'CAT SEMPROT');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `ussername` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `hak_akses` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`ussername`, `password`, `hak_akses`) VALUES
('admin', 'mira', 'Admin'),
('kepala_toko', 'kolit', 'Kepala Toko'),
('kasir', 'anjas', 'Kasir');

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `id_pegawai` char(11) NOT NULL,
  `nama_pegawai` varchar(25) NOT NULL,
  `jenis_kelamin` varchar(15) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`id_pegawai`, `nama_pegawai`, `jenis_kelamin`, `alamat`) VALUES
('P001', 'ANJAS', 'Laki-laki', 'BABADAN'),
('P002', 'DANDY', 'Laki-laki', 'TURI');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `kode_cat` varchar(9) NOT NULL,
  `kode_kategori` varchar(9) NOT NULL,
  `nama_kategori` varchar(25) NOT NULL,
  `nama_cat` varchar(25) NOT NULL,
  `ukuran` char(9) NOT NULL,
  `harga_beli` int(12) NOT NULL,
  `harga_jual` int(12) NOT NULL,
  `stok` int(12) NOT NULL,
  `satuan` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`kode_cat`, `kode_kategori`, `nama_kategori`, `nama_cat`, `ukuran`, `harga_beli`, `harga_jual`, `stok`, `satuan`) VALUES
('C001', '001', 'CAT KAYU', 'GLOVIN', '600 ML', 40000, 45000, 15, 'BIJIAN'),
('C002', '002', 'CAT DINDING', 'NIPPON PAINT', '2500 ML', 80000, 87000, 11, 'BIJIAN'),
('C003', '003', 'CAT LUKIS', 'ACRYLIC', '1 KG', 120000, 132000, 6, 'BIJIAN'),
('C004', '004', 'CAT BESI', 'AVIAN', '2500ML', 145000, 162000, 9, 'BIJIAN'),
('C005', '005', 'CAT SEMPROT', 'PHYLOX', '600 ML', 25000, 28000, 32, 'BIJIAN'),
('C006', '002', 'CAT DINDING', 'DULUX', '2500 ML', 150000, 163000, 12, 'BIJIAN'),
('C007', '001', 'CAT KAYU', 'BIOVARNISH', '1 LITER', 60000, 72000, 6, 'BIJIAN'),
('C008', '003', 'CAT LUKIS', 'WATER COLOR', '1', 125000, 132000, 9, 'BIJIAN');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `no_jual` char(25) NOT NULL,
  `kategori` varchar(25) NOT NULL,
  `nama_cat` varchar(25) NOT NULL,
  `harga_jual` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembali` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`no_jual`, `kategori`, `nama_cat`, `harga_jual`, `qty`, `total`, `bayar`, `kembali`) VALUES
('1', 'CAT KAYU', 'GLOVIN', 45000, 4, 180000, 200000, 20000),
('2', 'CAT KAYU', 'GLOVIN', 45000, 7, 495000, 500000, 5000),
('3', 'CAT LUKIS', 'ACRYLIC', 132000, 4, 528000, 600000, 72000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`kode_kategori`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id_pegawai`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`kode_cat`),
  ADD KEY `kode_kategori` (`kode_kategori`),
  ADD KEY `nama_cat` (`nama_cat`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`no_jual`),
  ADD KEY `nama_cat` (`nama_cat`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `produk`
--
ALTER TABLE `produk`
  ADD CONSTRAINT `produk_ibfk_1` FOREIGN KEY (`kode_kategori`) REFERENCES `kategori` (`kode_kategori`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
