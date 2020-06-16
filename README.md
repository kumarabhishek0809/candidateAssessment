
 Java 
================================================= 
http://localhost:8080/valdiateLogin?loginId=Admin&password=UEBzc3cwciQ
=================================================
http://localhost:8080/candidateDetail?emailId=john.doe@gmail.com
=================================================
http://localhost:8080/assessments
=================================================

http://localhost:8080/candidateDetails

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
	  emailAddress" : "john.doe@gmail.com"
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

http://localhost:8080/submitAssessment?emailId=kumar.abhishek1@gmail.com

{
 "assessmentId": 1,
 "questionAnswerReq"  : [
	{"questionId" : 1 , "optionId" : 1},
	{"questionId" : 2 , "optionId" : 1}
 ]
}

============================EXAM SUBMIT LINK =======
http://18.191.46.80:8080/assessment?emailId=KUMAR.ABHISHEK1@synechron.com&assessmentId=3
================================================

For SQL Please follow folder resource/sql inside src/main

==========================================================
http://18.191.46.80:8080/candidateAssessmentCount

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

create database clientAssessmentPortal; 

create user 'clientAssessmentPortal' identified by 'July@2020'; 

grant all on clientAssessmentPortal.* to 'clientAssessmentPortal';

############ Gradle ##################
    
curl -s "https://get.sdkman.io" | bash 
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install gradle

############ Start Programing ########

git version
git clone https://github.com/kumarabhishek0809/candidateAssessment.git

nohup java -jar -Xmx512m -Xms256m candidate-0.0.1.jar &


Question Set
https://www.sanfoundry.com/java-mcqs-integer-floating-data-types/
https://angular-quiz.surge.sh/ 
 
 