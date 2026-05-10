package com.abnamro.apps.referenceandroid.steps

import com.abnamro.apps.referenceandroid.screens.MainScreen
import com.abnamro.apps.referenceandroid.support.BaseTest
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

/**
 * Cucumber step definitions.
 *
 * Responsibilities:
 * - Scenario setup/teardown
 * - Call Page Object methods
 * - Keep step definitions thin and readable
 */
class MainScreenSteps : BaseTest() {

    private val mainScreen = MainScreen()

    @Before
    fun beforeScenario() {
        beforeScenarioSetup()
    }

    @After
    fun afterScenario() {
        afterScenarioTearDown()
    }

    @Given("the app is launched")
    fun theAppIsLaunched() {
        launchApplication()
        mainScreen.verifyToolbarTitleIsDisplayed().verifyWelcomeMessageIsDisplayed()
    }

    @When("I open the settings menu")
    fun iOpenTheSettingsMenu() {
        mainScreen.openSettingsMenu()
    }

    @When("I select the settings option")
    fun iSelectTheSettingsOption() {
        mainScreen.selectSettings()
    }

    @When("I tap the floating action button")
    fun iTapTheFloatingActionButton() {
        mainScreen.tapFloatingActionButton()
    }

    @When("I tap the floating action button {int} times")
    fun iTapTheFloatingActionButtonTimes(times: Int) {
        mainScreen.tapFloatingActionButton(times)
    }

    @When("I press back")
    fun iPressBack() {
        mainScreen.pressDeviceBack()
    }

    @When("I perform repeated mixed interactions")
    fun iPerformRepeatedMixedInteractions() {
        mainScreen.performRepeatedMixedInteractions()
    }

    @When("I rotate the device to landscape")
    fun iRotateTheDeviceToLandscape() {
        rotateDeviceToLandscape()
        mainScreen.verifyMainScreenFieldsAfterRotation()
    }

    @When("I rotate the device to portrait")
    fun iRotateTheDeviceToPortrait() {
        rotateDeviceToPortrait()
        mainScreen.verifyMainScreenFieldsAfterRotation()
    }

    @Then("the main screen toolbar title is displayed")
    fun theToolbarTitleIsDisplayed() {
        mainScreen.verifyToolbarTitleIsDisplayed()
    }

    @Then("the welcome message is displayed")
    fun theWelcomeMessageIsDisplayed() {
        mainScreen.verifyWelcomeMessageIsDisplayed()
    }

    @Then("the snackbar message is displayed")
    fun theSnackbarMessageIsDisplayed() {
        mainScreen.verifySnackbarMessageIsDisplayed()
    }

    @When("I open and select the settings option")
    fun iOpenAndSelectTheSettingsOption() {
        mainScreen.openAndSelectSettings()
    }
}
