pipeline {
   agent none

   environment {
       DOCKER_HOST = 'tcp://amazing_heyrovsky:2375'  // ou 'tcp://socat:2375'
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