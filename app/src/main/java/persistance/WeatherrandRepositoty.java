package persistance;

import android.content.Context;
import androidx.lifecycle.LiveData;
import async.airpollution.DeleteAirPollutionAsyncTask;
import async.airpollution.InsertAirPollutionAsyncTask;
import async.currentwearther.DeleteCurrentWeatherAsyncTask;
import async.currentwearther.InsertCurrentWeatherAsyncTask;
import async.dailyweather.DeleteDailyWeatherAsyncTask;
import async.dailyweather.InsertDailyWeatherAsyncTask;
import async.earthquake.DeleteAllEarthquakeAsyncTask;
import async.earthquake.InsertEarthquakeAsyncTask;
import async.hourlyweather.DeleteHourlyWeatherAsyncTask;
import async.hourlyweather.InsertHourlyWeatherAsyncTask;
import async.monthlyweather.DeleteMonthlyWeatherAsyncTask;
import async.monthlyweather.InsertMonthlyWeatherAsyncTask;
import async.userdata.DeleteUserDataAsyncTask;
import async.userdata.InsertUserDataAsyncTask;
import async.userdata.UpdateUserDataAsyncTask;
import models.*;

import java.util.List;

public class WeatherrandRepositoty {

    private final WetherrandDatabase mWetherrandDatabase;

    public WeatherrandRepositoty(Context context) {
        mWetherrandDatabase = WetherrandDatabase.getInstance(context);
    }

    public void insertCurrentWeather(CurrentWeather currentWeather) {
        new InsertCurrentWeatherAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute(currentWeather);
    }

    public void insertHourlyWeather(HourlyWeather hourlyWeather) {
        new InsertHourlyWeatherAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute(hourlyWeather);
    }

    public void insertDailyWeather(DailyWeather dailyWeather) {
        new InsertDailyWeatherAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute(dailyWeather);
    }

    public void insertMonthlyWeather(MonthlyWeather monthlyWeather) {
        new InsertMonthlyWeatherAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute(monthlyWeather);
    }

    public void insertAirPollution(AirPollution airPollution) {
        new InsertAirPollutionAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute(airPollution);
    }

    public void insertUserData(UserData userData) {
        new InsertUserDataAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute(userData);
    }

    public void insertEarthquake(Earthquake earthquake) {
        new InsertEarthquakeAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute(earthquake);
    }

    public LiveData<List<CurrentWeather>> getCurrentWeather() {
        return mWetherrandDatabase.getWeatherrandDao().getCurrentWeather();
    }

    public LiveData<List<HourlyWeather>> getHourlyWeather() {
        return mWetherrandDatabase.getWeatherrandDao().getHourlyWeather();
    }

    public LiveData<List<DailyWeather>> getDailyWeather() {
        return mWetherrandDatabase.getWeatherrandDao().getDailyWeather();
    }

    public LiveData<List<MonthlyWeather>> getMonthlyWeather() {
        return mWetherrandDatabase.getWeatherrandDao().getMonthlyWeather();
    }

    public LiveData<List<AirPollution>> getAirPollution() {
        return mWetherrandDatabase.getWeatherrandDao().getAirPollution();
    }

    public LiveData<List<UserData>> getUserData() {
        return mWetherrandDatabase.getWeatherrandDao().getUserData();
    }

    public LiveData<List<Integer>> getUserTableCount() {
        return mWetherrandDatabase.getWeatherrandDao().getUserTableCount();
    }

    public LiveData<List<Earthquake>> getEarthquake() {
        return mWetherrandDatabase.getWeatherrandDao().getEarthquake();
    }

    public void updateUserData(UserData userData) {
        new UpdateUserDataAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute(userData);
    }

    public void deleteAllHourlyWeather() {
        new DeleteHourlyWeatherAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute();
    }

    public void deleteAllDailyWeather() {
        new DeleteDailyWeatherAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute();
    }

    public void deleteAllMonthlyWeather() {
        new DeleteMonthlyWeatherAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute();
    }

    public void deleteAllCurrentWeather() {
        new DeleteCurrentWeatherAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute();
    }

    public void deleteAllAirPollution() {
        new DeleteAirPollutionAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute();
    }

    public void deleteAllUserData() {
        new DeleteUserDataAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute();
    }

    public void deleteAllEarthquake() {
        new DeleteAllEarthquakeAsyncTask(mWetherrandDatabase.getWeatherrandDao()).execute();
    }


}
