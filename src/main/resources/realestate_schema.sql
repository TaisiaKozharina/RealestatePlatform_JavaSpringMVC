create database realestate;

use realestate;

CREATE TABLE IF NOT EXISTS `contact_msg` (
  `contact_id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `mobile_num` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `message` varchar(500) NOT NULL,
  `status` varchar(10) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `apartments` (
    `code` int AUTO_INCREMENT PRIMARY KEY,
    `type` varchar(10) NOT NULL,
    `city` varchar(70) NOT NULL,
    `address` varchar(150) NOT NULL,
    `price` real NOT NULL,
    `size` real NOT NULL,
    `rooms` int NOT NULL,
    `picture_url` varchar(30),
    `description` varchar(500) DEFAULT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` varchar(50) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL
);