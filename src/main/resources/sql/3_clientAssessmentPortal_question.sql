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
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `header` varchar(255) DEFAULT NULL,
  `answer_id` int(11) DEFAULT NULL,
  `question_type_id` int(11) DEFAULT NULL,
  `technology` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2w9qd6mx9oh2vchntaokhlj4f` (`answer_id`),
  KEY `FK7svspov4rexjawqdvi2jni81u` (`question_type_id`),
  CONSTRAINT `FK2w9qd6mx9oh2vchntaokhlj4f` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`),
  CONSTRAINT `FK7svspov4rexjawqdvi2jni81u` FOREIGN KEY (`question_type_id`) REFERENCES `question_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'An expression involving byte, int, and literal numbers is promoted to which of these',1,1,'Java');
INSERT INTO `question` VALUES (2,'Which of these literals can be contained in float data type variable?',1,1,'Java');
INSERT INTO `question` VALUES (3,'What is the range of short data type in Java ?',1,1,'Java');
INSERT INTO `question` VALUES (4,'What is the range of byte data type in Java ?',1,1,'Java');
INSERT INTO `question` VALUES (5,'Which data type value is returned by all transcendental math functions?',1,1,'Java');
INSERT INTO `question` VALUES (6,'In Angular, you can pass data from parent component to child component using',1,1,'Angular');
INSERT INTO `question` VALUES (7,'In Angular, you can pass data from child component to parent component using',1,1,'Angular');
INSERT INTO `question` VALUES (8,'You can create local HTML reference of HTML tag using variable which starts with character',1,1,'Angular');
INSERT INTO `question` VALUES (9,'A directive which modifies DOM hierarchy is called',1,1,'Angular');
INSERT INTO `question` VALUES (10,'Select correct form control class name which is set to true via [(ngModel)] whenever value is modified',1,1,'Angular');
INSERT INTO `question` VALUES (11,'React is a',1,1,'React');
INSERT INTO `question` VALUES (12,'ReactJS covers',1,1,'React');
INSERT INTO `question` VALUES (13,'ReactJS uses _____ to increase performance',1,1,'React');
INSERT INTO `question` VALUES (14,'Number of elements, a valid react component can return ',1,1,'React');
INSERT INTO `question` VALUES (15,'State in react is________',1,1,'React');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-10 14:25:57
