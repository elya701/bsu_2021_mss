package com.example.lab7_task2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    String[] islands = { "Канары", "Курилы", "Мальдивы", "Филиппины"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, islands);

        setListAdapter(adapter);

        setContentView(R.layout.lis_layout);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent1 = new Intent(com.example.lab7_task2.MainActivity.this, Canari.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(com.example.lab7_task2.MainActivity.this, Kurili.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(com.example.lab7_task2.MainActivity.this, Maldivies.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(com.example.lab7_task2.MainActivity.this, Phillipiens.class);
                        startActivity(intent4);
                        break;
                }

                Toast.makeText(getApplicationContext(), "Вы выбрали " +
                        parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        };

        getListView().setOnItemClickListener(itemListener);
    }
}