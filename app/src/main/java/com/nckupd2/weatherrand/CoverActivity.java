package com.nckupd2.weatherrand;

import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CoverActivity extends AppCompatActivity {

    Animation leftAni , rightAni , sunAni;
    ImageView sunIcon,cloudIcon,cloud1,cloud2;
    TextView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cover);

        rightAni = AnimationUtils.loadAnimation(this,R.anim.right_ain);
        leftAni = AnimationUtils.loadAnimation(this,R.anim.left_ani);
        sunAni = AnimationUtils.loadAnimation(this,R.anim.sun_ani);


        sunIcon = findViewById(R.id.weatherrand_sun);
        cloudIcon = findViewById(R.id.weatherrand_cloud);
        cloud1 = findViewById(R.id.cover_cloud1);
        cloud2 = findViewById(R.id.cover_cloud2);
        logo = findViewById(R.id.weaterrand_text);

        sunIcon.setAnimation(sunAni);
        cloudIcon.setAnimation(leftAni);
        logo.setAnimation(rightAni);
        cloud1.setAnimation(rightAni);
        cloud2.setAnimation(leftAni);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CoverActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);

    }
}