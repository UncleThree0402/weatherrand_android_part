package com.nckupd2.weatherrand;


import SqlServerData.SqlServerRetrieveData;
import adapter.FragmentAdapter;
import android.Manifest;
import android.content.pm.PackageManager;

import android.os.StrictMode;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.ActivityCompat;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import fragments.AirPollutionFragment;
import fragments.DailyPageFragment;
import fragments.SheetBtnFragment;
import fragments.TodayPageFragment;
import models.UserData;
import thread.UpdateDataHandle;
import thread.UpdateDataHandlerThread;
import thread.UpdateDataMethod;
import viewmodels.UserDataViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";

    //UI
    private ViewPager mViewPager;

    //Var
    private FragmentAdapter mFragmentAdapter;

    private String currentLocation = "TaiNanCity";

    private SqlServerRetrieveData mSqlServerRetrieveData;
    private UserDataViewModel mUserDataViewModel;

    private UpdateDataHandlerThread mUpdateDataHandlerThread;
    private UpdateDataHandle testHandle;

    private UpdateDataMethod mUpdateDataMethod;

    private GestureDetector mGestureDetector;
    private int currentPage = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);
//        mUserDataViewModel.getUserData().observe(this, new Observer<List<UserData>>() {
//            @Override
//            public void onChanged(List<UserData> userData) {
//                if(userData.size() == 0){
//                    mUserDataViewModel.insertUserData(new UserData(currentLocation,false));
//                    Log.d(TAG, "onChanged: called");
//                }
//            }
//        });
        mUpdateDataHandlerThread = new UpdateDataHandlerThread();
        mUpdateDataHandlerThread.start();
        testHandle = new UpdateDataHandle(mUpdateDataHandlerThread.getLooper(), this,this);
        mUpdateDataMethod = new UpdateDataMethod(testHandle);
        mUpdateDataMethod.init(currentLocation);


        mViewPager = findViewById(R.id.view_page_container);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        setViewPage(mViewPager);
        mViewPager.setCurrentItem(1);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUpdateDataHandlerThread.quitSafely();
    }

    public void setViewPage(ViewPager viewPage) {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new AirPollutionFragment(),"Air Pollution Page");
        fragmentAdapter.addFragment(new TodayPageFragment(), "Today Page");
        fragmentAdapter.addFragment(new DailyPageFragment(),"Daily Page");
        viewPage.setAdapter(fragmentAdapter);
    }

    private void initUserData() {
        mUserDataViewModel.getUserData().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                Log.d(TAG, "onChanged: called");
            }
        });
    }

    public void locationListener(View view) {
        SheetBtnFragment sheetBtn = new SheetBtnFragment(this,this);
        sheetBtn.show(getSupportFragmentManager(), "Sheet Button");
    }



}