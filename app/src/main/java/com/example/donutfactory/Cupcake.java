package com.example.donutfactory;

import android.content.Context;

public class Cupcake extends Pastry{

    public Cupcake (String baseChoice, String icingChoice, String sprinkleChoice, Context context) {
        super(baseChoice, icingChoice, sprinkleChoice, context);

    }

    @Override
    public int getImage() {
        String donutImageName = baseChoice + "_" + icingChoice + "_" + sprinkleChoice + "_cupcake";
        int Image = context.getResources().getIdentifier(donutImageName, "drawable", context.getPackageName());
        return Image;
    }
}