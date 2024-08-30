pipeline {
    agent any

    stages {
        stage('Checkout Repos') {
            steps {
                // UI Layer Repository
                git url: 'https://github.com/cschmertz/Cucumber_Selenium', branch: 'main'

                // API Layer Repository
                dir('api-layer') {
                    git url: 'https://github.com/cschmertz/ApiLayer', branch: 'main'
                }

                // Database Layer Repository
                dir('database-layer') {
                    git url: 'https://github.com/cschmertz/DatabaseLayer', branch: 'main'
                }
            }
        }

        stage('Build and Test UI Layer') {
            steps {
                dir('Cucumber_Selenium') {
                    sh 'mvn clean install'  // This builds and runs tests for the UI layer
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
                dir('api-layer') {
                    sh 'mvn clean install'  // This builds and runs tests for the API layer
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
                dir('database-layer') {
                    sh 'mvn clean install'  // This builds and runs tests for the Database layer
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
