package com.example.findaparttime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.findaparttime.adapter.RecyclerViewAdapter;
import com.example.findaparttime.control.DataHandler;
import com.example.findaparttime.data.DataFile;
import com.example.findaparttime.data.RegistrationData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SeeAllJobsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    public ArrayList<DataFile> allJobList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference;
    private onClickInterface onClickinterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_jobs);

        recyclerView = findViewById(R.id.recyclerViewid);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        collectionReference = db.collection("datas");
        allJobList = new ArrayList<>();
        final String email = getIntent().getStringExtra("email-id");

//        documentReference = db.collection("datas").document(email);
//        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                RegistrationData registrationData= documentSnapshot.toObject(RegistrationData.class);
//                allJobList.addAll(registrationData.getLi());
//                recyclerViewAdapter = new RecyclerViewAdapter(SeeAllJobsActivity.this,allJobList);
//                recyclerView.setAdapter(recyclerViewAdapter);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d("HelloBaby", "onFailure: "+e.getMessage());
//            }
//        });

        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot snapshots : queryDocumentSnapshots){
                    RegistrationData registrationData = snapshots.toObject(RegistrationData.class);
                    allJobList.addAll(registrationData.getLi());
                }
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
                recyclerViewAdapter = new RecyclerViewAdapter(SeeAllJobsActivity.this,allJobList,onClickinterface);
                recyclerView.setAdapter(recyclerViewAdapter);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("errormess", "onFailure: "+e.getMessage());
            }
        });

    }
}
