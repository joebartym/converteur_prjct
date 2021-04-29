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

import com.example.convert.MainActivity;
import com.example.convert.R;

public class Mass extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String spinner_values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mass);
        Z_elem_list_creator elem = new Z_elem_list_creator();

        final ArrayAdapter<String> main_menu_list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);

        String tone = getString(R.string.tone);
        String kilogramme = getString(R.string.kilogramme);
        String livre = getString(R.string.livre);
        String once = getString(R.string.once);
        String gramme = getString(R.string.gramme);
        String miligramme = getString(R.string.miligramme);

        main_menu_list.addAll(kilogramme,gramme,miligramme,livre,once,tone);

        ListView listView = findViewById(R.id.list_mass);
        listView.setAdapter(main_menu_list);



        // SPINNER
        Spinner mass_spn = findViewById(R.id.mass_spn);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.mass_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mass_spn.setAdapter(adapter);
        mass_spn.setOnItemSelectedListener(this);
        spinner_values = kilogramme;

// BUTTON

        EditText nb_to_convert = findViewById(R.id.mass_input);

        Button convert_btn = findViewById(R.id.conv_mass_btn);
        convert_btn.setOnClickListener((v) -> {
            Double my_unit = 0.0;
            String tmp_tone, tmp_kilogramme, tmp_livre, tmp_once,tmp_gramme,tmp_miligramme;
            Double tmp_nb;
            try {
                tmp_nb = Double.parseDouble(nb_to_convert.getText().toString());
            }catch (Exception e){
                tmp_nb = 0.0;
            }


            // first step, all convert to one unit : the kilogramme
            if (spinner_values.equals(getString(R.string.tone))){
                my_unit = tmp_nb *1000;
            }else if (spinner_values.equals( getString(R.string.livre))){
                my_unit = tmp_nb / (2.2046226218478 );
            }else if (spinner_values.equals( getString(R.string.once))){
                my_unit = tmp_nb  / (35.27396194958);
            }else if (spinner_values.equals(getString(R.string.gramme))){
                my_unit = tmp_nb / (1000);
            }else if (spinner_values.equals(getString(R.string.miligramme))){
                my_unit = tmp_nb / (1000 * 1000);
            }else if (spinner_values.equals(getString(R.string.kilogramme))){
                my_unit = tmp_nb  * 1 ;
            }



            //second step : translation of all unit create all element
            tmp_gramme = elem.getElem(my_unit * 1000,getString(R.string.gramme),getString(R.string.smb_gramme));
            tmp_kilogramme = elem.getElem(my_unit * 1,getString(R.string.kilogramme),getString(R.string.smb_kilogramme));
            tmp_livre = elem.getElem(my_unit * 2.2046226218478 ,getString(R.string.livre),getString(R.string.smb_livre));
            tmp_miligramme = elem.getElem(my_unit * 1000 * 1000,getString(R.string.miligramme),getString(R.string.smb_miligramme));
            tmp_once = elem.getElem(my_unit *35.27396194958,getString(R.string.once),getString(R.string.smb_once));
            tmp_tone = elem.getElem(my_unit /1000,getString(R.string.tone),getString(R.string.smb_tone));

            //reset view with new elem

            main_menu_list.clear();
            main_menu_list.addAll(tmp_kilogramme,tmp_gramme,tmp_miligramme,tmp_livre,tmp_once,tmp_tone);
            listView.setAdapter(main_menu_list);
        });






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