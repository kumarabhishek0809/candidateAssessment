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
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `options` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,'JLT');
INSERT INTO `options` VALUES (2,'JBR');
INSERT INTO `options` VALUES (3,'Media City');
INSERT INTO `options` VALUES (4,'Business Bay');
INSERT INTO `options` VALUES (5,'Meydan ');
INSERT INTO `options` VALUES (6,'None Of These');
INSERT INTO `options` VALUES (7,' -128 to 127');
INSERT INTO `options` VALUES (8,'-32768 to 32767');
INSERT INTO `options` VALUES (9,'-2147483648 to 2147483647');
INSERT INTO `options` VALUES (10,'None of the mentioned');
INSERT INTO `options` VALUES (11,'int');
INSERT INTO `options` VALUES (12,'long');
INSERT INTO `options` VALUES (13,'byte');
INSERT INTO `options` VALUES (14,'float');
INSERT INTO `options` VALUES (15,'double');
INSERT INTO `options` VALUES (16,'-1.7e+308');
INSERT INTO `options` VALUES (17,'-3.4e+038\n');
INSERT INTO `options` VALUES (18,'+1.7e+308');
INSERT INTO `options` VALUES (19,'-3.4e+050');
INSERT INTO `options` VALUES (20,'@Output()');
INSERT INTO `options` VALUES (21,'@Input()');
INSERT INTO `options` VALUES (22,'Input');
INSERT INTO `options` VALUES (23,'Output');
INSERT INTO `options` VALUES (24,'@');
INSERT INTO `options` VALUES (25,'#');
INSERT INTO `options` VALUES (26,'*');
INSERT INTO `options` VALUES (27,'&');
INSERT INTO `options` VALUES (28,'Structural directive');
INSERT INTO `options` VALUES (29,'Attribute directive');
INSERT INTO `options` VALUES (30,'.ng-invalid');
INSERT INTO `options` VALUES (31,'.ng-pending');
INSERT INTO `options` VALUES (32,'.ng-pristine');
INSERT INTO `options` VALUES (33,'.ng-dirty');
INSERT INTO `options` VALUES (34,'Javascript library');
INSERT INTO `options` VALUES (35,'Javascript framework');
INSERT INTO `options` VALUES (36,'Both of above');
INSERT INTO `options` VALUES (37,'User Interface layer in an application');
INSERT INTO `options` VALUES (38,'Data layer in an application');
INSERT INTO `options` VALUES (39,'Virtual DOM');
INSERT INTO `options` VALUES (40,'Original DOM');
INSERT INTO `options` VALUES (41,'Component based');
INSERT INTO `options` VALUES (42,'1');
INSERT INTO `options` VALUES (43,'2');
INSERT INTO `options` VALUES (44,'3');
INSERT INTO `options` VALUES (45,'A permanent storage');
INSERT INTO `options` VALUES (46,'Internal storage of the component');
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-10 14:26:41