package com.example.a4sightmyopiaexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AfterTestAnalysis extends AppCompatActivity {

    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_test_analysis);

        score = (TextView) findViewById(R.id.results);

        int fontScore = 0;
        Intent intent = getIntent();
        fontScore = intent.getIntExtra(MainActivity.FINAL_FONT_SIZE, 0);

        findScore(fontScore);

        Button treatment=(Button) findViewById(R.id.treatmentbtn);
        treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent treatmentIntent= new Intent(getApplicationContext(),TreatmentOptions.class);
                startActivity(treatmentIntent);
            }
        });

        Button eyecareBtn=(Button) findViewById(R.id.eyecareButton);
        treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent properEyecareIntent= new Intent(getApplicationContext(),Proper_eyecare.class);
                startActivity(properEyecareIntent);
            }
        });
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
