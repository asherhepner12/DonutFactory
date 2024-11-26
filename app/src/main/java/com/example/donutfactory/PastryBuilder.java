package com.example.donutfactory;

import android.content.Context;

public class PastryBuilder {
    public static Pastry getPastry(String baseChoice, String icingChoice, String sprinkleChoice, String pastryChoice, Context context) {
        if (pastryChoice.equalsIgnoreCase("Cupcake"))
        {
            Cupcake cupcake = new Cupcake(baseChoice, icingChoice, sprinkleChoice, context);
            return cupcake;
        }
        else
        {
            Donut donut = new Donut(baseChoice, icingChoice, sprinkleChoice, context);
            return donut;
        }

    }


}
