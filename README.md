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
	  "emailAddress" : "john.doe2@gmail.com",
	  "dateOfBirth" : "1982-07-20",
	  "countryCode" : "971",
	  "mobileNo" : "523083264",
	  "loginId": "john.doe2@gmail.com"
	},
	"candidateAssessment" : {
		"assessment" : {
			"id" : "01"
		}
	}
}
========================================================
http://localhost:8080/sendmail

========================================================
Setting UP DB
 create database db_example; -- Creates the new database
 create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
 grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database


clientAssessmentPortal

 create database clientAssessmentPortal; 
 create user 'clientAssessmentPortal' identified by 'clientAssessmentPortal'; 
 grant all on clientAssessmentPortal.* to 'clientAssessmentPortal'; 


