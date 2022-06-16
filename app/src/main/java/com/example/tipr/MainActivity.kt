package com.example.tipr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}