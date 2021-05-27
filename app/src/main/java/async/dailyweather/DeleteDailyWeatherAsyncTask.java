package async.dailyweather;

import android.os.AsyncTask;
import persistance.WeatherrandDao;

public class DeleteDailyWeatherAsyncTask extends AsyncTask<Void,Void,Void> {
    private WeatherrandDao mWeatherrandDao;

    public DeleteDailyWeatherAsyncTask(WeatherrandDao mWeatherrandDao) {
        this.mWeatherrandDao = mWeatherrandDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mWeatherrandDao.deleteAllDailyWeather();
        return null;
    }
}
