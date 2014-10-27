-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 13, 2014 at 11:35 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Pembayaran_Siswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `tabel_jenis_pembayaran`
--

CREATE TABLE IF NOT EXISTS `tabel_jenis_pembayaran` (
`ID` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `tipe` varchar(50) NOT NULL,
  `nominal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tabel_kelas`
--

CREATE TABLE IF NOT EXISTS `tabel_kelas` (
`ID` int(11) NOT NULL,
  `nama_kelas` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tabel_siswa`
--

CREATE TABLE IF NOT EXISTS `tabel_siswa` (
`ID` int(11) NOT NULL,
  `nis` varchar(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `kelas` varchar(5) NOT NULL,
  `jenis_kelamin` varchar(15) NOT NULL,
  `agama` varchar(15) NOT NULL,
  `tempat_lahir` varchar(50) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `alamat` text NOT NULL,
  `nama_ortu` varchar(50) NOT NULL,
  `pekerjaan` varchar(50) NOT NULL,
  `agama_ortu` varchar(15) NOT NULL,
  `telp` varchar(20) NOT NULL,
  `alamat_ortu` text NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `tabel_siswa`
--

INSERT INTO `tabel_siswa` (`ID`, `nis`, `nama`, `kelas`, `jenis_kelamin`, `agama`, `tempat_lahir`, `tgl_lahir`, `alamat`, `nama_ortu`, `pekerjaan`, `agama_ortu`, `telp`, `alamat_ortu`) VALUES
(1, '00001', 'yufri', '1a', 'laki-laki', 'islam', 'Banjarnegara', '1992-09-14', 'jl.hatta no 01', 'wiryo', 'wiraswasta', 'islam', '085293998100', 'jl.Bagong no 09');

-- --------------------------------------------------------

--
-- Table structure for table `tabel_tanggungan`
--

CREATE TABLE IF NOT EXISTS `tabel_tanggungan` (
`ID` int(11) NOT NULL,
  `nis` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `kelas` varchar(5) NOT NULL,
  `tanggungan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tabel_jenis_pembayaran`
--
ALTER TABLE `tabel_jenis_pembayaran`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tabel_kelas`
--
ALTER TABLE `tabel_kelas`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tabel_siswa`
--
ALTER TABLE `tabel_siswa`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tabel_tanggungan`
--
ALTER TABLE `tabel_tanggungan`
 ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tabel_jenis_pembayaran`
--
ALTER TABLE `tabel_jenis_pembayaran`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tabel_kelas`
--
ALTER TABLE `tabel_kelas`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tabel_siswa`
--
ALTER TABLE `tabel_siswa`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tabel_tanggungan`
--
ALTER TABLE `tabel_tanggungan`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
