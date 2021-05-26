package async.hourlyweather;

import android.os.AsyncTask;
import models.HourlyWeather;
import persistance.WeatherrandDao;

public class InsertHourlyWeatherAsyncTask extends AsyncTask<HourlyWeather,Void,Void> {

    private WeatherrandDao mWeatherrandDao;

    public InsertHourlyWeatherAsyncTask(WeatherrandDao weatherrandDao) {
        this.mWeatherrandDao = weatherrandDao;
    }

    @Override
    protected Void doInBackground(HourlyWeather... hourlyWeathers) {
        mWeatherrandDao.insertHourlyWeather(hourlyWeathers);
        return null;
    }
}
