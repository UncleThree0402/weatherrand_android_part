package viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import models.MonthlyWeather;
import persistance.WeatherrandRepositoty;

import java.util.List;

public class MonthlyWeatherViewModel extends AndroidViewModel {

    private WeatherrandRepositoty mWeatherrandRepositoty;
    private LiveData<List<MonthlyWeather>> mMonthlyWeather;

    public MonthlyWeatherViewModel(@NonNull Application application) {
        super(application);
        mWeatherrandRepositoty = new WeatherrandRepositoty(application);
        mMonthlyWeather = mWeatherrandRepositoty.getMonthlyWeather();
    }

    public void insetMonthlyWeather(MonthlyWeather monthlyWeather){
        mWeatherrandRepositoty.insertMonthlyWeather(monthlyWeather);
    }

    public LiveData<List<MonthlyWeather>> getMonthlyWeather(){
        return mMonthlyWeather;
    }

    public void deleteAllMonthlyWeather(){
        mWeatherrandRepositoty.deleteAllMonthlyWeather();
    }
}
