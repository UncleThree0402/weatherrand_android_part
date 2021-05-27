package async.airpollution;

import android.os.AsyncTask;
import persistance.WeatherrandDao;

public class DeleteAirPollutionAsyncTask extends AsyncTask<Void,Void,Void> {
    private WeatherrandDao mWeatherrandDao;

    public DeleteAirPollutionAsyncTask(WeatherrandDao mWeatherrandDao) {
        this.mWeatherrandDao = mWeatherrandDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mWeatherrandDao.deleteAllAirPollution();
        return null;
    }
}
