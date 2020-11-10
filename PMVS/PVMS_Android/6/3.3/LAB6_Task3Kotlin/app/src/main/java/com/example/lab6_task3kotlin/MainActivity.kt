package com.example.lab6_task3kotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //MARK: - Variables
    private val RANDOM_BOUND = 100
    private var tvInfo: TextView? = null
    private var etInput: EditText? = null
    private var bControl: Button? = null
    private var curNum = 0
    private var gameOn = false

    //MARK: - Init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        curNum = generateNumber()
        gameOn = false
        tvInfo = findViewById<View>(R.id.textView) as TextView
        etInput = findViewById<View>(R.id.editTextNumber) as EditText
        bControl = findViewById<View>(R.id.button) as Button
    }

    //MARK: - Button Methods
    fun onHelp(view: View?) {
        setUpMessage(resources.getString(R.string.hint),
            R.color.design_default_color_secondary_variant)
        etInput?.setText(curNum.toString())
    }

    fun onClick(v: View?) {
        if (!gameOn) {
            curNum = generateNumber()
            gameOn = true
            bControl?.setBackgroundColor(resources.getColor(R.color.purple_500))
            bControl?.text = resources.getString(R.string.input_value)
        } else {
            try {
                tvInfo?.setTextColor(this.resources.getColor(R.color.black))
                val num = etInput?.text.toString().toInt()
                println(curNum)
                if (outOfRandomRange(num)) {
                    setUpMessage(this.resources.getString(R.string.error), R.color.purple_200)
                } else {
                    if (num < curNum) {
                        setUpMessage(resources.getString(R.string.behind), R.color.black)
                    } else if (num > curNum) {
                        setUpMessage(resources.getString(R.string.ahead), R.color.black)
                    } else {
                        setUpMessage(resources.getString(R.string.hit), R.color.black)
                        gameOn = false

                        bControl?.setBackgroundColor(resources.getColor(R.color.replay_green))

                        bControl?.text = resources.getString(R.string.play_more)
                    }
                }
            } catch (ex: NumberFormatException) {
                setUpMessage(resources.getString(R.string.error), R.color.red)
            }
        }
    }

    //MARK: - Other methods
    private fun setUpMessage(message: String, color: Int) {
        tvInfo?.text = message
        tvInfo?.setTextColor(this.resources.getColor(color))
    }

    private fun outOfRandomRange(num: Int): Boolean {
        return num < 0 || num > RANDOM_BOUND
    }

    private fun generateNumber(): Int {
        return (Math.random() * RANDOM_BOUND).toInt()
    }
}