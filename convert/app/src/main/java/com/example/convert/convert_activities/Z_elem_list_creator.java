package com.example.convert.convert_activities;
import androidx.appcompat.app.AppCompatActivity;

import com.example.convert.R;



public class Z_elem_list_creator extends AppCompatActivity{
    private double values;

    void Z_elem_list_creator(){
        values = 0;
    }

    String getElem(double value,String unite, String symbol){ // this class is just to have a fonction where we want
        String elem = "";
        elem = String.format("%s :    %f  %s",unite,value,symbol);
        return elem;
    }
    String Fulltime_get(long  value){
        String elem = "", all = "All :  ";
        long years,days,hour,min,sec;
        years = value / 31536000;
        value %= 31536000;
        days = value / 86400;
        value %= 86400;
        hour = value / 3600;
        value %= 3600;
        min = value / 60;
        sec = value %60;
        elem =elem +all + years+" a "+ days+" j "+hour+" h "+ min+ " min "+sec+" sec";
        /*String.format("%s :   %d %s %d %s %d %s %d %s %d %d",all, years,"a",
                days,"j",hour,"h", min,
                "min",sec,"sec");*/
        return elem;
    }


}
