package com.example.convert.convert_activities;

public class Z_elem_list_creator {
    private double values;

    void Z_elem_list_creator(){
        values = 0;
    }

    String getElem(double value,String unite, String symbol){ // this class is just to have a fonction where we want
        String elem = "";
        String.format(elem,"%s :    %f  %s",unite,value,symbol);
        return elem;
    }
}
