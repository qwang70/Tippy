package com.stanford.edu

import android.animation.ArgbEvaluator
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.stanford.edu.MyApplication.Companion.tipInfoList
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENT = 15

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    fun setUpTipUI() {
        seekBarTip.progress = INITIAL_TIP_PERCENT
        tvTipPercent.text = "$INITIAL_TIP_PERCENT%"
        updateTipDescription(INITIAL_TIP_PERCENT)
        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")
                tvTipPercent.text = "$progress%"
                updateTipDescription(progress)
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }

    fun setUpTextEditorUI() {
        etBase.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                computeTipAndTotal()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etSplitBy.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                computeTipAndTotal()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    fun setUpSpinnerUI() {
        spSpinner.onItemSelectedListener = this
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.currency_choice,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spSpinner.adapter = adapter
        }
    }

    private fun setUpButtons() {
        btSave.setOnClickListener {
            if (!etBase.text.isEmpty()) {
                tipInfoList.add(
                    TipInfo(
                        base = etBase.text.toString().toDouble(),
                        tipPercent = seekBarTip.progress,
                        total = computeTotal(),
                        currency = currencySymbol,
                        date = Calendar.getInstance()
                    )
                )
            }

        }
        btHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private var currencySymbol = "$"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpSpinnerUI()
        setUpTipUI()
        setUpTextEditorUI()
        setUpButtons()
    }

    private fun updateTipDescription(tipPercent: Int) {
        val tipDescription: String
        when (tipPercent) {
            in 0..9 -> tipDescription = "Poor\t\uD83D\uDC4E"
            in 10..14 -> tipDescription = "Acceptable\t\uD83D\uDC4C"
            in 15..19 -> tipDescription = "Good\t\uD83D\uDC4C"
            in 20..24 -> tipDescription = "Great\t\uD83D\uDC4D"
            else -> tipDescription = "Amazing\t\uD83D\uDC4D"
        }
        tvTipDescription.text = tipDescription
        val color = ArgbEvaluator().evaluate(
            tipPercent.toFloat() / seekBarTip.max,
            ContextCompat.getColor(this, R.color.colorWorstTip),
            ContextCompat.getColor(this, R.color.colorBestTip)
        ) as Int
        tvTipDescription.setTextColor(color)
    }

    private fun computeTotal(): Double {
        if (etBase.text.isEmpty()) {
            return 0.0
        }
        val baseAmount = etBase.text.toString().toDouble()
        val tipPercent = seekBarTip.progress
        val tipAmount = baseAmount * tipPercent / 100
        val totalAmount = baseAmount + tipAmount
        return totalAmount
    }
    private fun computeTipAndTotal() {
        // Get the value of the base and tip percent
        if (etBase.text.isEmpty()) {
            tvTipAmount.text = ""
            tvTotalAmount.text = ""
            tvPerPersonAmount.text = ""
            return
        }
        val baseAmount = etBase.text.toString().toDouble()
        val tipPercent = seekBarTip.progress
        val tipAmount = baseAmount * tipPercent / 100
        val totalAmount = baseAmount + tipAmount
        tvTipAmount.text = "%.2f $currencySymbol".format(tipAmount)
        tvTotalAmount.text = "%.2f $currencySymbol".format(totalAmount)
        if (etSplitBy.text.isEmpty() || etSplitBy.text.toString().toDouble() < 2) {
            tvPerPersonAmount.text = tvTotalAmount.text
        } else {
            val splitBy = etSplitBy.text.toString().toDouble()
            tvPerPersonAmount.text = "%.2f $currencySymbol".format(totalAmount / splitBy)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        currencySymbol = parent?.getItemAtPosition(pos) as String
        computeTipAndTotal()
    }
}
