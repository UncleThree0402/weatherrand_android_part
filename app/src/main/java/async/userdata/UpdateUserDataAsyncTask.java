package async.userdata;

import android.os.AsyncTask;
import models.UserData;
import persistance.WeatherrandDao;

public class UpdateUserDataAsyncTask extends AsyncTask<UserData, Void, Void> {

    private final WeatherrandDao mWeatherrandDao;

    public UpdateUserDataAsyncTask(WeatherrandDao mWeatherrandDao) {
        this.mWeatherrandDao = mWeatherrandDao;
    }

    @Override
    protected Void doInBackground(UserData... userData) {
        mWeatherrandDao.updateUserData(userData);
        return null;
    }
}
