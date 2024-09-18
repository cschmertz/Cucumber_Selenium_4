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
                browserstack(credentialsId: '64bac0ff-5ccd-44ce-a197-6656c4374c85') {
                    // Run tests using Maven
                    sh 'mvn clean test -Dmaven.test.failure.ignore=true || (echo "UI Layer tests failed"; exit 1)'
                }
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
                    sh 'mvn clean test -Dmaven.test.failure.ignore=true || (echo "API Layer tests failed"; exit 1)'
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
                    sh 'mvn clean test -Dmaven.test.failure.ignore=true || (echo "Database Layer tests failed"; exit 1)'
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
            browserStackReportPublisher 'automate'
            cleanWs()
        }
        failure {
            echo 'The pipeline failed. Please check the console output for details.'
        }
    }
}
