package com.abnamro.apps.referenceandroid.screens

import android.view.KeyEvent
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.abnamro.apps.referenceandroid.R
import org.hamcrest.Matchers.allOf

class MainScreen {

    fun verifyToolbarTitleIsDisplayed(): MainScreen {
        verifyTextIsDisplayed(R.string.app_name)
        return this
    }

    fun verifyWelcomeMessageIsDisplayed(): MainScreen {
        verifyTextIsDisplayed("Hello World!")
        return this
    }

    fun openSettingsMenu(): MainScreen {
        onView(isRoot()).perform(pressKey(KeyEvent.KEYCODE_MENU))
        return this
    }

    fun selectSettings(): MainScreen {
        onView(withText(R.string.action_settings)).perform(click())
        return this
    }

    fun tapFloatingActionButton(): MainScreen {
        onView(allOf(withId(R.id.fab), isDisplayed())).perform(click())
        return this
    }

    fun tapFloatingActionButton(times: Int): MainScreen {
        repeat(times) {
            tapFloatingActionButton()
            verifySnackbarMessageIsDisplayed()
        }
        return this
    }

    fun pressDeviceBack(): MainScreen {
        pressBack()
        return this
    }

    fun performRepeatedMixedInteractions(): MainScreen {
        tapFloatingActionButton()
        openSettingsMenu()
        pressDeviceBack()
        tapFloatingActionButton()
        openSettingsMenu()
        selectSettings()
        return this
    }

    fun verifySnackbarMessageIsDisplayed(): MainScreen {
        onView(allOf(
            withId(com.google.android.material.R.id.snackbar_text),
            withText("Replace with your own action")
        )).check(matches(isDisplayed()))
        return this
    }

    private fun verifyTextIsDisplayed(@StringRes textResId: Int) {
        onView(withText(textResId)).check(matches(isDisplayed()))
    }

    private fun verifyTextIsDisplayed(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }
}
