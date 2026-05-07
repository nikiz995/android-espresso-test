pipeline {
    agent any

    environment {
        AVD_NAME = "Pixel_6_API_35"
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
                bat 'bundle exec fastlane android force_clean'
            }
        }

        stage('Build APKs') {
            steps {
                bat 'bundle exec fastlane android build_tests'
            }
        }

        stage('Start Emulator') {
            steps {
                bat '''
                start /B emulator -avd %AVD_NAME% -no-snapshot -no-audio -no-boot-anim

                adb wait-for-device

                :checkboot
                for /f "tokens=*" %%i in ('adb shell getprop sys.boot_completed') do set boot=%%i
                if not "%boot%"=="1" (
                    timeout /t 5
                    goto checkboot
                )

                adb shell input keyevent 82
                '''
            }
        }

        stage('Run Espresso Tests and Screenshots') {
            steps {
                bat 'bundle exec fastlane android screenshots'
            }
        }
    }

    post {
        always {
            bat 'adb emu kill || exit /b 0'

            archiveArtifacts artifacts: 'app/build/reports/androidTests/connected/**', allowEmptyArchive: true
            archiveArtifacts artifacts: 'app/build/outputs/androidTest-results/**', allowEmptyArchive: true

            junit allowEmptyResults: true, testResults: 'app/build/outputs/androidTest-results/connected/**/*.xml'
        }
    }
}