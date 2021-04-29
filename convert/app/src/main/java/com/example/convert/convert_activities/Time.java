package com.example.convert.convert_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.convert.R;

public class Time extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String spinner_values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        Z_elem_list_creator elem = new Z_elem_list_creator();

        final ArrayAdapter<String> main_menu_list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);

        String seconde = getString(R.string.seconde);
        String heur = getString(R.string.heure);
        String minute= getString(R.string.minute);
        String miliseconde = getString(R.string.miliseconde);
        String jour= getString(R.string.jour);
        String annee= getString(R.string.annee);
        String all= "All";

        main_menu_list.addAll(annee,jour,heur,minute,seconde,all,miliseconde);

        ListView listView = findViewById(R.id.list_time);
        listView.setAdapter(main_menu_list);



        // SPINNER
        Spinner time_spn = findViewById(R.id.time_unit_1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.time_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spn.setAdapter(adapter);
        time_spn.setOnItemSelectedListener(this);
        spinner_values = seconde;

// BUTTON

        EditText nb_to_convert = findViewById(R.id.time_input);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        nb_to_convert.setText(preferences.getString("PREF_TEMP_INPUTti", nb_to_convert.getText().toString()));

        Button convert_btn = findViewById(R.id.conv_time_btn);
        convert_btn.setOnClickListener((v) -> {

            getPreferences(MODE_PRIVATE)
                    .edit()
                    .putString("PREF_TEMP_INPUTti", nb_to_convert.getText().toString())
                    .apply();
            Double my_unit = 0.0;
            String tmp_seconde,tmp_heure,tmp_minute,tmp_annee,tmp_jour,tmp_miliseconde,tmp_all;
            Double tmp_nb;
            try {
                tmp_nb = Double.parseDouble(nb_to_convert.getText().toString());
            }catch (Exception e){
                tmp_nb = 0.0;
            }


            // first step, all convert to one unit : the seconde
            if (spinner_values.equals(getString(R.string.seconde))){
                my_unit = tmp_nb *1;
            }else if (spinner_values.equals( getString(R.string.heure))){
                my_unit = tmp_nb * (3600);
            }else if (spinner_values.equals( getString(R.string.minute))){
                my_unit = tmp_nb  * (60);
            }else if (spinner_values.equals(getString(R.string.jour))){
                my_unit = tmp_nb  * 86400;
            }else if (spinner_values.equals( getString(R.string.annee))){
                my_unit = tmp_nb * 31536000;
            }else if (spinner_values.equals( getString(R.string.miliseconde))){
                my_unit = tmp_nb  / (1000);
            }
            double trp_all = my_unit;

            //second step : translation of all unit create all element
            tmp_heure = elem.getElem(my_unit / 3600 ,getString(R.string.heure),getString(R.string.smb_heure));
            tmp_jour = elem.getElem(my_unit   / 86400,getString(R.string.jour),getString(R.string.smb_jour));
            tmp_annee = elem.getElem(my_unit /31536000,getString(R.string.annee),getString(R.string.smb_annee));
            tmp_seconde = elem.getElem(my_unit *1,getString(R.string.seconde),getString(R.string.smb_seconde));
            tmp_miliseconde = elem.getElem(my_unit  * 1000,getString(R.string.miliseconde),getString(R.string.smb_miliseconde));
            tmp_minute = elem.getElem(my_unit /60,getString(R.string.minute),getString(R.string.smb_minute));
            tmp_all = elem.Fulltime_get((long) trp_all);

            //reset view with new elem

            main_menu_list.clear();
            main_menu_list.addAll(tmp_annee,tmp_jour,tmp_heure,tmp_minute,tmp_seconde,tmp_all,tmp_miliseconde);
            listView.setAdapter(main_menu_list);
        });



        // ALL BOTTOM BUTTON LINK

        Button home_btn =findViewById(R.id.home_btn); // BUTTON HOME
        home_btn.setOnClickListener((v)->{
            this.finish();
        });

        Button time_btn = findViewById(R.id.time_btn); // BUTTON TIME ACTIVITY
        time_btn.setOnClickListener((v)->{
            Intent intent = new Intent(this, Time.class);
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