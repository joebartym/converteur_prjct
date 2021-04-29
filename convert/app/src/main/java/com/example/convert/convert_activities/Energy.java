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

public class Energy extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String spinner_values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy);
        Z_elem_list_creator elem = new Z_elem_list_creator();

        final ArrayAdapter<String> main_menu_list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        String joule = getString(R.string.joule);
        String kilojoule = getString(R.string.kilojoule);
        String calorie = getString(R.string.calorie);
        String kilocalorie = getString(R.string.Kilocalorie);
        String watt_heure = getString(R.string.watt_heure);
        String kilowatt_heure = getString(R.string.kilowatt_heure);
        String electronvolt = getString(R.string.electronvolt);

        main_menu_list.addAll(joule,kilojoule,calorie,kilocalorie,watt_heure,kilowatt_heure,electronvolt);
        ListView listView = findViewById(R.id.list_angle);
        listView.setAdapter(main_menu_list);



        // SPINNER
        Spinner ener_spn = findViewById(R.id.ener_unit_1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ener_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ener_spn.setAdapter(adapter);
        ener_spn.setOnItemSelectedListener(this);
        spinner_values = joule;

// BUTTON

        EditText nb_to_convert = findViewById(R.id.ener_input);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        nb_to_convert.setText(preferences.getString("PREF_TEMP_INPUTe", nb_to_convert.getText().toString()));

        Button convert_btn = findViewById(R.id.conv_ener_btn);
        convert_btn.setOnClickListener((v) -> {

            getPreferences(MODE_PRIVATE)
                    .edit()
                    .putString("PREF_TEMP_INPUTe", nb_to_convert.getText().toString())
                    .apply();
            Double my_unit = 0.0;
            String tmp_kilojoule,tmp_joule,tmp_electronvolt,tmp_calorie,tmp_kilocalorie,tmp_kilowatt_heure,tmp_watt_heure; Double tmp_nb;
            try {
                tmp_nb = Double.parseDouble(nb_to_convert.getText().toString());
            }catch (Exception e){
                tmp_nb = 0.0;
            }


            // first step, all convert to one unit : the joule
            if (spinner_values.equals(getString(R.string.kilojoule))){
                my_unit = tmp_nb * 1000;
            }else if (spinner_values.equals( getString(R.string.calorie))){
                my_unit = tmp_nb * 4.184;
            }else if (spinner_values.equals( getString(R.string.Kilocalorie))){
                my_unit = tmp_nb  * 4184;
            }else if (spinner_values.equals(getString(R.string.kilowatt_heure))){
                my_unit = tmp_nb * 3600 * 1000;
            }else if (spinner_values.equals(getString(R.string.watt_heure))){
                my_unit = tmp_nb * 3600;
            }else if (spinner_values.equals(getString(R.string.electronvolt))){
                my_unit = tmp_nb /( 1000000 *6.2415) ;
            }else if (spinner_values.equals(getString(R.string.joule))){
                my_unit = tmp_nb;
            }



            //second step : translation of all unit create all element
            tmp_kilojoule = elem.getElem(my_unit /1000,getString(R.string.kilojoule),getString(R.string.smb_kilojoule));
            tmp_joule = elem.getElem(my_unit* 1,getString(R.string.joule),getString(R.string.smb_joule));
            tmp_calorie = elem.getElem(my_unit /4.814,getString(R.string.calorie),getString(R.string.smb_calorie));
            tmp_kilocalorie = elem.getElem(my_unit / 4814,getString(R.string.Kilocalorie),getString(R.string.smb_Kilocalorie));
            tmp_electronvolt= elem.getElem(my_unit * (6.2415063094* 1000000),getString(R.string.electronvolt),getString(R.string.smb_electronvolt));
            tmp_watt_heure = elem.getElem(my_unit / 3600,getString(R.string.watt_heure),getString(R.string.smb_watt_heure));
            tmp_kilowatt_heure = elem.getElem(my_unit / (3600 * 1000),getString(R.string.kilowatt_heure),getString(R.string.smb_kilowatt_heure));

            //reset view with new elem

            main_menu_list.clear();
            main_menu_list.addAll(tmp_joule,tmp_kilojoule,tmp_calorie,tmp_kilocalorie,tmp_watt_heure,tmp_kilowatt_heure,tmp_electronvolt);
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

