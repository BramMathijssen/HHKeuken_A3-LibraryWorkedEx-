-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 20, 2014 at 10:43 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `library`
--

USE `library`;

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `ISBN` int(11) NOT NULL,
  `Title` varchar(64) NOT NULL,
  `Author` varchar(32) NOT NULL,
  `Edition` int(11) NOT NULL,
  PRIMARY KEY (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`ISBN`, `Title`, `Author`, `Edition`) VALUES
(1111, 'Het leven is vurrukkulluk', 'Remco Campert', 1),
(2222, 'De Ontdekking van de Hemel', 'Harry Mulisch', 5);

-- --------------------------------------------------------

--
-- Table structure for table `copy`
--

CREATE TABLE IF NOT EXISTS `copy` (
  `CopyID` int(11) NOT NULL,
  `LendingPeriod` int(11) NOT NULL,
  `BookISBN` int(11) NOT NULL,
  PRIMARY KEY (`CopyID`),
  KEY `BookISBN` (`BookISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `copy`
--

INSERT INTO `copy` (`CopyID`, `LendingPeriod`, `BookISBN`) VALUES
(10001, 5, 2222),
(10002, 21, 1111);

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE IF NOT EXISTS `loan` (
  `ReturnDate` date NOT NULL,
  `MembershipNr` int(11) NOT NULL,
  `CopyID` int(11) NOT NULL,
  PRIMARY KEY (`ReturnDate`,`MembershipNr`,`CopyID`),
  KEY `MembershipNr` (`MembershipNr`),
  KEY `CopyID` (`CopyID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loan`
--

INSERT INTO `loan` (`ReturnDate`, `MembershipNr`, `CopyID`) VALUES
('2013-10-16', 1000, 10001);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `MembershipNumber` int(11) NOT NULL,
  `FirstName` varchar(32) NOT NULL,
  `LastName` varchar(32) NOT NULL,
  `Street` varchar(32) NOT NULL,
  `HouseNumber` varchar(4) NOT NULL,
  `City` varchar(32) NOT NULL,
  `PhoneNumber` varchar(16) NOT NULL,
  `EmailAddress` varchar(32) NOT NULL,
  `Fine` double NOT NULL,
  `Ssn` bigint(20) NOT NULL,
  PRIMARY KEY (`MembershipNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`MembershipNumber`, `FirstName`, `LastName`, `Street`, `HouseNumber`, `City`, `PhoneNumber`, `EmailAddress`, `Fine`, `Ssn`) VALUES
(1000, 'Pascal', 'van Gastel', 'Lovensdijkstraat', '61', 'Breda', '0765238754', 'ppth.vangastel@avans.nl', 0, 111222333),
(1001, 'Erco', 'Argante', 'Hogeschoollaan', '1', 'Breda', '0765231234', 'e.argante@avans.nl', 0, 111222333),
(1002, 'Marice', 'Bastiaensen', 'Lovensdijkstraat', '63', 'Breda', '0765236789', 'mmcm.bastiaensen@avans.nl', 5, 123456782);

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `ReservationDate` date NOT NULL,
  `MembershipNumber` int(11) NOT NULL,
  `BookISBN` int(11) NOT NULL,
  PRIMARY KEY (`ReservationDate`,`MembershipNumber`,`BookISBN`),
  KEY `MembershipNumber` (`MembershipNumber`),
  KEY `BookISBN` (`BookISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`ReservationDate`, `MembershipNumber`, `BookISBN`) VALUES
('2013-09-29', 1001, 1111);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `copy`
--
ALTER TABLE `copy`
  ADD CONSTRAINT `copy_ibfk_1` FOREIGN KEY (`BookISBN`) REFERENCES `book` (`ISBN`) ON UPDATE CASCADE;

--
-- Constraints for table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`MembershipNr`) REFERENCES `member` (`MembershipNumber`) ON UPDATE CASCADE,
  ADD CONSTRAINT `loan_ibfk_2` FOREIGN KEY (`CopyID`) REFERENCES `copy` (`CopyID`) ON UPDATE CASCADE;

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`MembershipNumber`) REFERENCES `member` (`MembershipNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`BookISBN`) REFERENCES `book` (`ISBN`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
