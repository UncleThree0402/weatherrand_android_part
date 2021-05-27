package async.monthlyweather;

import android.os.AsyncTask;
import persistance.WeatherrandDao;

public class DeleteMonthlyWeatherAsyncTask extends AsyncTask<Void,Void,Void> {

    private WeatherrandDao mWeatherrandDao;

    public DeleteMonthlyWeatherAsyncTask(WeatherrandDao mWeatherrandDao) {
        this.mWeatherrandDao = mWeatherrandDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mWeatherrandDao.deleteAllMonthlyWeather();
        return null;
    }
}
