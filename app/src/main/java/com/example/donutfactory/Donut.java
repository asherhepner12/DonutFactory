package com.example.donutfactory;


import android.content.Context;

public class Donut extends Pastry{

    public Donut (String baseChoice, String icingChoice, String sprinkleChoice, Context context) {
        super(baseChoice, icingChoice, sprinkleChoice, context);

    }

    @Override
    public int getImage() {
        String donutImageName = baseChoice + "_" + icingChoice + "_" + sprinkleChoice + "_donut";
        int Image = context.getResources().getIdentifier(donutImageName, "drawable", context.getPackageName());
        return Image;
    }
}
