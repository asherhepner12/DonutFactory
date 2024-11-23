package com.example.donutfactory;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private TextView titleView;
    private TextView donutView;
    private Spinner donutSpinner;
    private TextView icingView;
    private Spinner icingSpinner;
    private TextView sprinkleView;
    private Spinner sprinkleSpinner;
    private Button bakeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        String[] donutTypes = {"Vanilla", "Chocolate", "Strawberry"};
        ArrayAdapter<String> donutAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, donutTypes);
        donutSpinner.setAdapter(donutAdapter);

        String[] icingTypes = {"Green", "Blue", "Red", "White", "None"};
        ArrayAdapter<String> icingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, icingTypes);
        icingSpinner.setAdapter(icingAdapter);

        String[] sprinkleTypes = {"Rainbow", "Brown", "Red","Blue", "None"};
        ArrayAdapter<String> sprinkleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sprinkleTypes);
        sprinkleSpinner.setAdapter(sprinkleAdapter);

        bakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {bakeDonut();}
        });
        }

    private void bakeDonut() {
        String donutType = donutSpinner.getSelectedItem().toString();
        String icingType = icingSpinner.getSelectedItem().toString();
        String sprinkleType = sprinkleSpinner.getSelectedItem().toString();

        Intent intent = new Intent(MainActivity.this, DisplayDonut.class);
        intent.putExtra("donutType", donutType);
        intent.putExtra("icingType", icingType);
        intent.putExtra("sprinkleType", sprinkleType);
        startActivity(intent);

    }


}