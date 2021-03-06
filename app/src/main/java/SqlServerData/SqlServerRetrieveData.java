package SqlServerData;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import models.*;
import viewmodels.*;

import java.sql.ResultSet;
import java.sql.SQLException;


@SuppressWarnings("SqlResolve")
public class SqlServerRetrieveData {
    private static final String TAG = "SqlServerRetrieveData";
    private final boolean initStatus = true;
    private ViewModelStoreOwner mViewModelStoreOwner;
    private LifecycleOwner mLifecycleOwner;
    private UserDataViewModel mUserDataViewModel;
    private CurrentWeatherViewModel mCurrentWeatherViewModel;
    private AirPollutionViewModel mAirPollutionViewModel;
    private HourlyWeatherViewModel mHourlyWeatherViewModel;
    private DailyWeatherViewModel mDailyWeatherViewModel;
    private MonthlyWeatherViewModel mMonthlyWeatherViewModel;


    public SqlServerRetrieveData(ViewModelStoreOwner viewModelStoreOwner, LifecycleOwner lifecycleOwner) {
        this.mViewModelStoreOwner = viewModelStoreOwner;
        this.mLifecycleOwner = lifecycleOwner;
    }

    public SqlServerRetrieveData() {
    }


    public void insertCurrentWeatherNAirPollution(String location) {
        mCurrentWeatherViewModel = new ViewModelProvider(mViewModelStoreOwner).get(CurrentWeatherViewModel.class);
        mAirPollutionViewModel = new ViewModelProvider(mViewModelStoreOwner).get(AirPollutionViewModel.class);
        try {
            ResultSet resultSet = SqlServerConnection.getStatement().executeQuery("Select * from " + location + "CurrentlyWeather; ");
            mAirPollutionViewModel.deleteAllAirPollution();
            mCurrentWeatherViewModel.deleteAllCurrentWeather();
            while (resultSet.next()) {
                CurrentWeather currentWeather = new CurrentWeather();
                AirPollution airPollution = new AirPollution();
                currentWeather.setDateTime(resultSet.getString("dt"));
                currentWeather.setSunriseTime(resultSet.getString("sunrise"));
                currentWeather.setSunsetTime(resultSet.getString("sunset"));

                currentWeather.setCurrentTemperature(resultSet.getString("temp"));
                currentWeather.setFeelLikeTemperature(resultSet.getString("feels_like"));
                currentWeather.setMaximumTemperature(resultSet.getString("temp_max"));
                currentWeather.setMinimumTemperature(resultSet.getString("temp_min"));

                currentWeather.setPressure(resultSet.getString("pressure"));
                currentWeather.setHumidity(resultSet.getString("humidity"));
                currentWeather.setVisibility(resultSet.getString("visibility"));
                currentWeather.setClouds(resultSet.getString("clouds"));

                currentWeather.setWindSpeed(resultSet.getString("wind_speed"));
                currentWeather.setWindGust(resultSet.getString("wind_gust"));
                currentWeather.setWindDeg(resultSet.getString("wind_deg"));
                currentWeather.setWindDegText(resultSet.getString("wind_deg_text"));

                currentWeather.setWeatherDescription(resultSet.getString("weather_description"));
                currentWeather.setWeatherIconUrl(resultSet.getString("weather_icon"));
                currentWeather.setWeatherMain(resultSet.getString("weather_main"));
                currentWeather.setWeatherId(resultSet.getString("weather_id"));

                airPollution.setAqi(Integer.toString((int) Double.parseDouble(resultSet.getString("aqi"))));
                airPollution.setCo(resultSet.getString("ap_co"));
                airPollution.setNo(resultSet.getString("ap_no"));
                airPollution.setNoTwo(resultSet.getString("ap_no2"));
                airPollution.setOThree(resultSet.getString("ap_o3"));
                airPollution.setSoTwo(resultSet.getString("ap_so2"));
                airPollution.setPmTwoPFive(resultSet.getString("ap_pm2_5"));
                airPollution.setPmTen(resultSet.getString("ap_pm10"));
                airPollution.setNhThree(resultSet.getString("ap_nh3"));

                mCurrentWeatherViewModel.insetCurrentWeather(currentWeather);
                mAirPollutionViewModel.insetAirPollution(airPollution);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void insertHourlyWeather(String location) {
        mHourlyWeatherViewModel = new ViewModelProvider(mViewModelStoreOwner).get(HourlyWeatherViewModel.class);
        try {
            mHourlyWeatherViewModel.deleteAllHourlyWeather();
            ResultSet resultSet = SqlServerConnection.getStatement().executeQuery("Select * from " + location + "HourlyWeatherForecast; ");
            while (resultSet.next()) {
                HourlyWeather hourlyWeather = new HourlyWeather();
                hourlyWeather.setDateTime(resultSet.getString("dt"));

                hourlyWeather.setTemperature(resultSet.getString("temp"));
                hourlyWeather.setFeelLike(resultSet.getString("feels_like"));

                hourlyWeather.setUvi(resultSet.getString("uvi"));
                hourlyWeather.setPressure(resultSet.getString("pressure"));
                hourlyWeather.setClouds(resultSet.getString("clouds"));
                hourlyWeather.setRainPercentage(resultSet.getString("pop"));
                hourlyWeather.setHumidity(resultSet.getString("humidity"));
                hourlyWeather.setVisibility(resultSet.getString("visibility"));
                hourlyWeather.setDewPoint(resultSet.getString("dew_point"));

                hourlyWeather.setWindDeg(resultSet.getString("wind_deg"));
                hourlyWeather.setWindDegText(resultSet.getString("wind_deg_text"));
                hourlyWeather.setWindGust(resultSet.getString("wind_gust"));
                hourlyWeather.setWindSpeed(resultSet.getString("wind_speed"));

                hourlyWeather.setWeatherIconUrl(resultSet.getString("weather_icon"));
                hourlyWeather.setWeatherDescription(resultSet.getString("weather_description"));
                hourlyWeather.setWeatherId(resultSet.getString("weather_main"));
                hourlyWeather.setWeatherId(resultSet.getString("weather_id"));

                mHourlyWeatherViewModel.insetHourlyWeather(hourlyWeather);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDailyWeather(String location) {
        mDailyWeatherViewModel = new ViewModelProvider(mViewModelStoreOwner).get(DailyWeatherViewModel.class);
        try {
            mDailyWeatherViewModel.deleteAllDailyWeather();
            ResultSet resultSet = SqlServerConnection.getStatement().executeQuery("Select * from " + location + "DailyWeatherForecast; ");
            while (resultSet.next()) {
                DailyWeather dailyWeather = new DailyWeather();
                dailyWeather.setDatetime(resultSet.getString("dt"));
                dailyWeather.setSunriseTime(resultSet.getString("sunrise"));
                dailyWeather.setSunsetTime(resultSet.getString("sunset"));

                dailyWeather.setMoonPhase(resultSet.getString("moon_phase"));
                dailyWeather.setMinimumTemperature(resultSet.getString("temp_min"));
                dailyWeather.setMaximumTemperature(resultSet.getString("temp_max"));

                dailyWeather.setMonTemperature(resultSet.getString("temp_morn"));
                dailyWeather.setDayTemperature(resultSet.getString("temp_day"));
                dailyWeather.setEveTemperature(resultSet.getString("temp_eve"));
                dailyWeather.setNightTemperature(resultSet.getString("temp_night"));
                dailyWeather.setMonFeelLike(resultSet.getString("feels_like_morn"));
                dailyWeather.setDayFeelLike(resultSet.getString("feels_like_day"));
                dailyWeather.setEveFeelLike(resultSet.getString("feels_like_eve"));
                dailyWeather.setNightFeelLike(resultSet.getString("feels_like_night"));

                dailyWeather.setUvi(resultSet.getString("uvi"));
                dailyWeather.setPressure(resultSet.getString("pressure"));
                dailyWeather.setClouds(resultSet.getString("clouds"));
                dailyWeather.setRainPercentage(resultSet.getString("pop"));
                dailyWeather.setHumidity(resultSet.getString("humidity"));
                dailyWeather.setDewPoint(resultSet.getString("dew_point"));

                dailyWeather.setWindDeg(resultSet.getString("wind_deg"));
                dailyWeather.setWindDegText(resultSet.getString("wind_deg_text"));
                dailyWeather.setWindGust(resultSet.getString("wind_gust"));
                dailyWeather.setWindSpeed(resultSet.getString("wind_speed"));

                dailyWeather.setWeatherIconUrl(resultSet.getString("weather_icon"));
                dailyWeather.setWeatherDescription(resultSet.getString("weather_description"));
                dailyWeather.setWeatherMain(resultSet.getString("weather_main"));
                dailyWeather.setWeatherId(resultSet.getString("weather_id"));

                mDailyWeatherViewModel.insetDailyWeather(dailyWeather);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void insertMonthlyWeather(String location) {
        mMonthlyWeatherViewModel = new ViewModelProvider(mViewModelStoreOwner).get(MonthlyWeatherViewModel.class);
        try {
            mMonthlyWeatherViewModel.deleteAllMonthlyWeather();
            ResultSet resultSet = SqlServerConnection.getStatement().executeQuery("Select * from " + location + "30DaysWeatherForecast; ");
            while (resultSet.next()) {
                MonthlyWeather monthlyWeather = new MonthlyWeather();
                monthlyWeather.setDatetime(resultSet.getString("dt"));
                monthlyWeather.setSunriseTime(resultSet.getString("sunrise"));
                monthlyWeather.setSunsetTime(resultSet.getString("sunset"));

                monthlyWeather.setMinimumTemperature(resultSet.getString("temp_min"));
                monthlyWeather.setMaximumTemperature(resultSet.getString("temp_max"));

                monthlyWeather.setMonTemperature(resultSet.getString("temp_morn"));
                monthlyWeather.setDayTemperature(resultSet.getString("temp_day"));
                monthlyWeather.setEveTemperature(resultSet.getString("temp_eve"));
                monthlyWeather.setNightTemperature(resultSet.getString("temp_night"));
                monthlyWeather.setMonFeelLike(resultSet.getString("feels_like_morn"));
                monthlyWeather.setDayFeelLike(resultSet.getString("feels_like_day"));
                monthlyWeather.setEveFeelLike(resultSet.getString("feels_like_eve"));
                monthlyWeather.setNightFeelLike(resultSet.getString("feels_like_night"));

                monthlyWeather.setPressure(resultSet.getString("pressure"));
                monthlyWeather.setClouds(resultSet.getString("clouds"));
                monthlyWeather.setHumidity(resultSet.getString("humidity"));
                monthlyWeather.setRain(resultSet.getString("rain"));

                monthlyWeather.setWindDeg(resultSet.getString("wind_deg"));
                monthlyWeather.setWindDegText(resultSet.getString("wind_deg_text"));
                monthlyWeather.setWindSpeed(resultSet.getString("wind_speed"));

                monthlyWeather.setWeatherIconUrl(resultSet.getString("weather_icon"));
                monthlyWeather.setWeatherDescription(resultSet.getString("weather_description"));
                monthlyWeather.setWeatherMain(resultSet.getString("weather_main"));
                monthlyWeather.setWeatherId(resultSet.getString("weather_id"));

                mMonthlyWeatherViewModel.insetMonthlyWeather(monthlyWeather);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet retrieveEarthquake() {
        SqlServerConnection.connect();
        if (SqlServerConnection.getConnection() != null) {
            try {
                ResultSet resultSet = SqlServerConnection.getStatement().executeQuery("Select * from earthquake ");
                return resultSet;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

}
