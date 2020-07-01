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
REPLACE  IGNORE INTO `question` (`id`, `header`, `answer_id`, `question_type_id`, `technology`) VALUES (1,'An expression involving byte, int, and literal numbers is promoted to which of these',1,1,'Java'),(2,'Which of these literals can be contained in float data type variable?',1,1,'Java'),(3,'What is the range of short data type in Java ?',1,1,'Java'),(4,'What is the range of byte data type in Java ?',1,1,'Java'),(5,'Which data type value is returned by all transcendental math functions?',1,1,'Java'),(6,'In Angular, you can pass data from parent component to child component using',1,1,'Angular'),(7,'In Angular, you can pass data from child component to parent component using',1,1,'Angular'),(8,'You can create local HTML reference of HTML tag using variable which starts with character',1,1,'Angular'),(9,'A directive which modifies DOM hierarchy is called',1,1,'Angular'),(10,'Select correct form control class name which is set to true via [(ngModel)] whenever value is modified',1,1,'Angular'),(11,'React is a',1,1,'React'),(12,'ReactJS covers',1,1,'React'),(13,'ReactJS uses _____ to increase performance',1,1,'React'),(14,'Number of elements, a valid react component can return ',1,1,'React'),(15,'State in react is________',1,1,'React'),(84,'Which of the following is not a core AngularJS directive',1,1,'Angular'),(430,'Which of the following option leads to the portability and security of Java',1,1,'Java'),(435,'Which of the following is not a Java features',1,1,'Java'),(454,'What is the return type of the hashCode() method in the Object class',1,1,'Java'),(460,'What does the expression float a = 35 / 0 return',1,1,'Java'),(466,'Which of the following creates a List of 3 visible items and multiple selections abled',1,1,'Java'),(472,'Which of the following for loop declaration is not valid',1,1,'Java'),(478,'Which method of the Class.class is used to determine the name of a class represented by the class object as a String',1,1,'Java'),(484,'In which process, a local variable has the same name as one of the instance variables',1,1,'Java'),(490,'Which of the following is true about the anonymous inner class',1,1,'Java'),(529,'Which package contains the Random class',1,1,'Java'),(535,'What do you mean by nameless objects',1,1,'Java'),(541,'An interface with no fields or methods is known as a ______',1,1,'Java'),(547,'Which option is false about the final keyword',1,1,'Java'),(553,'Which of these classes are the direct subclasses of the Throwable class',1,1,'Java'),(559,'What do you mean by chained exceptions in Java',1,1,'Java'),(565,'Which of the following is a marker interface',1,1,'Java'),(571,'Which of the following is a reserved keyword in Java',1,1,'Java'),(577,'Which keyword is used for accessing the features of a package',1,1,'Java'),(583,'In java, jar stands for_____',1,1,'Java'),(589,'Which of the given methods are of Object class',1,1,'Java'),(595,'Which of the following is a valid syntax to synchronize the HashMap',1,1,'Java'),(601,'What is the initial quantity of the ArrayList list : ArrayList list = new ArrayList();  ',1,1,'Java'),(607,'Which of the following is a mutable class in java ',1,1,'Java'),(613,'What is meant by the classes and objects that dependents on each other ',1,1,'Java'),(619,'If three threads trying to share a single object at the same time, which condition will arise in this scenario',1,1,'Java'),(800,'What kind of component import React from \'react\' is',1,1,'React'),(806,'What is ReactJS',1,1,'React'),(812,'Which of following is used to pass data to a component from outside',1,1,'React'),(818,'What function allows you to render React content in an HTML page',1,1,'React'),(824,'React.js was created by',1,1,'React'),(830,'Arbitrary inputs of components in React are called',1,1,'React'),(836,'For what \"webpack\" command is used',1,1,'React'),(842,'What create-react-app command do',1,1,'React'),(862,'Spring-WS provides various abstract endpoint classes for you to process the request',1,1,'Spring Boot'),(868,'Which among these is not an applications server provided by Spring Boot',1,1,'Spring Boot'),(874,'Does Spring Boot in anyway reduce the need to write lots of configuration',1,1,'Spring Boot'),(880,'Why is it possible to get started with minimum effort on Spring Boost',1,1,'Spring Boot'),(886,'Which among these does Spring Boot not provide',1,1,'Spring Boot'),(892,'The auto-configuration chooses what to create based on the availability of what',1,1,'Spring Boot'),(898,'Which is a peculiar quality of the Spring Boot platform',1,1,'Spring Boot'),(904,'Which of these is not true of Spring Boot',1,1,'Spring Boot'),(910,'Does Spring Boot favour Configuration over Convention',1,1,'Spring Boot'),(916,'Which of these is not true of Spring Boot',1,1,'Spring Boot');
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

-- Dump completed on 2020-06-30 19:10:49
