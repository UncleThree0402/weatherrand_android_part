package persistance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import models.*;

import java.util.List;

@Dao
public interface WeatherrandDao {


    @Insert
    long[] insertCurrentWeather(CurrentWeather... currentWeathers);

    @Insert
    long[] insertHourlyWeather(HourlyWeather... hourlyWeathers);

    @Insert
    long[] insertDailyWeather(DailyWeather... dailyWeathers);

    @Insert
    long[] insertMonthlyWeather(MonthlyWeather... monthlyWeathers);

    @Insert
    long[] insertAirPollution(AirPollution... airPollutions);

    @Insert
    long[] insertUserData(UserData... userData);

    @Insert
    long[] insertEarthquake(Earthquake... earthquakes);

    @Query("SELECT * FROM current_weather")
    LiveData<List<CurrentWeather>> getCurrentWeather();

    @Query("SELECT * FROM hourly_weather")
    LiveData<List<HourlyWeather>> getHourlyWeather();

    @Query("SELECT * FROM daily_weather")
    LiveData<List<DailyWeather>> getDailyWeather();

    @Query("SELECT * FROM monthly_weather")
    LiveData<List<MonthlyWeather>> getMonthlyWeather();

    @Query("SELECT * FROM air_pollution")
    LiveData<List<AirPollution>> getAirPollution();

    @Query("SELECT * FROM user_table")
    LiveData<List<UserData>> getUserData();

    @Query("SELECT COUNT(*) FROM user_table")
    LiveData<List<Integer>> getUserTableCount();

    @Query("SELECT * FROM earthquake")
    LiveData<List<Earthquake>> getEarthquake();

    @Update
    int updateUserData(UserData... userData);

    @Query("DELETE FROM current_weather")
    void deleteAllCurrentWeather();

    @Query("DELETE FROM daily_weather")
    void deleteAllDailyWeather();

    @Query("DELETE FROM hourly_weather")
    void deleteAllHourlyWeather();

    @Query("DELETE FROM monthly_weather")
    void deleteAllMonthlyWeather();

    @Query("DELETE FROM air_pollution")
    void deleteAllAirPollution();

    @Query("DELETE FROM user_table")
    void deleteAllUserData();

    @Query("DELETE FROM earthquake")
    void deleteAllEarthquake();
}
