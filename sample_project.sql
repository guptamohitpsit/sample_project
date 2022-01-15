-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: sample_project
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp` (
  `id` int NOT NULL AUTO_INCREMENT,
  `otp` int NOT NULL,
  `mobile_no` varchar(10) NOT NULL,
  `resend_count` int NOT NULL DEFAULT '0',
  `validate_count` int NOT NULL DEFAULT '0',
  `status` bit(1) DEFAULT b'1',
  `updated_by` char(40) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` char(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
INSERT INTO `otp` VALUES (2,2223,'8765676556',0,1,_binary '\0',NULL,'2022-01-14 23:51:00','2022-01-14 23:51:00',NULL);
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revinfo`
--

DROP TABLE IF EXISTS `revinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revinfo` (
  `REV` int NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revinfo`
--

LOCK TABLES `revinfo` WRITE;
/*!40000 ALTER TABLE `revinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `revinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `mobile_no` varchar(15) DEFAULT NULL,
  `payment_mode` varchar(255) NOT NULL DEFAULT '0',
  `transaction_type` varchar(255) NOT NULL DEFAULT '0',
  `amount` double DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT '0',
  `status` bit(1) DEFAULT b'1',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` char(40) DEFAULT NULL,
  `updated_by` char(40) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (12,'8765676555','NET_BANKING','CREDIT',100,'SUCCESS',_binary '','2022-01-15 00:05:11','2022-01-15 00:05:11',NULL,NULL),(13,'8765676555','NET_BANKING','CREDIT',100,'SUCCESS',_binary '','2022-01-15 00:05:14','2022-01-15 00:05:14',NULL,NULL),(14,'8765676555','NET_BANKING','CREDIT',100,'SUCCESS',_binary '','2022-01-15 00:05:14','2022-01-15 00:05:14',NULL,NULL),(15,'8765676555','NET_BANKING','CREDIT',100,'SUCCESS',_binary '','2022-01-15 00:05:15','2022-01-15 00:05:15',NULL,NULL),(16,'8765676555','NET_BANKING','CREDIT',100,'SUCCESS',_binary '','2022-01-15 00:05:15','2022-01-15 00:05:15',NULL,NULL),(17,'8765676550','RECIEVED','CREDIT',100,'SUCCESS',_binary '','2022-01-15 00:07:26','2022-01-15 00:07:26',NULL,NULL),(18,'8765676555','TRANSFER','DEBIT',100,'SUCCESS',_binary '','2022-01-15 00:07:26','2022-01-15 00:07:26',NULL,NULL);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `middle_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `mobile_no` varchar(10) NOT NULL,
  `email_id` varchar(20) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `address_line_1` text,
  `address_line_2` text,
  `created_at` datetime DEFAULT NULL,
  `created_by` char(40) DEFAULT NULL,
  `status` bit(1) DEFAULT b'1',
  `updated_at` datetime DEFAULT NULL,
  `updated_by` char(40) DEFAULT NULL,
  `wallet` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile_no_UNIQUE` (`mobile_no`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'mohit','kumar','gupta','8765676556','m@gmail.com','MALE','Kakhawatoo','Kakhawatoo',NULL,NULL,_binary '',NULL,NULL,0),(3,'mohit','gu','Gupta','8765676555','gupta@gmail.com','MALE','kakhawatoo','khawatoo','2022-01-15 00:00:59',NULL,_binary '','2022-01-15 00:00:59',NULL,400),(4,'mohit','gu','Gupta','8765676550','gupta@gmail.com','MALE','kakhawatoo','khawatoo','2022-01-15 00:01:27',NULL,_binary '','2022-01-15 00:01:27',NULL,100),(5,'mohit','gu','Gupta','8765676500','gupta@gmail.com','MALE','kakhawatoo','khawatoo','2022-01-15 00:04:57',NULL,_binary '','2022-01-15 00:04:57',NULL,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_session`
--

DROP TABLE IF EXISTS `user_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_session` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mobile_no` char(40) NOT NULL,
  `token` varchar(100) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` char(40) DEFAULT NULL,
  `status` bit(1) DEFAULT b'1',
  `updated_at` datetime DEFAULT NULL,
  `updated_by` char(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_session`
--

LOCK TABLES `user_session` WRITE;
/*!40000 ALTER TABLE `user_session` DISABLE KEYS */;
INSERT INTO `user_session` VALUES (4,'8765676556','b97236f8-3b02-4126-ab55-357a9f555654','2022-01-14 23:51:24',NULL,_binary '\0','2022-01-15 00:44:03',NULL);
/*!40000 ALTER TABLE `user_session` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-15 20:51:52
