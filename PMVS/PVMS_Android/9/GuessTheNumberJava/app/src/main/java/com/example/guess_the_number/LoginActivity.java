package com.example.guess_the_number;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Intent intent;
    SharedPreferences prf;

    EditText user_name_field;
    EditText password_field;
    Button btnL;

    public static final String AUTH_PREFERENCES_NAME = "auth";

    public void onClickLogin(View view) {
        String user_name = user_name_field.getText().toString();
        String password_from_view = password_field.getText().toString();

        String key_passwd = user_name + "_password";

        String password = prf.getString(key_passwd, null);

        intent.putExtra("username", user_name);

        if (password != null) {
            if (password.equals(password_from_view)) {
                Toast.makeText(getApplicationContext(), "Login Successful",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Login Failed",Toast.LENGTH_SHORT).show();
            }

        } else {
            SharedPreferences.Editor editor = prf.edit();

            editor.putString(key_passwd, password_from_view);
            editor.apply();

            Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        intent = new Intent(LoginActivity.this, MainActivity.class);
        user_name_field = findViewById(R.id.userName);
        password_field = findViewById(R.id.pwdName);
        btnL = findViewById(R.id.btnL);
        prf = getSharedPreferences(AUTH_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}
