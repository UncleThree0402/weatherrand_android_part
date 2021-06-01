package com.nckupd2.weatherrand;


import SqlServerData.SqlServerRetrieveData;
import adapter.FragmentAdapter;
import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.pm.PackageManager;

import android.os.StrictMode;

import android.util.Log;
import android.view.*;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.navigation.NavigationView;
import fragments.AirPollutionFragment;
import fragments.DailyPageFragment;
import fragments.SheetBtmFragment;
import fragments.TodayPageFragment;
import models.UserData;
import services.EarthquakeService;
import thread.UpdateDataHandle;
import thread.UpdateDataHandlerThread;
import thread.UpdateDataMethod;
import viewmodels.UserDataViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //UI
    private ViewPager mViewPager;
    private Toolbar toolbar;

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

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        scheduleJob();

        mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);
        mUserDataViewModel.getUserData().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                if(userData.size() == 0){
                    mUserDataViewModel.insertUserData(new UserData(currentLocation,false));
                    Log.d(TAG, "onChanged: called");
                }
            }
        });
        mUpdateDataHandlerThread = new UpdateDataHandlerThread();
        mUpdateDataHandlerThread.start();
        testHandle = new UpdateDataHandle(mUpdateDataHandlerThread.getLooper(), this, this);
        mUpdateDataMethod = new UpdateDataMethod(testHandle);
        mUpdateDataMethod.init(currentLocation);


        mViewPager = findViewById(R.id.view_page_container);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        setViewPage(mViewPager);
        mViewPager.setCurrentItem(1);



    }

    @Override
    protected void onResume() {
        super.onResume();
        mUpdateDataMethod.init(currentLocation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelJob();
        mUpdateDataHandlerThread.quitSafely();
    }

    private void scheduleJob() {
        ComponentName componentName = new ComponentName(this, EarthquakeService.class);
        JobInfo info = new JobInfo.Builder(1, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }

    }

    private void cancelJob() {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(1);
    }

    public void setViewPage(ViewPager viewPage) {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new AirPollutionFragment(), "Air Pollution Page");
        fragmentAdapter.addFragment(new TodayPageFragment(), "Today Page");
        fragmentAdapter.addFragment(new DailyPageFragment(), "Daily Page");
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
        SheetBtmFragment sheetBtn = new SheetBtmFragment(this, this);
        sheetBtn.show(getSupportFragmentManager(), "Sheet Button");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.nav_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_about_us:
                Toast.makeText(MainActivity.this, "about us", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_notify:
                if(item.isChecked()) {
                    item.setChecked(false);
                    item.setIcon(R.drawable.bell_off_icon);
                }else{
                    item.setChecked(true);
                    item.setIcon(R.drawable.bell_on_icon);
                }
                return true;
            default:
                return true;
        }
    }
}