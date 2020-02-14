package com.example.a4sightmyopiaexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    private Button beginExamButton;
    private Button settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        settingsBtn= (Button) findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
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
        Intent intent = new Intent(this, InstructionsPage.class);
        startActivity(intent);
    }

    private void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
