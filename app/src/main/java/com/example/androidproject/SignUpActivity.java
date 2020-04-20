package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    EditText email,password;
    Button login;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle("Sign Up");

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login =(Button)  findViewById(R.id.login);

        auth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();


                if(!emailText.equals("") && !passwordText.equals("")){
                    Login(emailText,passwordText);
                }
            }
        });

    }

    private void Login(String userName , String passWord){
        auth.signInWithEmailAndPassword(userName,passWord).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Succesful Login! ",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SignUpActivity.this, UserListActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"An error has occurred, check the information! ",Toast.LENGTH_LONG).show();
                }

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.registerMenu:
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                break;

            case R.id.menuMenu:
                startActivity(new Intent(SignUpActivity.this, MenuActivity.class));
                break;

            case R.id.sharedPreferenceMenu:
                startActivity(new Intent(SignUpActivity.this, SharedPreferencesActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }





}
