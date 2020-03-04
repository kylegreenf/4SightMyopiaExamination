package com.example.a4sightmyopiaexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
                Intent seelog=new Intent(getApplicationContext(),Log.class);
                startActivity(seelog);
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


    }

    public void openExplanation() {
        Intent intent = new Intent(this, BeforeTest.class);
        startActivity(intent);
    }

    private void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }


}


