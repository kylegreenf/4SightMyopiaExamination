package com.example.a4sightmyopiaexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InstructionsPage extends AppCompatActivity {

    private Button beginExamButton;
    private TextView instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions_page);

        beginExamButton = (Button) findViewById(R.id.examStart);
        beginExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest();
            }
        });


        instructions = (TextView)findViewById(R.id.instructionsText);
        if(BeforeTest.settingsChartType == 0){
            instructions.setText(R.string.snellen_instructions_text);
        }else {
            instructions.setText(R.string.tumbling_instructions_text);
        }

    }


    public void startTest() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
