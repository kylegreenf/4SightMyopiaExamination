package com.example.a4sightmyopiaexamination;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView letterTesting;
    private String correctAnswer;
    private MediaPlayer correctGuess;
    private MediaPlayer wrongGuess;

    private int incorrectCount;
    private int totalQuestions;
    private int fontSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Counter setup
        incorrectCount = 0;
        totalQuestions = 0;
        fontSize = 160;

        // Adding sounds for correct or incorrect guess
        correctGuess = MediaPlayer.create(this, R.raw.correct);
        wrongGuess = MediaPlayer.create(this, R.raw.wrong);


        // Changing the text results
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
                        checkCorrectGuess(result.get(0), correctAnswer);
                        // letterTesting.setText(generateRandomLetter());
                        letterTesting.setText(generateSpecificRandomLetter(fontSize));

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

    private String generateSpecificRandomLetter(int currentFontSize) {
        char letter = correctAnswer.charAt(0);
        char previousLetter = correctAnswer.charAt(0);

        Random r = new Random();

        while (previousLetter == letter) { //Ensures new character is presented
            int toChoose = r.nextInt(3); // 0, 1, 2

            switch (currentFontSize) { //Different font sizes should use different letters according to optometrists.
                case 160: // E M F
                    if (toChoose == 0) {
                        letter = 'E';
                    } else if (toChoose == 1) {
                        letter = 'M';
                    } else if (toChoose == 2) {
                        letter = 'F';
                    }
                    break;
                case 105: //U H S
                    if (toChoose == 0) {
                        letter = 'U';
                    } else if (toChoose == 1) {
                        letter = 'H';
                    } else if (toChoose == 2) {
                        letter = 'S';
                    }
                    break;
                case 80: //N V T
                    if (toChoose == 0) {
                        letter = 'N';
                    } else if (toChoose == 1) {
                        letter = 'V';
                    } else if (toChoose == 2) {
                        letter = 'T';
                    }
                    break;
                case 66: //Z P D
                    if (toChoose == 0) {
                        letter = 'Z';
                    } else if (toChoose == 1) {
                        letter = 'P';
                    } else if (toChoose == 2) {
                        letter = 'D';
                    }
                    break;
                case 60: //F K L
                    if (toChoose == 0) {
                        letter = 'F';
                    } else if (toChoose == 1) {
                        letter = 'K';
                    } else if (toChoose == 2) {
                        letter = 'L';
                    }
                    break;
                case 49: //A T H
                    if (toChoose == 0) {
                        letter = 'A';
                    } else if (toChoose == 1) {
                        letter = 'T';
                    } else if (toChoose == 2) {
                        letter = 'H';
                    }
                    break;
                case 45: //R O Y
                    if (toChoose == 0) {
                        letter = 'R';
                    } else if (toChoose == 1) {
                        letter = 'O';
                    } else if (toChoose == 2) {
                        letter = 'Y';
                    }
                    break;
                case 41: //L T H
                    if (toChoose == 0) {
                        letter = 'L';
                    } else if (toChoose == 1) {
                        letter = 'T';
                    } else if (toChoose == 2) {
                        letter = 'H';
                    }
                    break;
                case 33: //L K H
                    if (toChoose == 0) {
                        letter = 'L';
                    } else if (toChoose == 1) {
                        letter = 'K';
                    } else if (toChoose == 2) {
                        letter = 'H';
                    }
                    break;

            }
        }


        String letterToString = letter + "";
        correctAnswer = letterToString;
        return letterToString;
    }


    private void checkCorrectGuess(String result, String correctAnswer) {
        String guess = result;
        totalQuestions++;
        if (isPossibleAnswer(guess.toLowerCase(), correctAnswer.toLowerCase())) {
            correctGuess.start(); // Play a noise
            reduceFontSize();
            Toast.makeText(this, "Correct input!", Toast.LENGTH_SHORT).show();
        } else {
            incorrectCount++;
            if (incorrectCount == 3) {
                // Open diagnosis/analyze
            }
            wrongGuess.start(); // Play incorrect noise
            Toast.makeText(this, "Your guess, " + result + " was incorrect. Correct: " + correctAnswer, Toast.LENGTH_SHORT).show();
        }
        if (totalQuestions == 20) {
            // Open diagonsis/analyze
        }
    }

    private void reduceFontSize() {
        switch (fontSize) {
            case 160:
                fontSize = 105;
                break;
            case 105:
                fontSize = 80;
                break;
            case 80:
                fontSize = 66;
                break;
            case 66:
                fontSize = 60;
                break;
            case 60:
                fontSize = 49;
                break;
            case 49:
                fontSize = 45;
                break;
            case 45:
                fontSize = 41;
                break;
            case 41:
                fontSize = 33;
                break;
            case 33:
                fontSize = 33;
                break;
        }

        letterTesting.setTextSize(TypedValue.COMPLEX_UNIT_PT, fontSize);
    }

    private boolean isPossibleAnswer(String guess, String correctAnswer) {

        switch (correctAnswer) {
            case "a":
                if (guess.equals("a") || guess.equals("hey") || guess.equals("aye")) {
                    return true;
                }
                break;
            case "b":
                if (guess.equals("b") || guess.equals("bee")) {
                    return true;
                }
                break;
            case "c":
                if (guess.equals("c") || guess.equals("see")) {
                    return true;
                }
                break;
            case "d":
                if (guess.equals("d") || guess.equals("dee") || guess.equals("the")) {
                    return true;
                }
                break;
            case "e":
                if (guess.equals("e") || guess.equals("eek") || guess.equals("aye")) {
                    return true;
                }
                break;
            case "f":
                if (guess.equals("f")) {
                    return true;
                }
                break;
            case "g":
                if (guess.equals("g") || guess.equals("gee")) {
                    return true;
                }
                break;
            case "h":
                if (guess.equals("h")) {
                    return true;
                }
                break;
            case "i":
                if (guess.equals("i") || guess.equals("eye")) {
                    return true;
                }
                break;
            case "j":
                if (guess.equals("j") || guess.equals("jay")) {
                    return true;
                }
                break;
            case "k":
                if (guess.equals("k") || guess.equals("ok") || guess.equals("kay")) {
                    return true;
                }
                break;
            case "l":
                if (guess.equals("l") || guess.equals("elle")) {
                    return true;
                }
                break;
            case "m":
                if (guess.equals("m") || guess.equals("um") || guess.equals("ehm")) {
                    return true;
                }
                break;
            case "n":
                if (guess.equals("n") || guess.equals("and n") || guess.equals("end") || guess.equals("and")) {
                    return true;
                }
                break;
            case "o":
                if (guess.equals("o") || guess.equals("oh")) {
                    return true;
                }
                break;
            case "p":
                if (guess.equals("p") || guess.equals("pee")) {
                    return true;
                }
                break;
            case "q":
                if (guess.equals("q") || guess.equals("que")) {
                    return true;
                }
                break;
            case "r":
                if (guess.equals("r") || guess.equals("our") || guess.equals("are") || guess.equals("ar")) {
                    return true;
                }
                break;
            case "s":
                if (guess.equals("s") || guess.equals("essay") || guess.equals("ess")) {
                    return true;
                }
                break;
            case "t":
                if (guess.equals("t") || guess.equals("tea") || guess.equals("tee")) {
                    return true;
                }
                break;
            case "u":
                if (guess.equals("u") || guess.equals("you")) {
                    return true;
                }
                break;
            case "v":
                if (guess.equals("v") || guess.equals("v")) {
                    return true;
                }
                break;
            case "w":
                if (guess.equals("w") || guess.equals("double you")) {
                    return true;
                }
                break;
            case "x":
                if (guess.equals("x") || guess.equals("ex")) {
                    return true;
                }
                break;
            case "y":
                if (guess.equals("y") || guess.equals("why")) {
                    return true;
                }
                break;
            case "z":
                if (guess.equals("z") || guess.equals("zee")) {
                    return true;
                }
                break;

        }

        return false;
    }
}
