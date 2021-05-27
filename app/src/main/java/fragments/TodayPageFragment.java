package fragments;

import adapter.HourlyRecycleViewAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import models.AirPollution;
import models.CurrentWeather;
import models.HourlyWeather;
import viewmodels.AirPollutionViewModel;
import viewmodels.CurrentWeatherViewModel;
import viewmodels.HourlyWeatherViewModel;

import java.util.ArrayList;
import java.util.List;

public class TodayPageFragment extends Fragment {
    private static final String TAG = "TodayPageFragment";

    //Ui
    private TextView currentTemp;
    private ImageView currentIcon;
    private TextView currentWeatherText;
    private TextView currentWind;
    private TextView currentAqi;
    private TextView currentHumidity;
    private RecyclerView hourlyWeatherRecycleView;

    //Var
    private ArrayList<HourlyWeather> mHourlyWeathers = new ArrayList<>();
    private HourlyRecycleViewAdapter mHourlyRecycleViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.today_page_fragment, container, false);
        currentTemp = view.findViewById(R.id.current_temp_text);
        currentIcon = view.findViewById(R.id.current_weather_icon);
        currentWeatherText = view.findViewById(R.id.current_weather_text);
        currentWind = view.findViewById(R.id.current_wind_text);
        currentAqi = view.findViewById(R.id.current_aqi_text);
        currentHumidity = view.findViewById(R.id.current_humidity_text);
        hourlyWeatherRecycleView = view.findViewById(R.id.hourly_recycle_view);

        CurrentWeatherViewModel mCurrentWeatherViewModel = new ViewModelProvider(this).get(CurrentWeatherViewModel.class);
        AirPollutionViewModel mAirPollutionViewModel = new ViewModelProvider(this).get(AirPollutionViewModel.class);
        HourlyWeatherViewModel mHourlyWeatherViewModel = new ViewModelProvider(this).get(HourlyWeatherViewModel.class);

        initHourlyRecycleView();

        mCurrentWeatherViewModel.getCurrentWeather().observe(this.getViewLifecycleOwner(), new Observer<List<CurrentWeather>>() {
            @Override
            public void onChanged(List<CurrentWeather> currentWeathers) {
                if (currentWeathers.size() > 0) {
                    currentTemp.setText(currentWeathers.get(0).getCurrentTemperature());
                    currentWind.setText(currentWeathers.get(0).getWindSpeed());
                    currentHumidity.setText(currentWeathers.get(0).getHumidity());
                    Glide.with(getActivity()).asBitmap().load(currentWeathers.get(0).getWeatherIconUrl()).into(currentIcon);
                    currentWeatherText.setText(currentWeathers.get(0).getWeatherDescription());
                }
            }
        });

        mAirPollutionViewModel.getAirPollution().observe(this.getViewLifecycleOwner(), new Observer<List<AirPollution>>() {
            @Override
            public void onChanged(List<AirPollution> airPollutions) {
                if (airPollutions.size() > 0) {
                    currentAqi.setText(airPollutions.get(0).getAqi());
                }
            }
        });

        mHourlyWeatherViewModel.getHourlyWeather().observe(this.getViewLifecycleOwner(), new Observer<List<HourlyWeather>>() {
            @Override
            public void onChanged(List<HourlyWeather> hourlyWeathers) {
                if(hourlyWeathers.size() > 0){
                    if (mHourlyWeathers.size() > 0) {
                        mHourlyWeathers.clear();
                    }
                    if (mHourlyWeathers != null) {
                        mHourlyWeathers.addAll(hourlyWeathers);
                    }
                    Log.d(TAG, "onChanged: " + mHourlyWeathers.size());
                    mHourlyRecycleViewAdapter.notifyDataSetChanged();
                }
            }
        });



        return view;
    }

    private void initHourlyRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        hourlyWeatherRecycleView.setLayoutManager(linearLayoutManager);
        mHourlyRecycleViewAdapter = new HourlyRecycleViewAdapter(mHourlyWeathers, getContext());
        hourlyWeatherRecycleView.setAdapter(mHourlyRecycleViewAdapter);
    }
}
