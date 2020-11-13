package com.example.lab6_task4;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class Phillipiens extends AppCompatActivity {

    var animAlphaChange: Animation? = null

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.philippiens);
        animAlphaChange =
                AnimationUtils.loadAnimation(this, R.anim.alpha_change)
    }
}
