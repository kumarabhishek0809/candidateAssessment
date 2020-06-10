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
-- Table structure for table `question_options`
--

DROP TABLE IF EXISTS `question_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_options` (
  `question_id` int(11) NOT NULL,
  `options_id` int(11) NOT NULL,
  PRIMARY KEY (`question_id`,`options_id`),
  KEY `FK18p5p89dis3ma9g5kveiwltmq` (`options_id`),
  CONSTRAINT `FK18p5p89dis3ma9g5kveiwltmq` FOREIGN KEY (`options_id`) REFERENCES `options` (`id`),
  CONSTRAINT `FKjk4v42xhyfv4ca1yyhorsg5tv` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_options`
--

LOCK TABLES `question_options` WRITE;
/*!40000 ALTER TABLE `question_options` DISABLE KEYS */;
INSERT INTO `question_options` VALUES (9,6);
INSERT INTO `question_options` VALUES (11,6);
INSERT INTO `question_options` VALUES (12,6);
INSERT INTO `question_options` VALUES (13,6);
INSERT INTO `question_options` VALUES (14,6);
INSERT INTO `question_options` VALUES (3,7);
INSERT INTO `question_options` VALUES (4,7);
INSERT INTO `question_options` VALUES (3,8);
INSERT INTO `question_options` VALUES (4,8);
INSERT INTO `question_options` VALUES (3,9);
INSERT INTO `question_options` VALUES (4,9);
INSERT INTO `question_options` VALUES (3,10);
INSERT INTO `question_options` VALUES (4,10);
INSERT INTO `question_options` VALUES (1,11);
INSERT INTO `question_options` VALUES (1,12);
INSERT INTO `question_options` VALUES (5,12);
INSERT INTO `question_options` VALUES (1,13);
INSERT INTO `question_options` VALUES (5,13);
INSERT INTO `question_options` VALUES (1,14);
INSERT INTO `question_options` VALUES (5,14);
INSERT INTO `question_options` VALUES (5,15);
INSERT INTO `question_options` VALUES (2,16);
INSERT INTO `question_options` VALUES (2,17);
INSERT INTO `question_options` VALUES (2,18);
INSERT INTO `question_options` VALUES (2,19);
INSERT INTO `question_options` VALUES (6,20);
INSERT INTO `question_options` VALUES (7,20);
INSERT INTO `question_options` VALUES (6,21);
INSERT INTO `question_options` VALUES (7,21);
INSERT INTO `question_options` VALUES (6,22);
INSERT INTO `question_options` VALUES (7,22);
INSERT INTO `question_options` VALUES (6,23);
INSERT INTO `question_options` VALUES (7,23);
INSERT INTO `question_options` VALUES (8,24);
INSERT INTO `question_options` VALUES (8,25);
INSERT INTO `question_options` VALUES (8,26);
INSERT INTO `question_options` VALUES (8,27);
INSERT INTO `question_options` VALUES (9,28);
INSERT INTO `question_options` VALUES (9,29);
INSERT INTO `question_options` VALUES (10,30);
INSERT INTO `question_options` VALUES (10,31);
INSERT INTO `question_options` VALUES (10,32);
INSERT INTO `question_options` VALUES (10,33);
INSERT INTO `question_options` VALUES (11,34);
INSERT INTO `question_options` VALUES (11,35);
INSERT INTO `question_options` VALUES (11,36);
INSERT INTO `question_options` VALUES (12,37);
INSERT INTO `question_options` VALUES (12,38);
INSERT INTO `question_options` VALUES (13,39);
INSERT INTO `question_options` VALUES (13,40);
INSERT INTO `question_options` VALUES (14,42);
INSERT INTO `question_options` VALUES (14,43);
INSERT INTO `question_options` VALUES (14,44);
INSERT INTO `question_options` VALUES (15,45);
INSERT INTO `question_options` VALUES (15,46);
/*!40000 ALTER TABLE `question_options` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-10 14:26:28
