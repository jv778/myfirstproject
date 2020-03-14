package com.example.findaparttime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    //private TextView register_label1;
    private EditText firstname_field1;
    private EditText email_field1;
    private EditText password_field2;
    private EditText phonenum_field1;
    private Button submit_button1;
   // private TextView login_text;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mauth;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firstname_field1 = findViewById(R.id.firstname_field);
        //lastname_field1 = findViewById(R.id.last_name_field);
        email_field1 = findViewById(R.id.email_field);
        password_field2 = findViewById(R.id.password_field);
        submit_button1 = findViewById(R.id.registration_submit_button);
        //login_text = findViewById(R.id.registration_login_field);
        phonenum_field1 = findViewById(R.id.phone_number_field);

//        username3 = getIntent().getStringExtra("email").toString().trim();
//        password3 = getIntent().getStringExtra("pass").toString().trim();

        //Log.d("MyHello", "I am password: "+password3);


//        email_field1.setText(username3);
//        password_field2.setText(password3);
        submit_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = firstname_field1.getText().toString().trim();
                final String emailid = email_field1.getText().toString().trim();
                final String pasword = password_field2.getText().toString().trim();
                String numb = phonenum_field1.getText().toString().trim();

                if(TextUtils.isEmpty(firstname)){
                    Toast.makeText(getApplicationContext(), "Enter First Name", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(emailid)){
                    Toast.makeText(getApplicationContext(), "Enter Email ID", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(pasword)){
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(numb)){
                    Toast.makeText(getApplicationContext(), "Enter Phone number", Toast.LENGTH_SHORT).show();
                }

                else{
                    ArrayList<DataFile> arrayList = new ArrayList<DataFile>();
//                DataFile dataFile1 = new DataFile();
//                arrayList.add(dataFile1);
                    RegistrationData dataFile = new RegistrationData(firstname, emailid, pasword, numb,arrayList);
                    db.collection("datas").document(emailid)
                            .set(dataFile).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent inte = new Intent(getApplicationContext(),HomeActivity.class);
                            inte.putExtra("emailrd",emailid);

                            create_new_user(emailid,pasword);

                            startActivity(inte);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegistrationActivity.this, "failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

    }

    public void create_new_user(String email,String passwor){
        mauth = FirebaseAuth.getInstance();
        mauth.createUserWithEmailAndPassword(email, passwor)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("registerloginsuccess", "onComplete: "+"complete");

                        } else {
                            Log.d("registerlogin", "onComplete: "+"incomplete");
                        }
                    }

                });
    }



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
