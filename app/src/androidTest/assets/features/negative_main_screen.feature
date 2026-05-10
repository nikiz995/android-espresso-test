@mobile @reference_android @negative
Feature: Main screen negative scenarios
  As a mobile user
  I want the app to remain stable during unexpected or repeated interactions
  So that basic error-prone flows do not break the main screen

  @ui @negative @regression
  Scenario: Opening settings menu and pressing back keeps the main screen visible
    Given the app is launched
    When I open the settings menu
    And I press back
    Then the welcome message is displayed

  @ui @negative @regression
  Scenario: Tapping floating action button repeatedly keeps the app stable
    Given the app is launched
    When I tap the floating action button 3 times
    Then the snackbar message is displayed
    And the welcome message is displayed

  @exception @negative @regression
  Scenario: Repeated mixed interactions do not cause an unhandled exception
    Given the app is launched
    When I perform repeated mixed interactions
    Then the welcome message is displayed

