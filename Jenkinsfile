pipeline {
    agent any

    environment {
        AVD_NAME = "Pixel_6"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/nikiz995/android-espresso-test.git'
            }
        }

        stage('Clean') {
            steps {
                bat 'gradlew.bat clean'
            }
        }

        stage('Build APKs') {
            steps {
                bat 'gradlew.bat assembleDebug assembleDebugAndroidTest'
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