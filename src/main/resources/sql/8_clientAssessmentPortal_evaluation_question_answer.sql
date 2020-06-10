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
-- Table structure for table `evaluation_question_answer`
--

DROP TABLE IF EXISTS `evaluation_question_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluation_question_answer` (
  `id` int(11) NOT NULL,
  `marks` int(11) DEFAULT NULL,
  `assessment_id` int(11) DEFAULT NULL,
  `options_id` int(11) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkc7j7q3juw0hk1omhb1hppk1s` (`assessment_id`),
  KEY `FK7vwl74u7e2s12g26go874vym8` (`options_id`),
  KEY `FK9lw0iwbhshsf9njqr0h7x7ul6` (`question_id`),
  CONSTRAINT `FK7vwl74u7e2s12g26go874vym8` FOREIGN KEY (`options_id`) REFERENCES `options` (`id`),
  CONSTRAINT `FK9lw0iwbhshsf9njqr0h7x7ul6` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FKkc7j7q3juw0hk1omhb1hppk1s` FOREIGN KEY (`assessment_id`) REFERENCES `assessment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation_question_answer`
--

LOCK TABLES `evaluation_question_answer` WRITE;
/*!40000 ALTER TABLE `evaluation_question_answer` DISABLE KEYS */;
INSERT INTO `evaluation_question_answer` VALUES (1,5,1,11,1);
INSERT INTO `evaluation_question_answer` VALUES (2,5,1,19,2);
INSERT INTO `evaluation_question_answer` VALUES (3,5,1,8,3);
INSERT INTO `evaluation_question_answer` VALUES (4,5,1,7,4);
INSERT INTO `evaluation_question_answer` VALUES (5,5,1,15,5);
INSERT INTO `evaluation_question_answer` VALUES (6,5,2,21,6);
INSERT INTO `evaluation_question_answer` VALUES (7,5,2,20,7);
INSERT INTO `evaluation_question_answer` VALUES (8,NULL,2,25,8);
INSERT INTO `evaluation_question_answer` VALUES (9,NULL,2,28,9);
INSERT INTO `evaluation_question_answer` VALUES (10,5,2,33,10);
INSERT INTO `evaluation_question_answer` VALUES (11,5,3,34,11);
INSERT INTO `evaluation_question_answer` VALUES (12,5,3,37,12);
INSERT INTO `evaluation_question_answer` VALUES (13,5,3,39,13);
INSERT INTO `evaluation_question_answer` VALUES (14,5,3,42,14);
INSERT INTO `evaluation_question_answer` VALUES (15,5,3,46,15);
/*!40000 ALTER TABLE `evaluation_question_answer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-10 14:26:22
