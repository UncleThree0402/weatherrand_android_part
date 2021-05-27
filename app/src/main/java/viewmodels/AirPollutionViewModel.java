package viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import models.AirPollution;
import models.CurrentWeather;
import persistance.WeatherrandRepositoty;

import java.util.List;

public class AirPollutionViewModel extends AndroidViewModel {

    private WeatherrandRepositoty mWeatherrandRepositoty;
    private LiveData<List<AirPollution>> mAirPollution;

    public AirPollutionViewModel(@NonNull Application application) {
        super(application);
        mWeatherrandRepositoty = new WeatherrandRepositoty(application);
        mAirPollution = mWeatherrandRepositoty.getAirPollution();
    }

    public void insetAirPollution(AirPollution airPollution){
        mWeatherrandRepositoty.insertAirPollution(airPollution);
    }

    public LiveData<List<AirPollution>> getAirPollution(){
        return mAirPollution;
    }

    public void deleteAllAirPollution(){
        mWeatherrandRepositoty.deleteAllAirPollution();
    }
}
