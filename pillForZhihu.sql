-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2015-06-16 20:28:51
-- 服务器版本: 5.5.43-0ubuntu0.14.04.1
-- PHP 版本: 5.5.9-1ubuntu4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `pillForZhihu`
--

-- --------------------------------------------------------

--
-- 表的结构 `content`
--

CREATE TABLE IF NOT EXISTS `content` (
  `content_id` bigint(12) NOT NULL AUTO_INCREMENT,
  `content_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `content_key`
--

CREATE TABLE IF NOT EXISTS `content_key` (
  `content_key_id` int(4) NOT NULL AUTO_INCREMENT,
  `content_key` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`content_key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `content_value`
--

CREATE TABLE IF NOT EXISTS `content_value` (
  `content_value_id` bigint(16) NOT NULL AUTO_INCREMENT,
  `content_id` bigint(12) NOT NULL,
  `content_key_id` int(4) NOT NULL,
  `content_value` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`content_value_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(12) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_pwd` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `user_edge`
--

CREATE TABLE IF NOT EXISTS `user_edge` (
  `user_edge_id` bigint(12) NOT NULL AUTO_INCREMENT,
  `from_userId` bigint(12) NOT NULL,
  `to_user_id` bigint(12) NOT NULL,
  `user_edge` varchar(40) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`user_edge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `user_key`
--

CREATE TABLE IF NOT EXISTS `user_key` (
  `user_key_id` int(4) NOT NULL AUTO_INCREMENT,
  `user_key` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`user_key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `user_value`
--

CREATE TABLE IF NOT EXISTS `user_value` (
  `user_value_id` bigint(16) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(12) NOT NULL,
  `user_key_id` int(4) NOT NULL,
  `user_value` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`user_value_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
