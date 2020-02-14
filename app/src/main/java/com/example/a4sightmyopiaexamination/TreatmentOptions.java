package com.example.a4sightmyopiaexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TreatmentOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_options);

        Button eyeglass = (Button) findViewById(R.id.glassbtn);
        eyeglass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seeGlasses=new Intent(getApplicationContext(),Glasses.class);
                startActivity(seeGlasses);
            }
        });


        Button lens= (Button) findViewById(R.id.lensbtn);
        lens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seeLens=new Intent(getApplicationContext(),Contacts.class);
                startActivity(seeLens);
            }
        });


        Button lasik= (Button) findViewById(R.id.lasikbtn);
        lasik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seeLasik= new Intent(getApplicationContext(),Lasik.class);
                startActivity(seeLasik);
            }
        });

        Button prk= (Button) findViewById(R.id.prkbtn);
        prk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seePrk= new Intent(getApplicationContext(),PRK.class);
                startActivity(seePrk);
            }
        });

        Button orthok= (Button) findViewById(R.id.orthokbtn);
        orthok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seeOK=new Intent(getApplicationContext(),Orthok.class);
                startActivity(seeOK);
            }
        });
    }
}
