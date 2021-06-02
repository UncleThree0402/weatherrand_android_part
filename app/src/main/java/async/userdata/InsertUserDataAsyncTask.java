package async.userdata;

import android.os.AsyncTask;
import models.UserData;
import persistance.WeatherrandDao;

public class InsertUserDataAsyncTask extends AsyncTask<UserData, Void, Void> {

    private final WeatherrandDao mWeatherrandDao;

    public InsertUserDataAsyncTask(WeatherrandDao mWeatherrandDao) {
        this.mWeatherrandDao = mWeatherrandDao;
    }

    @Override
    protected Void doInBackground(UserData... userData) {
        mWeatherrandDao.insertUserData(userData);
        return null;
    }
}
