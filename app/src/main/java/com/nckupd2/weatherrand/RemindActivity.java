package com.nckupd2.weatherrand;

import SqlServerData.SqlServerConnection;
import SqlServerData.SqlServerRetrieveData;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import dataformatter.NumberFormatter;
import models.DailyWeather;
import models.UserData;
import viewmodels.DailyWeatherViewModel;
import viewmodels.UserDataViewModel;

import java.util.List;

public class RemindActivity extends AppCompatActivity {

    //ui
    private Animation rightAni, leftAni;
    private RelativeLayout relativeLayout;
    private TextView weather, reminder;

    //Var
    private UserDataViewModel mUserDataViewModel;
    private DailyWeatherViewModel mDailyWeatherViewModel;
    private SqlServerRetrieveData mSqlServerRetrieveData;

    private DailyWeather mDailyWeather;
    private boolean initStatus = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_remind);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        relativeLayout = findViewById(R.id.rel_remind);
        weather = findViewById(R.id.weather_text);
        reminder = findViewById(R.id.remind_reminder_text);

        rightAni = AnimationUtils.loadAnimation(this, R.anim.right_ain);
        leftAni = AnimationUtils.loadAnimation(this, R.anim.left_ani);

        mSqlServerRetrieveData = new SqlServerRetrieveData(this, this);
        mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);
        mDailyWeatherViewModel = new ViewModelProvider(this).get(DailyWeatherViewModel.class);

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
                            mSqlServerRetrieveData.insertDailyWeather(userData.get(0).getLocation());
                        }
                        UserData newUserData = new UserData(userData.get(0).getUser_id(), userData.get(0).getLocation(), false, userData.get(0).isNotificationStatus());
                        mUserDataViewModel.updateUserData(newUserData);
                        initStatus = false;
                    } else {
                        setReminder(mDailyWeather.getWeatherId());
                    }
                }
            }
        });

        mDailyWeatherViewModel.getDailyWeather().observe(this, new Observer<List<DailyWeather>>() {
            @Override
            public void onChanged(List<DailyWeather> dailyWeathers) {
                if (dailyWeathers.size() > 0) {
                    mDailyWeather = dailyWeathers.get(0);
                    layoutSrc(dailyWeathers.get(0).getWeatherId());

                }
            }
        });

        weather.setAnimation(rightAni);
        reminder.setAnimation(leftAni);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(RemindActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }

    private void layoutSrc(String weatherId) {
        int check = NumberFormatter.stringToNumber(weatherId);
        if ((check >= 200 && check <= 202) || (check >= 230 && check <= 232)) {
            relativeLayout.setBackgroundResource(R.drawable.rel_back_1);
        } else if (check >= 210 && check <= 221) {
            relativeLayout.setBackgroundResource(R.drawable.rel_back_2);
        } else if (check >= 300 && check <= 321) {
            relativeLayout.setBackgroundResource(R.drawable.rel_back_3);
        } else if (check >= 520 && check <= 531) {
            relativeLayout.setBackgroundResource(R.drawable.rel_back_4);
        } else if (check >= 500 && check <= 511) {
            relativeLayout.setBackgroundResource(R.drawable.rel_back_5);
        } else if (check >= 600 && check <= 622) {
            relativeLayout.setBackgroundResource(R.drawable.rel_back_6);
        } else if (check == 731 || check == 751 || check == 761 || check == 762) {
            relativeLayout.setBackgroundResource(R.drawable.rel_back_7);
        } else if (check == 701 || check == 711 || check == 721 || check == 742 || check == 771 || check == 781) {
            relativeLayout.setBackgroundResource(R.drawable.rel_back_8);
        } else if (check == 800) {
            relativeLayout.setBackgroundResource(R.drawable.rel_back_9);
        } else if (check == 801 || check == 802) {
            relativeLayout.setBackgroundResource(R.drawable.rel_back_10);
        } else if (check == 803 || check == 804) {
            relativeLayout.setBackgroundResource(R.drawable.rel_back_11);
        }
    }

    private void setReminder(String weatherId) {
        int checkWeather = Integer.parseInt(weatherId);
        if (checkWeather >= 200 && checkWeather <= 232) {
            weather.setText("Rainy Day");
            reminder.setText("\"Scary lightening...\"");
        } else if (checkWeather >= 300 && checkWeather <= 321) {
            weather.setText("Rainy Day");
            reminder.setText("\"Who is crying??\"");
        } else if (checkWeather >= 500 && checkWeather <= 531) {
            weather.setText("Rainy Day");
            reminder.setText("\"Please close the faucet after using!!\"");
        } else if (checkWeather >= 600 && checkWeather <= 622) {
            weather.setText("Snow Day");
            reminder.setText("\"Did someone forget to close the refrigirator???\"");
        } else if (checkWeather == 701 || checkWeather == 741) {
            weather.setText("Foggy Day");
            reminder.setText("\"Get out my way!!!!\"");
        } else if (checkWeather >= 711 && checkWeather <= 762) {
            weather.setText("Dusty Day");
            reminder.setText("\"Better to have a goggle.\"");
        } else if (checkWeather == 771 || checkWeather == 781) {
            weather.setText("Windy Day");
            reminder.setText("\"Wish your roof still exist.\"");
        } else if (checkWeather >= 800 && checkWeather <= 802) {
            weather.setText("Normal Day");
            reminder.setText("\"Just another normal day...\"");
        } else if (checkWeather == 803 || checkWeather == 804) {
            weather.setText("Cloudy Day");
            reminder.setText("\"Turn on the light, please.\"");
        }

    }


}