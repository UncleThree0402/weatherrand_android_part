package persistance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
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
}
