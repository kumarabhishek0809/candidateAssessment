CREATE DATABASE  IF NOT EXISTS `clientAssessmentPortal` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `clientAssessmentPortal`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 18.223.111.230    Database: clientAssessmentPortal
-- ------------------------------------------------------
-- Server version	5.7.30

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
-- Table structure for table `assessment_questions`
--

DROP TABLE IF EXISTS `assessment_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assessment_questions` (
  `assessment_id` int(11) NOT NULL,
  `questions_id` int(11) NOT NULL,
  KEY `FKaf20224e4jochperimgguvnuw` (`assessment_id`),
  KEY `FKbsio8xaewqfgk6w24cydb5be8` (`questions_id`),
  CONSTRAINT `FKaf20224e4jochperimgguvnuw` FOREIGN KEY (`assessment_id`) REFERENCES `assessment` (`id`),
  CONSTRAINT `FKbsio8xaewqfgk6w24cydb5be8` FOREIGN KEY (`questions_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assessment_questions`
--

LOCK TABLES `assessment_questions` WRITE;
/*!40000 ALTER TABLE `assessment_questions` DISABLE KEYS */;
INSERT INTO `assessment_questions` VALUES (2,1);
INSERT INTO `assessment_questions` VALUES (2,2);
INSERT INTO `assessment_questions` VALUES (2,3);
INSERT INTO `assessment_questions` VALUES (2,4);
INSERT INTO `assessment_questions` VALUES (2,5);
INSERT INTO `assessment_questions` VALUES (3,1);
INSERT INTO `assessment_questions` VALUES (3,2);
INSERT INTO `assessment_questions` VALUES (3,3);
INSERT INTO `assessment_questions` VALUES (3,4);
INSERT INTO `assessment_questions` VALUES (3,5);
INSERT INTO `assessment_questions` VALUES (1,1);
INSERT INTO `assessment_questions` VALUES (1,2);
INSERT INTO `assessment_questions` VALUES (1,3);
INSERT INTO `assessment_questions` VALUES (1,4);
INSERT INTO `assessment_questions` VALUES (1,5);
/*!40000 ALTER TABLE `assessment_questions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-10 11:16:35
