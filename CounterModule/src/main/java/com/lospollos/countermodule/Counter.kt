package com.lospollos.countermodule

import java.util.Timer
import java.util.Date
import kotlin.concurrent.timer

class Counter @JvmOverloads constructor(
    private val minCounterValue: Int = 0,
    private val maxCounterValue: Int = 999,
    private val periodicMillis: Long = 20L
) {

    private lateinit var minusTimer: Timer
    private val minusTimerName = "minusTimer"
    private lateinit var plusTimer: Timer
    private val plusTimerName = "plusTimer"

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
        minusTimer = timer(
            minusTimerName,
            period = periodicMillis,
            startAt = Date(currentDate + delayMillis)
        ) {
            decrease()
            onDecrease()
        }
    }

    fun multipleIncreaseWithDelay(delayMillis: Long = 500L, onIncrease: () -> Unit) {
        val currentDate = Date().time
        plusTimer = timer(
            plusTimerName,
            period = periodicMillis,
            startAt = Date(currentDate + delayMillis)
        ) {
            increase()
            onIncrease()
        }
    }

    fun cancelMultipleDecreaseOperation() {
        minusTimer.cancel()
    }

    fun cancelMultipleIncreaseOperation() {
        plusTimer.cancel()
    }

    fun getCounter() = counter

}