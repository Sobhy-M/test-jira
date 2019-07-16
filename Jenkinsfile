pipeline {
    agent any
    tools { 
         
        jdk 'jdk8' 
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
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