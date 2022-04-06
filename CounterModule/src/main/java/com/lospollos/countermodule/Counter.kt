package com.lospollos.countermodule

import java.util.Timer
import java.util.Date
import kotlin.concurrent.timer

class Counter @JvmOverloads constructor(
    private val minCounterValue: Int = 0,
    private val maxCounterValue: Int = 999,
    private val periodicMillis: Long = 20L
) {

    private lateinit var timer: Timer
    private val timerName = "timer"

    private var counter: Int = 0

    fun increase() {
        if (counter < maxCounterValue) {
            counter++
        }
    }

    fun decrease() {
        if (counter > minCounterValue) {
            counter--
        }
    }

    fun multipleDecreaseWithDelay(delayMillis: Long = 500L, onDecrease: () -> Unit) {
        val currentDate = Date().time
        timer = timer(
            timerName,
            period = periodicMillis,
            startAt = Date(currentDate + delayMillis)
        ) {
            decrease()
            onDecrease()
        }
    }

    fun multipleIncreaseWithDelay(delayMillis: Long = 500L, onIncrease: () -> Unit) {
        val currentDate = Date().time
        timer = timer(
            timerName,
            period = periodicMillis,
            startAt = Date(currentDate + delayMillis)
        ) {
            increase()
            onIncrease()
        }
    }

    fun cancelMultipleOperation() {
        timer.cancel()
    }

    fun getCounter() = counter

}