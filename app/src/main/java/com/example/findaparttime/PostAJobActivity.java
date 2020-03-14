package com.example.findaparttime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.findaparttime.adapter.RecyclerViewAdapter;
import com.example.findaparttime.data.DataFile;
import com.example.findaparttime.data.RegistrationData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class PostAJobActivity extends AppCompatActivity{
    private FloatingActionButton fabbut;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    public ArrayList<DataFile> allJobList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference documentReference;
    private onClickInterface onClickinterface;
    private final int REQUEST_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ajob);

        fabbut = findViewById(R.id.fab_add);
        recyclerView = findViewById(R.id.recyclerViewid);

        fabbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(getApplicationContext(),InsertAJobActivity.class);
                String email_id = getIntent().getStringExtra("email-id");
                Log.d("Heyemail1", "onClick: "+email_id);

                inte.putExtra("email-id",email_id);
                startActivityForResult(inte,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            assert data != null;
            Toast.makeText(this, ""+data.getStringExtra("message"), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allJobList = new ArrayList<>();
        final String email = getIntent().getStringExtra("email-id");
        documentReference = db.collection("datas").document(email);



        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                RegistrationData registrationData= documentSnapshot.toObject(RegistrationData.class);
                allJobList.addAll(registrationData.getLi());

                onClickinterface = new onClickInterface() {
                    @Override
                    public void setClick(String email, String job_title, Double lat1, Double long1) {
                       Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                        intent.putExtra("latit",lat1);
                        intent.putExtra("longi",long1);
                       startActivity(intent);
                    }
                    public void AllInformation(String email,String job_title,String job_desc,String Timings,String Salary){
                        Intent intent = new Intent(getApplicationContext(),AllInfoJob.class);
                        intent.putExtra("e1",email);
                        intent.putExtra("j1",job_title);
                        intent.putExtra("j2",job_desc);
                        intent.putExtra("t1",Timings);
                        intent.putExtra("s1",Salary);
                        startActivity(intent);
                    }
                };

                recyclerViewAdapter = new RecyclerViewAdapter(PostAJobActivity.this,allJobList,onClickinterface);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("HelloBaby", "onFailure: "+e.getMessage());
            }
        });

    }
}
