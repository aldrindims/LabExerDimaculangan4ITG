package com.example.dimaculangan.labexerdimaculangan4itg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    Button btnClear, btnBack, btnLoadShared, btnInternal;
    TextView tvDisplay;
    SharedPreferences preferences;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnLoadShared = (Button) findViewById(R.id.btn_loadShared);
        btnInternal = (Button) findViewById(R.id.btn_loadInternal);
        tvDisplay = (TextView) findViewById(R.id.tv_display);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void displayPreferences(View view){
        String user = preferences.getString("user","");
        String password = preferences.getString("pwd","");
        tvDisplay.setText("Username is " + user + " and the password is " + password);
    }

    public void displayInternal (View view){
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("output.txt");
            while ((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void backBtn (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void clearBtn (View view){
        tvDisplay.setText("");
    }
}
