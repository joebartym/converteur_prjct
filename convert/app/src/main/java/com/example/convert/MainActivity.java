package com.example.convert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.convert.convert_activities.Angle;
import com.example.convert.convert_activities.Energy;
import com.example.convert.convert_activities.Length;
import com.example.convert.convert_activities.Mass;
import com.example.convert.convert_activities.Speed;
import com.example.convert.convert_activities.Temperature;
import com.example.convert.convert_activities.Time;
import com.example.convert.convert_activities.Volume;

public class MainActivity extends AppCompatActivity {
    Intent intente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayAdapter<String> main_menu_list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        String angle = getString(R.string.angle);
        String currency = getString(R.string.currency);
        String energy = getString(R.string.energy);
        String length = getString(R.string.length);
        String mass = getString(R.string.mass);
        String speed = getString(R.string.speed);
        String temperature = getString(R.string.temperature);
        String time = getString(R.string.time);
        String volume = getString(R.string.volume);
        String url = "https://www.boursorama.com/bourse/devises/convertisseur-devises/";
        main_menu_list.addAll(angle,currency,energy,length,mass,speed,temperature,time,volume);
        ListView listView = findViewById(R.id.list_theme_mainmenu);
        listView.setAdapter(main_menu_list);

        listView.setOnItemClickListener((adapterView, view, position, rowId) -> {
            String theme = main_menu_list.getItem(position);
        if(theme == angle ){
                intente = new Intent(this, Angle.class);}
        else if(theme == currency ){
                intente = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                this.startActivity(intente);}
        else if(theme == energy ){
            intente = new Intent(this, Energy.class);}
        else if(theme == length ){
            intente = new Intent(this, Length.class);}
        else if(theme == mass ){
            intente = new Intent(this, Mass.class);}
        else if(theme == speed ){
            intente = new Intent(this, Speed.class);}
        else if(theme == temperature ){
            intente = new Intent(this, Temperature.class);}
        else if(theme == time ){
            intente = new Intent(this, Time.class);}
        else if(theme == volume ){
            intente = new Intent(this, Volume.class);}

            startActivity(intente);
        });



        // ALL BOTTOM BUTTON LINK


        Button time_btn = findViewById(R.id.time_btn); // BUTTON TIME ACTIVITY
            time_btn.setOnClickListener((v)->{
            Intent intent = new Intent(this, Time.class);
            startActivity(intent);
        });


        Button leng_btn = findViewById(R.id.leng_btn); // BUTTON LENGTH ACTIVITY
        leng_btn.setOnClickListener((v)->{
            Intent intent = new Intent(this, Length.class);
            startActivity(intent);
        });


        Button mass_btn = findViewById(R.id.mass_btn); // BUTTON LENGTH ACTIVITY
        mass_btn.setOnClickListener((v)->{
            Intent intent = new Intent(this, Mass.class);
            startActivity(intent);
        });


        Button speed_btn = findViewById(R.id.speed_btn); // BUTTON SPEED ACTIVITY
        speed_btn.setOnClickListener((v)->{
            Intent intent = new Intent(this, Speed.class);
            startActivity(intent);
        });


        Button temp_btn = findViewById(R.id.temp_btn); // BUTTON TEMPERATURE ACTIVITY
        temp_btn.setOnClickListener((v)->{
            Intent intent = new Intent(this, Temperature.class);
            startActivity(intent);
        });


    }

}