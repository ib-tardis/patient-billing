-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2021 at 04:34 AM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.1.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db1`
--

CREATE DATABASE IF NOT EXISTS `db1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `db1`;


-- --------------------------------------------------------

--
-- Table structure for table `pb_billing`
--

CREATE TABLE `pb_billing` (
  `bill_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `physician_id` int(11) NOT NULL,
  `bill_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pb_billing`
--

INSERT INTO `pb_billing` (`bill_id`, `patient_id`, `physician_id`, `bill_datetime`) VALUES
(1, 1, 1, '2021-01-24 11:06:34'),
(2, 1, 1, '2021-01-24 11:08:25'),
(3, 1, 1, '2021-01-24 11:09:11'),
(4, 1, 1, '2021-01-24 11:09:33'),
(5, 3, 2, '2021-01-24 11:11:19');

-- --------------------------------------------------------

--
-- Table structure for table `pb_holiday`
--

CREATE TABLE `pb_holiday` (
  `hl_name` varchar(100) NOT NULL,
  `hl_date` date NOT NULL,
  `hl_createddatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hl_modifieddatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hl_createdby` int(11) NOT NULL,
  `hl_modifiedby` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pb_holiday`
--

INSERT INTO `pb_holiday` (`hl_name`, `hl_date`, `hl_createddatetime`, `hl_modifieddatetime`, `hl_createdby`, `hl_modifiedby`) VALUES
('Holiday 1', '2021-01-23', '2021-01-23 23:21:35', '2021-01-23 23:21:35', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `pb_patient`
--

CREATE TABLE `pb_patient` (
  `patient_id` int(11) NOT NULL,
  `p_name` varchar(50) NOT NULL,
  `p_age` int(11) NOT NULL,
  `p_gender` varchar(10) NOT NULL,
  `p_createdtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `p_modifiedtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `p_createdby` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pb_patient`
--

INSERT INTO `pb_patient` (`patient_id`, `p_name`, `p_age`, `p_gender`, `p_createdtime`, `p_modifiedtime`, `p_createdby`) VALUES
(1, 'Patient 1', 25, 'Female', '2021-01-23 22:37:01', '2021-01-23 22:37:01', 1),
(2, 'Patient 2', 25, 'Male', '2021-01-23 22:58:58', '2021-01-24 00:59:36', 1),
(3, 'Patient 3', 25, 'Male', '2021-01-24 01:01:02', '2021-01-24 01:01:02', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pb_physician`
--

CREATE TABLE `pb_physician` (
  `physician_id` int(11) NOT NULL,
  `ph_name` varchar(100) NOT NULL,
  `ph_createdtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ph_modifiedtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ph_createdby` int(11) NOT NULL,
  `ph_modifiedby` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pb_physician`
--

INSERT INTO `pb_physician` (`physician_id`, `ph_name`, `ph_createdtime`, `ph_modifiedtime`, `ph_createdby`, `ph_modifiedby`) VALUES
(1, 'Physician 1', '2021-01-24 01:54:21', '2021-01-24 01:54:21', 1, 1),
(2, 'Physician 2', '2021-01-24 02:04:21', '2021-01-24 07:46:26', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `pb_visit`
--

CREATE TABLE `pb_visit` (
  `visit_id` int(11) NOT NULL,
  `v_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `physician_id` int(11) NOT NULL,
  `v_reason` varchar(1000) NOT NULL,
  `v_createdtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `v_modifiedtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `v_createdby` int(11) NOT NULL,
  `v_modifiedby` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pb_visit`
--

INSERT INTO `pb_visit` (`visit_id`, `v_datetime`, `physician_id`, `v_reason`, `v_createdtime`, `v_modifiedtime`, `v_createdby`, `v_modifiedby`) VALUES
(1, '2021-01-24 07:52:14', 1, 'Not feeling well.', '2021-01-24 07:52:14', '2021-01-24 09:34:39', 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pb_billing`
--
ALTER TABLE `pb_billing`
  ADD UNIQUE KEY `bill_id` (`bill_id`);

--
-- Indexes for table `pb_patient`
--
ALTER TABLE `pb_patient`
  ADD UNIQUE KEY `patient_id` (`patient_id`);

--
-- Indexes for table `pb_physician`
--
ALTER TABLE `pb_physician`
  ADD UNIQUE KEY `physicin_id` (`physician_id`);

--
-- Indexes for table `pb_visit`
--
ALTER TABLE `pb_visit`
  ADD UNIQUE KEY `visit_id` (`visit_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pb_billing`
--
ALTER TABLE `pb_billing`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `pb_patient`
--
ALTER TABLE `pb_patient`
  MODIFY `patient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pb_physician`
--
ALTER TABLE `pb_physician`
  MODIFY `physician_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pb_visit`
--
ALTER TABLE `pb_visit`
  MODIFY `visit_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
