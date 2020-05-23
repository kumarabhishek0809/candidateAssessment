Setting UP DB

 create database clientAssessmentPortal; 
 create user 'clientAssessmentPortal' identified by 'July@2020'; 
 grant all on clientAssessmentPortal.* to 'clientAssessmentPortal';
 
======================================================== 
http://localhost:8080/valdiateLogin?loginId=Admin&password=UEBzc3cwciQ
========================================================
http://localhost:8080/candidateDetails?emailId=john.doe@gmail.com
========================================================
http://localhost:8080/assessments
========================================================

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

========================================================

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

========================================================

http://localhost:8080/sendmail
{
	"toEmail" : "kumar.abhishek0809@gmail.com",
	"subject" : "Good Luck You have cleared the test",
	"message" : "Our Hiring Manager will getback to you."
}
========================================================

http://localhost:8080/sendmailAttachment
{
	"toEmail" : "kumar.abhishek0809@gmail.com",
	"subject" : "Good Luck You have cleared the test",
	"message" : "Our Hiring Manager will getback to you.",
	"pathToAttachment" : "/home/ec2-user/candidateAssessment/assessment.csv"
}

==========================================================

http://localhost:8080/processAssessmentForCandidate 
{
   "emailAddress" : "john.doe@gmail.com",
    "candidateAssessments" : [{"id" : "15", "status" : true},{"id" : "14", "status" : true},{"id" : "12", "status" : false},{"id" : "13", "status" : false}]   
}

==========================================================

http://localhost:8080/submitAssessment?emailId=john.doe@gmail.com
==========================================================

{
 "assessmentId": 1,
 "questionAnswers"  : [
	{"questionId" : 1 , "answerId" : 1}
 ]
}

==========================================================

http://localhost:8080/assessment/1?emailId=john.doe@gmail.com
==========================================================

INSERT INTO `clientAssessmentPortal`.`answer_option` (`id`, `description`) VALUES ('1', 'Dubai Media City');
INSERT INTO `clientAssessmentPortal`.`answer_option` (`id`, `description`) VALUES ('2', 'Dubai Business Bay');
INSERT INTO `clientAssessmentPortal`.`answer_option` (`id`, `description`) VALUES ('3', 'Dubai JBR');

INSERT INTO `clientAssessmentPortal`.`answer` (`id`, `description`, `header`) VALUES ('1', 'All answers are mandatory', 'Please read the answer carefuly');

INSERT INTO `clientAssessmentPortal`.`answer_answer_options` (`answer_id`, `answer_options_id`) VALUES ('1', '1');
INSERT INTO `clientAssessmentPortal`.`answer_answer_options` (`answer_id`, `answer_options_id`) VALUES ('1', '2');
INSERT INTO `clientAssessmentPortal`.`answer_answer_options` (`answer_id`, `answer_options_id`) VALUES ('1', '3');


INSERT INTO `clientAssessmentPortal`.`question` (`id`, `description`, `header`, `answer_id`) VALUES ('1', 'All Questions Are Mandatory', 'Please attmept Question carefully', '1');
INSERT INTO `clientAssessmentPortal`.`assessment_questions` (`assessment_id`, `questions_id`) VALUES ('1', '1');

====================================================================
SELECT * FROM clientAssessmentPortal.assessment;
SELECT * FROM clientAssessmentPortal.candidate_assessment;
SELECT * FROM clientAssessmentPortal.candidate;
SELECT * FROM clientAssessmentPortal.question;
SELECT * FROM clientAssessmentPortal.answer_option;
SELECT * FROM clientAssessmentPortal.answer;
SELECT * FROM clientAssessmentPortal.answer_answer_options;
SELECT * FROM clientAssessmentPortal.question;
SELECT * FROM clientassessmentportal.assessment_questions;

=====================================================================

Intitial script
#!/bin/sh

sudo yum update -y
sudo yum install git -y

sudo yum install -y https://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm
sudo yum install -y mysql-community-server

sudo yum -y install java-1.8.0-openjdk-devel

#############install mysql #############################
sudo service mysqld start
sudo service mysqld status
sudo grep 'temporary password' /var/log/mysqld.log
====
mysql -u root -p
SET PASSWORD = PASSWORD('July@2020');
############ Gradle ####################################
curl -s "https://get.sdkman.io" | bash 
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install gradle

############ Start Programing ##########################
git version
git clone https://github.com/kumarabhishek0809/candidateAssessment.git