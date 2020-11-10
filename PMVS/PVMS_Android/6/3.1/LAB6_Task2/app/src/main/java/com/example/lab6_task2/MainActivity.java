package com.example.lab6_task2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl;

    private Integer RANDOM_BOUND = 100;
    private Integer curNum = 0;
    private Boolean gameOn = false;

    public void onHelp(View v) {
        setUpMessage(getResources().getString(R.string.hint),
                R.color.design_default_color_secondary_variant);
        etInput.setText(curNum.toString());
    }

    private void setUpMessage(String message, Integer color) {
        tvInfo.setText(message);
        tvInfo.setTextColor(this.getResources().getColor(color));
    }

    private Integer generateNumber() {
        Double rand = (Math.random() * RANDOM_BOUND);
        return rand.intValue();
    }

    private Boolean outOfRandomRange(Integer num) {
        return num < 0 || num > RANDOM_BOUND;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        curNum = generateNumber();
        gameOn = false;
        tvInfo = (TextView)findViewById(R.id.textView);
        etInput = (EditText)findViewById(R.id.editTextNumber);
        bControl = (Button)findViewById(R.id.button);
    }

    public void onClick(View v){

        if (!gameOn) {
            curNum = generateNumber();
            gameOn = true;
            bControl.setBackgroundColor(getResources().getColor(R.color.purple_500));
            bControl.setText(getResources().getString(R.string.input_value));
        } else {
            try {
                tvInfo.setTextColor(this.getResources().getColor(R.color.black));
                Integer num = Integer.parseInt(etInput.getText().toString());
                System.out.println(curNum);
                if (outOfRandomRange(num)) {
                    setUpMessage(this.getResources().getString(R.string.wrong_input), R.color.purple_200);
                } else {
                    if (num < curNum) {
                        setUpMessage(getResources().getString(R.string.behind), R.color.black);
                    } else if (num > curNum) {
                        setUpMessage(getResources().getString(R.string.ahead), R.color.black);
                    } else {
                        setUpMessage(getResources().getString(R.string.hit), R.color.black);
                        gameOn = false;

                        bControl.setBackgroundColor(getResources().getColor(R.color.replay_green));

                        bControl.setText(getResources().getString(R.string.play_more));
                    }
                }

            } catch (NumberFormatException ex){
                setUpMessage(getResources().getString(R.string.error), R.color.red);
            }
        }
    }
}