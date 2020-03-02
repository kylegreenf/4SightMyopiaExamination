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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_test_analysis);
        score = (TextView) findViewById(R.id.results);
        Intent intent = getIntent();
        final int fontScore = intent.getIntExtra(MainActivity.FINAL_FONT_SIZE, 0);
        findScore(fontScore);
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

        Button saveLog=(Button) findViewById(R.id.logbtn);
        saveLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editName=(EditText)findViewById(R.id.nametxt);
                String name=editName.getText().toString();
                if(name.length()==0){
                    Toast.makeText(getApplicationContext(),"Please enter a name for this log entry.", Toast.LENGTH_LONG).show();
                }
                String sideEye;
                RadioGroup side=(RadioGroup) findViewById(R.id.whicheye);
                int selectedid=side.getCheckedRadioButtonId();
                RadioButton selectedside=(RadioButton) findViewById(selectedid);
                String lefteye="Left Eye";
                if (selectedside.getText().equals(lefteye)){
                    sideEye="Left Eye";
                }
                else{
                    sideEye="Right Eye";
                }
                DateFormat currentdate=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date=new Date();

                SharedPreferences sharedPref = getSharedPreferences("RESULTLOG",MODE_PRIVATE);
                SharedPreferences.Editor myEdit=sharedPref.edit();
                //HERE SAVE TO LOG
                String today=currentdate.format(date);
                String savedString="Name: " + name+"\nDate: " + today + "\nSide: "+ sideEye + "\nScore: " + "20/" + findNum(fontScore)+"\n";
                //save to log
                myEdit.putString(today,savedString);

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


    private void findScore(int fontSize) {
        String sphConversion = "";
        switch (fontSize) { //Different font sizes assign to different scores
            case 160: //incalculable 0%
                sphConversion = "an incalculable amount of";
                break;
            case 105: //2.0-2.5ph 10%
                sphConversion = "2.0-2.5ph";
                break;
            case 80: //1.75-2.0sph 20%
                sphConversion = "1.75-2.0sph";
                break;
            case 66: // 1.5sph 30%
                sphConversion = "1.5sph";
                break;
            case 60: //1.0-1.25sph 40%
                sphConversion = "1.0-1.25sph";
                break;
            case 49: //.5sph 50%
                sphConversion = ".5sph";
                break;
            case 45: //.5sph 60%
                sphConversion = ".5sph";
                break;
            case 41: //.5sph 80%
                sphConversion = ".5sph";
                break;
            case 33: //0-.25sph 90%
                sphConversion = "0-.25sph";
                break;
            default: //0 - if they get 33pt font correct
                sphConversion = "no";
                break;

        }
        score.setText("We calculated that your vision is in need of (approximately) " + sphConversion + " correction");
    }
}
