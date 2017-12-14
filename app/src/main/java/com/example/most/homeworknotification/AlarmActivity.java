package com.example.most.homeworknotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class AlarmActivity extends AppCompatActivity {
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ok = (Button) findViewById(R.id.ok);

    }


}
