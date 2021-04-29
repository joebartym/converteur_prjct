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

public class Angle extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String spinner_values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angle);
        Z_elem_list_creator elem = new Z_elem_list_creator();

        final ArrayAdapter<String> main_menu_list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        String radian = getString(R.string.radian); // 0 to 2pi
        String gradian = getString(R.string.gradian); // 0 to 400
        String degree = getString(R.string.degree); // 0 to 360
        String turn = getString(R.string.turn); // 0 to 1
        String angular_mil = getString(R.string.angular_mil); // 0 6400
        main_menu_list.addAll(radian,gradian,degree,turn,angular_mil);
        ListView listView = findViewById(R.id.list_angle);
        listView.setAdapter(main_menu_list);



 // SPINNER
        Spinner angle_spn = findViewById(R.id.angle_unit_1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.angle_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        angle_spn.setAdapter(adapter);
        angle_spn.setOnItemSelectedListener(this);
        spinner_values = angular_mil;

// BUTTON

        EditText nb_to_convert = findViewById(R.id.angle_input);

        Button convert_btn = findViewById(R.id.conv_a_btn);
        convert_btn.setOnClickListener((v) -> {
            Double my_unit = 0.0;
            String tmp_radian,tmp_degree,tmp_gradian,tmp_turn,tmp_angular_mil; Double tmp_nb;
            try {
                tmp_nb = Double.parseDouble(nb_to_convert.getText().toString());
            }catch (Exception e){
                tmp_nb = 0.0;
            }


            Double pi  = Math.PI;
            // first step, all convert to one unit : the turn
            if (spinner_values.equals(getString(R.string.radian))){
                my_unit = tmp_nb /(2 * pi);
            }else if (spinner_values.equals( getString(R.string.gradian))){
                my_unit = tmp_nb /400;
            }else if (spinner_values.equals( getString(R.string.degree))){
                my_unit = tmp_nb /360;
            }else if (spinner_values.equals(getString(R.string.angular_mil))){
                my_unit = tmp_nb /6400;
            }else if (spinner_values.equals(getString(R.string.turn))){
                my_unit = tmp_nb;
            }
            my_unit = my_unit %1; // because an angle is periodic

            //second step : translation of all unit create all element
            tmp_radian = elem.getElem(my_unit * 2 * pi,getString(R.string.radian),getString(R.string.smb_radian));
            tmp_gradian = elem.getElem(my_unit * 400,getString(R.string.gradian),getString(R.string.smb_gradian));
            tmp_degree = elem.getElem(my_unit * 360,getString(R.string.degree),getString(R.string.smb_degree));
            tmp_angular_mil = elem.getElem(my_unit * 6400,getString(R.string.angular_mil),getString(R.string.smb_angular_mil));
            tmp_turn = elem.getElem(my_unit * 1,getString(R.string.turn),getString(R.string.smb_turn));

            //reset view with new elem

            main_menu_list.clear();
            main_menu_list.addAll(tmp_radian,tmp_gradian,tmp_degree,tmp_turn,tmp_angular_mil);
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