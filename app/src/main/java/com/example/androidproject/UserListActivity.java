package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase;

    RecyclerView recyclerView;
    ArrayList<UserProfile> list;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        getSupportActionBar().setTitle("User List");

        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<UserProfile>();

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    UserProfile p = dataSnapshot1.getValue(UserProfile.class);
                    list.add(p);
                }
                adapter = new MyAdapter(UserListActivity.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UserListActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

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
            case R.id.signUpMenu:
                startActivity(new Intent(UserListActivity.this, SignUpActivity.class));
                break;
            case R.id.sharedPreferenceMenu:
                startActivity(new Intent(UserListActivity.this, SharedPreferencesActivity.class));
                break;
            case R.id.menuMenu:
                startActivity(new Intent(UserListActivity.this, MenuActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
