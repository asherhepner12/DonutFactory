package com.example.donutfactory;

import android.content.Context;

public class PastryBuilder {
    public static Pastry getPastry(String baseChoice, String icingChoice, String sprinkleChoice, String pastryChoice, Context context) {
        if (pastryChoice.equalsIgnoreCase("Cupcake"))
        {
            return new Cupcake(baseChoice, icingChoice, sprinkleChoice, context);
        }
        if (pastryChoice.equalsIgnoreCase("Donut"))
        {
            return new Donut(baseChoice, icingChoice, sprinkleChoice, context);
        }
        else
        {
            return new Empty(baseChoice, icingChoice, sprinkleChoice, context);
        }

    }


}
