package com.nckupd2.weatherrand;


import SqlServerData.SqlServerRetrieveData;
import adapter.FragmentAdapter;
import android.Manifest;
import android.content.pm.PackageManager;

import android.os.StrictMode;

import android.view.View;
import androidx.core.app.ActivityCompat;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import fragments.TodayPageFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //UI
    private ViewPager mViewPager;

    //Var
    private FragmentAdapter mFragmentAdapter;
    private String currentLocation = "TaiNanCity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SqlServerRetrieveData sqlServerRetrieveData = new SqlServerRetrieveData(this);
        sqlServerRetrieveData.insertCurrentWeatherNAirPollution(currentLocation);
        sqlServerRetrieveData.insertHourlyWeather(currentLocation);


        mViewPager = findViewById(R.id.view_page);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        setViewPage(mViewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mUpdateDataHandlerThread.quitSafely();
    }

    public void setViewPage(ViewPager viewPage){
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new TodayPageFragment(),"Fresh Food");
        viewPage.setAdapter(fragmentAdapter);
    }

//    public void init(String location){
//        updateCurrentWeatherNAirPollution(location);
//        updateHourlyWeather(location);
//        updateDailyWeather(location);
//        updateMonthlyWeather(location);
//    }
//
//    public void updateCurrentWeatherNAirPollution(String currentLocation){
//        Message msg = Message.obtain();
//        msg.what = 1;
//        msg.obj = currentLocation;
//       testHandle.sendMessage(msg);
//    }
//
//    public void updateHourlyWeather(String currentLocation){
//        Message msg = Message.obtain();
//        msg.what = 2;
//        msg.obj = currentLocation;
//        testHandle.sendMessage(msg);
//    }
//
//    public void updateDailyWeather(String currentLocation){
//        Message msg = Message.obtain();
//        msg.what = 3;
//        msg.obj = currentLocation;
//        testHandle.sendMessage(msg);
//    }
//
//    public void updateMonthlyWeather(String currentLocation){
//        Message msg = Message.obtain();
//        msg.what = 4;
//        msg.obj = currentLocation;
//        testHandle.sendMessage(msg);
//    }


}