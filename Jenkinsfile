pipeline {
    agent none

    stages {
        stage('Building stage') {
            agent {
                docker {
                    image 'gradle:8.14.0-jdk21'
                }
            }
            steps {
                sh 'gradle build -x test'
            }
        }
        stage('Testing stage') {
            agent {
                docker {
                    image 'gradle:8.14.0-jdk21'
                }
            }
            steps {
                sh 'gradle test'
            }
        }
    }
}
