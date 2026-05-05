@mobile @reference_android
Feature: Main screen
  As a mobile user
  I want the ReferenceAndroid app to open and respond to basic interactions
  So that I can trust the main screen is working

  @ui @smoke @regression @r1
  Scenario: Main screen renders expected content
    Given the app is launched
    Then the main screen toolbar title is displayed
    And the welcome message is displayed

  @integration @regression
  Scenario: Settings menu can be opened and selected
    Given the app is launched
    When I open the settings menu
    And I select the settings option
    Then the welcome message is displayed

  @ui @regression
  Scenario: Floating action button shows snackbar
    Given the app is launched
    When I tap the floating action button
    Then the snackbar message is displayed
