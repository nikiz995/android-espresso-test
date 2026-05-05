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


@RunWith(AndroidJUnit4::class)
class MainScreenActivityScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val mainScreen = MainScreen()

    @Before
    fun clearScreenshots() {
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
        ScreenshotHelper.capture("main_screen_toolbar_title_displayed")

        mainScreen
            .verifyWelcomeMessageIsDisplayed()
        ScreenshotHelper.capture("main_screen_welcome_message_displayed")
    }

    @Test
    fun settingsMenuItemCanBeOpenedAndSelected() {
        ScreenshotHelper.capture("settings_menu_app_launched")

        mainScreen.openSettingsMenu()
        ScreenshotHelper.capture("settings_menu_open")

        mainScreen
            .selectSettings()
            .verifyWelcomeMessageIsDisplayed()
        ScreenshotHelper.capture("settings_menu_welcome_message_displayed")
    }

    @Test
    fun floatingActionButtonShowsSnackbar() {
        ScreenshotHelper.capture("fab_app_launched")

        mainScreen
            .tapFloatingActionButton()
            .verifySnackbarMessageIsDisplayed()

        ScreenshotHelper.capture("fab_snackbar_visible")
    }

    @Test
    fun openingSettingsMenuAndPressingBackKeepsMainScreenVisible() {
        ScreenshotHelper.capture("negative_settings_back_app_launched")

        mainScreen
            .openSettingsMenu()
        ScreenshotHelper.capture("negative_settings_menu_open")

        mainScreen
            .pressDeviceBack()
            .verifyWelcomeMessageIsDisplayed()
        ScreenshotHelper.capture("negative_settings_back_welcome_message_displayed")
    }

    @Test
    fun tappingFloatingActionButtonRepeatedlyKeepsAppStable() {
        ScreenshotHelper.capture("negative_repeated_fab_app_launched")

        mainScreen
            .tapFloatingActionButton(3)
            .verifySnackbarMessageIsDisplayed()
        ScreenshotHelper.capture("negative_repeated_fab_snackbar_visible")

        mainScreen.verifyWelcomeMessageIsDisplayed()
        ScreenshotHelper.capture("negative_repeated_fab_welcome_message_displayed")
    }

    @Test
    fun repeatedMixedInteractionsDoNotCauseUnhandledException() {
        ScreenshotHelper.capture("negative_mixed_interactions_app_launched")

        mainScreen
            .performRepeatedMixedInteractions()
            .verifyWelcomeMessageIsDisplayed()

        ScreenshotHelper.capture("negative_mixed_interactions_completed")
    }
}
