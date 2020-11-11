package com.example.lab6_task4


import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var dataSourceMetric: Array<String>? = null

    var dataSourceHistoryAndCountry: Array<String>? = null

    private var spinnerOne: Spinner? = null
    private var spinnerTwo: Spinner? = null
    private var editText: EditText? = null
    private var resultView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataSourceMetric = arrayOf(resources.getString(R.string.squareM),
            resources.getString(R.string.ga), resources.getString(R.string.squareKM))
        dataSourceHistoryAndCountry =  arrayOf(resources.getString(R.string.squareFT),
            resources.getString(R.string.quadraChile), resources.getString(R.string.quadraUruguay),
            resources.getString(R.string.donumСyprus), resources.getString(R.string.mansanCostaRica))
        setContentView(R.layout.activity_main)
        spinnerOne = findViewById<View>(R.id.spinner) as Spinner
        spinnerTwo = findViewById<View>(R.id.spinner2) as Spinner
        editText = findViewById<View>(R.id.editTextNumberDecimal) as EditText
        resultView = findViewById<View>(R.id.textView) as TextView
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        val adapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, dataSourceMetric!!)

        val adapter2 =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, dataSourceHistoryAndCountry!!)
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        // Применяем адаптер к элементу spinner
        spinnerOne?.adapter = adapter
        spinnerTwo?.adapter = adapter2
    }

    //MARK: - Button Methods
    fun onClick(view: View?) {
        val inputNumberText: String = editText?.text.toString()
        var coefficient = 1;
        var inputNumber: Double?
        var resultNumber: Double?
        if (inputNumberText != "") {
            inputNumber = inputNumberText.toDouble();
        } else {
            inputNumber = 0.0
        }
        val spinnerOneItem: String = spinnerOne?.selectedItem.toString()
        val spinnerTwoItem: String = spinnerTwo?.selectedItem.toString()
        //println(inputNumberText.toDouble())

        if (spinnerOneItem == resources.getString(R.string.ga)) {
            inputNumber = inputNumber * 0.01
            //println("hello!")
        }  else if (spinnerOneItem == resources.getString(R.string.squareM)) {
            inputNumber = inputNumber * 0.000001
        }



        if (spinnerTwoItem == resources.getString(R.string.squareFT)) {
            resultNumber = inputNumber * 10763910
        } else if (spinnerTwoItem == resources.getString(R.string.quadraChile)) {
            resultNumber = inputNumber * 63.59
        } else if (spinnerTwoItem == resources.getString(R.string.quadraUruguay)) {
            resultNumber = inputNumber * 135.5
        } else if (spinnerTwoItem == resources.getString(R.string.donumСyprus)) {
            resultNumber = inputNumber * 2446
        } else {
            resultNumber = inputNumber * 143.1
        }
        val string = resources.getString(R.string.result)
        resultView?.text = "$string $resultNumber $spinnerTwoItem"
    }
}