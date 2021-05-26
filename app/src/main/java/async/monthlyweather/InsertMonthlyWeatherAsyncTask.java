package async.monthlyweather;

import android.os.AsyncTask;
import models.MonthlyWeather;
import persistance.WeatherrandDao;

public class InsertMonthlyWeatherAsyncTask extends AsyncTask<MonthlyWeather,Void,Void> {

    private WeatherrandDao mWeatherrandDao;

    public InsertMonthlyWeatherAsyncTask(WeatherrandDao weatherrandDao) {
        this.mWeatherrandDao = weatherrandDao;
    }

    @Override
    protected Void doInBackground(MonthlyWeather... monthlyWeathers) {
        mWeatherrandDao.insertMonthlyWeather(monthlyWeathers);
        return null;
    }
}
