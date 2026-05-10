package com.abnamro.apps.referenceandroid.screens

import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.abnamro.apps.referenceandroid.R
import com.abnamro.apps.referenceandroid.support.BasePage
import org.hamcrest.Matchers.allOf

/**
 * Page Object Model class for the Main screen.
 *
 * Responsibilities:
 * 1. Store locators for this screen.
 * 2. Expose user actions on this screen.
 * 3. Expose screen-level assertions.
 *
 * All action/assertion methods return MainScreen using apply {}
 * so method chaining can be used in step definitions.
 */
class MainScreen : BasePage() {

    // Locators
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

    // Actions
    fun openSettingsMenu() = apply {
        logStep("Open settings overflow menu")
        openActionBarOverflowOrOptionsMenu(
            InstrumentationRegistry.getInstrumentation().targetContext
        )
        captureScreenshot("settings_menu_opened")
    }

    fun selectSettings() = apply {
        logStep("Select settings option")
        clickElement(settingsMenuOption)
        captureScreenshot("settings_selected")
    }

    fun tapFloatingActionButton() = apply {
        logStep("Tap floating action button")
        clickElement(floatingActionButton)
        captureScreenshot("fab_clicked")
    }

    fun tapFloatingActionButton(times: Int) = apply {
        logStep("Tap floating action button $times time(s)")

        repeat(times) {
            tapFloatingActionButton()
                .verifySnackbarMessageIsDisplayed()
        }

        captureScreenshot("fab_clicked_${times}_times")
    }

    fun pressDeviceBack() = apply {
        logStep("Press device back")
        pressBack()
        captureScreenshot("device_back_pressed")
    }

    fun performRepeatedMixedInteractions() = apply {
        logStep("Perform repeated mixed interactions")

        tapFloatingActionButton()
            .openSettingsMenu()
            .pressDeviceBack()
            .tapFloatingActionButton()
            .openSettingsMenu()
            .selectSettings()

        captureScreenshot("mixed_interactions_completed")
    }

    // Verifications
    fun verifyToolbarTitleIsDisplayed() = apply {
        logStep("Verify toolbar title")
        verifyTextDisplayed(R.string.app_name)
        captureScreenshot("toolbar_title_displayed")
    }

    fun verifyWelcomeMessageIsDisplayed() = apply {
        logStep("Verify welcome message")
        verifyTextDisplayed("Hello World!")
        captureScreenshot("welcome_message_displayed")
    }

    fun verifySnackbarMessageIsDisplayed() = apply {
        logStep("Verify snackbar message")
        verifyElementDisplayed(snackbarMessage)
        captureScreenshot("snackbar_visible")
    }

    fun verifyMainScreenFieldsAfterRotation() = apply {
        logStep("Verify main screen fields after rotation")

        verifyToolbarTitleIsDisplayed()
            .verifyWelcomeMessageIsDisplayed()

    }
}