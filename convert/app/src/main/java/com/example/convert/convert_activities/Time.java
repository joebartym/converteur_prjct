package com.example.convert.convert_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.convert.MainActivity;
import com.example.convert.R;

public class Time extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String spinner_values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        // ALL BOTTOM BUTTON LINK

        Button home_btn =findViewById(R.id.home_btn); // BUTTON HOME
        home_btn.setOnClickListener((v)->{
            this.finish();
        });

        Button money_btn = findViewById(R.id.curr_btn); // BUTTON CURRENCY ACTIVITY
        money_btn.setOnClickListener((v)->{
            Intent intent = new Intent(this, Currency.class);
            this.finish();
            startActivity(intent);
        });


        Button leng_btn = findViewById(R.id.leng_btn); // BUTTON LENGTH ACTIVITY
        leng_btn.setOnClickListener((v)->{
            Intent intent = new Intent(this, Length.class);
            this.finish();
            startActivity(intent);
        });


        Button mass_btn = findViewById(R.id.mass_btn); // BUTTON LENGTH ACTIVITY
        mass_btn.setOnClickListener((v)->{
            Intent intent = new Intent(this, Mass.class);
            this.finish();
            startActivity(intent);
        });


        Button speed_btn = findViewById(R.id.speed_btn); // BUTTON SPEED ACTIVITY
        speed_btn.setOnClickListener((v)->{
            Intent intent = new Intent(this, Speed.class);
            this.finish();
            startActivity(intent);
        });


        Button temp_btn = findViewById(R.id.temp_btn); // BUTTON TEMPERATURE ACTIVITY
        temp_btn.setOnClickListener((v)->{
            Intent intent = new Intent(this, Temperature.class);
            this.finish();
            startActivity(intent);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinner_values = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}