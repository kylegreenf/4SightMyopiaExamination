package com.example.a4sightmyopiaexamination;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;
    private TextView letterTesting;
    private String correctAnswer;
    private MediaPlayer correctGuess;
    private MediaPlayer wrongGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adding sounds for correct or incorrect guess
        correctGuess = MediaPlayer.create(this, R.raw.correct);
        wrongGuess = MediaPlayer.create(this, R.raw.wrong);


        // Changing the text results
        txtResult = (TextView) findViewById(R.id.txtResult);
        letterTesting = (TextView) findViewById(R.id.letterTesting);
        letterTesting.setText(generateRandomLetter());
    }

    public void getSpeechInput(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result.get(0) != null) {
                        txtResult.setText(result.get(0));
                        checkCorrectGuess(result.get(0), correctAnswer);
                        letterTesting.setText(generateRandomLetter());
                    }
                }
                break;
        }
    }

    public String generateRandomLetter() {
        char letter;
        Random r = new Random();
        int l = r.nextInt(26) + 65;
        letter = (char) l;
        String letterToString = letter + "";
        correctAnswer = letterToString;
        return letterToString;
    }

    private void checkCorrectGuess(String result, String correctAnswer) {
        String guess = result;
        if (guess.toLowerCase().equals(correctAnswer.toLowerCase())) {
            correctGuess.start(); // Play a noise
            Toast.makeText(this, "Correct input!", Toast.LENGTH_SHORT).show();
        } else {
            wrongGuess.start(); // Play incorrect noise
            Toast.makeText(this, "Your guess, " + result + " was incorrect. Correct: " + correctAnswer, Toast.LENGTH_SHORT).show();
        }
    }
}
