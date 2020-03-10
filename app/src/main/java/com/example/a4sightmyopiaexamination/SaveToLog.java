package com.example.a4sightmyopiaexamination;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.lang.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class SaveToLog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_to_log);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button saveLog=(Button) findViewById(R.id.logbtn2);
        saveLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editName=(EditText)findViewById(R.id.nametxt2);
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
                String fontScore=getIntent().getStringExtra("SCORE");
                String savedString="Name: " + name+"\nDate: " + today + "\nSide: "+ sideEye + "\nScore: " + "20/" + fontScore+"\n";
                //save to log
                myEdit.putString(today,savedString);
                myEdit.apply();
                Intent intent=new Intent(getApplicationContext(),AfterTestAnalysis.class);
                Toast.makeText(getApplicationContext(), "Successfully saved to log", Toast.LENGTH_SHORT);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);

            }
        });
        Button goHome=(Button) findViewById(R.id.homebtn);
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),HomePage.class);
                startActivity(intent);
            }
        });
    }

}
