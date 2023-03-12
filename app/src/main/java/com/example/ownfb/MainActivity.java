package com.example.ownfb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ownfb.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    int maxId;

    Uri uri;

    String ImageURl;

    int counter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.Iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,45);



            }
        });









        FirebaseDatabase.getInstance().getReference().child("School").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                maxId = (int) snapshot.getChildrenCount();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                HashMap<String, Object> map = new HashMap<>();

                map.put("first", binding.etFirst.getText().toString());
                map.put("second", binding.etSecond.getText().toString());
                map.put("third", binding.etThird.getText().toString());
                map.put("Imageurl",String.valueOf(counter+1));



                FirebaseDatabase.getInstance().getReference().child("School").child(String.valueOf(counter+1)).setValue(map);




               /* FirebaseStorage.getInstance().getReference().child("FolderName").child(String.valueOf(maxId+1)).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        ImageURl = uriTask.getResult().toString();






                    }
                });
*/















            }
        });


        binding.btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseDatabase.getInstance().getReference().child("School").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Map map = (Map) snapshot.getValue();

                        if (snapshot.exists()) {
                            String name = (String) map.get("first");
                            String second = (String) map.get("second");
                            String third = (String) map.get("third");




                           /* binding.tvFirst.setText(name);
                            binding.tvSecond.setText(second);
                            binding.tvThird.setText(third);*/


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });


        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap updateHash = new HashMap();

                updateHash.put("first", binding.etFirst.getText().toString());
                updateHash.put("second", binding.etSecond.getText().toString());
                updateHash.put("third", binding.etThird.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("School").updateChildren(updateHash);






            }
        });


        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseDatabase.getInstance().getReference().child("School").removeValue();


            }
        });



        binding.ButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK)
        {

           uri = data.getData();
            binding.Iv1.setImageURI(uri);

        }


    }
}




