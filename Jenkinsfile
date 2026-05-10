pipeline {

    agent any

    environment {
        GRADLE_USER_HOME = "${WORKSPACE}\\.gradle"
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Using repository configured in Jenkins SCM'
                checkout scm
            }
        }

        stage('Clean Workspace') {
            steps {
                bat 'gradlew.bat clean'
            }
        }

        stage('Build Debug APKs') {
            steps {
                bat '''
                gradlew.bat --refresh-dependencies ^
                assembleDebug ^
                assembleDebugAndroidTest
                '''
            }
        }

        stage('Verify Connected Device') {
            steps {
                bat '''
                adb devices
                adb wait-for-device
                adb shell input keyevent 3
                '''
            }
        }

        stage('Execute Espresso Tests') {
            steps {
                bat 'gradlew.bat connectedAndroidTest'
            }
        }

        stage('Pull Test Screenshots') {
            steps {
                bat '''
                if not exist app\\build\\reports\\androidTests\\connected\\screenshots (
                    mkdir app\\build\\reports\\androidTests\\connected\\screenshots
                )

                adb pull /sdcard/Download/referenceandroid-test-screenshots ^
                app/build/reports/androidTests/connected/screenshots
                '''
            }
        }
    }

    post {

        always {

            bat 'adb devices'

            archiveArtifacts(
                artifacts: 'app/build/reports/androidTests/connected/**',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'app/build/outputs/androidTest-results/**',
                allowEmptyArchive: true
            )

            junit(
                allowEmptyResults: true,
                testResults: 'app/build/outputs/androidTest-results/connected/**/*.xml'
            )
        }

        success {
            echo 'Android Espresso execution completed successfully.'
        }

        failure {
            echo 'Android Espresso execution failed.'
        }
    }
}