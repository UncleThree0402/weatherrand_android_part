package viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import models.HourlyWeather;
import persistance.WeatherrandRepositoty;

import java.util.List;

public class HourlyWeatherViewModel extends AndroidViewModel {

    private final WeatherrandRepositoty mWeatherrandRepositoty;
    private final LiveData<List<HourlyWeather>> mHourlyWeather;

    public HourlyWeatherViewModel(@NonNull Application application) {
        super(application);
        mWeatherrandRepositoty = new WeatherrandRepositoty(application);
        mHourlyWeather = mWeatherrandRepositoty.getHourlyWeather();
    }

    public void insetHourlyWeather(HourlyWeather hourlyWeather) {
        mWeatherrandRepositoty.insertHourlyWeather(hourlyWeather);
    }

    public LiveData<List<HourlyWeather>> getHourlyWeather() {
        return mHourlyWeather;
    }

    public void deleteAllHourlyWeather() {
        mWeatherrandRepositoty.deleteAllHourlyWeather();
    }


}
