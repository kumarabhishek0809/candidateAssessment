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
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `options` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk821m563xwucpkx6ju8r3rkm4` (`question_id`),
  CONSTRAINT `FKk821m563xwucpkx6ju8r3rkm4` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
REPLACE  IGNORE INTO `options` (`id`, `description`, `question_id`) VALUES (1,'Container Orchestration Method',11),(2,'None Of These',11),(3,'Database Storage',12),(4,'None Of These',12),(5,'Meydan ',NULL),(6,'None Of These',NULL),(7,' -128 to 127',3),(8,'-32768 to 32767',3),(9,'-2147483648 to 2147483647',3),(10,'None of the mentioned',3),(11,'int',1),(12,'long',1),(13,'byte',1),(14,'float',1),(15,'double',5),(16,'-1.7e+308',2),(17,'-3.4e+038\n',2),(18,'+1.7e+308',2),(19,'-3.4e+050',2),(20,'@Output()',7),(21,'@Input()',6),(22,'Input',6),(23,'Output',6),(24,'@',8),(25,'#',8),(26,'*',8),(27,'&',8),(28,'Structural directive',9),(29,'Attribute directive',9),(30,'.ng-invalid',10),(31,'.ng-pending',10),(32,'.ng-pristine',10),(33,'.ng-dirty',10),(34,'Javascript library',11),(35,'Javascript framework',11),(36,'Jquery Library',NULL),(37,'User Interface layer in an application',12),(38,'Data layer in an application',12),(39,'Virtual DOM',13),(40,'Original DOM',13),(41,'Component based',13),(42,'1',14),(43,'2',14),(44,'3',14),(45,'A permanent storage',15),(46,'Internal storage of the component',15),(47,'Controller Layer',15),(48,'XML Dom',15),(49,'4',14),(50,' -128 to 127',4),(51,'-32768 to 32767',4),(52,'-2147483648 to 2147483647',4),(53,'None of the mentioned',4),(54,'long',5),(55,'byte',5),(56,'float',5),(57,'@Output()',6),(58,'@Input()',7),(59,'Input',7),(60,'Output',7),(85,'ng-app',84),(86,'ng-model',84),(87,'ng-bind',84),(88,'ng-state',84),(431,'Bytecode is executed by JVM',430),(432,'The applet makes the Java code secure and portable',430),(433,'Use of exception handling',430),(434,'Dynamic binding between objects',430),(436,'Dynamic',435),(437,'Architecture Neutral',435),(438,'Use of pointers',435),(439,'Object-oriented',435),(455,'Object',454),(456,'int',454),(457,'long',454),(458,'void',454),(461,'0',460),(462,'Not a Number',460),(463,'Infinity',460),(464,'Run time exception',460),(467,'new List(false, 3)',466),(468,'new List(3, true)',466),(469,'new List(true, 3)',466),(470,'new List(3, false)',466),(473,'for ( int i = 99; i >= 0; i / 9 )',472),(474,'for ( int i = 7; i <= 77; i += 7 )',472),(475,'for ( int i = 20; i >= 2; - -i )',472),(476,'for ( int i = 2; i <= 20; i = 2* i )',472),(479,'getClass()',478),(480,'intern()',478),(481,'getName()',478),(482,'toString()',478),(485,'Serialization',484),(486,'Variable Shadowing',484),(487,'Abstraction',484),(488,'Multi-threading',484),(491,'It has only methods',490),(492,'Objects can\'t be created',490),(493,'It has a fixed class name',490),(494,'It has no class name',490),(530,'java.util package',529),(531,'java.lang package',529),(532,'java.awt package',529),(533,'java.io package',529),(536,'An object created by using the new keyword',535),(537,'An object of a superclass created in the subclass',535),(538,'An object without having any name but having a reference',535),(539,'An object that has no reference',535),(542,'Runnable Interface',541),(543,'Marker Interface',541),(544,'Abstract Interface',541),(545,'CharSequence Interface',541),(548,'A final method cannot be overridden in its subclasses.',547),(549,'A final class cannot be extended.',547),(550,'A final class cannot extend other classes.',547),(551,'A final method can be inherited',547),(554,'RuntimeException and Error class',553),(555,'Exception and VirtualMachineError class',553),(556,'Error and Exception class',553),(557,'IOException and VirtualMachineError class',553),(560,'Exceptions occurred by the VirtualMachineError',559),(561,'An exception caused by other exceptions',559),(562,'Exceptions occur in chains with discarding the debugging information',559),(563,'None of the above',559),(566,'Runnable interface',565),(567,'Remote interface',565),(568,'Readable interface',565),(569,'Result interface',565),(572,'object',571),(573,'strictfp',571),(574,'main',571),(575,'system',571),(578,'package',577),(579,'import',577),(580,'extends',577),(581,'export',577),(584,'Java Archive Runner',583),(585,'Java ARchive',583),(586,'Java Application Runner',583),(587,'None of the above',583),(590,'notify(), wait( long msecs ), and synchronized()',589),(591,'wait( long msecs ), interrupt(), and notifyAll()',589),(592,'notify(), notifyAll(), and wait()',589),(593,'sleep( long msecs ), wait(), and notify()',589),(596,'Map m = hashMap.synchronizeMap()',595),(597,'HashMap map =hashMap.synchronizeMap()',595),(598,'Map m1 = Collections.synchronizedMap(hashMap)',595),(599,'Map m2 = Collection.synchronizeMap(hashMap)',595),(602,'5',601),(603,'10',601),(604,'0',601),(605,'100',601),(608,'java.lang.String',607),(609,'java.lang.Byte',607),(610,'java.lang.Short',607),(611,'java.lang.StringBuilder',607),(614,'Tight Coupling',613),(615,'Cohesion',613),(616,'Loose Coupling',613),(617,'None of the above',613),(620,'Time-Lapse',619),(621,'Critical situation',619),(622,'Race condition',619),(623,'Recursion',619),(801,'stateless component',800),(802,'statefull component',800),(803,'pure component',800),(804,'None of These',800),(807,'User-interface framework',806),(808,'Server-side Framework',806),(809,'Both server & Client',806),(810,'None of These',806),(813,'PropTypes',812),(814,'setState',812),(815,'render with arguments',812),(816,'props',812),(819,'React.mount()',818),(820,'ReactDOM.start()',818),(821,'ReactDOM.render()',818),(822,'React.render()',818),(825,'Jordan Walke',824),(826,'Jordan mike',824),(827,'Tim Lee',824),(828,'Jordan Lee',824),(831,'Keys',830),(832,'Props',830),(833,'Elements',830),(834,'Ref',830),(837,'Transpiles all the Javascript down into one file',836),(838,'Runs react local development server.',836),(839,'A module bundler',836),(840,'None of these',836),(843,'Update a react app',842),(844,'Creates new react app',842),(845,'install dependencies',842),(846,'None of these',842),(863,'org.springframework.ws.server.endpoint',862),(864,'org.springframework.ws.server',862),(865,'org.springframework.*',862),(866,'none of the mentioned',862),(869,'Embedded Tomcat',868),(870,'Jetty',868),(871,'Undertow',868),(872,'Binary link',868),(875,'Yes',874),(876,'No',874),(877,'Undecided',874),(878,'No Idea',874),(881,'Because it has easy to use codes',880),(882,'Because it is enabled by the Spring framework',880),(883,'Because it has an opinionated view on Spring platform',880),(884,'Because it is well explained',880),(887,'Externalized configuration',886),(888,'Equalizer',886),(889,'Health checks',886),(890,'Metrix',886),(893,'Fork',892),(894,'Files',892),(895,'Beans',892),(896,'Information',892),(899,'It is scalable',898),(900,'It is micro-service ready',898),(901,'It is macro-service ready',898),(902,'Undecided',898),(905,'It is invasive',904),(906,'It simplifies Spring dependencies',904),(907,'One can build more with less code',904),(908,'It can be run straight from a command line',904),(911,'Yes',910),(912,'No',910),(913,'No Idea',910),(914,'Un Decided',910),(917,'No code generation',916),(918,'No requirement for XML configuration',916),(919,'No starter POMs',916),(920,'No need for WAR files',916);
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

-- Dump completed on 2020-06-30 19:11:42
