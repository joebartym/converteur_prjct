package com.example.convert.convert_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.convert.R;

public class Speed extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
        String spinner_values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);
        Z_elem_list_creator elem = new Z_elem_list_creator();

        final ArrayAdapter<String> main_menu_list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);

        String metre_seconde = getString(R.string.metre_seconde);
        String kilometre_heur = getString(R.string.kilometre_heur);
        String miles_heur = getString(R.string.miles_heur);
        String noeud = getString(R.string.noeud);

        main_menu_list.addAll(kilometre_heur,metre_seconde,miles_heur,noeud);

        ListView listView = findViewById(R.id.list_speed);
        listView.setAdapter(main_menu_list);



        // SPINNER
        Spinner speed_spn = findViewById(R.id.speed_unit_1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.speed_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speed_spn.setAdapter(adapter);
        speed_spn.setOnItemSelectedListener(this);
        spinner_values = kilometre_heur;

// BUTTON

        EditText nb_to_convert = findViewById(R.id.speed_input);

        Button convert_btn = findViewById(R.id.conv_speed_btn);
        convert_btn.setOnClickListener((v) -> {
            Double my_unit = 0.0;
            String tmp_metre_seconde,tmp_kilometre_heur,tmp_miles_heur,tmp_noeud;
            Double tmp_nb;
            try {
                tmp_nb = Double.parseDouble(nb_to_convert.getText().toString());
            }catch (Exception e){
                tmp_nb = 0.0;
            }


            // first step, all convert to one unit : the metre
            if (spinner_values.equals(getString(R.string.metre_seconde))){
                my_unit = tmp_nb *1;
            }else if (spinner_values.equals( getString(R.string.kilometre_heur))){
                my_unit = tmp_nb / (3.6);
            }else if (spinner_values.equals( getString(R.string.miles_heur))){
                my_unit = tmp_nb  / (2.236936);
            }else if (spinner_values.equals(getString(R.string.noeud))){
                my_unit = tmp_nb / (1.943844);
            }


            //second step : translation of all unit create all element
            tmp_kilometre_heur = elem.getElem(my_unit * 3.6,getString(R.string.kilometre_heur),getString(R.string.smb_kilometre_heur));
            tmp_miles_heur = elem.getElem(my_unit  * 2.236936,getString(R.string.miles_heur),getString(R.string.smb_miles_heur));
            tmp_noeud = elem.getElem(my_unit *1.943844,getString(R.string.noeud),getString(R.string.smb_noeud));
            tmp_metre_seconde = elem.getElem(my_unit /1,getString(R.string.metre_seconde),getString(R.string.smb_metre_seconde));

            //reset view with new elem

            main_menu_list.clear();
            main_menu_list.addAll(tmp_kilometre_heur,tmp_metre_seconde,tmp_miles_heur,tmp_noeud);
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