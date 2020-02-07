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

    private void findScore(int fontSize) {
        switch (fontSize) { //Different font sizes assign to different scores
            case 160: //Uncalculatable 0%
                break;
            case 105: //2.0-2.5ph 10%
                break;
            case 80: //1.75-2.0sph 20%
                break;
            case 66: // 1.5sph 30%
                break;
            case 60: //1.0-1.25sph 40%
                break;
            case 49: //.5sph 50%
                break;
            case 45: //.5sph 60%
                break;
            case 41: //.5sph 80%
                break;
            case 33: //0-.25sph 90%
                break;
            default: //0 - if they get 33pt font correct
                break;

        }
    }
}
