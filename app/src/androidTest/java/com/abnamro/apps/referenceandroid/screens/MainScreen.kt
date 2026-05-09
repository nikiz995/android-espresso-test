package com.abnamro.apps.referenceandroid.screens

import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.abnamro.apps.referenceandroid.R
import org.hamcrest.Matchers.allOf

/**
 * Page Object Model class for the main screen.
 *
 * Responsibilities:
 * 1. Store locators for this screen.
 * 2. Expose user actions on this screen.
 * 3. Expose screen-level assertions.
 *
 * Step definitions should call these methods instead of directly using Espresso.
 */
class MainScreen : BaseScreen() {

    // =========================
    // Locators
    // =========================

    private val floatingActionButton =
        allOf(withId(R.id.fab), isDisplayed())

    private val settingsMenuOption =
        withText(R.string.action_settings)

    private val snackbarMessage =
        allOf(
            withId(com.google.android.material.R.id.snackbar_text),
            withText("Replace with your own action"),
            isDisplayed()
        )

    // =========================
    // Actions
    // =========================

    fun openSettingsMenu() = apply {
        logStep("Open settings overflow menu")

        openActionBarOverflowOrOptionsMenu(
            InstrumentationRegistry.getInstrumentation().targetContext
        )
    }

    fun selectSettings() = apply {
        logStep("Select settings menu option")
        clickElement(settingsMenuOption)
    }

    fun tapFloatingActionButton() = apply {
        logStep("Tap floating action button")
        clickElement(floatingActionButton)
    }

    fun tapFloatingActionButton(times: Int) = apply {
        logStep("Tap floating action button $times time(s)")

        repeat(times) {
            tapFloatingActionButton()
            verifySnackbarMessageIsDisplayed()
        }
    }

    fun pressDeviceBack() = apply {
        logStep("Press device back")
        pressBack()
    }

    fun performRepeatedMixedInteractions() = apply {
        logStep("Perform repeated mixed interactions")

        tapFloatingActionButton()
        openSettingsMenu()
        pressDeviceBack()
        tapFloatingActionButton()
        openSettingsMenu()
        selectSettings()
    }

    // =========================
    // Verifications
    // =========================

    fun verifyToolbarTitleIsDisplayed() = apply {
        logStep("Verify toolbar title is displayed")
        verifyTextDisplayed(R.string.app_name)
    }

    fun verifyWelcomeMessageIsDisplayed() = apply {
        logStep("Verify welcome message is displayed")
        verifyTextDisplayed("Hello World!")
    }

    fun verifySnackbarMessageIsDisplayed() = apply {
        logStep("Verify snackbar message is displayed")
        verifyElementDisplayed(snackbarMessage)
    }
}
