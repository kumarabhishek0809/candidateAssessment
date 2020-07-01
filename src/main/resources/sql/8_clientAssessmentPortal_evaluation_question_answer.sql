-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 3.15.175.168    Database: clientAssessmentPortal
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
REPLACE  IGNORE INTO `evaluation_question_answer` (`id`, `marks`, `assessment_id`, `options_id`, `question_id`) VALUES (1,5,1,11,1),(2,5,1,19,2),(3,5,1,8,3),(4,5,1,50,4),(5,5,1,15,5),(6,5,2,21,6),(7,5,2,20,7),(8,NULL,2,25,8),(9,NULL,2,28,9),(10,5,2,33,10),(11,5,3,34,11),(12,5,3,37,12),(13,5,3,39,13),(14,5,3,42,14),(15,5,3,46,15),(448,5,1,438,435),(459,5,1,456,454),(465,5,1,463,460),(471,5,1,468,466),(477,5,1,473,472),(483,5,1,481,478),(489,5,1,486,484),(495,5,1,494,490),(534,5,1,530,529),(540,5,1,538,535),(546,5,1,543,541),(552,5,1,550,547),(558,5,1,556,553),(564,5,1,561,559),(570,5,1,567,565),(576,5,1,573,571),(582,5,1,579,577),(588,5,1,585,583),(594,5,1,592,589),(600,5,1,598,595),(606,5,1,603,601),(612,5,1,611,607),(618,5,1,614,613),(624,5,1,622,619),(805,5,3,801,800),(811,5,3,807,806),(817,5,3,816,812),(823,5,3,821,818),(829,5,3,825,824),(835,5,3,832,830),(841,5,3,839,836),(847,5,3,844,842),(867,5,4,863,862),(873,5,4,872,868),(879,5,4,875,874),(885,5,4,883,880),(891,5,4,888,886),(897,5,4,895,892),(903,5,4,900,898),(909,5,4,905,904),(915,5,4,912,910),(921,5,4,919,916);
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

-- Dump completed on 2020-06-30 19:11:28
