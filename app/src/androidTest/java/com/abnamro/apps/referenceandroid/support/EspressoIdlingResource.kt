package com.abnamro.apps.referenceandroid.support

import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Global Idling Resource used by Espresso to wait for asynchronous work.
 *
 * Important:
 * Register this in test hooks/base test.
 * Increment before async work starts and decrement when async work completes.
 */
object EspressoIdlingResource {

    private const val RESOURCE_NAME = "REFERENCE_ANDROID_GLOBAL_IDLING_RESOURCE"

    val countingIdlingResource = CountingIdlingResource(RESOURCE_NAME)

    fun increment() {
        if (countingIdlingResource.isIdleNow) {
                    println("TEST increment:")

            countingIdlingResource.increment()
        } else {
            countingIdlingResource.increment()
        }
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
                                println("TEST increment: ")

            countingIdlingResource.decrement()
        }
    }
}
