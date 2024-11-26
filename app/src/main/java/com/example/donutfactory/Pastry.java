package com.example.donutfactory;

import android.content.Context;


public abstract class Pastry {
    protected String baseChoice;
    protected String icingChoice;
    protected String sprinkleChoice;
    protected Context context;

    public Pastry(String baseChoice, String icingChoice, String sprinkleChoice, Context context) {
        this.baseChoice = baseChoice;
        this.icingChoice = icingChoice;
        this.sprinkleChoice = sprinkleChoice;
        this.context = context;
    }

    public abstract int getImage();
}
