pipeline {
    agent any

    environment {
        AVD_NAME = "Pixel_6"
        PATH = "C:\\Ruby40-x64\\bin;${env.PATH}"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/nikiz995/android-espresso-test.git'
            }
        }

        stage('Force Clean') {
            steps {
                bat 'C:\\Ruby40-x64\\bin\\fastlane.bat android force_clean'
            }
        }

        stage('Build APKs') {
            steps {
                bat 'C:\\Ruby40-x64\\bin\\fastlane.bat android build_tests'
            }
        }

        stage('Start Emulator') {
            steps {
                bat '''
                start /B emulator -avd %AVD_NAME% -no-snapshot -no-audio -no-boot-anim
                adb wait-for-device
                timeout /t 20
                adb shell input keyevent 82
                '''
            }
        }

        stage('Run Espresso Tests and Screenshots') {
            steps {
                bat 'C:\\Ruby40-x64\\bin\\fastlane.bat android screenshots'
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