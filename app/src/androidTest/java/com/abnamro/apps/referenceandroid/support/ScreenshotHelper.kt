package com.abnamro.apps.referenceandroid.support

import androidx.test.platform.app.InstrumentationRegistry
import java.util.Locale

/**
 * Reusable utility for capturing and managing screenshots
 */

object ScreenshotHelper {

    private const val SCREENSHOTS_DIRECTORY =
        "/sdcard/Download/referenceandroid-test-screenshots"
    private var screenshotsCleared = false
    private var screenshotCount = 0

    fun clearScreenshotsOnce() {
        if (screenshotsCleared) {
            return
        }

        val instrumentation = InstrumentationRegistry.getInstrumentation()

        instrumentation.uiAutomation
            .executeShellCommand("rm -rf $SCREENSHOTS_DIRECTORY && mkdir -p $SCREENSHOTS_DIRECTORY")
            .close()

        screenshotsCleared = true
        screenshotCount = 0
    }

    fun capture(fileName: String) {
        clearScreenshotsOnce()

        val instrumentation = InstrumentationRegistry.getInstrumentation()
        instrumentation.waitForIdleSync()
        val sanitizedFileName = fileName
            .toLowerCase(Locale.US)
            .replace("[^a-z0-9_-]".toRegex(), "_")
            .trim('_')
            .ifBlank { "screenshot" }
        val screenshotFileName = "%02d_%s.png".format(++screenshotCount, sanitizedFileName)

        instrumentation.uiAutomation
            .executeShellCommand("mkdir -p $SCREENSHOTS_DIRECTORY")
            .close()
        instrumentation.uiAutomation
            .executeShellCommand("screencap -p $SCREENSHOTS_DIRECTORY/$screenshotFileName")
            .close()
    }
}
