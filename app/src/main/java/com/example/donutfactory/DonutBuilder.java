package com.example.donutfactory;

import android.content.Context;

public class DonutBuilder {
    public static Donut getDonut(String donutChoice, String icingChoice, String sprinkleChoice, Context context) {
    Donut donut = new Donut(donutChoice, icingChoice, sprinkleChoice, context);
    return donut;
    }


}
