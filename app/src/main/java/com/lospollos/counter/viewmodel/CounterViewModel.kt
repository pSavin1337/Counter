package com.lospollos.counter.viewmodel

import android.view.MotionEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lospollos.countermodule.Counter

class CounterViewModel: ViewModel() {

    private val counter = Counter()

    private val counterLiveData = MutableLiveData<Int>()
    fun getCounterLiveData(): LiveData<Int> = counterLiveData

    private fun onMinusButtonClick() {
        counter.decrease()
        counterLiveData.postValue(counter.getCounter())
    }

    private fun onPlusButtonClick() {
        counter.increase()
        counterLiveData.postValue(counter.getCounter())
    }

    fun onMinusButtonTouch(event: MotionEvent) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            counter.multipleDecreaseWithDelay {
                counterLiveData.postValue(counter.getCounter())
            }
        }
        if (event.action == MotionEvent.ACTION_UP) {
            counter.cancelMultipleOperation()
            onMinusButtonClick()
        }
    }

    fun onPlusButtonTouch(event: MotionEvent) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            counter.multipleIncreaseWithDelay {
                counterLiveData.postValue(counter.getCounter())
            }
        }
        if (event.action == MotionEvent.ACTION_UP) {
            counter.cancelMultipleOperation()
            onPlusButtonClick()
        }
    }

}