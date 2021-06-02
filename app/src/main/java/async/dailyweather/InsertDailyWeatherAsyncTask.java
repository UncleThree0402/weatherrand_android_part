package async.dailyweather;

import android.os.AsyncTask;
import models.DailyWeather;
import persistance.WeatherrandDao;

public class InsertDailyWeatherAsyncTask extends AsyncTask<DailyWeather, Void, Void> {

    private final WeatherrandDao mWeatherrandDao;

    public InsertDailyWeatherAsyncTask(WeatherrandDao weatherrandDao) {
        this.mWeatherrandDao = weatherrandDao;
    }

    @Override
    protected Void doInBackground(DailyWeather... dailyWeathers) {
        mWeatherrandDao.insertDailyWeather(dailyWeathers);
        return null;
    }
}
