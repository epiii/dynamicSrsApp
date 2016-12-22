-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2016 at 10:46 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dynamic_srs`
--

-- --------------------------------------------------------

--
-- Table structure for table `cat`
--

CREATE TABLE IF NOT EXISTS `cat` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(50) NOT NULL,
  PRIMARY KEY (`cat_id`),
  UNIQUE KEY `cat_name` (`cat_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `cat`
--

INSERT INTO `cat` (`cat_id`, `cat_name`) VALUES
(1, 'Category 1'),
(10, 'Category 10'),
(13, 'Category 11'),
(14, 'Category 12'),
(2, 'Category 2'),
(3, 'Category 3'),
(4, 'Category 4'),
(5, 'Category 5'),
(6, 'Category 6'),
(7, 'Category 7'),
(8, 'Category 8'),
(9, 'Category 9');

-- --------------------------------------------------------

--
-- Table structure for table `description`
--

CREATE TABLE IF NOT EXISTS `description` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `srs_id` int(11) NOT NULL,
  `perspective` varchar(255) NOT NULL,
  `functions` varchar(255) NOT NULL,
  `characteristic` varchar(255) NOT NULL,
  `constraints` varchar(255) NOT NULL,
  `dependencies` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `description`
--

INSERT INTO `description` (`id`, `srs_id`, `perspective`, `functions`, `characteristic`, `constraints`, `dependencies`) VALUES
(2, 12, 'desc 1', 'desc 1', 'desc 1', 'desc 1', 'desc 1'),
(3, 13, 'desc2', 'desc2', 'desc2', 'desc2', 'desc2'),
(4, 22, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(5, 24, 'Data 2', 'Data 2', 'Data 2', 'Data 2', 'Data 2'),
(6, 25, 'Data 2', 'Data 2', 'Data 2', 'Data 2', 'Data 2'),
(7, 26, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(8, 27, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(9, 28, 'Data 2', 'Data 2', 'Data 2', 'Data 2', 'Data 2'),
(10, 29, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(11, 30, 'Data 2', 'Data 2', 'Data 2', 'Data 2', 'Data 2'),
(12, 31, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(13, 33, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(14, 34, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(15, 35, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(16, 36, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(17, 37, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(18, 38, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(19, 42, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1');

-- --------------------------------------------------------

--
-- Table structure for table `introduction`
--

CREATE TABLE IF NOT EXISTS `introduction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `srs_id` int(11) NOT NULL,
  `purpose` varchar(255) NOT NULL,
  `scope` varchar(255) NOT NULL,
  `definition` varchar(255) NOT NULL,
  `reference` varchar(255) NOT NULL,
  `overview` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `introduction`
--

INSERT INTO `introduction` (`id`, `srs_id`, `purpose`, `scope`, `definition`, `reference`, `overview`) VALUES
(4, 12, 'intro 1', 'intro 1', 'intro 1', 'intro 1', 'intro 1'),
(5, 13, 'intro2', 'intro2', 'intro2', 'intro2', 'intro2'),
(6, 15, 'Tes1', 'Tes1', 'Tes1', 'Tes1', 'Tes1'),
(7, 16, 'App 1', 'App 1', 'App 1', 'App 1', 'App 1'),
(8, 20, 'App 4', 'App 4', 'App 4', 'App 4', 'App 4'),
(9, 21, 'Test 6', 'Test 5', 'Test 5', 'Test 5', 'Test 5'),
(10, 22, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(11, 24, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(12, 25, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(13, 26, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(14, 27, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(15, 28, 'Data 2', 'Data 2', 'Data 2', 'Data 2', 'Data 2'),
(16, 29, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(17, 30, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(18, 31, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(19, 33, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(20, 34, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(21, 35, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(22, 36, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(23, 37, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(24, 38, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(25, 39, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1'),
(26, 42, 'Data 1', 'Data 1', 'Data 1', 'Data 1', 'Data 1');

-- --------------------------------------------------------

--
-- Table structure for table `nfr`
--

CREATE TABLE IF NOT EXISTS `nfr` (
  `nfr_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_id` int(11) NOT NULL,
  `srs_id` int(11) NOT NULL,
  `nfr_name` varchar(50) NOT NULL,
  `nfr_desc` varchar(255) NOT NULL,
  `nfr_fit_cri` varchar(255) NOT NULL,
  PRIMARY KEY (`nfr_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `nfr`
--

INSERT INTO `nfr` (`nfr_id`, `cat_id`, `srs_id`, `nfr_name`, `nfr_desc`, `nfr_fit_cri`) VALUES
(3, 1, 12, 'cat 1', 'cat 1', 'cat 1'),
(4, 10, 12, 'cat 2', 'cat 2', 'cat 2'),
(5, 2, 12, 'cat 3', 'cat 3', 'cat 3'),
(6, 1, 13, 'nfr1', 'nfr1', 'nfr1'),
(7, 1, 13, 'nfr2', 'nfr2', 'nfr2'),
(8, 10, 13, 'nfr1', 'nfr1', 'nfr1');

-- --------------------------------------------------------

--
-- Table structure for table `sfr`
--

CREATE TABLE IF NOT EXISTS `sfr` (
  `sfr_id` int(11) NOT NULL AUTO_INCREMENT,
  `step_id` int(11) NOT NULL,
  `srs_id` int(11) NOT NULL,
  `sfr_name` varchar(50) NOT NULL,
  `sfr_desc` varchar(255) NOT NULL,
  `sfr_fit_cri` varchar(255) NOT NULL,
  PRIMARY KEY (`sfr_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `sfr`
--

INSERT INTO `sfr` (`sfr_id`, `step_id`, `srs_id`, `sfr_name`, `sfr_desc`, `sfr_fit_cri`) VALUES
(5, 1, 12, 'fr 1', 'fr 1', 'fr 1'),
(6, 10, 12, 'fr 2', 'fr 2', 'fr 2'),
(7, 2, 12, 'fr 3', 'fr 3', 'fr 3'),
(8, 9, 13, 'sfr1', 'sfr1', 'sfr1'),
(9, 9, 13, 'sfr2', 'sfr2', 'sfr2'),
(10, 9, 13, 'sfr3', 'sfr3', 'sfr3'),
(11, 8, 13, 'sfr1', 'sfr1', 'sfr1'),
(12, 1, 25, 'SFR 1', 'SFR 1', 'SFR 1'),
(13, 1, 26, 'SFR 2', 'Data 1', 'Data 1'),
(14, 1, 27, 'SFR 1', 'Data 1', 'Data 1'),
(15, 1, 28, 'SFR 1', 'Data 2', 'Data 2'),
(16, 1, 29, 'SFR 1', 'Data 1', 'Data 1'),
(17, 1, 30, 'SFR 1', 'Data 1', 'Data 1'),
(18, 1, 31, 'SFR 1', 'Data 1', 'Data 1'),
(19, 1, 33, 'SFR 1', 'Data 1', 'Data 1'),
(20, 1, 33, 'SFR 2', 'Data 1', 'Data 1'),
(21, 1, 34, 'SFR 1', 'Data 1', 'Data 1'),
(22, 1, 34, 'SFR 2', 'Data 1', 'Data 1'),
(23, 1, 35, 'SFR 1', 'Data 1', 'Data 1'),
(24, 1, 36, 'SFR 1', 'Data 1', 'Data 1'),
(25, 1, 37, 'SFR 1', 'Data 1', 'Data 1'),
(26, 1, 37, 'SFR 2', 'Data 1', 'Data 1'),
(27, 1, 38, 'SFR 1', 'Data 1', 'Data 1'),
(28, 1, 39, 'SFR 1', 'Data 1', 'Data 1'),
(29, 1, 40, 'SFR 1', 'Data 1', 'Data 1'),
(30, 1, 41, 'SFR 1', 'Data 1', 'Data 1'),
(31, 1, 41, 'SFR 2', 'Data 2', 'Data 2'),
(32, 1, 42, 'SFR 1', 'Data 1', 'Data 1');

-- --------------------------------------------------------

--
-- Table structure for table `srs`
--

CREATE TABLE IF NOT EXISTS `srs` (
  `srs_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `srs_name` varchar(50) NOT NULL,
  PRIMARY KEY (`srs_id`),
  UNIQUE KEY `srs_name` (`srs_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `step`
--

CREATE TABLE IF NOT EXISTS `step` (
  `step_id` int(11) NOT NULL AUTO_INCREMENT,
  `step_name` varchar(50) NOT NULL,
  PRIMARY KEY (`step_id`),
  UNIQUE KEY `step_name` (`step_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `step`
--

INSERT INTO `step` (`step_id`, `step_name`) VALUES
(1, 'Step 1'),
(10, 'Step 10'),
(13, 'Step 11'),
(15, 'Step 12'),
(16, 'Step 13'),
(17, 'Step 14'),
(18, 'Step 15'),
(2, 'Step 2'),
(3, 'Step 3'),
(4, 'Step 4'),
(5, 'Step 5'),
(6, 'Step 6'),
(7, 'Step 7'),
(8, 'Step 8'),
(9, 'Step 9');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(64) DEFAULT NULL,
  `first_name` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `is_admin` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `last_name`, `first_name`, `email`, `password`, `is_admin`) VALUES
(1, 'Alpha', 'Joe', 'joe.alpha@foo.com', 'k4AXDX87vLHmzXC18klyPqt84vp6HlrPd+cnU4IggndUq8Vrikonz/pbdrXQUJlz', 0),
(2, 'Beta', 'Jane', 'jane.beta@foo.com', 'X70IvzjITvoh76FV2gBXPdnt6hYr6KgUoTm+fJcrhlGWpNigYQRY8Ql/+lI/rcdx', 0),
(3, 'Zeta', 'Becky', 'becky.zeta@foo.com', 'zO3IE+Yb23RpGiYWeSHkZzKVu0YXQ0/pY0omSWIyi1SexPuuEpdDxl2nlDerx2TM', 0),
(4, 'Charlie', 'Admin', 'charlie.admin@foo.com', 'C9VcMJhFKQCiK9WHQ2cM0DxbuRbpHt2o5/K8078ci5XQGkGg/Zfv5HCpG6wGwrR1', 1),
(5, 'Miracles', 'Percy', 'percy@foo.com', '7LhDtE2xbCJHKcvH80XpSKUolWnlC0SokdX83ZiBfHSP7xHwTKm6KLwmxQeiXvvV', 1),
(6, 'Enda', 'Depandi', 'depandienda@gmail.com', 'Kewez5TtigXK6qj5ebPHqR2rKNzigc1XuyMexNdDWv6Oj4aWhnjm9RwcmSs9ulPp', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
