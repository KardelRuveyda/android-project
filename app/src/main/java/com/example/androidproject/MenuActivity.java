package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    Button registerButton, signUpButton, userListButton, sharedPreferencesButton, sensorButton,mailButton,sensorsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        registerButton =(Button) findViewById(R.id.menuNewRegister);
        signUpButton =(Button) findViewById(R.id.menuSignUp);
        userListButton =(Button) findViewById(R.id.menuUserList);
        sharedPreferencesButton=(Button) findViewById(R.id.menuSharedPreferences);
        sensorButton =(Button) findViewById(R.id.menuSensors);
        mailButton =(Button) findViewById(R.id.menuMail);
        sensorsButton =(Button) findViewById(R.id.menuSensors);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        userListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, UserListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        sharedPreferencesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SharedPreferencesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        sensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SensorActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MailActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
