package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPreferencesActivity extends AppCompatActivity {
    EditText userNameEditText;
    EditText ageEditText;
    EditText weightEditText;
    EditText heightEditText;
    CheckBox femaleCheckBox;
    CheckBox maleCheckBox;
    Button   saveButton;
    Button   showButton;

    String username;

    String age;
    String weight;
    String height;
    Boolean male;
    Boolean female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        getSupportActionBar().setTitle("Shared Preferences");

        userNameEditText =(EditText) findViewById(R.id.sharedPreferenceUserName);
        ageEditText =(EditText) findViewById(R.id.sharedPreferenceAge);
        weightEditText =(EditText) findViewById(R.id.sharedPreferenceWeight);
        heightEditText = (EditText) findViewById(R.id.sharedPreferenceHeight);
        maleCheckBox = (CheckBox) findViewById(R.id.sharedPreferencecheckBoxErkek);
        femaleCheckBox = (CheckBox) findViewById(R.id.sharedPreferencecheckBoxKadin);
        saveButton = (Button) findViewById(R.id.save_button);
        showButton =(Button) findViewById(R.id.show_text_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPref = getSharedPreferences("Enter",MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPref.edit();


                username = userNameEditText.getText().toString();
                age = ageEditText.getText().toString();
                weight = weightEditText.getText().toString();
                height = heightEditText.getText().toString();
                male = maleCheckBox.isChecked();
                female = femaleCheckBox.isChecked();

                editor.putString("UserName",username);
                editor.putString("Age",age);
                editor.putString("Weight",weight);
                editor.putString("Height", height);
                editor.putBoolean("Male",male);
                editor.putBoolean("Female",female);

                editor.commit();

                userNameEditText.getText().clear();
                ageEditText.getText().clear();
                weightEditText.getText().clear();
                heightEditText.getText().clear();
                femaleCheckBox.setChecked(false);
                maleCheckBox.setChecked(false);


                Toast.makeText(getApplicationContext(),"Succesful Registered!",Toast.LENGTH_LONG).show();
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedReadData = getSharedPreferences("Enter",MODE_PRIVATE);

                userNameEditText.setText(sharedReadData.getString("UserName",""));
                ageEditText.setText(sharedReadData.getString("Age",""));
                weightEditText.setText(sharedReadData.getString("Weight",""));
                heightEditText.setText(sharedReadData.getString("Height",""));
                maleCheckBox.setChecked(sharedReadData.getBoolean("Male",false));
                femaleCheckBox.setChecked(sharedReadData.getBoolean("FeMale",false));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuMenu:
                startActivity(new Intent(SharedPreferencesActivity.this, MenuActivity.class));
                break;
            case R.id.registerMenu:
                startActivity(new Intent(SharedPreferencesActivity.this, MainActivity.class));
                break;

            case R.id.signUpMenu:
                startActivity(new Intent(SharedPreferencesActivity.this, SignUpActivity.class));
                break;
            case R.id.userListMenu:
                startActivity(new Intent(SharedPreferencesActivity.this, UserListActivity.class));
                break;
            case R.id.sendMailMenu:
                startActivity(new Intent(SharedPreferencesActivity.this, MailActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }



}