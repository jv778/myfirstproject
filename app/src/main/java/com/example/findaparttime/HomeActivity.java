package com.example.findaparttime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private CardView see_all;
    private CardView postajob;
    private CardView logout;
    private CardView profile;
    private CardView helpp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        postajob = findViewById(R.id.postId);
        logout = findViewById(R.id.logoutId);
        see_all = findViewById(R.id.bankcardId);


        see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getIntent().getStringExtra("emailrd");
                Intent inte = new Intent(getApplicationContext(),SeeAllJobsActivity.class);
                inte.putExtra("email-id",email);
                startActivity(inte);
            }
        });

        postajob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getIntent().getStringExtra("emailrd");
                Log.d("postemail", "onClick: "+email);
                Intent inte = new Intent(getApplicationContext(),PostAJobActivity.class);
                inte.putExtra("email-id",email);
                startActivity(inte);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });



    }
}
