package com.abnamro.apps.referenceandroid.screens

import android.view.KeyEvent
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.platform.app.InstrumentationRegistry
import com.abnamro.apps.referenceandroid.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class MainScreen {

    // =========================
    // Locators
    // =========================

    private val rootView = isRoot()

    private val floatingActionButton =
        allOf(withId(R.id.fab), isDisplayed())

    private val settingsMenuOption =
        withText(R.string.action_settings)

    private val snackbarMessage =
        allOf(
            withId(com.google.android.material.R.id.snackbar_text),
            withText("Replace with your own action")
        )

    // =========================
    // Actions
    // =========================

    fun openSettingsMenu() = apply {
    openActionBarOverflowOrOptionsMenu(
        InstrumentationRegistry.getInstrumentation().targetContext
    )
}

    fun selectSettings() = apply {
        onView(settingsMenuOption)
            .perform(click())
    }

    fun tapFloatingActionButton() = apply {
        onView(floatingActionButton)
            .perform(click())
    }

    fun tapFloatingActionButton(times: Int) = apply {
        repeat(times) {
            tapFloatingActionButton()
            verifySnackbarMessageIsDisplayed()
        }
    }

    fun pressDeviceBack() = apply {
        pressBack()
    }

    fun performRepeatedMixedInteractions() = apply {
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
        verifyTextIsDisplayed(R.string.app_name)
    }

    fun verifyWelcomeMessageIsDisplayed() = apply {
        verifyTextIsDisplayed("Hello World!")
    }

    fun verifySnackbarMessageIsDisplayed() = apply {
        onView(snackbarMessage)
            .check(matches(isDisplayed()))
    }

    // =========================
    // Reusable Methods
    // =========================

    private fun verifyTextIsDisplayed(@StringRes textResId: Int) {
        onView(withText(textResId))
            .check(matches(isDisplayed()))
    }

    private fun verifyTextIsDisplayed(text: String) {
        onView(withText(text))
            .check(matches(isDisplayed()))
    }
}