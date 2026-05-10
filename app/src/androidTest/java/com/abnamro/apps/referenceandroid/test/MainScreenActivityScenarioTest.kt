package com.abnamro.apps.referenceandroid

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.abnamro.apps.referenceandroid.screens.MainScreen
import com.abnamro.apps.referenceandroid.support.ScreenshotHelper
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * ActivityScenario-based Espresso test runner.
 *
 * Responsibilities:
 * - Launch MainActivity using ActivityScenarioRule
 * - Execute UI validation scenarios
 * - Reuse Page Object Model screen methods
 * - Capture screenshots for debugging/reporting
 */
@RunWith(AndroidJUnit4::class)
class MainScreenActivityScenarioTest {

    @get:Rule
    val activityScenarioRule =
        ActivityScenarioRule(MainActivity::class.java)

    private val mainScreen = MainScreen()

    @Before
    fun beforeTest() {
        ScreenshotHelper.clearScreenshotsOnce()
    }

    @Test
    fun mainScreenRendersExpectedContent() {

        activityScenarioRule.scenario.onActivity { activity ->
            assertFalse(activity.isFinishing)
        }

        ScreenshotHelper.capture("main_screen_app_launched")

        mainScreen
            .verifyToolbarTitleIsDisplayed()
            .verifyWelcomeMessageIsDisplayed()

        ScreenshotHelper.capture("main_screen_content_verified")
    }

    @Test
    fun settingsMenuItemCanBeOpenedAndSelected() {

        ScreenshotHelper.capture("settings_menu_app_launched")

        mainScreen
            .openSettingsMenu()
            .selectSettings()
            .verifyWelcomeMessageIsDisplayed()

        ScreenshotHelper.capture("settings_menu_verified")
    }

    @Test
    fun floatingActionButtonShowsSnackbar() {

        ScreenshotHelper.capture("fab_app_launched")

        mainScreen
            .tapFloatingActionButton()
            .verifySnackbarMessageIsDisplayed()

        ScreenshotHelper.capture("fab_snackbar_verified")
    }

    @Test
    fun openingSettingsMenuAndPressingBackKeepsMainScreenVisible() {

        ScreenshotHelper.capture("negative_settings_back_app_launched")

        mainScreen
            .openSettingsMenu()
            .pressDeviceBack()
            .verifyWelcomeMessageIsDisplayed()

        ScreenshotHelper.capture("negative_settings_back_verified")
    }

    @Test
    fun tappingFloatingActionButtonRepeatedlyKeepsAppStable() {

        ScreenshotHelper.capture("negative_repeated_fab_app_launched")

        mainScreen
            .tapFloatingActionButton(3)
            .verifySnackbarMessageIsDisplayed()
            .verifyWelcomeMessageIsDisplayed()

        ScreenshotHelper.capture("negative_repeated_fab_verified")
    }

    @Test
    fun repeatedMixedInteractionsDoNotCauseUnhandledException() {

        ScreenshotHelper.capture("negative_mixed_interactions_app_launched")

        mainScreen
            .performRepeatedMixedInteractions()
            .verifyWelcomeMessageIsDisplayed()

        ScreenshotHelper.capture("negative_mixed_interactions_verified")
    }
}