package com.example.findaparttime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findaparttime.data.DataFile;
import com.example.findaparttime.data.RegistrationData;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InsertAJobActivity extends AppCompatActivity {

    private EditText jobnam;
    private EditText jobdesc;
    private EditText timin;
    private EditText salar;
    private Button enterdet;
    //private TextView add;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference documentReference;
    private FusedLocationProviderClient mFusedLocationClient;

    private Double lattitude = 0.0;
    private Double longitude= 0.0;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_ajob);
        jobdesc = findViewById(R.id.jobdescription);
        jobnam = findViewById(R.id.jobname);
        timin = findViewById(R.id.Timings);
        salar = findViewById(R.id.salary);
        enterdet = findViewById(R.id.enterdetails);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        new AlertDialog.Builder(this)
                        .setTitle("Required Location Permission")
                        .setMessage("You have to give this permission to acess this feature")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               getLoc();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent int2 = getIntent();
                                int2.putExtra("message","Not Inserted");
                                setResult(RESULT_OK,int2);
                                finish();
                            }
                        })
                        .create()
                        .show();

        enterdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_id = getIntent().getStringExtra("email-id");
                Log.d("Heyemail", "onClick: "+email_id);
                documentReference = db.collection("datas").document(email_id);

                final String jobtit = jobnam.getText().toString().trim();

                String jobdec = jobdesc.getText().toString().trim();
                String jobtem = timin.getText().toString().trim();
                String jobasl = salar.getText().toString().trim();
                if(TextUtils.isEmpty(jobtit)){
                    Toast.makeText(context, "Enter Job Title", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(jobdec)){
                    Toast.makeText(context, "Enter Job Description", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(jobtem)){
                    Toast.makeText(context, "Enter Job Timings", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(jobasl)){
                    Toast.makeText(context, "Enter Job Salary", Toast.LENGTH_SHORT).show();
                }

                else{
                    final DataFile dataFile = new DataFile(jobtit,jobdec,jobtem,jobasl,lattitude,longitude,email_id);
                    documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            RegistrationData registrationData= documentSnapshot.toObject(RegistrationData.class);
                            ArrayList<DataFile> li1 = registrationData.getLi();
                            Log.d("listsize", "onSuccess: "+ li1.size());
                            li1.add(dataFile);
                            RegistrationData registrationData1 =
                                    new RegistrationData(registrationData.getFname(),registrationData.getEmail(),registrationData.getPassword(),registrationData.getPhonenumber(),li1);
                            documentReference.set(registrationData1);
                            Intent int1 = getIntent();
                            int1.putExtra("message","Inserted Successfully");
                            setResult(RESULT_OK,int1);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("ThisErrror", "onFailure: "+e.getMessage());
                        }
                    });

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //abc
            }else{

            }
        }
    }

    public void getLoc(){
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.d("iamin", "onCreate: I am in");
                            // Logic to handle location object
                            lattitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                });
    }
}
