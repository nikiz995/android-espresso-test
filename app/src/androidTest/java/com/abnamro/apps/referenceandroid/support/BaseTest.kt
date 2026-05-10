package com.abnamro.apps.referenceandroid.support

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.espresso.IdlingRegistry
import androidx.test.platform.app.InstrumentationRegistry

/**
 * Generic test base class for activity lifecycle, idling resource registration,
 * orientation changes, and screenshot utilities.
 */
open class BaseTest {

    protected var launchedActivity: Activity? = null

    protected fun beforeScenarioSetup() {
        IdlingRegistry.getInstance().register(
            EspressoIdlingResource.countingIdlingResource
        )
        ScreenshotHelper.clearScreenshotsOnce()
    }

    protected fun afterScenarioTearDown() {
        closeApplication()
        IdlingRegistry.getInstance().unregister(
            EspressoIdlingResource.countingIdlingResource
        )
    }

    protected fun launchApplication(): Activity {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val launchIntent = requireNotNull(
            context.packageManager
                .getLaunchIntentForPackage(context.packageName)
                ?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        ) { "Launch intent not found for package: ${context.packageName}" }

        EspressoIdlingResource.increment()
        return try {
            instrumentation.startActivitySync(launchIntent).also {
                launchedActivity = it
                instrumentation.waitForIdleSync()
                ScreenshotHelper.capture("app_launched")
            }
        } finally {
            EspressoIdlingResource.decrement()
        }
    }

    protected fun closeApplication() {
        launchedActivity?.finish()
        launchedActivity = null
    }

    protected fun rotateDeviceToLandscape() {
        rotateDevice(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    }

    protected fun rotateDeviceToPortrait() {
        rotateDevice(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    private fun rotateDevice(orientation: Int) {
        val activity = requireNotNull(launchedActivity) {
            "Activity is not launched. Call 'Given the app is launched' first."
        }

        val instrumentation = InstrumentationRegistry.getInstrumentation()

        EspressoIdlingResource.increment()
        try {
            activity.requestedOrientation = orientation
            instrumentation.waitForIdleSync()
        } finally {
            EspressoIdlingResource.decrement()
        }
    }
}
