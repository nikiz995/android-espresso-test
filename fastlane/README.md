fastlane documentation
----

# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```sh
xcode-select --install
```

For _fastlane_ installation instructions, see [Installing _fastlane_](https://docs.fastlane.tools/#installing-fastlane)

# Available Actions

## Android

### android build_tests

```sh
[bundle exec] fastlane android build_tests
```

Build the debug app and Android test APKs

### android test

```sh
[bundle exec] fastlane android test
```

Run all Cucumber Espresso scenarios on the connected device

### android screenshots

```sh
[bundle exec] fastlane android screenshots
```

Run Android tests and pull captured screenshots into the test report folder

### android clean

```sh
[bundle exec] fastlane android clean
```

Remove generated build outputs, test reports, and screenshots

### android report

```sh
[bundle exec] fastlane android report
```

Print the Android test report location

### android test_tags

```sh
[bundle exec] fastlane android test_tags
```

Run only Cucumber scenarios tagged name

### android force_clean

```sh
[bundle exec] fastlane android force_clean
```

Force clean build folders on Windows

----

This README.md is auto-generated and will be re-generated every time [_fastlane_](https://fastlane.tools) is run.

More information about _fastlane_ can be found on [fastlane.tools](https://fastlane.tools).

The documentation of _fastlane_ can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
