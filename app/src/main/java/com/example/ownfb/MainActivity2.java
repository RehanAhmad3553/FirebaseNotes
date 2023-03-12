package com.example.ownfb;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ownfb.databinding.ActivityMain2Binding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ButtonFETCHRecod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rollnumber= binding.etRoll.getText().toString();

                FirebaseDatabase.getInstance().getReference().child("School").child(rollnumber).


                        addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Map map = (Map) snapshot.getValue();
                        if(snapshot.exists())
                        {

                            String name = (String) map.get("first");
                            String second = (String) map.get("second");
                            String third = (String) map.get("third");
                            String ImageUrl = (String) map.get("Imageurl");


                            binding.tvRecFirst.setText(name);
                            binding.tvRecSecond.setText(second);
                            binding.tvRecThird.setText(third);

                            Glide.with(MainActivity2.this).load(ImageUrl).into(binding.iv2);





                        }
                        else
                        {
                            Toast.makeText(MainActivity2.this, "Data no found", Toast.LENGTH_SHORT).show();
                        }





                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });






    }


}