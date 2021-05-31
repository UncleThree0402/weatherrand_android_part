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
import androidx.viewpager.widget.ViewPager;
import fragments.AirPollutionFragment;
import fragments.DailyPageFragment;
import fragments.SheetBtmFragment;
import fragments.TodayPageFragment;
import models.UserData;
import thread.UpdateDataHandle;
import thread.UpdateDataHandlerThread;
import thread.UpdateDataMethod;
import viewmodels.UserDataViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {
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


        mUpdateDataHandlerThread = new UpdateDataHandlerThread();
        mUpdateDataHandlerThread.start();
        testHandle = new UpdateDataHandle(mUpdateDataHandlerThread.getLooper(), this,this);
        mUpdateDataMethod = new UpdateDataMethod(testHandle);
        mUpdateDataMethod.init(currentLocation);


        mViewPager = findViewById(R.id.view_page_container);
        mGestureDetector = new GestureDetector(this, this);
        mViewPager.setOnTouchListener(this);
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
        SheetBtmFragment sheetBtn = new SheetBtmFragment(this,this);
        sheetBtn.show(getSupportFragmentManager(), "Sheet Button");
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.view_page_container:
                mGestureDetector.onTouchEvent(event);
                break;
        }
        Log.d(TAG, "onFling: " + currentPage);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (velocityX < 0) {
            Log.d(TAG, "onFling: velocity X < 0");
            if (currentPage + 1 <= 2) {
                currentPage++;
                mViewPager.setCurrentItem(currentPage);
            }
        }
        if (velocityX > 0) {
            Log.d(TAG, "onFling: velocity X > 0");
            if (currentPage - 1 >= 0) {
                currentPage--;
                mViewPager.setCurrentItem(currentPage);
            }
        }
        return false;
    }



}