package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button newRegister,menu,signup;
    FirebaseAuth auth;
    FirebaseUser user;

    String emailText,passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Register");

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        newRegister = (Button) findViewById(R.id.newRegister);
        signup= (Button) findViewById(R.id.signup);
        menu= (Button) findViewById(R.id.menu);

        auth = FirebaseAuth.getInstance();


        newRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 emailText = email.getText().toString();
                 passwordText = password.getText().toString();

                if(!emailText.equals("") && !passwordText.equals("")){
                    Register(emailText,passwordText);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void Register(String email , String passWord){
        auth.createUserWithEmailAndPassword(email,passWord).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Succesful Registration! ",Toast.LENGTH_LONG).show();

                    sendUserData();
                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"An error has occurred, check the information! You can become a member ",Toast.LENGTH_LONG).show();
                }

            }

        });
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child("Users").child(auth.getUid());

        String string = emailText;
        String[] parts = string.split("@");
        String userNameText = parts[0];

         UserProfile userProfile = new UserProfile(emailText,userNameText,passwordText);
        myRef.setValue(userProfile);
    }



}
