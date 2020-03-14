package com.example.findaparttime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AllInfoJob extends AppCompatActivity {

    private TextView tit;
    private TextView des;
    private TextView timi;
    private TextView sal;
    private TextView ema;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_info_job);

        tit = findViewById(R.id.job_tit_id);
        des = findViewById(R.id.job_desc_id);
        timi = findViewById(R.id.job_time_id);
        sal = findViewById(R.id.job_salary_id);
        ema = findViewById(R.id.job_provider_id);


        final String title = getIntent().getStringExtra("j1");
        final String description = getIntent().getStringExtra("j2");
        final String timings = getIntent().getStringExtra("t1");
        final String salary = getIntent().getStringExtra("s1");
        final String email = getIntent().getStringExtra("e1");

        tit.setText(title);
        des.setText(description);
        timi.setText(timings);
        sal.setText(salary);
        ema.setText(email);

    }
}
