-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 07 Jan 2024 pada 13.16
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pbotubes`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kode_barang` varchar(100) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `kategori` varchar(100) NOT NULL,
  `harga` int(100) NOT NULL,
  `stok` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kode_barang`, `nama_barang`, `kategori`, `harga`, `stok`) VALUES
('001', 'Stetoskop Classic', 'Alat Kedokteran', 300000, 28),
('002', 'Termometer Digital', 'Termometer', 75000, 50);

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang_in`
--

CREATE TABLE `barang_in` (
  `nama_barang` varchar(100) NOT NULL,
  `kode_barang` varchar(100) NOT NULL,
  `kategori` varchar(100) NOT NULL,
  `harga` int(100) NOT NULL,
  `stok` int(100) NOT NULL,
  `nama_sup` varchar(100) NOT NULL,
  `kode_sup` varchar(100) NOT NULL,
  `noHP` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `barang_in`
--

INSERT INTO `barang_in` (`nama_barang`, `kode_barang`, `kategori`, `harga`, `stok`, `nama_sup`, `kode_sup`, `noHP`) VALUES
('Stetoskop Classic', '001', 'Alat Kedokteran', 310000, 30, 'Frizqya', '01', '082387032882'),
('Termometer Digital', '002', 'Termometer', 75000, 50, 'Dela', '02', '0834567834567');

-- --------------------------------------------------------

--
-- Struktur dari tabel `beli_barang`
--

CREATE TABLE `beli_barang` (
  `nama_barang` varchar(100) NOT NULL,
  `kode_barang` varchar(100) NOT NULL,
  `jumlah` int(100) NOT NULL,
  `harga` int(100) NOT NULL,
  `total` int(100) NOT NULL,
  `nama_pembeli` varchar(100) NOT NULL,
  `no_pembeli` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `beli_barang`
--

INSERT INTO `beli_barang` (`nama_barang`, `kode_barang`, `jumlah`, `harga`, `total`, `nama_pembeli`, `no_pembeli`) VALUES
('Stetoskop Classic', '001', 2, 300000, 570000, 'Fara', '08136276334');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
