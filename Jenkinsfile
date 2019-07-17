pipeline {
    agent any
    tools { 
         
        jdk 'jdk8' 
		
    }
	stages {	




        stage ('Initialize') {
            steps {
               bat 'cd ./Topup_18-06-2015_1'
            }
        }
		
		


			stage ('Build') {
			steps {
	        withMaven(mavenLocalRepo: '.repository') {
          
             
				bat 'cd ./Topup_18-06-2015_1'
                bat 'mvn -f "./Topup_18-06-2015_1/pom.xml" -X -DskipTests   clean package'
            }
        }
		}
		

    
	
	}
	}
