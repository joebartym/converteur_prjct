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

public class Length extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String spinner_values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);
        Z_elem_list_creator elem = new Z_elem_list_creator();

        final ArrayAdapter<String> main_menu_list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);

        String kilométre = getString(R.string.kilométre);
        String hectométre = getString(R.string.hectométre);
        String décamétre = getString(R.string.décamétre);
        String métre = getString(R.string.métre);
        String décimétre = getString(R.string.décimétre);
        String centimétre = getString(R.string.centimétre);
        String millimétre = getString(R.string.millimétre);
        String micrométre = getString(R.string.micrométre);
        String nanométre = getString(R.string.nanométre);
        String miles = getString(R.string.miles);
        String  yard = getString(R.string.yard);
        String pied = getString(R.string.pied);
        String pouce = getString(R.string.pouce);
        String mile_marin = getString(R.string.mile_marin);

        main_menu_list.addAll(miles,yard,pied,pouce,mile_marin,kilométre,hectométre,décamétre,métre,décimétre,
                centimétre,millimétre,micrométre,nanométre);

        ListView listView = findViewById(R.id.list_length);
        listView.setAdapter(main_menu_list);



        // SPINNER
        Spinner length_spn = findViewById(R.id.length_spn);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.length_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        length_spn.setAdapter(adapter);
        length_spn.setOnItemSelectedListener(this);
        spinner_values = métre;

// BUTTON

        EditText nb_to_convert = findViewById(R.id.length_input);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        nb_to_convert.setText(preferences.getString("PREF_TEMP_INPUTl", nb_to_convert.getText().toString()));

        Button convert_btn = findViewById(R.id.conv_length_btn);
        convert_btn.setOnClickListener((v) -> {

            getPreferences(MODE_PRIVATE)
                    .edit()
                    .putString("PREF_TEMP_INPUTl", nb_to_convert.getText().toString())
                    .apply();
            Double my_unit = 0.0;
            String tmp_nanométre,tmp_micrométre,tmp_millimétre,tmp_centimétre,tmp_décimétre,tmp_métre,tmp_décamétre,tmp_hectométre,tmp_kilométre,tmp_miles,tmp_yard,tmp_pied,tmp_pouce,tmp_mile_marin;
            Double tmp_nb;
            try {
                tmp_nb = Double.parseDouble(nb_to_convert.getText().toString());
            }catch (Exception e){
                tmp_nb = 0.0;
            }


            // first step, all convert to one unit : the metre
            if (spinner_values.equals(getString(R.string.nanométre))){
                my_unit = tmp_nb / (1000 *1000 * 1000);
            }else if (spinner_values.equals( getString(R.string.micrométre))){
                my_unit = tmp_nb / (1000 *1000 );
            }else if (spinner_values.equals( getString(R.string.millimétre))){
                my_unit = tmp_nb  / (1000);
            }else if (spinner_values.equals(getString(R.string.centimétre))){
                my_unit = tmp_nb / (100);
            }else if (spinner_values.equals(getString(R.string.décimétre))){
                my_unit = tmp_nb / (10);
            }else if (spinner_values.equals(getString(R.string.métre))){
                my_unit = tmp_nb  * 1 ;
            }else if (spinner_values.equals( getString(R.string.kilométre))){
                my_unit = tmp_nb  * (1000);
            }else if (spinner_values.equals(getString(R.string.hectométre))){
                my_unit = tmp_nb * (100);
            }else if (spinner_values.equals(getString(R.string.décamétre))){
                my_unit = tmp_nb * (10);
            }else if (spinner_values.equals(getString(R.string.miles))){
                my_unit = tmp_nb  / 0.0006213717 ;
            }else if (spinner_values.equals(getString(R.string.yard))){
                my_unit = tmp_nb / 1.09361;
            }else if (spinner_values.equals(getString(R.string.pied))){
                my_unit = tmp_nb / (3.28084);
            }else if (spinner_values.equals(getString(R.string.pouce))){
                my_unit = tmp_nb  / (39.3701) ;
            }else if (spinner_values.equals(getString(R.string.mile_marin))){
                my_unit = tmp_nb / 0.000539957;
            }



            //second step : translation of all unit create all element
            tmp_kilométre = elem.getElem(my_unit/1000,getString(R.string.kilométre),getString(R.string.smb_kilométre));
            tmp_hectométre = elem.getElem(my_unit  / 100 ,getString(R.string.hectométre),getString(R.string.smb_hectométre));
            tmp_décamétre = elem.getElem(my_unit /10,getString(R.string.décamétre),getString(R.string.smb_décamétre));
            tmp_métre = elem.getElem(my_unit /1,getString(R.string.métre),getString(R.string.smb_métre));
            tmp_décimétre = elem.getElem(my_unit *10,getString(R.string.décimétre),getString(R.string.smb_décimétre));
            tmp_centimétre = elem.getElem(my_unit *100,getString(R.string.centimétre),getString(R.string.smb_centimétre));
            tmp_millimétre = elem.getElem(my_unit *1000,getString(R.string.millimétre),getString(R.string.smb_millimétre));
            tmp_micrométre = elem.getElem(my_unit *1000 * 1000,getString(R.string.micrométre),getString(R.string.smb_micrométre));
            tmp_nanométre = elem.getElem(my_unit *1000*1000*1000,getString(R.string.nanométre),getString(R.string.smb_nanométre));
            tmp_miles =  elem.getElem(my_unit * (0.0006213717),getString(R.string.miles),getString(R.string.smb_miles));
            tmp_yard = elem.getElem(my_unit *  1.09361,getString(R.string.yard),getString(R.string.smb_yard));
            tmp_pied = elem.getElem(my_unit * (3.28084),getString(R.string.pied),getString(R.string.smb_pied));
            tmp_pouce = elem.getElem(my_unit * (39.3701),getString(R.string.pouce),getString(R.string.smb_pouce));
            tmp_mile_marin = elem.getElem(my_unit * 0.000539957,getString(R.string.mile_marin),getString(R.string.smb_mile_marin));

            //reset view with new elem

            main_menu_list.clear();
            main_menu_list.addAll(tmp_miles,tmp_yard,tmp_pied,tmp_pouce,tmp_mile_marin,tmp_kilométre,tmp_hectométre,tmp_décamétre,tmp_métre,tmp_décimétre,tmp_centimétre,
                    tmp_millimétre, tmp_micrométre,tmp_nanométre);
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