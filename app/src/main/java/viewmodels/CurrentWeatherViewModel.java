package viewmodels;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import models.CurrentWeather;
import persistance.WeatherrandRepositoty;

import java.util.List;

public class CurrentWeatherViewModel extends AndroidViewModel {
    private static final String TAG = "CurrentWeatherViewModel";

    private WeatherrandRepositoty mWeatherrandRepositoty;
    private LiveData<List<CurrentWeather>> mCurrentWeather;

    public CurrentWeatherViewModel(@NonNull Application application) {
        super(application);
        mWeatherrandRepositoty = new WeatherrandRepositoty(application);
        mCurrentWeather = mWeatherrandRepositoty.getCurrentWeather();
    }

    public void insetCurrentWeather(CurrentWeather currentWeather){
        Log.d(TAG, "insetCurrentWeather: " + currentWeather.getDateTime());
        mWeatherrandRepositoty.insertCurrentWeather(currentWeather);
    }

    public LiveData<List<CurrentWeather>> getCurrentWeather(){
        return mCurrentWeather;
    }

    public void deleteAllCurrentWeather(){
        mWeatherrandRepositoty.deleteAllCurrentWeather();
    }
}
