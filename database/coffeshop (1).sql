-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 27 Okt 2019 pada 13.50
-- Versi Server: 10.1.10-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coffeshop`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `pesanan`
--

CREATE TABLE `pesanan` (
  `no` int(11) NOT NULL,
  `id_pemesanan` int(11) NOT NULL,
  `no_meja` int(11) NOT NULL,
  `barang` varchar(50) NOT NULL,
  `qty` int(11) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pesanan`
--

INSERT INTO `pesanan` (`no`, `id_pemesanan`, `no_meja`, `barang`, `qty`, `harga`) VALUES
(183, 718191, 1, 'Kopi Jos', 1, 15000),
(185, 718192, 2, 'Roti Bakar Pisang Coklat', 1, 15000),
(186, 718192, 2, 'Nasi Ayam Barbeque', 1, 25000),
(187, 718192, 2, 'Kopi Nyus', 1, 15000),
(188, 718192, 2, 'Jasmine Tea', 2, 10000),
(189, 718193, 3, 'Roti Bakar Greentea', 2, 40000),
(192, 718193, 3, 'Kopi Nyus', 2, 15000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `total`
--

CREATE TABLE `total` (
  `id_pemesanan` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `total`
--

INSERT INTO `total` (`id_pemesanan`, `total_harga`) VALUES
(718191, 15000),
(718192, 65000),
(718193, 55000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`no`),
  ADD KEY `id_pemesanan` (`id_pemesanan`);

--
-- Indexes for table `total`
--
ALTER TABLE `total`
  ADD PRIMARY KEY (`id_pemesanan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=193;
--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `total`
--
ALTER TABLE `total`
  ADD CONSTRAINT `total_ibfk_1` FOREIGN KEY (`id_pemesanan`) REFERENCES `pesanan` (`id_pemesanan`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
