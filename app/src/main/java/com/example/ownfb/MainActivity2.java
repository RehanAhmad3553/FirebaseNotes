package com.example.ownfb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ownfb.databinding.ActivityMain2Binding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;


    myAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.Recycler.setLayoutManager(layoutManager);




        Query query = FirebaseDatabase.getInstance().getReference().child("Shaheen");



        FirebaseRecyclerOptions<model> options = new FirebaseRecyclerOptions.Builder<model>()
                .setQuery(query,model.class)
                .build();




         adapter = new myAdapter(options);

        binding.Recycler.setAdapter(adapter);










    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}