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
                   gradle test jacocoTestReport sonar \
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
                  args '--entrypoint="" --user root -v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                        sh '''
                            docker build -t killerquen69/$DOCKER_IMAGE:$DOCKER_TAG .
                        '''
                }
        }
        stage ("Scanning Stage") {
           agent {
              docker {
               image 'aquasec/trivy'
               args '--entrypoint="" --user root -v /var/run/docker.sock:/var/run/docker.sock' // Attention à la sécurité ici

              }

           }
           steps {
              //sh 'trivy image --no-progress --exit-code 1 --severity HIGH,CRITICAL killerquen69/$DOCKER_IMAGE:$DOCKER_TAG'
              sh 'trivy image --no-progress --severity HIGH,CRITICAL killerquen69/$DOCKER_IMAGE:$DOCKER_TAG'

           }
        }
        stage ("Pushing stage ") {
          agent {
            docker {
               image 'docker:24.0-cli'
               args '--entrypoint="" --user root -v /var/run/docker.sock:/var/run/docker.sock'

            }
          }
          steps {
            withCredentials([usernamePassword(credentialsId: 'killerquen69', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                  sh '''
                     echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin

                     docker push $DOCKER_USERNAME/$DOCKER_IMAGE:$DOCKER_TAG
                     '''
            }


          }

        }
        stage ("Deploying") {
           agent any
           steps {
             withCredentials([usernamePassword(credentialsId: 'github', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {

                sh '''
                   git config --global user.email "hamza.elmalki1234@gmail.com"
                   git config --global user.name "Hamzacherkaouiel"
                   git fetch origin main
                   git checkout main
                   cd k8s
                   sed -i "s|image: killerquen69/gradlespringboot:[^ ]*|image: killerquen69/gradlespringboot:$DOCKER_TAG|g" Manifest.yml
                   cat Manifest.yml
                   git add .
                   git commit -m "update manifest"
                   git remote set-url origin https://$GIT_USERNAME:$GIT_PASSWORD@github.com/Hamzacherkaouiel/Gradle_test.git
                   git push -u origin main
                   '''

             }


           }

        }


    }
}
