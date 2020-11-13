package com.example.lab6_task3kotlin

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ThreadLocalRandom

class MainActivity : AppCompatActivity() {

    //MARK: - Variables
    private val RANDOM_BOUND = 100
    private var tvInfo: TextView? = null
    private var etInput: EditText? = null
    private var bControl: Button? = null
    private var profileImage: ImageView? = null
    private var progressBar: ProgressBar? = null
    private var curNum = 0
    private var gameOn = false

    var animAlphaChange: Animation? = null
    var animRotate: Animation? = null
    var animResize: Animation? = null
    var animTranslate: Animation? = null

    private val maxTries = 5
    var countTries = 0


    //MARK: - Init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        curNum = generateNumber()
        gameOn = false
        tvInfo = findViewById<View>(R.id.textView) as TextView
        etInput = findViewById<View>(R.id.editTextNumber) as EditText
        bControl = findViewById<View>(R.id.button) as Button
        profileImage = findViewById<View>(R.id.imageView) as ImageView
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar

        countTries = maxTries
        animAlphaChange =
            AnimationUtils.loadAnimation(this, R.anim.alpha_change)
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
        animResize = AnimationUtils.loadAnimation(this, R.anim.resize_anim)
        animTranslate =
            AnimationUtils.loadAnimation(this, R.anim.translate_anim)
    }

    //MARK: - Button Methods
    fun onHelp(view: View?) {
        setUpMessage(resources.getString(R.string.hint),
            R.color.design_default_color_secondary_variant)
        etInput?.setText(curNum.toString())
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.hint))
        builder.setMessage(resources.getString(R.string.hint))
        bControl?.startAnimation(animRotate)
        progressBar?.startAnimation(animResize)

        // add a button
        builder.setPositiveButton("OK", null)

        // create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun onClick(v: View?) {
        if (!gameOn) {
            curNum = generateNumber()
            gameOn = true
            bControl?.setBackgroundColor(resources.getColor(R.color.purple_500))
            bControl?.text = resources.getString(R.string.input_value)
        } else {
            if (countTries == 0) {
                //tvInfo!!.text = resources.getString(R.string.end_tries)
                bControl!!.text = resources.getString(R.string.play_more)
                bControl!!.startAnimation(animTranslate)
                gameOn = false
                countTries = maxTries
                profileImage!!.animation.cancel()
            } else {
                try {
                    profileImage!!.startAnimation(animAlphaChange)
                    tvInfo?.setTextColor(this.resources.getColor(R.color.black))
                    val num = etInput?.text.toString().toInt()
                    println(curNum)
                    countTries--
                    progressBar?.setProgress(
                        Math.round(100 * 1.0 * countTries / maxTries).toInt()
                    )
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
                            countTries = maxTries
                            progressBar?.setProgress(100, true)
                            bControl?.setBackgroundColor(resources.getColor(R.color.replay_green))

                            bControl?.text = resources.getString(R.string.play_more)
                        }
                    }
                } catch (ex: NumberFormatException) {
                    setUpMessage(resources.getString(R.string.error), R.color.red)
                }
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