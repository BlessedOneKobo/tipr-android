package com.example.tipr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tipr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vBinding.root)

        vBinding.button.setOnClickListener {
            calculateTip()
        }
        vBinding.tipRoundUp.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val serviceCost: Double? = vBinding.costOfService.text.toString().toDoubleOrNull()

        if (serviceCost == null || serviceCost == 0.0) {
            vBinding.tipAmount.text = ""
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
        vBinding.tipAmount.text = getString(R.string.tip_amount, tipAmount)
        vBinding.totalAmount.text = getString(R.string.total_amount, tipAmount + serviceCost)
    }
}