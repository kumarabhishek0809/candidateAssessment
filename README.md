
 Java 
================================================= 
http://localhost:8080/valdiateLogin?loginId=Admin&password=UEBzc3cwciQ
=================================================
http://localhost:8080/candidateDetails?emailId=john.doe@gmail.com
=================================================
http://localhost:8080/assessments
=================================================

http://localhost:8080/registerCandidate

{
  "firstName" : "Sample First Name",
  "lastName" : "Sample Last Name",
  "emailAddress" : "john.doe2@gmail.com",
  "dateOfBirth" : "1982-07-20",
  "countryCode" : "971",
  "mobileNo" : "523083264",
  "loginId": "john.doe2@gmail.com"
}

=================================================

http://localhost:8080/registerCandidateScheduleAssessment


{
	 "candidate" : {
	  "firstName" : "Sample First Name",
	  "lastName" : "Sample Last Name",
	  "emailAddress" : "john.doe@gmail.com",
	  "dateOfBirth" : "1982-07-20",
	  "countryCode" : "971",
	  "mobileNo" : "523083264"
	},
	"candidateAssessment" : {
		"assessment" : {
			"id" : "01"
		}
	}
}

===============================================

http://localhost:8080/sendmail

{
	"toEmail" : "kumar.abhishek0809@gmail.com",
	"subject" : "Good Luck You have cleared the test",
	"message" : "Our Hiring Manager will getback to you."
}
===============================================

http://localhost:8080/sendmailAttachment

{
	"toEmail" : "kumar.abhishek0809@gmail.com",
	"subject" : "Good Luck You have cleared the test",
	"message" : "Our Hiring Manager will getback to you.",
	"pathToAttachment" : "/home/ec2-user/candidateAssessment/assessment.csv"
}

=================================================

http://localhost:8080/processAssessmentForCandidate 

{
   "emailAddress" : "john.doe@gmail.com",
    "candidateAssessments" : [{"id" : "15", "status" : true},{"id" : "14", "status" : true},{"id" : "12", "status" : false},{"id" : "13", "status" : false}]   
}

================================================

http://localhost:8080/submitAssessment?emailId=kumar.abhishek1@synechron.com

{
 "assessmentId": 1,
 "questionAnswerReq"  : [
	{"questionId" : 1 , "optionId" : 1},
	{"questionId" : 2 , "optionId" : 1}
 ]
}

==============================================

http://localhost:8080/assessment/1?emailId=john.doe@gmail.com
================================================

 
INSERT INTO `clientassessmentportal`.`answer` (`id`, `description`, `header`) VALUES ('1', 'Ans Dec 1', 'Ans Hed 1');
INSERT INTO `clientassessmentportal`.`answer` (`id`, `description`, `header`) VALUES ('2', 'Ans Dec 2', 'Ans Hed 2');


INSERT INTO `clientassessmentportal`.`options` (`id`, `description`) VALUES ('1', 'JLT');
INSERT INTO `clientassessmentportal`.`options` (`id`, `description`) VALUES ('2', 'JBR');
INSERT INTO `clientassessmentportal`.`options` (`id`, `description`) VALUES ('3', 'Media City');
INSERT INTO `clientassessmentportal`.`options` (`id`, `description`) VALUES ('4', 'Business Bay');
INSERT INTO `clientassessmentportal`.`options` (`id`, `description`) VALUES ('5', 'Meydan ');
INSERT INTO `clientassessmentportal`.`options` (`id`, `description`) VALUES ('6', 'None Of These');

INSERT INTO `clientAssessmentPortal`.`assessment` (`id`, `duration`, `name`, `technology`) VALUES ('1', '30', 'JAVA 101', 'JAVA');
INSERT INTO `clientAssessmentPortal`.`assessment` (`id`, `duration`, `name`, `technology`) VALUES ('2', '40', 'SCALA', 'SCALA');
INSERT INTO `clientAssessmentPortal`.`assessment` (`id`, `duration`, `name`, `technology`) VALUES ('3', '50 ', 'SPRING BOOT', 'JAVA');

INSERT INTO `clientAssessmentPortal`.`question` (`id`, `description`, `header`, `answer_id`) VALUES ('1', 'QUES DESC 1', 'QUES HEAD 1', '1');
INSERT INTO `clientAssessmentPortal`.`question` (`id`, `description`, `header`, `answer_id`) VALUES ('2', 'QUEST DESC 2', 'QUES HEAD 2', '2');

drop table clientAssessmentPortal.answer_options;

CREATE TABLE clientAssessmentPortal.answer_options (
  `answer_id` int(11) NOT NULL,
  `options_id` int(11) NOT NULL,
  PRIMARY KEY (`answer_id`,`options_id`),
  CONSTRAINT `FK2bn9ytu1jr6fiml7xncpefjnb` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`),
  CONSTRAINT `FK65v596w6gy9fp02qomgiro2mh` FOREIGN KEY (`options_id`) REFERENCES `options` (`id`)
);

INSERT INTO `clientAssessmentPortal`.`answer_options` (`answer_id`, `options_id`) VALUES ('1', '2');
INSERT INTO `clientAssessmentPortal`.`answer_options` (`answer_id`, `options_id`) VALUES ('1', '3');
INSERT INTO `clientAssessmentPortal`.`answer_options` (`answer_id`, `options_id`) VALUES ('1', '4');
INSERT INTO `clientAssessmentPortal`.`answer_options` (`answer_id`, `options_id`) VALUES ('2', '1');
INSERT INTO `clientAssessmentPortal`.`answer_options` (`answer_id`, `options_id`) VALUES ('2', '2');
INSERT INTO `clientAssessmentPortal`.`answer_options` (`answer_id`, `options_id`) VALUES ('2', '3');
COMMIT;

drop table clientAssessmentPortal.assessment_questions;

CREATE TABLE clientAssessmentPortal.assessment_questions (
  `assessment_id` int(11) NOT NULL,
  `questions_id` int(11) NOT NULL,
  PRIMARY KEY (`assessment_id`,`questions_id`),
   CONSTRAINT `FKaf20224e4jochperimgguvnuw` FOREIGN KEY (`assessment_id`) REFERENCES `assessment` (`id`),
  CONSTRAINT `FKbsio8xaewqfgk6w24cydb5be8` FOREIGN KEY (`questions_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `clientAssessmentPortal`.`assessment_questions` (`assessment_id`, `questions_id`) VALUES ('1', '1');
INSERT INTO `clientAssessmentPortal`.`assessment_questions` (`assessment_id`, `questions_id`) VALUES ('1', '2');
INSERT INTO `clientAssessmentPortal`.`assessment_questions` (`assessment_id`, `questions_id`) VALUES ('2', '2');
INSERT INTO `clientAssessmentPortal`.`assessment_questions` (`assessment_id`, `questions_id`) VALUES ('2', '1');
INSERT INTO `clientAssessmentPortal`.`assessment_questions` (`assessment_id`, `questions_id`) VALUES ('3', '2');
INSERT INTO `clientAssessmentPortal`.`assessment_questions` (`assessment_id`, `questions_id`) VALUES ('3', '1');
COMMIT;

INSERT INTO `clientassessmentportal`.`question_answer` (`id`, `marks`, `assessment_id`, `options_id`, `question_id`) VALUES ('1', '5', '1', '2', '1');
INSERT INTO `clientassessmentportal`.`question_answer` (`id`, `marks`, `assessment_id`, `options_id`, `question_id`) VALUES ('2', '1', '1', '1', '2');
INSERT INTO `clientassessmentportal`.`question_answer` (`id`, `marks`, `assessment_id`, `options_id`, `question_id`) VALUES ('3', '4', '2', '2', '1');
INSERT INTO `clientassessmentportal`.`question_answer` (`id`, `marks`, `assessment_id`, `options_id`, `question_id`) VALUES ('4', '3', '2', '3', '1');
COMMIT;

=======================================================

SELECT * FROM clientAssessmentPortal.assessment;
SELECT * FROM clientAssessmentPortal.candidate_assessment;
SELECT * FROM clientAssessmentPortal.candidate;
SELECT * FROM clientAssessmentPortal.question;
SELECT * FROM clientAssessmentPortal.answer_option;
SELECT * FROM clientAssessmentPortal.answer;
SELECT * FROM clientAssessmentPortal.answer_answer_options;
SELECT * FROM clientAssessmentPortal.question;
SELECT * FROM clientassessmentportal.assessment_questions;

==========================================================

************Intitial script************

#!/bin/sh

sudo yum update -y
sudo yum install git -y

sudo yum install -y https://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm
sudo yum install -y mysql-community-server

sudo yum -y install java-1.8.0-openjdk-devel

###########   install mysql ##########

sudo service mysqld start
sudo service mysqld status
sudo grep 'temporary password' /var/log/mysqld.log
====
mysql -u root -p
SET PASSWORD = PASSWORD('July@2020');

############ Gradle ##################

curl -s "https://get.sdkman.io" | bash 
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install gradle

############ Start Programing ########

git version
git clone https://github.com/kumarabhishek0809/candidateAssessment.git

############ Schema setup ############
Setting UP DB

 create database clientAssessmentPortal; 
 create user 'clientAssessmentPortal' identified by 'July@2020'; 
 grant all on clientAssessmentPortal.* to 'clientAssessmentPortal';
 