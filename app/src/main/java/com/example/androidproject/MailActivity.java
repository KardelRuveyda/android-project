package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class MailActivity extends AppCompatActivity {
    private EditText editTextTo;
    private EditText editTextSubject;
    private  EditText editTextMessage;
    private  EditText twAttachment;
    String emailTo;
    String subject;
    String message;
    Uri URI = null;
    private Button sendMailButton, add_attachments;



    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;

    String attachmentFile;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        getSupportActionBar().setTitle("Send Mail");

        editTextTo= (EditText) findViewById(R.id.text_to);
        editTextMessage =(EditText) findViewById(R.id.text_message);
        editTextSubject=(EditText) findViewById(R.id.text_subject);
        twAttachment = (EditText) findViewById(R.id.twAttachment);

        sendMailButton = findViewById(R.id.send_mail);
        add_attachments = findViewById(R.id.add_attachments);

        sendMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMailActivty();
            }
        });

        //attachments
        add_attachments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
            twAttachment.setText(URI.getLastPathSegment());
            twAttachment.setVisibility(View.VISIBLE);
        }
    }

    public void sendMailActivty(){

        try {
            emailTo = editTextTo.getText().toString();
            subject = editTextSubject.getText().toString();
            message = editTextMessage.getText().toString();
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{emailTo});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
            if (URI != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
            }
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
            this.startActivity(Intent.createChooser(emailIntent, "Sending email..."));
        } catch (Throwable t) {
            Toast.makeText(this, "Request failed try again: "+ t.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void openFolder()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
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