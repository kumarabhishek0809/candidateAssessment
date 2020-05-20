http://localhost:8080/valdiateLogin?loginId=Admin&password=UEBzc3cwciQ
http://localhost:8080/candidateDetails?emailId=john.doe@gmail.com
http://localhost:8080/assessments
http://localhost:8080/registerCandidate


========================================================
Setting UP DB
 create database db_example; -- Creates the new database
 create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
 grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database


clientAssessmentPortal

 create database clientAssessmentPortal; 
 create user 'clientAssessmentPortal' identified by 'clientAssessmentPortal'; 
 grant all on clientAssessmentPortal.* to 'clientAssessmentPortal'; 


