package com.example.lab6_task4;
import android.os.Bundle;
import android.view.View
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Phillipiens: AppCompatActivity() {

    private var animAlphaChange:Animation? = null
    private var imageView:ImageView? = null;
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.philippiens);
        imageView = findViewById<View>(R.id.imageView1) as ImageView
        animAlphaChange =
                AnimationUtils.loadAnimation(this, R.anim.alpha_change)
        imageView?.startAnimation(animAlphaChange)
    }
}
