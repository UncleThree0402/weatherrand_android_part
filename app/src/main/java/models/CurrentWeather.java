package models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "current_weather")
public class CurrentWeather {

    @PrimaryKey(autoGenerate = true)
    private int current_weather_id;

    @ColumnInfo(name = "datetime")
    private String dateTime;
    @ColumnInfo(name = "sunrise_time")
    private String sunriseTime;
    @ColumnInfo(name = "sunset_time")
    private String sunsetTime;

    @ColumnInfo(name = "current_temp")
    private String currentTemperature;
    @ColumnInfo(name = "feel_like_temp")
    private String feelLikeTemperature;
    @ColumnInfo(name = "max_temp")
    private String maximumTemperature;
    @ColumnInfo(name = "min_temp")
    private String minimumTemperature;

    @ColumnInfo(name = "pressure")
    private String pressure;
    @ColumnInfo(name = "humidity")
    private String humidity;
    @ColumnInfo(name = "visibility")
    private String visibility;
    @ColumnInfo(name = "clouds")
    private String clouds;

    @ColumnInfo(name = "wind_speed")
    private String windSpeed;
    @ColumnInfo(name = "wind_gust")
    private String windGust;
    @ColumnInfo(name = "wind_deg")
    private String windDeg;
    @ColumnInfo(name = "wind_deg_text")
    private String windDegText;

    @ColumnInfo(name = "description")
    private String weatherDescription;
    @ColumnInfo(name = "icon")
    private String weatherIconUrl;
    @ColumnInfo(name = "main")
    private String weatherMain;
    @ColumnInfo(name = "weather_id")
    private String weatherId;

    public CurrentWeather(int current_weather_id, String dateTime, String sunriseTime, String sunsetTime, String currentTemperature, String feelLikeTemperature, String maximumTemperature, String minimumTemperature, String pressure, String humidity, String visibility, String clouds, String windSpeed, String windGust, String windDeg, String windDegText, String weatherDescription, String weatherIconUrl, String weatherMain, String weatherId) {
        this.current_weather_id = current_weather_id;
        this.dateTime = dateTime;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.currentTemperature = currentTemperature;
        this.feelLikeTemperature = feelLikeTemperature;
        this.maximumTemperature = maximumTemperature;
        this.minimumTemperature = minimumTemperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        this.windGust = windGust;
        this.windDeg = windDeg;
        this.windDegText = windDegText;
        this.weatherDescription = weatherDescription;
        this.weatherIconUrl = weatherIconUrl;
        this.weatherMain = weatherMain;
        this.weatherId = weatherId;
    }

    @Ignore
    public CurrentWeather() {
    }

    public int getCurrent_weather_id() {
        return current_weather_id;
    }

    public void setCurrent_weather_id(int current_weather_id) {
        this.current_weather_id = current_weather_id;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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

    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public String getFeelLikeTemperature() {
        return feelLikeTemperature;
    }

    public void setFeelLikeTemperature(String feelLikeTemperature) {
        this.feelLikeTemperature = feelLikeTemperature;
    }

    public String getMaximumTemperature() {
        return maximumTemperature;
    }

    public void setMaximumTemperature(String maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    public String getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(String minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindGust() {
        return windGust;
    }

    public void setWindGust(String windGust) {
        this.windGust = windGust;
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

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(String weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }
}
