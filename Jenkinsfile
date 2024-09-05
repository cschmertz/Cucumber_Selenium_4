pipeline {
    agent any
    tools {
        maven 'Maven 3.8.1'
    }
    stages {
        stage('Checkout Repos') {
            steps {
                // UI Layer Repository
                checkout scm

                // API Layer Repository
                dir('ApiLayer') {
                    git url: 'https://github.com/cschmertz/ApiLayer', branch: 'main'
                }

                // Database Layer Repository
                dir('DatabaseLayer') {
                    git url: 'https://github.com/cschmertz/DatabaseLayer', branch: 'main'
                }
            }
        }

        stage('Build and Test UI Layer') {
            steps {
                // Continue running tests even if some fail
                sh 'mvn clean test -Dmaven.test.failure.ignore=true'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build and Test API Layer') {
            steps {
                dir('ApiLayer') {
                    // Continue running tests even if some fail
                    sh 'mvn clean test -Dmaven.test.failure.ignore=true'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build and Test Database Layer') {
            steps {
                dir('DatabaseLayer') {
                    // Continue running tests even if some fail
                    sh 'mvn clean test -Dmaven.test.failure.ignore=true'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
    }

    post {
        always {
            // Clean up workspace after build
            cleanWs()
        }
    }
}
