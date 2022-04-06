package com.lospollos.counter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.lospollos.counter.R
import com.lospollos.counter.viewmodel.CounterViewModel
import android.annotation.SuppressLint

class MainActivity : AppCompatActivity() {

    private lateinit var counterViewModel: CounterViewModel
    private lateinit var counterTextView: TextView
    private lateinit var minusButton: Button
    private lateinit var plusButton: Button

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterViewModel = ViewModelProvider(this)[CounterViewModel::class.java]
        counterTextView = findViewById(R.id.counter_text_view)
        minusButton = findViewById(R.id.minus_button)
        plusButton = findViewById(R.id.plus_button)
        counterViewModel.getCounterLiveData().observe(this) {
            counterTextView.text = it.toString()
        }
        minusButton.setOnTouchListener { _, event ->
            counterViewModel.onMinusButtonTouch(event)
            true
        }
        plusButton.setOnTouchListener { _, event ->
            counterViewModel.onPlusButtonTouch(event)
            true
        }

    }

}