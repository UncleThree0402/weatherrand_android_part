package fragments;

import adapter.DailyRecycleViewAdapter;
import adapter.HourlyRecycleViewAdapter;
import android.os.Bundle;
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
import dataformatter.NumberFormatter;
import dataformatter.TimeFormatter;
import models.DailyWeather;
import models.HourlyWeather;
import models.UserData;
import util.DailyWeatherDecorator;
import viewmodels.DailyWeatherViewModel;
import viewmodels.UserDataViewModel;

import java.util.ArrayList;
import java.util.List;

public class DailyPageFragment extends Fragment {

    //UI
    private RecyclerView mDailyRecycleView;
    private TextView tmrDate;
    private TextView tmrTemp;
    private TextView tmrWind;
    private TextView tmrHum;
    private TextView tmrRain;
    private ImageView tmrIcon;
    private TextView tmrDescription;

    //Var
    private ArrayList<DailyWeather> mDailyWeathers = new ArrayList<>();
    private DailyRecycleViewAdapter mDailyRecycleViewAdapter;
    private DailyWeatherViewModel dailyWeatherViewModel;
    private UserDataViewModel userDataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_page_fragment, container, false);

        mDailyRecycleView = view.findViewById(R.id.seven_day_recycle_view);
        tmrDate = view.findViewById(R.id.tomorrow_date_text);
        tmrTemp = view.findViewById(R.id.tomorrow_temp_text);
        tmrWind = view.findViewById(R.id.tomorrow_wind_text);
        tmrHum = view.findViewById(R.id.tomorrow_humidity_text);
        tmrRain = view.findViewById(R.id.tomorrow_cloud_text);
        tmrIcon = view.findViewById(R.id.tomorrow_weather_icon);
        tmrDescription = view.findViewById(R.id.tomorrow_weather_text);

        dailyWeatherViewModel = new ViewModelProvider(this).get(DailyWeatherViewModel.class);
        userDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);

        userDataViewModel.getUserData().observe(getViewLifecycleOwner(), new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                if(mDailyWeathers.size() > 0){
                    tmrDate.setText(TimeFormatter.datetimeToHour(mDailyWeathers.get(0).getDatetime()));
                    tmrTemp.setText(NumberFormatter.roundNumber(mDailyWeathers.get(0).getDayTemperature()) + "°");
                    tmrWind.setText(mDailyWeathers.get(0).getWindSpeed() + "km/h");
                    tmrHum.setText(NumberFormatter.percentageFormat(mDailyWeathers.get(0).getHumidity()));
                    tmrRain.setText(NumberFormatter.percentageFormat(mDailyWeathers.get(0).getRainPercentage()));
                    Glide.with(getActivity()).asBitmap().load(mDailyWeathers.get(0).getWeatherIconUrl()).into(tmrIcon);
                    tmrDescription.setText(mDailyWeathers.get(0).getWeatherDescription());
                    mDailyWeathers.remove(0);
                    mDailyRecycleViewAdapter.notifyDataSetChanged();
                }
            }
        });

        dailyWeatherViewModel.getDailyWeather().observe(getViewLifecycleOwner(), new Observer<List<DailyWeather>>() {
            @Override
            public void onChanged(List<DailyWeather> dailyWeathers) {
                if (dailyWeathers.size() > 0) {
                    if (mDailyWeathers.size() > 0) {
                        mDailyWeathers.clear();
                    }
                    if (mDailyWeathers != null) {
                        mDailyWeathers.addAll(dailyWeathers);
                    }
                }
            }
        });

        initDailyRecycleView();

        return view;
    }

    private void initDailyRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        mDailyRecycleView.setLayoutManager(linearLayoutManager);
        DailyWeatherDecorator dailyWeatherDecorator = new DailyWeatherDecorator(16);
        mDailyRecycleView.addItemDecoration(dailyWeatherDecorator);
        mDailyRecycleViewAdapter = new DailyRecycleViewAdapter(mDailyWeathers, this.getContext());
        mDailyRecycleView.setAdapter(mDailyRecycleViewAdapter);
    }


}