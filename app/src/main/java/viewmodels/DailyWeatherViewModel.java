package viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import models.DailyWeather;
import persistance.WeatherrandRepositoty;

import java.util.List;

public class DailyWeatherViewModel extends AndroidViewModel {

    private final WeatherrandRepositoty mWeatherrandRepositoty;
    private final LiveData<List<DailyWeather>> mDailyWeather;

    public DailyWeatherViewModel(@NonNull Application application) {
        super(application);
        mWeatherrandRepositoty = new WeatherrandRepositoty(application);
        mDailyWeather = mWeatherrandRepositoty.getDailyWeather();
    }

    public void insetDailyWeather(DailyWeather dailyWeather) {
        mWeatherrandRepositoty.insertDailyWeather(dailyWeather);
    }

    public LiveData<List<DailyWeather>> getDailyWeather() {
        return mDailyWeather;
    }

    public void deleteAllDailyWeather() {
        mWeatherrandRepositoty.deleteAllDailyWeather();
    }
}
