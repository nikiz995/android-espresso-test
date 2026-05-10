package com.abnamro.apps.referenceandroid.support

import android.view.View
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher

/**
 * Generic reusable base class for screen/page-object actions.
 */
open class BasePage {

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

    protected fun captureScreenshot(name: String) {
        ScreenshotHelper.capture(name)
    }

    protected fun logStep(message: String) {
        println("STEP: $message")
    }
}
