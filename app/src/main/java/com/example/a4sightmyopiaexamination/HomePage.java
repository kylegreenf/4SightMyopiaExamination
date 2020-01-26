package com.example.a4sightmyopiaexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    private Button beginExamButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        beginExamButton = (Button) findViewById(R.id.BeginExamButton);
        beginExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExplanation();
            }
        });
    }

    public void openExplanation() {
        Intent intent = new Intent(this, InstructionsPage.class);
        startActivity(intent);
    }
}
