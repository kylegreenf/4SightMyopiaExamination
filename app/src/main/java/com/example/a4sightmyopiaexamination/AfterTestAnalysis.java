package com.example.a4sightmyopiaexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AfterTestAnalysis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_test_analysis);

        int fontScore = 0;
        Intent intent = getIntent();
        fontScore = intent.getIntExtra(MainActivity.FINAL_FONT_SIZE, 0);

        updateScore(fontScore);
    }

    private void updateScore(int fontSize) {
        TextView score = (TextView) findViewById(R.id.results);
        String result = Integer.toString(fontSize);
        score.setText("You can read letters at " + result + " pt font size. Right now that means nothing!");
    }
}
