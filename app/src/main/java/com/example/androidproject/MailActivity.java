package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MailActivity extends AppCompatActivity {
    private EditText editTextTo;
    private EditText editTextSubject;
    private  EditText editTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        getSupportActionBar().setTitle("Send Mail");

        editTextTo= (EditText) findViewById(R.id.text_to);
        editTextMessage =(EditText) findViewById(R.id.text_message);
        editTextSubject=(EditText) findViewById(R.id.text_subject);

        Button sendMailButton = findViewById(R.id.send_mail);

        sendMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMailActivty();
            }
        });

    }

    public void sendMailActivty(){
        String recipientList = editTextTo.getText().toString();
        //ruveydakardelcetin@gmail.com, kardelruveydacetin@gmail.com
        String[] recipients = recipientList.split(",");

        String subject =editTextSubject.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc882");
        startActivity(Intent.createChooser(intent,"Choose an email Clients!"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuMenu:
                startActivity(new Intent(MailActivity.this, MenuActivity.class));
                break;
            case R.id.registerMenu:
                startActivity(new Intent(MailActivity.this, MainActivity.class));
                break;

            case R.id.signUpMenu:
                startActivity(new Intent(MailActivity.this, SignUpActivity.class));
                break;
            case R.id.userListMenu:
                startActivity(new Intent(MailActivity.this, UserListActivity.class));
                break;

            case R.id.sendSharedPref:
                startActivity(new Intent(MailActivity.this, SharedPreferencesActivity.class));
                break;

            case R.id.sensorPref:
                startActivity(new Intent(MailActivity.this, SensorActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}