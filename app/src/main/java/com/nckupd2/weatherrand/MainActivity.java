package com.nckupd2.weatherrand;


import SqlServerData.SqlServerConnection;
import SqlServerData.SqlServerRetrieveData;
import adapter.FragmentAdapter;
import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewpager.widget.ViewPager;
import dialog.AboutUsDialog;
import fragments.AirPollutionFragment;
import fragments.DailyPageFragment;
import fragments.SheetBtmFragment;
import fragments.TodayPageFragment;
import models.UserData;
import services.EarthquakeService;
import viewmodels.UserDataViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private final String currentLocation = "TaiNanCity";
    private final LifecycleOwner lifecycleOwner = this;
    private final ViewModelStoreOwner viewModelStoreOwner = this;
    //UI
    private ViewPager mViewPager;
    private Toolbar toolbar;
    private TextView mLocation;
    //Var
    private SqlServerRetrieveData mSqlServerRetrieveData;
    private UserDataViewModel mUserDataViewModel;
    private boolean initStatus = true;

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


        mSqlServerRetrieveData = new SqlServerRetrieveData(viewModelStoreOwner, lifecycleOwner);
        mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);
        mUserDataViewModel.getUserData().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                if (userData.size() == 0) {
                    UserData userData1 = new UserData("TaiNanCity", false, false);
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
                        UserData newUserData = new UserData(userData.get(0).getUser_id(), userData.get(0).getLocation(), false, userData.get(0).isNotificationStatus());
                        mUserDataViewModel.updateUserData(newUserData);
                        initStatus = false;
                    }
                }
            }
        });


        mViewPager = findViewById(R.id.view_page_container);
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
                mLocation.setText("Changhua County");
                mLocation.setTextSize(24);
                break;
            case "ChiaYiCity":
                mLocation.setText("Chiayi City");
                mLocation.setTextSize(28);
                break;
            case "ChiaYiCounty":
                mLocation.setText("Chiayi County");
                mLocation.setTextSize(28);
                break;
            case "HengChun":
                mLocation.setText("Hengchun");
                mLocation.setTextSize(28);
                break;
            case "HsinChuCounty":
                mLocation.setText("Hsinchu County");
                mLocation.setTextSize(28);
                break;
            case "HsinChuCity":
                mLocation.setText("Hsinchu City");
                mLocation.setTextSize(28);
                break;
            case "HuaLienCounty":
                mLocation.setText("Hualien County");
                mLocation.setTextSize(28);
                break;
            case "KaohSiungCity":
                mLocation.setText("Kaohsiung City");
                mLocation.setTextSize(28);
                break;
            case "KeeLungCity":
                mLocation.setText("Keelung City");
                mLocation.setTextSize(28);
                break;
            case "KinMen":
                mLocation.setText("Kinmen Islands");
                mLocation.setTextSize(24);
                break;
            case "LienChiang":
                mLocation.setText("Matsu Islands");
                mLocation.setTextSize(28);
                break;
            case "MiaoLiCounty":
                mLocation.setText("Miaoli County");
                mLocation.setTextSize(28);
                break;
            case "NanTouCounty":
                mLocation.setText("Nantou County");
                mLocation.setTextSize(28);
                break;
            case "NewTaipeiCity":
                mLocation.setText("New Taipei City");
                mLocation.setTextSize(28);
                break;
            case "PengHu":
                mLocation.setText("Penghu County");
                mLocation.setTextSize(28);
                break;
            case "PingTungCounty":
                mLocation.setText("Pingtung County");
                mLocation.setTextSize(28);
                break;
            case "TaiChungCity":
                mLocation.setText("Taichung City");
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
                mLocation.setText("Taitung County");
                mLocation.setTextSize(28);
                break;
            case "TaoYuanCity":
                mLocation.setText("Taoyuan City");
                mLocation.setTextSize(28);
                break;
            case "YiLanCounty":
                mLocation.setText("Yilan County");
                mLocation.setTextSize(28);
                break;
            case "YunLinCounty":
                mLocation.setText("Yunlin County");
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
        mUserDataViewModel.getUserData().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                if (userData.size() > 0) {
                    if (userData.get(0).isNotificationStatus()) {
                        menu.getItem(0).setChecked(true);
                        menu.getItem(0).setIcon(R.drawable.bell_on_icon);
                    } else {
                        menu.getItem(0).setChecked(false);
                        menu.getItem(0).setIcon(R.drawable.bell_off_icon);
                    }
                }
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_about_us:
                AboutUsDialog aboutUsDialog = new AboutUsDialog();
                aboutUsDialog.show(getSupportFragmentManager(), "AboutUs");
                return true;
            case R.id.nav_notify:
                boolean update;
                if (item.isChecked()) {
                    item.setChecked(false);
                    item.setIcon(R.drawable.bell_off_icon);
                    cancelJob();
                    update = false;
                } else {
                    item.setChecked(true);
                    item.setIcon(R.drawable.bell_on_icon);
                    scheduleJob();
                    update = true;
                }
                mUserDataViewModel.getUserData().observe(this, new Observer<List<UserData>>() {
                    boolean status = true;

                    @Override
                    public void onChanged(List<UserData> userData) {
                        UserData newUserData = userData.get(0);
                        newUserData.setNotificationStatus(update);
                        if (status) {
                            mUserDataViewModel.updateUserData(newUserData);
                            status = false;
                        }
                    }
                });
                return true;
            default:
                return true;
        }
    }
}