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
				bat 'cd ./Topup_18-06-2015_1'
                bat 'mvn ./Topup_18-06-2015_1 -X -DskipTests  clean package'
            }
        }
    }
}