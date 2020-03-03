package com.example.a4sightmyopiaexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;
import java.lang.*;
import java.text.*;

import org.w3c.dom.Text;

public class AfterTestAnalysis extends AppCompatActivity {

    TextView score;
    TextView explanation;
    TextView diop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_test_analysis);
        score = (TextView) findViewById(R.id.scoreBig);
        diop=(TextView) findViewById(R.id.dioptxt);
        Intent intent = getIntent();
        final int fontScore = 66;//intent.getIntExtra(MainActivity.FINAL_FONT_SIZE, 0);
        //findScore(fontScore);
        score.setText("Your result: 20/"+ findNum(fontScore));
        diop.setText(findDiop(fontScore));
        explanation = (TextView) findViewById(R.id.resexplanation);
        explanation.setText(findExp(fontScore));
        Button treatment=(Button) findViewById(R.id.treatmentbtn);
        treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent treatmentIntent= new Intent(getApplicationContext(),TreatmentOptions.class);
                startActivity(treatmentIntent);
            }
        });

        Button logbttn=(Button) findViewById(R.id.logbtn);
        logbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),SaveToLog.class);
                intent.putExtra("SCORE",findNum(fontScore));
                startActivity(intent);
            }
        });
    }

    private String findExp(int fontSize) {
        switch (fontSize) { //Different font sizes assign to different scores
            case 160: //incalculable 0%
                return getResources().getString(R.string.worst);
            case 105: //2.0-2.5ph 10%
                return getResources().getString(R.string.info20200);
            case 80: //1.75-2.0sph 20%
                return getResources().getString(R.string.info20100);
            case 66: // 1.5sph 30%
                return getResources().getString(R.string.info2070);
            case 60: //1.0-1.25sph 40%
                return getResources().getString(R.string.info2050);
            case 49: //.5sph 50%
                return getResources().getString(R.string.info2040);
            case 45: //.5sph 60%
                return getResources().getString(R.string.info2030);
            case 41: //.5sph 80%
                return getResources().getString(R.string.info2025);
            case 33: //0-.25sph 90%
                return getResources().getString(R.string.info2020);
            default: //0 - if they get 33pt font correct
                return getResources().getString(R.string.info2020);
        }
    }

    private String findNum(int fontSize) {
        switch (fontSize) { //Different font sizes assign to different scores
            case 160: //incalculable 0%
                return "200+";
            case 105: //2.0-2.5ph 10%
                return "200";
            case 80: //1.75-2.0sph 20%
                return "100";
            case 66: // 1.5sph 30%
                return "70";
            case 60: //1.0-1.25sph 40%
                return "50";
            case 49: //.5sph 50%
                return "40";
            case 45: //.5sph 60%
                return "30";
            case 41: //.5sph 80%
                return "25";
            case 33: //0-.25sph 90%
                return "20";
            default: //0 - if they get 33pt font correct
                return "20";
        }
    }

    private String findDiop(int fontSize) {
        switch (fontSize) { //Different font sizes assign to different scores
            case 160: //incalculable 0%
                return "Less than -2.50 diopters";
            case 105: //2.0-2.5ph 10%
                return "Between -2.50 and -2.00 diopters";
            case 80: //1.75-2.0sph 20%
                return "Between -2.00 and -1.75 diopters";
            case 66: // 1.5sph 30%
                return "Around -1.50 diopters";
            case 60: //1.0-1.25sph 40%
                return "Between -1.25 and -1.00 diopters";
            case 49: //.5sph 50%
                return "Around -0.50 diopters";
            case 45: //.5sph 60%
                return "Around -0.50 diopters";
            case 41: //.5sph 80%
                return "Around -0.50 diopters";
            case 33: //0-.25sph 90%
                return "Between -0.25 and 0.00 diopters";
            default: //0 - if they get 33pt font correct
                return "Around 0.00 diopters";
        }
    }
}
