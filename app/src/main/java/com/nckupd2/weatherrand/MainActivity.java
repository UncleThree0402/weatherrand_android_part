package com.nckupd2.weatherrand;


import adapter.FragmentAdapter;
import adapter.SheetBtnAdapter;
import android.Manifest;
import android.content.pm.PackageManager;

import android.os.Message;
import android.os.StrictMode;

import android.view.View;
import androidx.core.app.ActivityCompat;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import controller.SheetBtnController;
import fragments.TodayPageFragment;
import thread.UpdateDataHandle;
import thread.UpdateDataHandlerThread;
import thread.UpdateDataMethod;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //UI
    private ViewPager mViewPager;

    //Var
    private FragmentAdapter mFragmentAdapter;

    private String currentLocation = "TaiNanCity";

    private UpdateDataHandlerThread mUpdateDataHandlerThread;
    private UpdateDataHandle testHandle;

    private UpdateDataMethod mUpdateDataMethod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mUpdateDataHandlerThread = new UpdateDataHandlerThread();
        mUpdateDataHandlerThread.start();
        testHandle = new UpdateDataHandle(mUpdateDataHandlerThread.getLooper(),this);

        mUpdateDataMethod = new UpdateDataMethod(testHandle);
        mUpdateDataMethod.init(currentLocation);

        mViewPager = findViewById(R.id.view_page);

        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        setViewPage(mViewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUpdateDataHandlerThread.quitSafely();
    }

    public void setViewPage(ViewPager viewPage){
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new TodayPageFragment(),"Fresh Food");
        viewPage.setAdapter(fragmentAdapter);
    }

    public void locationListener(View view) {
        SheetBtnController sheetBtn = new SheetBtnController();
        sheetBtn.show(getSupportFragmentManager(),"sheet button");
    }




}