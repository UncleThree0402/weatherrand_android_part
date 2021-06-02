package async.hourlyweather;

import android.os.AsyncTask;
import persistance.WeatherrandDao;

public class DeleteHourlyWeatherAsyncTask extends AsyncTask<Void, Void, Void> {
    private final WeatherrandDao mWeatherrandDao;

    public DeleteHourlyWeatherAsyncTask(WeatherrandDao mWeatherrandDao) {
        this.mWeatherrandDao = mWeatherrandDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mWeatherrandDao.deleteAllHourlyWeather();
        return null;
    }
}
