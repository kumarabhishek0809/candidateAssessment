CREATE DATABASE  IF NOT EXISTS `clientAssessmentPortal` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `clientAssessmentPortal`;

DROP TABLE IF EXISTS `assessment_questions`;
CREATE TABLE `assessment_questions` (
  `assessment_id` int(11) NOT NULL,
  `questions_id` int(11) NOT NULL,
  KEY `FKaf20224e4jochperimgguvnuw` (`assessment_id`),
  CONSTRAINT `FKaf20224e4jochperimgguvnuw` FOREIGN KEY (`assessment_id`) REFERENCES `assessment` (`id`),
  CONSTRAINT `FKbsio8xaewqfgk6w24cydb5be8` FOREIGN KEY (`questions_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `assessment_questions` WRITE;
INSERT INTO `assessment_questions` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(2,6),(2,7),(2,8),(2,9),(2,10),(3,11),(3,12),(3,13),(3,14),(3,15);
UNLOCK TABLES;
