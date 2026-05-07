pipeline {
    agent any

    environment {
        GRADLE_USER_HOME = "${WORKSPACE}\\.gradle"
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Code already checked out from Git SCM'
            }
        }

        stage('Clean') {
            steps {
                bat 'gradlew.bat clean'
            }
        }

        stage('Build APKs') {
            steps {
                bat 'gradlew.bat --refresh-dependencies assembleDebug assembleDebugAndroidTest'
            }
        }

        stage('Check Emulator') {
            steps {
                bat '''
                adb devices
                adb wait-for-device
                adb shell input keyevent 3
                '''
            }
        }

        stage('Run Espresso Tests') {
            steps {
                bat 'gradlew.bat connectedAndroidTest'
            }
        }
    }

    post {
        always {
            bat 'adb devices'

            archiveArtifacts artifacts: 'app/build/reports/androidTests/connected/**', allowEmptyArchive: true
            archiveArtifacts artifacts: 'app/build/outputs/androidTest-results/**', allowEmptyArchive: true

            junit allowEmptyResults: true,
                  testResults: 'app/build/outputs/androidTest-results/connected/**/*.xml'
        }
    }
}