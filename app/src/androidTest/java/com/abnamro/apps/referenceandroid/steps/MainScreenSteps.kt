package com.abnamro.apps.referenceandroid.steps

import android.app.Activity
import android.content.Intent
import androidx.test.espresso.IdlingRegistry
import androidx.test.platform.app.InstrumentationRegistry
import com.abnamro.apps.referenceandroid.screens.MainScreen
import com.abnamro.apps.referenceandroid.support.EspressoIdlingResource
import com.abnamro.apps.referenceandroid.support.ScreenshotHelper
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

/**
 * Cucumber step definitions.
 *
 * Keep this class thin:
 * - Launch/close app
 * - Register/unregister test resources
 * - Call screen/page object methods
 */
class MainScreenSteps {

    private var launchedActivity: Activity? = null
    private val mainScreen = MainScreen()

    @Before
    fun beforeScenario() {
        IdlingRegistry.getInstance().register(
            EspressoIdlingResource.countingIdlingResource
        )

        ScreenshotHelper.clearScreenshotsOnce()
    }

    @After
    fun afterScenario() {
        launchedActivity?.finish()
        launchedActivity = null

        IdlingRegistry.getInstance().unregister(
            EspressoIdlingResource.countingIdlingResource
        )
    }

    @Given("the app is launched")
    fun theAppIsLaunched() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val launchIntent = requireNotNull(
            context.packageManager
                .getLaunchIntentForPackage(context.packageName)
                ?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )

        EspressoIdlingResource.increment()
        try {
            launchedActivity = instrumentation.startActivitySync(launchIntent)
        } finally {
            EspressoIdlingResource.decrement()
        }

        ScreenshotHelper.capture("app_launched")
    }

    @Then("the main screen toolbar title is displayed")
    fun theMainScreenToolbarTitleIsDisplayed() {
        mainScreen.verifyToolbarTitleIsDisplayed()
        ScreenshotHelper.capture("toolbar_title_displayed")
    }

    @Then("the welcome message is displayed")
    fun theWelcomeMessageIsDisplayed() {
        mainScreen.verifyWelcomeMessageIsDisplayed()
        ScreenshotHelper.capture("welcome_message_displayed")
    }

    @When("I open the settings menu")
    fun iOpenTheSettingsMenu() {
        mainScreen.openSettingsMenu()
        ScreenshotHelper.capture("settings_menu_opened")
    }

    @When("I select the settings option")
    fun iSelectTheSettingsOption() {
        mainScreen.selectSettings()
        ScreenshotHelper.capture("settings_selected")
    }

    @When("I tap the floating action button")
    fun iTapTheFloatingActionButton() {
        mainScreen.tapFloatingActionButton()
        ScreenshotHelper.capture("fab_clicked")
    }

    @When("I tap the floating action button {int} times")
    fun iTapTheFloatingActionButtonTimes(times: Int) {
        mainScreen.tapFloatingActionButton(times)
        ScreenshotHelper.capture("fab_clicked_${times}_times")
    }

    @When("I press back")
    fun iPressBack() {
        mainScreen.pressDeviceBack()
        ScreenshotHelper.capture("device_back_pressed")
    }

    @When("I perform repeated mixed interactions")
    fun iPerformRepeatedMixedInteractions() {
        mainScreen.performRepeatedMixedInteractions()
        ScreenshotHelper.capture("mixed_interactions_completed")
    }

    @Then("the snackbar message is displayed")
    fun theSnackbarMessageIsDisplayed() {
        mainScreen.verifySnackbarMessageIsDisplayed()
        ScreenshotHelper.capture("snackbar_visible")
    }
}
