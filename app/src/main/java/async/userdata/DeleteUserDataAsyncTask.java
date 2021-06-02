package async.userdata;

import android.os.AsyncTask;
import persistance.WeatherrandDao;

public class DeleteUserDataAsyncTask extends AsyncTask<Void, Void, Void> {

    private final WeatherrandDao mWeatherrandDao;

    public DeleteUserDataAsyncTask(WeatherrandDao mWeatherrandDao) {
        this.mWeatherrandDao = mWeatherrandDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mWeatherrandDao.deleteAllUserData();
        return null;
    }
}
