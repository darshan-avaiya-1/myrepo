pipeline {
    agent any

    parameters {
        string(
            name: 'GITHUB_CREDENTIAL_ID',
            defaultValue: 'mycredentialid1',
            description: 'Jenkins credential ID used to access GitHub'
        )

        choice(
            name: 'BROWSER',
            choices: [
                'chrome',
                'firefox',
                'edge'
            ],
            description: 'Select browser for automated tests'
        )
    }

    stages {

        stage('Deploy to QA') {
            steps {
                echo 'Deploying application to QA...'
                echo 'Application deployed to QA'
            }
        }

        stage('Automated Tests') {
            steps {
                echo 'Checking out automation project...'

                git(
                    branch: 'main',
                    credentialsId: params.GITHUB_CREDENTIAL_ID,
                    url: 'https://github.com/darshan-avaiya-1/myrepo.git'
                )

                echo "Running automated tests on ${params.BROWSER}..."

                bat "mvn clean test -Dheadless=false -Dbrowser=${params.BROWSER}"
            }

            post {
                always {
                    echo 'Publishing test results...'

                    junit(
                        allowEmptyResults: true,
                        testResults: '**/target/surefire-reports/*.xml'
                    )
                }

                success {
                    echo 'Automated tests PASSED'
                }

                failure {
                    echo 'Automated tests FAILED'
                }
            }
        }

        stage('Deploy to Staging') {
            steps {
                echo 'Automated tests passed.'
                echo 'Deploying application to Staging...'
                echo 'Application deployed to Staging'
            }
        }
    }

    post {
        success {
            echo 'Entire pipeline completed successfully.'
        }

        failure {
            echo 'Pipeline failed. Staging deployment was not performed.'
        }

        always {
            echo 'Pipeline execution completed.'
        }
    }
}
