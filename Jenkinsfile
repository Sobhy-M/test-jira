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
                bat 'mvn -X -DskipTests  clean package'
            }
        }
    }
}