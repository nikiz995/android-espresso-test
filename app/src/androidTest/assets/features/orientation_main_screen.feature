@mobile @reference_android @orientation
Feature: Main screen orientation scenarios
  As a mobile user
  I want the app to handle orientation changes correctly
  So that the main screen remains stable after rotation

  @ui @orientation @landscape @regression
  Scenario: Rotating screen to landscape keeps the main screen fields visible
    Given the app is launched
    When I rotate the device to landscape
    Then the main screen toolbar title is displayed
    And the welcome message is displayed

  @ui @orientation @portrait @regression
  Scenario: Rotating screen to portrait keeps the main screen fields visible
    Given the app is launched
    When I rotate the device to portrait
    Then the main screen toolbar title is displayed
    And the welcome message is displayed