package com.example.findaparttime.control;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.findaparttime.data.DataFile;
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

public class DataHandler {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference documentReference;
    private CollectionReference collectionReference = db.collection("datas");
    public ArrayList<DataFile> arrayList = new ArrayList<DataFile>();;

    public DataHandler() {
    }

    public ArrayList<DataFile> getAllJobs() {
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot snapshots : queryDocumentSnapshots){
                    RegistrationData registrationData = snapshots.toObject(RegistrationData.class);
                    arrayList.addAll(registrationData.getLi());
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("errormess", "onFailure: "+e.getMessage());
            }
        });
        return arrayList;
    }
    
    
    public ArrayList<DataFile> getSelectedJobs(String email){
        documentReference = db.collection("datas").document(email);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                RegistrationData registrationData= documentSnapshot.toObject(RegistrationData.class);
                arrayList.addAll(registrationData.getLi());
                Log.d("Haibabai1", "getSelectedJobs: "+arrayList);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("HelloBaby", "onFailure: "+e.getMessage());
            }
        });
        Log.d("Haibabai", "getSelectedJobs: "+arrayList);
        return arrayList;
    }
}


