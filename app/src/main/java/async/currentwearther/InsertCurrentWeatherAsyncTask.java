package async.currentwearther;

import android.os.AsyncTask;
import models.CurrentWeather;
import persistance.WeatherrandDao;

public class InsertCurrentWeatherAsyncTask extends AsyncTask<CurrentWeather, Void, Void> {

    private final WeatherrandDao mWeatherrandDao;

    public InsertCurrentWeatherAsyncTask(WeatherrandDao weatherrandDao) {
        mWeatherrandDao = weatherrandDao;
    }

    @Override
    protected Void doInBackground(CurrentWeather... currentWeathers) {
        mWeatherrandDao.insertCurrentWeather(currentWeathers);
        return null;
    }
}
