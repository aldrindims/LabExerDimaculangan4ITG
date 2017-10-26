package com.example.dimaculangan.labexerdimaculangan4itg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etUserName, etPassword;
    Button btnShared, btnInternal, btnNext;
    SharedPreferences preferences;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUserName = (EditText) findViewById(R.id.et_userName);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnShared = (Button) findViewById(R.id.btn_saveShared);
        btnInternal = (Button) findViewById(R.id.btn_saveInternal);
        btnNext = (Button) findViewById(R.id.btn_next);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void savePreferences(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user",etUserName.getText().toString());
        editor.putString("pwd",etPassword.getText().toString());
        editor.commit();
        Toast.makeText(this, "Saved in Shared Preferences!", Toast.LENGTH_LONG).show();
    }

    public void saveInternal (View view){
        String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        String message = "Username is " + username + " and the password is " + password;
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Message saved!", Toast.LENGTH_LONG).show();
    }

        public void nextPage (View view){
            Intent intent = new Intent(this,Main2Activity.class);
            startActivity(intent);
        }
}
