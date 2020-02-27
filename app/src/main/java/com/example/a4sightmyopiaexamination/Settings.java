package com.example.a4sightmyopiaexamination;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    public static int settingsChartType = 0;

    Button returnButton;
    RadioGroup chartGroup;
    RadioButton currentSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        returnButton = (Button) findViewById(R.id.returnButton);

        returnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               returnHome();
            }
        });

        chartGroup = findViewById(R.id.ChartTestGroup);

    }

    public void returnHome() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void updateChartType(View v) {
        int radioId = chartGroup.getCheckedRadioButtonId();
        currentSelected = findViewById(radioId);

        if (currentSelected.getText().equals("Tumbling E")) {
            settingsChartType = 1;

        }
        else {
            settingsChartType = 0;

        }

    }



}
