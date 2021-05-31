package viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import models.Earthquake;
import persistance.WeatherrandRepositoty;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeViewModel extends AndroidViewModel {

    private WeatherrandRepositoty mWeatherrandRepositoty;

    private LiveData<List<Earthquake>> mEarthquakes;

    public EarthquakeViewModel(@NonNull Application application) {
        super(application);
        mWeatherrandRepositoty = new WeatherrandRepositoty(application);
        mEarthquakes = mWeatherrandRepositoty.getEarthquake();
    }

    public void insertEarthquake(Earthquake earthquake){
        mWeatherrandRepositoty.insertEarthquake(earthquake);
    }

    public LiveData<List<Earthquake>> getEarthquakes() {
        return mEarthquakes;
    }
}
