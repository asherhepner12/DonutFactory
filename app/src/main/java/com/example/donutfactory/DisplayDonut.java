package com.example.donutfactory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class DisplayDonut extends AppCompatActivity {

    private ImageView pastryView1;
    private ImageView pastryView2;
    private ImageView pastryView3;
    private ImageView pastryView4;
    private ImageView pastryView5;
    private ImageView pastryView6;
    private ImageView pastryView7;
    private ImageView pastryView8;
    private ImageView pastryView9;

    private Button backButton;
    private Button resetButton;

    /*
    //Textview for debugging the donutImages array
    private TextView textView;
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_donut);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        //Get number of donuts from shared preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        //Get donut choices from MainActivity
        String baseChoice = getIntent().getStringExtra("baseChoice");
        String icingChoice = getIntent().getStringExtra("icingChoice");
        String sprinkleChoice = getIntent().getStringExtra("sprinkleChoice");
        String pastryChoice = getIntent().getStringExtra("pastryChoice");
        //Create donut object from DonutBuilder factory class
        Pastry pastry = PastryBuilder.getPastry(baseChoice, icingChoice, sprinkleChoice, pastryChoice,this);
        //Get the numberOfDonuts made
        int numberOfPastries = sharedPreferences.getInt("numberOfPastries", 0);
        //Get the donutImages as a String from shared preferences
        String pastriesImagesString = sharedPreferences.getString("pastriesImages", "0");
        //Turn the donutImagesString back into an int array
        int[] pastryImages = convertToIntArray(pastriesImagesString);
        //Use numberOfDonuts as an index to put the current donut image into the array
        pastryImages[numberOfPastries] = pastry.getImage();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("pastriesImages", Arrays.toString(pastryImages));
        editor.apply();

        displayPastries(pastryImages);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create new Intent object and put selected values into it
                Intent intent = new Intent(DisplayDonut.this, MainActivity.class);
                //Put selected choices into Intent
                intent.putExtra("baseChoice", baseChoice);
                intent.putExtra("icingChoice", icingChoice);
                intent.putExtra("sprinkleChoice", sprinkleChoice);
                intent.putExtra("pastryChoice" , pastryChoice);

                startActivity(intent);
            }
        });

    }
    private void displayPastries(int[] pastryImages) {         // DisplayPASTRY
        //If an element in the donutImages array is empty (0), replace it with the donut silhouette
        for (int i = 0; i < pastryImages.length; i++) {
            if (pastryImages[i] == 0) {
                pastryImages[i] = PastryBuilder.getPastry("x", "x", "x", "x", this).getImage();
            }
        }
        //Set each ImageView to the corresponding pastry image
        pastryView1 = findViewById(R.id.pastryView1);
        pastryView1.setImageResource(pastryImages[0]);
        pastryView2 = findViewById(R.id.pastryView2);
        pastryView2.setImageResource(pastryImages[1]);
        pastryView3 = findViewById(R.id.pastryView3);
        pastryView3.setImageResource(pastryImages[2]);
        pastryView4 = findViewById(R.id.pastryView4);
        pastryView4.setImageResource(pastryImages[3]);
        pastryView5 = findViewById(R.id.pastryView5);
        pastryView5.setImageResource(pastryImages[4]);
        pastryView6 = findViewById(R.id.pastryView6);
        pastryView6.setImageResource(pastryImages[5]);
        pastryView7 = findViewById(R.id.pastryView7);
        pastryView7.setImageResource(pastryImages[6]);
        pastryView8 = findViewById(R.id.pastryView8);
        pastryView8.setImageResource(pastryImages[7]);
        pastryView9 = findViewById(R.id.pastryView9);
        pastryView9.setImageResource(pastryImages[8]);
    }

    private static int[] convertToIntArray(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        int result[] = new int[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }
}