package fragments;

import adapter.HourlyRecycleViewAdapter;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.nckupd2.weatherrand.R;
import dataformatter.NumberFormatter;
import dataformatter.TimeFormatter;
import models.CurrentWeather;
import models.DailyWeather;
import models.HourlyWeather;
import models.UserData;
import util.HourlyWeatherDecorator;
import viewmodels.*;

import java.util.ArrayList;
import java.util.List;

public class TodayPageFragment extends Fragment {
    private static final String TAG = "TodayPageFragment";
    //Var
    private final ArrayList<HourlyWeather> mHourlyWeathers = new ArrayList<>();
    //Ui
    private TextView currentTemp;
    private ImageView currentIcon;
    private TextView currentWeatherText;
    private TextView currentWind;
    private TextView currentUv;
    private TextView currentHumidity;
    private RecyclerView hourlyWeatherRecycleView;
    private RelativeLayout layout;
    private HourlyRecycleViewAdapter mHourlyRecycleViewAdapter;
    private String currentTempText;
    private String currentIconText;
    private String currentWeatherTextText;
    private String currentWindText;
    private String currentUvText;
    private String currentHumidityText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.today_page_fragment, container, false);
        currentTemp = view.findViewById(R.id.current_temp_text);
        currentIcon = view.findViewById(R.id.current_weather_icon);
        currentWeatherText = view.findViewById(R.id.current_weather_text);
        currentWind = view.findViewById(R.id.current_wind_text);
        currentUv = view.findViewById(R.id.current_uv_text);
        currentHumidity = view.findViewById(R.id.current_humidity_text);
        hourlyWeatherRecycleView = view.findViewById(R.id.hourly_recycle_view);
        layout = getActivity().findViewById(R.id.main_layout);

        CurrentWeatherViewModel mCurrentWeatherViewModel = new ViewModelProvider(this).get(CurrentWeatherViewModel.class);
        AirPollutionViewModel mAirPollutionViewModel = new ViewModelProvider(this).get(AirPollutionViewModel.class);
        HourlyWeatherViewModel mHourlyWeatherViewModel = new ViewModelProvider(this).get(HourlyWeatherViewModel.class);
        UserDataViewModel mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);
        DailyWeatherViewModel mDailyWeatherViewModel = new ViewModelProvider(this).get(DailyWeatherViewModel.class);

        initHourlyRecycleView();

        mCurrentWeatherViewModel.getCurrentWeather().observe(this.getViewLifecycleOwner(), new Observer<List<CurrentWeather>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(List<CurrentWeather> currentWeathers) {
                if (currentWeathers.size() > 0) {
                    currentTempText = NumberFormatter.roundNumber(currentWeathers.get(0).getCurrentTemperature()) + "??";
                    currentWindText = currentWeathers.get(0).getWindSpeed() + "km/h";
                    currentHumidityText = currentWeathers.get(0).getHumidity() + "%";
                    currentIconText = currentWeathers.get(0).getWeatherIconUrl();
                    currentWeatherTextText = currentWeathers.get(0).getWeatherDescription();
                    layoutSrc(currentWeathers.get(0).getWeatherId());
                }
            }
        });
        mDailyWeatherViewModel.getDailyWeather().observe(this.getViewLifecycleOwner(), new Observer<List<DailyWeather>>() {
            @Override
            public void onChanged(List<DailyWeather> dailyWeathers) {
                if (dailyWeathers.size() > 0) {
                    currentUvText = dailyWeathers.get(0).getUvi();
                }
            }
        });


        mHourlyWeatherViewModel.getHourlyWeather().observe(this.getViewLifecycleOwner(), new Observer<List<HourlyWeather>>() {
            @Override
            public void onChanged(List<HourlyWeather> hourlyWeathers) {
                if (hourlyWeathers.size() > 0) {
                    if (mHourlyWeathers.size() > 0) {
                        mHourlyWeathers.clear();
                    }
                    if (mHourlyWeathers != null) {
                        mHourlyWeathers.addAll(hourlyWeathers);
                    }

                }
            }
        });

        mUserDataViewModel.getUserData().observe(this.getViewLifecycleOwner(), new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                if (userData.size() > 0) {
                    if (!userData.get(0).isUpdateStatus()) {
                        currentTemp.setText(currentTempText);
                        currentWind.setText(currentWindText);
                        currentHumidity.setText(currentHumidityText);
                        Glide.with(getActivity()).asBitmap().load(currentIconText).into(currentIcon);
                        currentWeatherText.setText(currentWeatherTextText);
                        currentUv.setText(currentUvText);
                        while (mHourlyWeathers.size() > 0) {
                            if (TimeFormatter.datetimeToHour(mHourlyWeathers.get(0).getDateTime()).equals(TimeFormatter.datetimeToHour(Long.toString(System.currentTimeMillis() / 1000)))) {
                                break;
                            }
                            mHourlyWeathers.remove(0);
                        }
                        mHourlyRecycleViewAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        return view;
    }

    private void initHourlyRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        hourlyWeatherRecycleView.setLayoutManager(linearLayoutManager);
        HourlyWeatherDecorator hourlyWeatherDecorator = new HourlyWeatherDecorator(20);
        hourlyWeatherRecycleView.addItemDecoration(hourlyWeatherDecorator);
        mHourlyRecycleViewAdapter = new HourlyRecycleViewAdapter(mHourlyWeathers, getContext());
        hourlyWeatherRecycleView.setAdapter(mHourlyRecycleViewAdapter);
    }

    private void layoutSrc(String weatherId) {
        int check = NumberFormatter.stringToNumber(weatherId);
        if ((check >= 200 && check <= 202) || (check >= 230 && check <= 232)) {
            layout.setBackgroundResource(R.drawable.rel_back_1);
        } else if (check >= 210 && check <= 221) {
            layout.setBackgroundResource(R.drawable.rel_back_2);
        } else if (check >= 300 && check <= 321) {
            layout.setBackgroundResource(R.drawable.rel_back_3);
        } else if (check >= 520 && check <= 531) {
            layout.setBackgroundResource(R.drawable.rel_back_4);
        } else if (check >= 500 && check <= 511) {
            layout.setBackgroundResource(R.drawable.rel_back_5);
        } else if (check >= 600 && check <= 622) {
            layout.setBackgroundResource(R.drawable.rel_back_6);
        } else if (check == 731 || check == 751 || check == 761 || check == 762) {
            layout.setBackgroundResource(R.drawable.rel_back_7);
        } else if (check == 701 || check == 711 || check == 721 || check == 742 || check == 771 || check == 781) {
            layout.setBackgroundResource(R.drawable.rel_back_8);
        } else if (check == 800) {
            layout.setBackgroundResource(R.drawable.rel_back_9);
        } else if (check == 801 || check == 802) {
            layout.setBackgroundResource(R.drawable.rel_back_10);
        } else if (check == 803 || check == 804) {
            layout.setBackgroundResource(R.drawable.rel_back_11);
        }
    }
}
