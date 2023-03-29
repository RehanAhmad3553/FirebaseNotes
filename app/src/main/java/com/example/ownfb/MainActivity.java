package com.example.ownfb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ownfb.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Uri uri;

    int maxid;

    String imageUrl;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        FirebaseDatabase.getInstance().getReference().child("School").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                maxid = (int) snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        binding.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,45);

            }
        });







        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(uri==null)
                {

                    HashMap<String,Object> map = new HashMap<>();

                    map.put("first",binding.etFirst.getText().toString());
                    map.put("second",binding.etSecond.getText().toString());
                    map.put("third",binding.etThird.getText().toString());
                    map.put("Image","No IMAGE");
                    FirebaseDatabase.getInstance().getReference().child("School").child(String.valueOf(maxid+1)).setValue(map);


                }

                else
                {
                    FirebaseStorage.getInstance().getReference().child("FolderName").child(String.valueOf(maxid+1)).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isComplete());
                            Uri ImageUri = uriTask.getResult();
                            imageUrl = ImageUri.toString();

                            HashMap<String,Object> map = new HashMap<>();

                            map.put("first",binding.etFirst.getText().toString());
                            map.put("second",binding.etSecond.getText().toString());
                            map.put("third",binding.etThird.getText().toString());
                            map.put("Image",imageUrl);
                            FirebaseDatabase.getInstance().getReference().child("School").child(String.valueOf(maxid+1)).setValue(map);


                        }
                    });
                }






            }
        });


        binding.btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseDatabase.getInstance().getReference().child("School").child("1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Map<String,Object> getMap = (Map) snapshot.getValue();

                        if(snapshot.exists())
                        {
                            String name = (String) getMap.get("first");
                            String second = (String) getMap.get("second");
                            String third  = (String) getMap.get("third");


                            binding.tvFirst.setText(name);
                            binding.tvSecond.setText(second);
                            binding.tvThird.setText(third);




                        }




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });


       /* binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap updateHash = new HashMap();

                updateHash.put("first",binding.etFirst.getText().toString());
                updateHash.put("second",binding.etSecond.getText().toString());
                updateHash.put("third",binding.etThird.getText().toString());


                FirebaseDatabase.getInstance().getReference().child("Students").updateChildren(updateHash);



            }
        });


        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Store Image WITH URL COPY

                String roll = binding.etRollNO.getText().toString();

                // FirebaseDatabase.getInstance().getReference().child("Students").removeValue();

                FirebaseDatabase.getInstance().getReference().child("School").child(String.valueOf(roll)).removeValue();

                FirebaseStorage.getInstance().getReference().child("FolderName").child(String.valueOf(roll)).delete();

            }
        });
*/







    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK)
        {
            uri=data.getData();
            binding.imageview.setImageURI(uri);

        }

    }
}

