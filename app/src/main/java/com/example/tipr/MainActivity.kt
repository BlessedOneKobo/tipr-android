package com.example.tipr

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tipr.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var vBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vBinding.root)

        vBinding.button.setOnClickListener {
            calculateTip()
        }
        vBinding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
        // vBinding.tipPercentage.setOnCheckedChangeListener { _, _ -> calculateTip() }
    }

    private fun calculateTip() {
        val serviceCost: Double? = vBinding.costOfServiceEditText.text.toString().toDoubleOrNull()

        if (serviceCost == null || serviceCost == 0.0) {
            vBinding.tipAmount.text = ""
            vBinding.totalAmount.text = ""
            return
        }

        val tipPercentage = when (vBinding.tipPercentage.checkedRadioButtonId) {
            R.id.amazing_tip -> 0.20
            R.id.good_tip -> 0.18
            else -> 0.15
        }

        var tipAmount = serviceCost * tipPercentage
        if (vBinding.tipRoundUp.isChecked) {
            tipAmount = kotlin.math.ceil(tipAmount)
        }
        val fmt = NumberFormat.getCurrencyInstance()
        vBinding.tipAmount.text = getString(R.string.tip_amount, fmt.format(tipAmount))
        vBinding.totalAmount.text =
            getString(R.string.total_amount, fmt.format(tipAmount + serviceCost))
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}