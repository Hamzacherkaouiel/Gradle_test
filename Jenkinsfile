pipeline {
    agent none
    triggers {
       pollSCM('*/1 * * * *')
    }
    environment{
       DOCKER_IMAGE = 'gradlespringboot'
       DOCKER_TAG = "${env.BUILD_NUMBER}"

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

        stage("Sonar Qube") {

            agent {
                docker {
                    image 'gradle:8.14.0-jdk21'
                    args '--network jenkins'

                }
            }
            steps {
            withSonarQubeEnv( installationName: 'sq1') {
                sh '''
                   gradle build jacocoTestReport -x test sonar \
                          -Dsonar.projectKey=jenkins \
                          -Dsonar.projectName='jenkins'
                   '''

            }


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
                    withCredentials([usernamePassword(credentialsId: 'killerquen69', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh '''
                            echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
                            docker build -t $DOCKER_USERNAME/$DOCKER_IMAGE:$DOCKER_TAG .
                            docker push $DOCKER_USERNAME/$DOCKER_IMAGE:$DOCKER_TAG
                        '''
                    }
                }

        }


    }
}
