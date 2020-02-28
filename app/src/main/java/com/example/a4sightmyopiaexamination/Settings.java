package com.example.a4sightmyopiaexamination;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {


    Button returnButton;
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

    }

    public void returnHome() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }



}
