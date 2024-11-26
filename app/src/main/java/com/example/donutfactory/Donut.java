package com.example.donutfactory;


import android.content.Context;

public class Donut{
    private String donutType;
    private String icingType;
    private String sprinkleType;
    private Context context;

    public Donut(String donutType, String icingType, String sprinkleType, Context context) {
        this.donutType = donutType;
        this.icingType = icingType;
        this.sprinkleType = sprinkleType;
        this.context = context;
    }

    public int getDonutImage() {
        String donutImageName = donutType + "_" + icingType + "_" + sprinkleType;
        int donutImage = context.getResources().getIdentifier(donutImageName, "drawable", context.getPackageName());
        return donutImage;
    }
}
