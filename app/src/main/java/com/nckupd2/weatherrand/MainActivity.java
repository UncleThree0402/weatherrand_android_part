package com.nckupd2.weatherrand;


import SqlServerData.SqlServerConnection;
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
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewpager.widget.ViewPager;
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
    private TextView mLocation;

    //Var
    private FragmentAdapter mFragmentAdapter;

    private String currentLocation = "TaiNanCity";

    private SqlServerRetrieveData mSqlServerRetrieveData;
    private UserDataViewModel mUserDataViewModel;
    private boolean initStatus = true;

    private UpdateDataHandlerThread mUpdateDataHandlerThread;
    private UpdateDataHandle updateHandle;
    private UpdateDataMethod mUpdateDataMethod;
    private LifecycleOwner lifecycleOwner = this;
    private ViewModelStoreOwner viewModelStoreOwner = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        mLocation = findViewById(R.id.location_text);
        setSupportActionBar(toolbar);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

//        mUpdateDataHandlerThread = new UpdateDataHandlerThread();
//        mUpdateDataHandlerThread.start();
//        updateHandle = new UpdateDataHandle(mUpdateDataHandlerThread.getLooper(), viewModelStoreOwner, lifecycleOwner);
//        mUpdateDataMethod = new UpdateDataMethod(updateHandle);

        mSqlServerRetrieveData = new SqlServerRetrieveData(viewModelStoreOwner, lifecycleOwner);
        mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);
        mUserDataViewModel.getUserData().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                if (userData.size() == 0) {
                    UserData userData1 = new UserData("TaiNanCity", false);
                    mUserDataViewModel.insertUserData(userData1);
                    SqlServerConnection.connect();
                    if (SqlServerConnection.getConnection() != null) {
                        mSqlServerRetrieveData.insertCurrentWeatherNAirPollution("TaiNanCity");
                        mSqlServerRetrieveData.insertHourlyWeather("TaiNanCity");
                        mSqlServerRetrieveData.insertDailyWeather("TaiNanCity");
                        mSqlServerRetrieveData.insertMonthlyWeather("TaiNanCity");
                    }
                } else if (userData.size() == 1) {
                    if (userData.get(0).isUpdateStatus() | initStatus) {
                        SqlServerConnection.connect();
                        if (SqlServerConnection.getConnection() != null) {
                            mSqlServerRetrieveData.insertCurrentWeatherNAirPollution(userData.get(0).getLocation());
                            mSqlServerRetrieveData.insertHourlyWeather(userData.get(0).getLocation());
                            mSqlServerRetrieveData.insertDailyWeather(userData.get(0).getLocation());
                            mSqlServerRetrieveData.insertMonthlyWeather(userData.get(0).getLocation());
                        }
                        setLocationName(userData.get(0).getLocation());
                        UserData newUserData = new UserData(userData.get(0).getUser_id(), userData.get(0).getLocation(), false);
                        mUserDataViewModel.updateUserData(newUserData);
                        initStatus = false;
                    }
                }
            }
        });


        mViewPager = findViewById(R.id.view_page_container);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        setViewPage(mViewPager);
        mViewPager.setCurrentItem(1);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    private void setLocationName(String name) {
        switch (name) {
            case "ChangHuaCounty":
                mLocation.setText("ChangHua County");
                mLocation.setTextSize(24);
                break;
            case "ChiaYiCity":
                mLocation.setText("ChiaYi City");
                mLocation.setTextSize(28);
                break;
            case "ChiaYiCounty":
                mLocation.setText("ChiaYi County");
                mLocation.setTextSize(28);
                break;
            case "HengChun":
                mLocation.setText("Heng Chun");
                mLocation.setTextSize(28);
                break;
            case "HsinChuCounty":
                mLocation.setText("HsinChu County");
                mLocation.setTextSize(28);
                break;
            case "HsinChuCity":
                mLocation.setText("HsinChu City");
                mLocation.setTextSize(28);
                break;
            case "HuaLienCounty":
                mLocation.setText("Hualien Country");
                mLocation.setTextSize(28);
                break;
            case "KaohSiungCity":
                mLocation.setText("KaohSiung City");
                mLocation.setTextSize(28);
                break;
            case "KeeLungCity":
                mLocation.setText("KeeLung City");
                mLocation.setTextSize(28);
                break;
            case "KinMen":
                mLocation.setText("KinMen");
                mLocation.setTextSize(28);
                break;
            case "LienChiang":
                mLocation.setText("LienChiang");
                mLocation.setTextSize(28);
                break;
            case "MiaoLiCounty":
                mLocation.setText("MiaoLi County");
                mLocation.setTextSize(28);
                break;
            case "NanTouCounty":
                mLocation.setText("NanTou County");
                mLocation.setTextSize(28);
                break;
            case "NewTaipeiCity":
                mLocation.setText("NewTaipei Cityy");
                mLocation.setTextSize(28);
                break;
            case "PengHu":
                mLocation.setText("PengHu");
                mLocation.setTextSize(28);
                break;
            case "PingTungCounty":
                mLocation.setText("PingTung County");
                mLocation.setTextSize(28);
                break;
            case "TaiChungCity":
                mLocation.setText("TaiChung City");
                mLocation.setTextSize(28);
                break;
            case "TainanCity":
                mLocation.setText("Tainan City");
                mLocation.setTextSize(28);
                break;
            case "TaipeiCity":
                mLocation.setText("Taipei City");
                mLocation.setTextSize(28);
                break;
            case "TaiTungCounty":
                mLocation.setText("TaiTung County");
                mLocation.setTextSize(28);
                break;
            case "TaoYuanCity":
                mLocation.setText("TaoYuan City");
                mLocation.setTextSize(28);
                break;
            case "YiLanCounty":
                mLocation.setText("YiLan County");
                mLocation.setTextSize(28);
                break;
            case "YunLinCounty":
                mLocation.setText("YunLin County");
                mLocation.setTextSize(28);
                break;
        }
    }

    public void locationListener(View view) {
        SheetBtmFragment sheetBtn = new SheetBtmFragment(this, this);
        sheetBtn.show(getSupportFragmentManager(), "Sheet Button");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.nav_menu, menu);
        menu.getItem(0).setChecked(true);
        menu.getItem(0).setIcon(R.drawable.bell_on_icon);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_about_us:
                Toast.makeText(MainActivity.this, "Weatherrand\n"+"NCKU PD2 Group project\n" + "Leader : Marco\n" + "Member : " +
                        "Tommy\n"+"                   Hugo\n"+"                   Wayne\n"+"                   Eason", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_notify:
                if (item.isChecked()) {
                    item.setChecked(false);
                    item.setIcon(R.drawable.bell_off_icon);
                    cancelJob();
                } else {
                    item.setChecked(true);
                    item.setIcon(R.drawable.bell_on_icon);
                    scheduleJob();
                }
                return true;
            default:
                return true;
        }
    }
}