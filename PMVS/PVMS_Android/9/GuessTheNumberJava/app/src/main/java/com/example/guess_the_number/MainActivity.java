package com.example.guess_the_number;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {

    private final int BOUND = 200;

    TextView tvInfo;
    EditText etInput;
    Button bControl;
    Button bAuthor;
    ProgressBar progressBar;
    ImageView imageTesla;

    Animation animAlphaChange;
    Animation animRotate;
    Animation animResize;
    Animation animTranslate;

    int randomNumber;
    int countTries;
    private int maxTries;

    boolean gameIsEnded;

    // PREFERENCES
    public static final String APP_PREFERENCES = "mysettings";
    public static final String ID_NAME = "ID";

    SharedPreferences appSettings;
    SharedPreferences userSettings;
    SharedPreferences authSettings;


    private boolean checkRange(int start, int end, int number) {
        return (number >= start) && (number <= end);
    }

    public void onClickInfoButton(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.info_button));
        builder.setMessage(getResources().getString(R.string.info_author));


        bAuthor.startAnimation(animRotate);

        // add a button
        builder.setPositiveButton("OK", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onClick(View v) {
        if (!gameIsEnded) {
            try {
                int input_number = Integer.parseInt(etInput.getText().toString());

                if (countTries == 0) {
                    tvInfo.setText(getResources().getString(R.string.end_tries));
                    bControl.setText(getResources().getString(R.string.play_more));

                    tvInfo.startAnimation(animTranslate);
                    imageTesla.startAnimation(animResize);

                    gameIsEnded = true;
                    countTries = maxTries;
                } else {
                    if (!checkRange(0, BOUND, input_number)) {
                        tvInfo.setText(getResources().getString(R.string.error));
                        tvInfo.setTextColor(getResources().getColor(R.color.error_color));
                    } else {
                        tvInfo.setTextColor(getResources().getColor(R.color.black)); // Back to standard text color

                        countTries--;
                        progressBar.setProgress((int) (Math.round(100 * 1.0 * countTries / maxTries)));

                        if (input_number > randomNumber) {
                            tvInfo.setText(getResources().getString(R.string.ahead));
                        } else if (input_number < randomNumber) {
                            tvInfo.setText(getResources().getString(R.string.behind));
                        } else {
                            tvInfo.setText(getResources().getString(R.string.hit));
                            bControl.setText(getResources().getString(R.string.play_more));
                            bControl.setTextColor(getResources().getColor(R.color.turquoise));

                            if (userSettings.getInt("points", Integer.MAX_VALUE) > countTries) {
                                SharedPreferences.Editor editor = userSettings.edit();
                                int points = countTries;

                                editor.putInt("points", points);

                                editor.apply();
                                Toast.makeText(getApplicationContext(), "New record! Congratulations! " + points, Toast.LENGTH_SHORT).show();
                            }

                            gameIsEnded = true;
                            countTries = maxTries;

                            progressBar.setProgress(100);

                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle(getResources().getString(R.string.win));
                            builder.setMessage(getResources().getString(R.string.hit));

                            // add a button
                            builder.setPositiveButton("OK", null);

                            // create and show the alert dialog
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                tvInfo.setText(getResources().getString(R.string.error));
            }
        } else {
            randomNumber = ThreadLocalRandom.current().nextInt(0, BOUND + 1);
            bControl.setText(getResources().getString(R.string.input_value));
            tvInfo.setText(getResources().getString(R.string.try_to_guess));
            gameIsEnded = false;
        }

        etInput.setText("");
    }

    public void createUniqueId(SharedPreferences appSettings) {
        if (appSettings.getString(ID_NAME, null) == null) {
            SharedPreferences.Editor editor = appSettings.edit();

            String uniqueID = UUID.randomUUID().toString();

            editor.putString(ID_NAME, uniqueID);
            editor.putInt("maxTries", 10);

            editor.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        userSettings = getSharedPreferences("user " + getIntent().getStringExtra("username"),
                Context.MODE_PRIVATE);


        SharedPreferences.Editor appEditor = appSettings.edit();

        createUniqueId(appSettings); // Create unique id with first start.

        int totalCountRunApp = appSettings.getInt("CountRunApp", 0);

        totalCountRunApp++;
        appEditor.putInt("totalCountRunApp", totalCountRunApp);
        appEditor.apply();

        maxTries = userSettings.getInt("maxTries", 8);

        tvInfo = findViewById(R.id.textView);
        imageTesla = findViewById(R.id.image_tesla);
        etInput = findViewById(R.id.editTextNumber);
        bControl = findViewById(R.id.button);
        bAuthor = findViewById(R.id.button2);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        animAlphaChange = AnimationUtils.loadAnimation(this, R.anim.alpha_change);
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        animResize = AnimationUtils.loadAnimation(this, R.anim.resize_anim);
        animTranslate = AnimationUtils.loadAnimation(this, R.anim.translate_anim);

        imageTesla.startAnimation(animAlphaChange);

        randomNumber = ThreadLocalRandom.current().nextInt(0, BOUND + 1);
        gameIsEnded = false;
        countTries = maxTries;
        progressBar.setProgress(100);
    }
}