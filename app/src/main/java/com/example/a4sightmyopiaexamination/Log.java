package com.example.a4sightmyopiaexamination;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.util.Map;

public class Log extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getting the log data from shared preferences and printing them out
        //for the log page
        TextView tv= (TextView) findViewById(R.id.logtxt);
        String log="Log:\n";
        SharedPreferences pref=getSharedPreferences("RESULTLOG",MODE_APPEND);
        Map<String,?> allLog=pref.getAll();
        for (Map.Entry<String,?> entry: allLog.entrySet()){
            log+=entry.getValue().toString();
        }
        tv.setText(log);
    }
}
