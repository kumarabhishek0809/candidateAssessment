
==========================================================
************Intitial script************

#!/bin/sh

sudo yum update -y
sudo yum install git -y
sudo yum -y install java-1.8.0-openjdk-devel
sudo yum install java-1.8.0
sudo /usr/sbin/alternatives --config java

############ Gradle ##################
    
curl -s "https://get.sdkman.io" | bash 
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install gradle

############ Start Programing ########

git version
git clone https://github.com/kumarabhishek0809/candidateAssessment.git

nohup java -jar -Xmx512m -Xms256m candidate-0.0.1.jar &
###########   install mysql ##########
sudo yum update -y
sudo yum install -y https://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm
sudo yum install -y mysql-community-server

sudo service mysqld start
sudo service mysqld status
sudo grep 'temporary password' /var/log/mysqld.log

mysql -u root -p
SET PASSWORD = PASSWORD('July@2020');
create database clientAssessmentPortal; 
create user 'clientAssessmentPortal' identified by 'July@2020'; 
grant all on clientAssessmentPortal.* to 'clientAssessmentPortal';



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


Clear transactional data
SET SQL_SAFE_UPDATES = 0;
delete from clientAssessmentPortal.user_login_history;
delete from clientAssessmentPortal.user_login;
delete  from clientAssessmentPortal.candidate_assessment_result_submission;
delete from clientAssessmentPortal.candidate_assessment;
delete from clientAssessmentPortal.candidate;
commit;
SET SQL_SAFE_UPDATES = 1;
