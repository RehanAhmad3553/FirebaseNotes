package com.example.ownfb;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ownfb.databinding.ActivityMain2Binding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;


    myAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        FirebaseDatabase.getInstance().getReference().child("Total").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Map<String,Object> getMap= (Map) snapshot.getValue();
                String Total = (String) getMap.get("TotalPrice");

                binding.tvTotal.setText("Total Price Rs "+Total);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.Recycler.setLayoutManager(layoutManager);


        Query query = FirebaseDatabase.getInstance().getReference("Add/School");

        FirebaseRecyclerOptions<model> options = new FirebaseRecyclerOptions.Builder<model>()
                .setQuery(query,model.class)
                .build();


        adapter= new myAdapter(options);
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
        adapter.startListening();
    }
}