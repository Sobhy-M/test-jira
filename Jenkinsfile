pipeline {
    agent any
    tools { 
         
        jdk 'jdk8' 
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "M2_HOME = /mnt/c/users/Mostafa.sobhy/.m2"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn -X -DskipTests -Dmaven.repo.local=.m2 clean package'
            }
        }
    }
}