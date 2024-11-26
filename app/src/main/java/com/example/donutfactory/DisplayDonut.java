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

    private ImageView donutView1;
    private ImageView donutView2;
    private ImageView donutView3;
    private ImageView donutView4;
    private ImageView donutView5;
    private ImageView donutView6;
    private ImageView donutView7;
    private ImageView donutView8;
    private ImageView donutView9;

    private Button backButton;

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



        //Get sharedPreferences object
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        //Get donut choices from MainActivity
        String donutChoice = getIntent().getStringExtra("donutChoice");
        String icingChoice = getIntent().getStringExtra("icingChoice");
        String sprinkleChoice = getIntent().getStringExtra("sprinkleChoice");
        //Create donut object from DonutBuilder factory class
        Donut donut = DonutBuilder.getDonut(donutChoice, icingChoice, sprinkleChoice, this);
        //Get the numberOfDonuts made
        int numberOfDonuts = sharedPreferences.getInt("numberOfDonuts", 0);
        //Get the donutImages as a String from shared preferences
        String donutImagesString = sharedPreferences.getString("donutImages", "0");
        //Turn the donutImagesString back into an int array
        int[] donutImages = convertToIntArray(donutImagesString);
        //Use numberOfDonuts as an index to put the current donut image into the array
        donutImages[numberOfDonuts] = donut.getDonutImage();

        //Save the new donutImages array to shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("donutImages", Arrays.toString(donutImages));
        editor.apply();

        displayDonuts(donutImages);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /*
        //Debugging the donutImages array
        textView = findViewById(R.id.textView);
        textView.setText(sharedPreferences.getString("donutImages", "0"));

         */
    }
    private void displayDonuts(int[] donutImages) {
        //If an element in the donutImages array is empty (0), replace it with the donut silhouette
        for (int i = 0; i < donutImages.length; i++) {
            if (donutImages[i] == 0) {
                donutImages[i] = DonutBuilder.getDonut("x", "x", "x", this).getDonutImage();
            }
        }
        //Set each ImageView to the corresponding donut image
        donutView1 = findViewById(R.id.donutView1);
        donutView1.setImageResource(donutImages[0]);
        donutView2 = findViewById(R.id.donutView2);
        donutView2.setImageResource(donutImages[1]);
        donutView3 = findViewById(R.id.donutView3);
        donutView3.setImageResource(donutImages[2]);
        donutView4 = findViewById(R.id.donutView4);
        donutView4.setImageResource(donutImages[3]);
        donutView5 = findViewById(R.id.donutView5);
        donutView5.setImageResource(donutImages[4]);
        donutView6 = findViewById(R.id.donutView6);
        donutView6.setImageResource(donutImages[5]);
        donutView7 = findViewById(R.id.donutView7);
        donutView7.setImageResource(donutImages[6]);
        donutView8 = findViewById(R.id.donutView8);
        donutView8.setImageResource(donutImages[7]);
        donutView9 = findViewById(R.id.donutView9);
        donutView9.setImageResource(donutImages[8]);
    }

    //Converts a string into an int array; used to convert the donutImagesString back into an int array
    private static int[] convertToIntArray(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        int result[] = new int[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }
}