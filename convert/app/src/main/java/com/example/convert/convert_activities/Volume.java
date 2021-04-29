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

public class Volume extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String spinner_values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);
        Z_elem_list_creator elem = new Z_elem_list_creator();

        final ArrayAdapter<String> main_menu_list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);

        String litre = getString(R.string.litre);
        String once_liquide_usa = getString(R.string.once_liquide_usa);
        String once_liquide_uk = getString(R.string.once_liquide_uk);
        String pinte = getString(R.string.pinte);
        String metre_cube = getString(R.string.metre_cube);

        main_menu_list.addAll(litre,pinte,once_liquide_uk,once_liquide_usa,metre_cube);

        ListView listView = findViewById(R.id.list_volume);
        listView.setAdapter(main_menu_list);



        // SPINNER
        Spinner volume_spn = findViewById(R.id.volume_unit_1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.volume_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        volume_spn.setAdapter(adapter);
        volume_spn.setOnItemSelectedListener(this);
        spinner_values = litre;

// BUTTON

        EditText nb_to_convert = findViewById(R.id.volume_input);

        Button convert_btn = findViewById(R.id.conv_vol_btn);
        convert_btn.setOnClickListener((v) -> {
            Double my_unit = 0.0;
            String tmp_litre,tmp_once_liquide_usa,tmp_once_liquide_uk,tmp_pinte,tmp_metre_cube;
            Double tmp_nb;
            try {
                tmp_nb = Double.parseDouble(nb_to_convert.getText().toString());
            }catch (Exception e){
                tmp_nb = 0.0;
            }


            // first step, all convert to one unit : the litre
            if (spinner_values.equals(getString(R.string.litre))){
                my_unit = tmp_nb *1;
            }else if (spinner_values.equals( getString(R.string.metre_cube))){
                my_unit = tmp_nb * 1000;
            }else if (spinner_values.equals( getString(R.string.once_liquide_uk))){
                my_unit = tmp_nb  / (35.1950797278);
            }else if (spinner_values.equals(getString(R.string.once_liquide_usa))){
                my_unit = tmp_nb / (33.81402270);
            }else if (spinner_values.equals(getString(R.string.pinte))){
                my_unit = tmp_nb / (1.759753986);
            }


            //second step : translation of all unit create all element
            tmp_litre= elem.getElem(my_unit * 1,getString(R.string.litre),getString(R.string.smb_litre));
            tmp_metre_cube = elem.getElem(my_unit  /1000,getString(R.string.metre_cube),getString(R.string.smb_metre_cube));
            tmp_once_liquide_uk= elem.getElem(my_unit *35.1950797278,getString(R.string.once_liquide_uk),getString(R.string.smb_once_liquide_uk));
            tmp_once_liquide_usa= elem.getElem(my_unit *33.81402270,getString(R.string.once_liquide_usa),getString(R.string.smb_once_liquide_usa));
            tmp_pinte= elem.getElem(my_unit *1.759753986,getString(R.string.pinte),getString(R.string.smb_pinte));

            //reset view with new elem

            main_menu_list.clear();
            main_menu_list.addAll(tmp_litre,tmp_pinte,tmp_once_liquide_uk,tmp_once_liquide_usa,tmp_metre_cube);
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