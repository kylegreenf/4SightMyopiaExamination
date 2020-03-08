package com.example.a4sightmyopiaexamination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    private Button beginExamButton;
    private Button logBtn;
    private Button eyecarebtn;
    private Button instrBtn;
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

        eyecarebtn= (Button) findViewById(R.id.eyecarebtn);
        eyecarebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEyecare();
            }
        });

        instrBtn= (Button) findViewById(R.id.instrBtn);
        instrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstructions();
            }
        });

        beginExamButton = (Button) findViewById(R.id.BeginExamButton);
        beginExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExplanation();
            }
        });

        /*Button resultButton = (Button) findViewById(R.id.resultpage);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seeResult= new Intent(getApplicationContext(),AfterTestAnalysis.class);
                startActivity(seeResult);
            }
        });*/

        checkAudioPermissions();
        isNetworkConnected();




    }

    public void isNetworkConnected() {
        try {
            String command = "ping -c 1 google.com";
            if (Runtime.getRuntime().exec(command).waitFor() == 0) {
                // Internet connected
            }
            else {
                // Internet not connected
                Intent seeResult= new Intent(getApplicationContext(), TurnOnInternet.class);
                startActivity(seeResult);

            }
        } catch (Exception e) {
            //Error with internet

        }

    }


    public void openExplanation() {
        Intent intent = new Intent(this, BeforeTest.class);
        startActivity(intent);
    }

    public void openInstructions() {
        Intent intent = new Intent(this, InstructionsPage.class);
        startActivity(intent);
    }

    public void openEyecare() {
        Intent intent = new Intent(this, Proper_eyecare.class);
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


