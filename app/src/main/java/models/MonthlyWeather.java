package models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "monthly_weather")
public class MonthlyWeather {
    @ColumnInfo(name = "datetime")
    private String datetime;
    @ColumnInfo(name = "sunrise_time")
    private String sunriseTime;
    @ColumnInfo(name = "sunset_time")
    private String sunsetTime;

    @ColumnInfo(name = "min_temp")
    private String minimumTemperature;
    @ColumnInfo(name = "max_temp")
    private String maximumTemperature;
    @ColumnInfo(name = "mon_temp")
    private String monTemperature;
    @ColumnInfo(name = "day_temp")
    private String dayTemperature;
    @ColumnInfo(name = "eve_temp")
    private String eveTemperature;
    @ColumnInfo(name = "night_temp")
    private String nightTemperature;
    @ColumnInfo(name = "mon_feel_like")
    private String monFeelLike;
    @ColumnInfo(name = "day_feel_like")
    private String dayFeelLike;
    @ColumnInfo(name = "eve_feel_like")
    private String eveFeelLike;
    @ColumnInfo(name = "night_feel_like")
    private String nightFeelLike;

    @ColumnInfo(name = "pressure")
    private String pressure;
    @ColumnInfo(name = "clouds")
    private String clouds;
    @ColumnInfo(name = "humidity")
    private String humidity;
    @ColumnInfo(name = "rain")
    private String rain;

    @ColumnInfo(name = "wind_deg")
    private String windDeg;
    @ColumnInfo(name = "wind_deg_text")
    private String windDegText;
    @ColumnInfo(name = "wind_speed")
    private String windSpeed;

    @ColumnInfo(name = "weather_icon")
    private String weatherIconUrl;
    @ColumnInfo(name = "weather_description")
    private String weatherDescription;
    @ColumnInfo(name = "weather_main")
    private String weatherMain;
    @ColumnInfo(name = "weather_id")
    private String weatherId;

    public MonthlyWeather(String datetime, String sunriseTime, String sunsetTime, String minimumTemperature, String maximumTemperature, String monTemperature, String dayTemperature, String eveTemperature, String nightTemperature, String monFeelLike, String dayFeelLike, String eveFeelLike, String nightFeelLike, String pressure, String clouds, String humidity, String rain, String windDeg, String windDegText, String windSpeed, String weatherIconUrl, String weatherDescription, String weatherMain, String weatherId) {
        this.datetime = datetime;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.monTemperature = monTemperature;
        this.dayTemperature = dayTemperature;
        this.eveTemperature = eveTemperature;
        this.nightTemperature = nightTemperature;
        this.monFeelLike = monFeelLike;
        this.dayFeelLike = dayFeelLike;
        this.eveFeelLike = eveFeelLike;
        this.nightFeelLike = nightFeelLike;
        this.pressure = pressure;
        this.clouds = clouds;
        this.humidity = humidity;
        this.rain = rain;
        this.windDeg = windDeg;
        this.windDegText = windDegText;
        this.windSpeed = windSpeed;
        this.weatherIconUrl = weatherIconUrl;
        this.weatherDescription = weatherDescription;
        this.weatherMain = weatherMain;
        this.weatherId = weatherId;
    }

    @Ignore
    public MonthlyWeather() {
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public String getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(String minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public String getMaximumTemperature() {
        return maximumTemperature;
    }

    public void setMaximumTemperature(String maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    public String getMonTemperature() {
        return monTemperature;
    }

    public void setMonTemperature(String monTemperature) {
        this.monTemperature = monTemperature;
    }

    public String getDayTemperature() {
        return dayTemperature;
    }

    public void setDayTemperature(String dayTemperature) {
        this.dayTemperature = dayTemperature;
    }

    public String getEveTemperature() {
        return eveTemperature;
    }

    public void setEveTemperature(String eveTemperature) {
        this.eveTemperature = eveTemperature;
    }

    public String getNightTemperature() {
        return nightTemperature;
    }

    public void setNightTemperature(String nightTemperature) {
        this.nightTemperature = nightTemperature;
    }

    public String getMonFeelLike() {
        return monFeelLike;
    }

    public void setMonFeelLike(String monFeelLike) {
        this.monFeelLike = monFeelLike;
    }

    public String getDayFeelLike() {
        return dayFeelLike;
    }

    public void setDayFeelLike(String dayFeelLike) {
        this.dayFeelLike = dayFeelLike;
    }

    public String getEveFeelLike() {
        return eveFeelLike;
    }

    public void setEveFeelLike(String eveFeelLike) {
        this.eveFeelLike = eveFeelLike;
    }

    public String getNightFeelLike() {
        return nightFeelLike;
    }

    public void setNightFeelLike(String nightFeelLike) {
        this.nightFeelLike = nightFeelLike;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(String windDeg) {
        this.windDeg = windDeg;
    }

    public String getWindDegText() {
        return windDegText;
    }

    public void setWindDegText(String windDegText) {
        this.windDegText = windDegText;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(String weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }
}
