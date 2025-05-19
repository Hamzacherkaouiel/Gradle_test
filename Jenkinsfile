pipeline {
    agent none
    triggers {
       pollSCM('*/1 * * * *')
    }
    environment{
       DOCKER_REGISTRY = 'killerquen69'
       DOCKER_IMAGE = 'gradlespringboot'
       DOCKER_TAG = "${env.BUILD_NUMBER}"
       DOCKER_CREDS = credentials('killerquen69')

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
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
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
        stage('Packaging stage') {
            agent {
                docker {
                  image 'docker:24.0-cli'
                  args '--entrypoint="" --user root -v /var/run/docker.sock:/var/run/docker.sock' // Attention à la sécurité ici
                }
            }
            steps {
               script {
                  sh 'echo ${DOCKER_CREDS} | docker login -u ${DOCKER_REGISTRY} --password-stdin'
                  sh 'docker build -t $DOCKER_REGISTRY/$DOCKER_IMAGE:$DOCKER_TAG .'
                  sh 'docker push $DOCKER_REGISTRY/$DOCKER_IMAGE:$DOCKER_TAG'

               }


            }

        }

    }
}
