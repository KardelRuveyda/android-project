package com.example.androidproject;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
 import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<UserProfile> profiles;

    public MyAdapter(Context c , ArrayList<UserProfile> p)
    {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(profiles.get(position).getUserName());
        holder.email.setText(profiles.get(position).getUserEmail());
        holder.password.setText(profiles.get(position).getPassWord());

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,email,password;
        Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.userName);
            email= (TextView) itemView.findViewById(R.id.userEmail);
            password= (TextView) itemView.findViewById(R.id.userPassword);

        }
        public void onClick(final int position)
        {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, position+" is clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}