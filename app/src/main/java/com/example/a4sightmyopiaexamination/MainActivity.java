package com.example.a4sightmyopiaexamination;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String FINAL_FONT_SIZE = "com.example.a4sightmyopiaexamination.FINAL_FONT_SIZE";

    private TextView letterTesting;
    private String correctAnswer;
    private MediaPlayer correctGuess;
    private MediaPlayer wrongGuess;

    private SpeechRecognizer mSpeechRecognizer;
    private Intent mSpeechRecognizerIntent;

    private int incorrectCount;
    private int totalQuestions;
    private int fontSize = 160;

    private int countdownint = 3;

    private int testType = 0;
    private int rotationDegrees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {
                //letterTesting.setText(Integer.toString(error));
                mSpeechRecognizer.cancel();
                startNextListenTimer();
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null) {
                    checkCorrectGuess(matches.get(0), correctAnswer);
                    letterTesting.setText(generateSpecificRandomLetter(fontSize));
                }
                startNextListenTimer();
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

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
        letterTesting.setText("3");


        // Start the timer before test begins
        startCountdownTimer();

    }

    private void startCountdownTimer() {
        new CountDownTimer(1250, 1000) {

            public void onTick(long millisUntilFinished) {
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                //mTextField.setText("done!");
                lowerCountdown();
            }
        }.start();
    }

    private void startNextListenTimer() {
        new CountDownTimer(750, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
            }
        }.start();
    }

    private void lowerCountdown() {
        switch (countdownint) {
            case 3:
                letterTesting.setText("2");
                startCountdownTimer();
                break;
            case 2:
                letterTesting.setText("1");
                startCountdownTimer();
                break;
            case 1:
                letterTesting.setText(correctAnswer);
                getSpeechInput(null);
                break;

        }
        countdownint--;
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }


    public void getSpeechInput(View v) {
        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
    }


    public String generateRandomLetter() {
        if (testType == 1) {
            correctAnswer = "right";
            return "E";
        }
        char letter;
        Random r = new Random();
        int l = r.nextInt(26) + 65;
        letter = (char) l;
        String letterToString = letter + "";
        correctAnswer = letterToString;
        return letterToString;
    }

    private String generateSpecificRandomLetter(int currentFontSize) {
        if (testType == 1) {
            return generateRandomDirectionE();
        }

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
                default:
                    letter = 'Z';
                    break;

            }
        }


        String letterToString = letter + "";
        correctAnswer = letterToString;
        return letterToString;
    }

    private String generateRandomDirectionE() {
        Random r = new Random();
        int previousRotationDegrees = rotationDegrees;
        rotationDegrees = r.nextInt(4) * 90;

        if (previousRotationDegrees == rotationDegrees) {
            rotationDegrees = (rotationDegrees + 90)%360;
        }

        switch (rotationDegrees) {
            case 0:
                correctAnswer = "right";
                letterTesting.setRotation(0);
                break;
            case 90:
                correctAnswer = "up";
                letterTesting.setRotation(270);
                break;
            case 180:
                correctAnswer = "left";
                letterTesting.setRotation(180);
                break;
            case 270:
                correctAnswer = "down";
                letterTesting.setRotation(90);
                break;
            default:
                correctAnswer = "wow";
                letterTesting.setRotation(0);
                break;
        }



        //Rotate letter
        return "E";
    }


    private void checkCorrectGuess(String result, String correctAnswer) {

        String guess = result;
        totalQuestions++;
        if (isPossibleAnswer(guess.toLowerCase(), correctAnswer.toLowerCase())) {
            //correctGuess.start(); // Play a noise
            incorrectCount = 0; //Reset count
            reduceFontSize();
        } else {
            incorrectCount++;
            if (incorrectCount == 3) {
                openAnalysisActivity();
            }
            //wrongGuess.start(); // Play incorrect noise
            Toast.makeText(this, "Your guess, " + result + " was incorrect. Correct: " + correctAnswer, Toast.LENGTH_SHORT).show();
        }
    }

    private void openAnalysisActivity() {
        mSpeechRecognizer.stopListening();
        mSpeechRecognizer.destroy();
        Intent intent = new Intent(this, AfterTestAnalysis.class);
        intent.putExtra(FINAL_FONT_SIZE, fontSize);
        startActivity(intent);
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
                fontSize = 32;
                openAnalysisActivity();
                break;
            default:
                fontSize= 170;
                openAnalysisActivity();
                break;

        }

        letterTesting.setTextSize(TypedValue.COMPLEX_UNIT_PT, fontSize);
    }

    private boolean isPossibleAnswer(String guess, String correctAnswer) {

        // If using Tumbling E type
        if (testType == 1) {
            return checkEDirection(guess, correctAnswer);
        }

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
                if (guess.equals("e") || guess.equals("eek") || guess.equals("aye") || guess.equals("chief")) {
                    return true;
                }
                break;
            case "f":
                if (guess.equals("f")) {
                    return true;
                }
                break;
            case "g":
                if (guess.equals("g") || guess.equals("gee") || guess.equals("chi")) {
                    return true;
                }
                break;
            case "h":
                if (guess.equals("h") || guess.equals("aches")) {
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
                if (guess.equals("k") || guess.equals("ok") || guess.equals("kay") || guess.equals("hey")) {
                    return true;
                }
                break;
            case "l":
                if (guess.equals("l") || guess.equals("elle") || guess.equals("hell")) {
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
                if (guess.equals("v") || guess.equals("pee")) {
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
                if (guess.equals("z") || guess.equals("sea") || guess.equals("see")) {
                    return true;
                }
                break;

        }

        return false;
    }

    private boolean checkEDirection(String guess, String correctAnswer) {
        if (guess.equals(correctAnswer)) {
            return true;
        }
        return false;
    }
}
