pipeline {
    agent none

    environment {
        DOCKER_HOST = 'tcp://172.17.0.1:2375' // IP de ta machine hôte ou bridge Docker (vérifie avec `docker network inspect bridge`)
    }

    triggers {
        pollSCM('*/1 * * * *')
    }

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
