package com.example.donutfactory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //Declare all UI components
    private TextView titleView;
    private TextView donutView;
    private Spinner donutSpinner;
    private TextView icingView;
    private Spinner icingSpinner;
    private TextView sprinkleView;
    private Spinner sprinkleSpinner;
    private Button bakeButton;
    private final int DONUTIMAGESLENGTH = 9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //General onCreate stuff
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Set up UI
        titleView = findViewById(R.id.titleView);
        donutView = findViewById(R.id.donutView);
        donutSpinner = findViewById(R.id.donutSpinner);
        icingView = findViewById(R.id.icingView);
        icingSpinner = findViewById(R.id.icingSpinner);
        sprinkleView = findViewById(R.id.sprinkleView);
        sprinkleSpinner = findViewById(R.id.sprinkleSpinner);
        bakeButton = findViewById(R.id.button);

        //Set up spinners with their options
        String[] donutTypes = {"Vanilla", "Chocolate"}; //Array of donut type options
        ArrayAdapter<String> donutAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, donutTypes); //Create adapter for donut spinner
        donutSpinner.setAdapter(donutAdapter);

        String[] icingTypes = {"None","Chocolate", "Strawberry"};
        ArrayAdapter<String> icingAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, icingTypes);
        icingSpinner.setAdapter(icingAdapter);

        String[] sprinkleTypes = {"None", "Chocolate", "White"};
        ArrayAdapter<String> sprinkleAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sprinkleTypes);
        sprinkleSpinner.setAdapter(sprinkleAdapter);

        //Create a shared preferences file and set the number of donuts to 0
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("numberOfDonuts",-1);

        //Create a list of donut images and save it to the shared preferences
        //This will be initialized to 0 for all elements

        int[] donutImages = new int[DONUTIMAGESLENGTH];
        editor.putString("donutImages", Arrays.toString(donutImages));

        editor.apply();

        /*
        //Create 3D donut array from all of the choices
        String[][][] donutArray = new String[donutTypes.length][icingTypes.length][sprinkleTypes.length];
        for (int donutType = 0; donutType < donutTypes.length; donutType++) {
            for (int icingType = 0; icingType < icingTypes.length; icingType++) {
                for (int sprinkleType = 0; sprinkleType < sprinkleTypes.length; sprinkleType++) {
                    donutArray[donutType][icingType][sprinkleType] = donutTypes[donutType] + "-" + icingTypes[icingType] + "-" + sprinkleTypes[sprinkleType];
                }}};
         */

        //Set up button to start new activity and pass values
        bakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bakeDonut();
            }
        });
        }

    private void bakeDonut() {
        //Get values from spinners
        String donutChoice = donutSpinner.getSelectedItem().toString().toLowerCase();
        String icingChoice = icingSpinner.getSelectedItem().toString().toLowerCase();
        String sprinkleChoice = sprinkleSpinner.getSelectedItem().toString().toLowerCase();

        //Create new Intent object and put selected values into it
        Intent intent = new Intent(MainActivity.this, DisplayDonut.class);
        //Put selected choices into Intent
        intent.putExtra("donutChoice", donutChoice);
        intent.putExtra("icingChoice", icingChoice);
        intent.putExtra("sprinkleChoice", sprinkleChoice);

        //Get the number of donuts from the shared preferences and increment it by 1
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int numberOfDonuts = sharedPreferences.getInt("numberOfDonuts", 0);
        //Incremement number of donuts if it does not exceed the amount of ImageViews
        if (numberOfDonuts < DONUTIMAGESLENGTH-1) {numberOfDonuts++;}


        //Save the new number of donuts to the shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("numberOfDonuts", numberOfDonuts);
        editor.apply();


        //Start new activity with the Intent
        startActivity(intent);

    }


}