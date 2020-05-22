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
	"pathToAttachment" : "C:/CandidateAssessment/Plan.xlsx"
}
==========================================================
http://localhost:8080/processAssessmentForCandidate 
{
   "emailAddress" : "john.doe@gmail.com",
    "candidateAssessments" : [{"id" : "15", "status" : true},{"id" : "14", "status" : true},{"id" : "12", "status" : false},{"id" : "13", "status" : false}]   
}
==========================================================

Setting UP DB

 create database clientAssessmentPortal; 
 create user 'clientAssessmentPortal' identified by 'July@2020'; 
 grant all on clientAssessmentPortal.* to 'clientAssessmentPortal'; 


