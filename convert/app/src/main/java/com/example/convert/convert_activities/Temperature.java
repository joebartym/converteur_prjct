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

public class Temperature extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String spinner_values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        Z_elem_list_creator elem = new Z_elem_list_creator();

        final ArrayAdapter<String> main_menu_list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);

        String fahrenheit = getString(R.string.fahrenheit);
        String celsius = getString(R.string.celsius);
        String kelvin = getString(R.string.kelvin);
        main_menu_list.addAll(fahrenheit,celsius,kelvin);

        ListView listView = findViewById(R.id.list_temp);
        listView.setAdapter(main_menu_list);



        // SPINNER
        Spinner temp_spn = findViewById(R.id.temp_unit_1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.temp_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        temp_spn.setAdapter(adapter);
        temp_spn.setOnItemSelectedListener(this);
        spinner_values = celsius;

// BUTTON

        EditText nb_to_convert = findViewById(R.id.temp_input);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        nb_to_convert.setText(preferences.getString("PREF_TEMP_INPUTte", nb_to_convert.getText().toString()));

        Button convert_btn = findViewById(R.id.conv_temp_btn);
        convert_btn.setOnClickListener((v) -> {

            getPreferences(MODE_PRIVATE)
                    .edit()
                    .putString("PREF_TEMP_INPUTte", nb_to_convert.getText().toString())
                    .apply();
            Double my_unit = 0.0;
            String tmp_fahrenheit,tmp_celsius,tmp_kelvin;
            Double tmp_nb;
            try {
                tmp_nb = Double.parseDouble(nb_to_convert.getText().toString());
            }catch (Exception e){
                tmp_nb = 0.0;
            }


            // first step, all convert to one unit : the celsius
            if (spinner_values.equals(getString(R.string.fahrenheit))){
                my_unit = (tmp_nb -32) * 5/9;
            }else if (spinner_values.equals( getString(R.string.celsius))){
                my_unit = tmp_nb * (1);
            }else if (spinner_values.equals( getString(R.string.kelvin))){
                my_unit = tmp_nb  -273.15;
            }


            //second step : translation of all unit create all element
            tmp_fahrenheit = elem.getElem((my_unit * 9/5) + 32,getString(R.string.fahrenheit),getString(R.string.smb_fahrenheit));
            tmp_celsius = elem.getElem(my_unit  * 1,getString(R.string.celsius),getString(R.string.smb_celsius));
            tmp_kelvin= elem.getElem(my_unit +273.15,getString(R.string.kelvin),getString(R.string.smb_kelvin));

            //reset view with new elem

            main_menu_list.clear();
            main_menu_list.addAll(tmp_fahrenheit,tmp_celsius,tmp_kelvin);
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