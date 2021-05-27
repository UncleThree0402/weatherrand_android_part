package async.currentwearther;

import android.os.AsyncTask;
import persistance.WeatherrandDao;

public class DeleteCurrentWeatherAsyncTask extends AsyncTask<Void,Void,Void> {
    private WeatherrandDao mWeatherrandDao;

    public DeleteCurrentWeatherAsyncTask(WeatherrandDao mWeatherrandDao) {
        this.mWeatherrandDao = mWeatherrandDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mWeatherrandDao.deleteAllCurrentWeather();
        return null;
    }
}
