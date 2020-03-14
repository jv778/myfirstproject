package com.example.findaparttime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button submit_button;
    private EditText username_field1;
    private EditText password_field1;
    private ImageButton registration_text;
    //private TextView statementfield;
    private FirebaseAuth mauth;
    //private Button reg_but;
    private String username3="";
    private String password3="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username_field1 = findViewById(R.id.login_username_field);
        password_field1 = findViewById(R.id.login_password_field);
        submit_button = findViewById(R.id.login_submit_button);
        registration_text = findViewById(R.id.btRegister);
        //reg_but = findViewById(R.id.register_button);


        //statementfield = findViewById(R.id.If_you_are_new_user_text);

        mauth = FirebaseAuth.getInstance();

        if(mauth.getCurrentUser()!=null){
            FirebaseUser user = mauth.getCurrentUser();
            String email = mauth.getCurrentUser().getEmail();
            Log.d("mainemail", "onCreate: "+email);
            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
            i.putExtra("emailrd",email);
            startActivity(i);
            finish();
        }



        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username3 = username_field1.getText().toString().trim();
                password3 = password_field1.getText().toString().trim();
                if(TextUtils.isEmpty(username3)){
                    Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(password3)){
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if(password3.length() < 8){
                    Toast.makeText(getApplicationContext(), "Password must be 8 characters", Toast.LENGTH_LONG).show();
                }
                else{
                    mauth.signInWithEmailAndPassword(username3, password3).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("ERRORLINE", "onComplete: Hello eRRROR");
                                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                                i.putExtra("emailrd", username3);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Enter a valid email id or password", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
        registration_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}







