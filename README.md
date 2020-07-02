
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
http://localhost:8080/assessment?emailId=KUMAR.ABHISHEK1@synechron.com&assessmentId=3
================================================
For SQL Please follow folder resource/sql inside src/main
==========================================================
http://localhost:8080/candidateAssessmentCount
==========================================================
http://localhost:8080/question
{
    "header": "An expression involving byte, int, and literal numbers is promoted to which of these",
    "answerId": 1,
    "options": [
        {
            "description": "int"
        },
        {
            "description": "long"
        },
        {
            "description": "byte"
        },
        {
            "description": "float"
        }
    ],
    "technology": "Java",
    "questionTypeId": 1
}
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

#Download Candidate Details
http://localhost/:8080/download/candidateDetails
 
Adding question.

curl --location --request POST 'http://localhost//question' \
--header 'Content-Type: application/json' \
--data-raw '{
    "header": "Which of the following is not a core AngularJS directive",
    "answerId": 1,
    "options": [
        {
            "description": "ng-app"
        },
        {
            "description": "ng-model"
        },
        {
            "description": "ng-bind"
        },
        {
            "description": "ng-state"
        }
    ],
    "technology": "Angular",
    "questionTypeId": 1
}'

Add Question to Assessment
http://localhost:8080/assessment
{
	"assessmentId" : 1,
	"questionIds": [435]
}

http://localhost:8080/evaluation
Add Evaluation record for Question
{
    "assessmentId" : 1,
    "questionId" : 430,
    "optionId" : 431 ,
    "marks" : 5
}


http://localhost:8080/questionOptionsAssessment
{
    "header": "An interface with no fields or methods is known as a ______",
    "answerId": 1,
    "options": [
        {
            "description": "Runnable Interface"
        },
        {
            "description": "Marker Interface",
			"answerOption" : true
        },
        {
            "description": "Abstract Interface"
        },
        {
            "description": "CharSequence Interface",
			"answerOption" : false
        }
    ],
    "technology": "Java",
    "questionTypeId": 1,
	"assessmentIds" : [1],
	"marks" : 5
}



select q.header,op.description from clientAssessmentPortal.question q, clientAssessmentPortal.options op , 
clientAssessmentPortal.evaluation_question_answer qa,clientAssessmentPortal.assessment_questions aq
where 
qa.question_id = q.id
and aq.assessment_id = 4
and aq.questions_id = q.id
and qa.options_id = op.id
and qa.question_id = aq.questions_id
and q.id = op.question_id
order by q.id asc