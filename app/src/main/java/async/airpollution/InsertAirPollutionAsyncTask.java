package async.airpollution;

import android.os.AsyncTask;
import models.AirPollution;
import persistance.WeatherrandDao;

public class InsertAirPollutionAsyncTask extends AsyncTask<AirPollution,Void,Void> {

    private WeatherrandDao mWeatherrandDao;

    public InsertAirPollutionAsyncTask(WeatherrandDao weatherrandDao) {
        this.mWeatherrandDao = weatherrandDao;
    }

    @Override
    protected Void doInBackground(AirPollution... airPollutions) {
        mWeatherrandDao.deleteAllAirPollution();
        mWeatherrandDao.insertAirPollution(airPollutions);
        return null;
    }
}
