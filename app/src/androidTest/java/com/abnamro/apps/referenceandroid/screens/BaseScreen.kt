package com.abnamro.apps.referenceandroid.screens

import android.view.View
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher

/**
 * Base class for all Page Object Model screen classes.
 * Keeps common Espresso actions and assertions reusable.
 */
open class BaseScreen {

    protected fun clickElement(matcher: Matcher<View>) {
        onView(matcher)
            .check(matches(isDisplayed()))
            .perform(click())
    }

    protected fun verifyElementDisplayed(matcher: Matcher<View>) {
        onView(matcher)
            .check(matches(isDisplayed()))
    }

    protected fun verifyTextDisplayed(@StringRes textResId: Int) {
        onView(withText(textResId))
            .check(matches(isDisplayed()))
    }

    protected fun verifyTextDisplayed(text: String) {
        onView(withText(text))
            .check(matches(isDisplayed()))
    }

    protected fun logStep(step: String) {
        println("TEST STEP: $step")
    }
}
