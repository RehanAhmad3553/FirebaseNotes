package com.example.ownfb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    int maxId;

    Uri uri;

    String ImageURl;

    int counter;



    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //fetch();


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









                FirebaseStorage.getInstance().getReference().child("Shaheen").child(String.valueOf(maxId+1)).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        ImageURl = uriTask.getResult().toString();


                        HashMap<String, Object> map = new HashMap<>();

                        map.put("first", binding.etFirst.getText().toString());
                        map.put("second", binding.etSecond.getText().toString());
                        map.put("third", binding.etThird.getText().toString());
                        map.put("imageUrl",ImageURl);

               /* // Extra Slider Pics Send
                map.put("extraLink1", binding.etLink.getText().toString());
                map.put("extraLink2", binding.etLink2.getText().toString());*/


                FirebaseDatabase.getInstance().getReference().child("School").child(String.valueOf(maxId+1)).setValue(map);

                        db.collection("USers").document(String.valueOf(maxId+1)).set(map);





                        binding.Iv1.setImageURI(null);

                        Toast.makeText(MainActivity.this, "Insert", Toast.LENGTH_SHORT).show();





                    }
                });














            }
        });


        binding.btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



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



    public void fetch()
    {
        FirebaseDatabase.getInstance().getReference().child("School").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Map map = (Map) snapshot.getValue();

                if (snapshot.exists()) {
                    String name = (String) map.get("first");
                    String second = (String) map.get("second");
                    String third = (String) map.get("third");

                    //Extra SliderPic FetchLogic
                    String extraLink = (String) map.get("extraLink1");
                    String extraLink2 = (String) map.get("extraLink2");

                    binding.carousel.addData(new CarouselItem(name,""));
                    binding.carousel.addData(new CarouselItem(second,""));
                    binding.carousel.addData(new CarouselItem(third,""));

                    binding.carousel.addData(new CarouselItem(extraLink,""));
                    binding.carousel.addData(new CarouselItem(extraLink2,""));



                         /*   binding.tvFirst.setText(name);
                            binding.tvSecond.setText(second);
                            binding.tvThird.setText(third);
*/

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}




