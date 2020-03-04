package com.example.a4sightmyopiaexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BeforeTest extends AppCompatActivity {

    private Button snellenButton;
    private Button tumblingEButton;
    private Button instructionsButton;
    private Button beginExamButton;
    private boolean buttonSelected;
    private boolean tumblingPressed;
    private boolean snellenPressed;
    public static int settingsChartType= 0;
    private RelativeLayout secondBtns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_test);

        buttonSelected = false;
        tumblingPressed = false;
        snellenPressed = false;
        secondBtns = (RelativeLayout) findViewById(R.id.secondBtns);

        beginExamButton = (Button) findViewById(R.id.startExamBtm);
        beginExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest();
            }
        });


        instructionsButton = (Button) findViewById(R.id.intstructionsBtn);
        instructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startInstructions();
            }
        });


        snellenButton = (Button) findViewById(R.id.snellenButton);
        snellenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSnellen();
            }
        });

        tumblingEButton = (Button) findViewById(R.id.tumblingButton);
        tumblingEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggletumbling();
            }
        });
        snellenButton.setBackgroundResource(android.R.drawable.btn_default);
        tumblingEButton.setBackgroundResource(android.R.drawable.btn_default);

    }

    public void startTest() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startInstructions() {
        Intent intent = new Intent(this, InstructionsPage.class);
        startActivity(intent);
    }

    public void toggletumbling(){
        if(!tumblingPressed && !snellenPressed){
            tumblingPressed = true;
            tumblingEButton.setBackground(getResources().getDrawable(R.drawable.button_border));
            snellenButton.setBackgroundResource(android.R.drawable.btn_default);
            settingsChartType= 1;
        }
        else if(!tumblingPressed  && snellenPressed ){
            snellenPressed = false;
            snellenButton.setBackgroundResource(android.R.drawable.btn_default);
            tumblingPressed = true;
            tumblingEButton.setBackground(getResources().getDrawable(R.drawable.button_border));
            settingsChartType= 1;
        }
        else if(tumblingPressed && !snellenPressed){
            tumblingPressed = false;
            tumblingEButton.setBackgroundResource(android.R.drawable.btn_default);
            snellenButton.setBackgroundResource(android.R.drawable.btn_default);
            settingsChartType= 0;
        }
        else {//tumblingPressed && snellenPressed
            snellenPressed = false;
            snellenButton.setBackgroundResource(android.R.drawable.btn_default);
            tumblingPressed = false;
            tumblingEButton.setBackgroundResource(android.R.drawable.btn_default);
            settingsChartType= 0;
        }
        updateBtns();
    }

    public void toggleSnellen(){
        if(!snellenPressed && !tumblingPressed){
            snellenPressed = true;
            snellenButton.setBackground(getResources().getDrawable(R.drawable.button_border));
            tumblingEButton.setBackgroundResource(android.R.drawable.btn_default);
            settingsChartType= 0;
        }
        else if(!snellenPressed && tumblingPressed){
            snellenPressed = true;
            snellenButton.setBackground(getResources().getDrawable(R.drawable.button_border));
            tumblingPressed = false;
            tumblingEButton.setBackgroundResource(android.R.drawable.btn_default);
            settingsChartType= 0;
        }
        else if(snellenPressed && !tumblingPressed){ //
            snellenPressed = false;
            snellenButton.setBackgroundResource(android.R.drawable.btn_default);
            tumblingEButton.setBackgroundResource(android.R.drawable.btn_default);
            settingsChartType= 0;
        }
        else {//tumblingPressed && snellenPressed
            snellenPressed = false;
            snellenButton.setBackgroundResource(android.R.drawable.btn_default);
            tumblingPressed = false;
            tumblingEButton.setBackgroundResource(android.R.drawable.btn_default);
            settingsChartType= 0;
        }
        updateBtns();
    }

    public void updateBtns(){
//        if(snellenPressed){
//            tumblingEButton.setBackgroundResource(android.R.drawable.btn_default);
//            snellenButton.setBackground(getResources().getDrawable(R.drawable.button_border));
//
//        }
//        if(tumblingPressed){
//            snellenButton.setBackgroundResource(android.R.drawable.btn_default);
//            tumblingEButton.setBackground(getResources().getDrawable(R.drawable.button_border));
//        }

        if(snellenPressed || tumblingPressed){
            secondBtns.setVisibility(View.VISIBLE);
        }
        else{
            secondBtns.setVisibility(View.INVISIBLE);
        }

    }
}
