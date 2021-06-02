package com.nckupd2.weatherrand;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RemindActivity extends AppCompatActivity {

    //ui
    Animation rightAni;
    RelativeLayout relativeLayout;
    TextView weather, reminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_remind);

        relativeLayout = findViewById(R.id.rel_remind);
        weather = findViewById(R.id.weather_text);
        reminder = findViewById(R.id.remind_reminder_text);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(RemindActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);


    }
}