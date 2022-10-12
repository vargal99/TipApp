package com.example.appborravalo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appborravalo.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        /*
        val calcb : Button = findViewById(R.id.calculate_button)
        calcb.text = "Kiszámol"
        */
        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = getString(R.string.empty_cost)
            return
        }
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPecentage = when (selectedId) {
            R.id.option_20 -> 0.20
            R.id.option_125 -> 0.125
            else -> 0.05
        }
        var tip = cost * tipPecentage
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}