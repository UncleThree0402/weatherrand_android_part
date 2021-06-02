package async.earthquake;

import android.os.AsyncTask;
import persistance.WeatherrandDao;

public class DeleteAllEarthquakeAsyncTask extends AsyncTask<Void, Void, Void> {

    private final WeatherrandDao mWeatherrandDao;

    public DeleteAllEarthquakeAsyncTask(WeatherrandDao mWeatherrandDao) {
        this.mWeatherrandDao = mWeatherrandDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mWeatherrandDao.deleteAllEarthquake();
        return null;
    }
}
