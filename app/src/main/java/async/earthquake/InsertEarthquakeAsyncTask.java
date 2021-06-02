package async.earthquake;

import android.os.AsyncTask;
import models.Earthquake;
import persistance.WeatherrandDao;

public class InsertEarthquakeAsyncTask extends AsyncTask<Earthquake, Void, Void> {

    private final WeatherrandDao mWeatherrandDao;

    public InsertEarthquakeAsyncTask(WeatherrandDao mWeatherrandDao) {
        this.mWeatherrandDao = mWeatherrandDao;
    }

    @Override
    protected Void doInBackground(Earthquake... earthquakes) {
        mWeatherrandDao.insertEarthquake(earthquakes);
        return null;
    }
}
