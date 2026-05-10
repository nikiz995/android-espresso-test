# ReferenceAndroid Test Project

This is a small Android test automation project for the ReferenceAndroid app.

The app itself is simple: it opens a main screen, shows a toolbar title, displays a welcome message, has a settings menu, and has a floating action button that shows a snackbar.

The main goal of this project is to use a native Android app using Espresso, Cucumber, Gradle, and Fastlane.

## What Is Included

- Native Android app written in Kotlin
- Espresso tests for the main screen
- Cucumber feature files for readable test scenarios
- Page object style screen class for reusable actions and checks
- Screenshot capture during test runs
- Fastlane commands for easier build and test execution
- Jenkins CI pipeline integration


## Test Coverage

The tests cover the basic user flows in the app:

- App launches successfully
- Toolbar title is visible
- Welcome message is visible
- Settings menu can be opened
- Floating action button shows the snackbar
- Some repeated and negative interactions keep the app stable


## Project Structure

```text
app/src/androidTest/

├── assets/
│    └── features/
│         ├── main_screen.feature
│         └── negative_main_screen.feature
│
├── java/com/abnamro/apps/referenceandroid/
│
│    ├── screens/
│    │     └── MainScreen.kt
│    │
│    ├── steps/
│    │     └── MainScreenSteps.kt
│    │
│    ├── support/
│    │     ├── BasePage.kt
│    │     ├── BaseTest.kt
│    │     ├── EspressoIdlingResource.kt
│    │     └── ScreenshotHelper.kt
│    │
│    └── runner/
│          ├── CucumberTestRunner.kt
│          └── MainScreenActivityScenarioTest.kt
│
├── fastlane/
│    └── Fastfile
│
└── Jenkinsfile

## Running The Tests

Make sure an emulator or Android device is connected first:

```bash
adb devices
```

Run all connected Android tests:

```bash
./gradlew connectedAndroidTest
```

On Windows:

```powershell
.\gradlew.bat connectedAndroidTest
```

Run the Fastlane test lane:

```bash
bundle exec fastlane android test
```

Run the tagged Cucumber lane:

```bash
bundle exec fastlane android test_tags
```

## Switching Between Cucumber And JUnit Tests

For Cucumber tests, use this runner in `app/build.gradle`:

```gradle
testInstrumentationRunner 'io.cucumber.android.runner.CucumberAndroidJUnitRunner'
```

For the normal Espresso JUnit test class, use:

```gradle
testInstrumentationRunner 'androidx.test.runner.AndroidJUnitRunner'
```



## Reports And Screenshots

After a test run, the report is generated here:

```text
app/build/reports/androidTests/connected/index.html
```

Screenshots are captured on the device and copied into the report folder:

```text
app/build/reports/androidTests/connected/screenshots/
```


# Remove old screenshots from device
adb shell rm -rf /sdcard/Download/referenceandroid-test-screenshots

# Keep screen awake while charging (USB/AC)
adb shell settings put global stay_on_while_plugged_in 3

# Prevent device from sleeping during test execution
adb shell svc power stayon true