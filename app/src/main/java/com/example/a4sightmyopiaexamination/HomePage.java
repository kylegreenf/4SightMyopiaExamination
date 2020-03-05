package com.example.a4sightmyopiaexamination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    private Button beginExamButton;
    private Button logBtn;
    private Button sliderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        logBtn= (Button) findViewById(R.id.logBtn);
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLog();
            }
        });

        beginExamButton = (Button) findViewById(R.id.BeginExamButton);
        beginExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExplanation();
            }
        });

        Button resultButton = (Button) findViewById(R.id.resultpage);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seeResult= new Intent(getApplicationContext(),AfterTestAnalysis.class);
                startActivity(seeResult);
            }
        });

        checkAudioPermissions();


    }

    public void openExplanation() {
        Intent intent = new Intent(this, BeforeTest.class);
        startActivity(intent);
    }

    private void openLog() {
        Intent intent = new Intent(this, Log.class);
        startActivity(intent);
    }

    private void checkAudioPermissions() {
        if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
            requestPermission();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.RECORD_AUDIO}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }
        else {
            requestPermission();
        }
        //Could run a check if granted or not
    }


}


