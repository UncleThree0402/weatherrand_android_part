package models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "hourly_weather")
public class HourlyWeather {


    @PrimaryKey
    private int hourly_weather_id;

    @ColumnInfo(name = "datetime")
    private String dateTime;

    @ColumnInfo(name = "temp")
    private String temperature;
    @ColumnInfo(name = "feel_like")
    private String feelLike;

    @ColumnInfo(name = "uvi")
    private String uvi;
    @ColumnInfo(name = "pressure")
    private String pressure;
    @ColumnInfo(name = "clouds")
    private String clouds;
    @ColumnInfo(name = "rain_percentage")
    private String rainPercentage;
    @ColumnInfo(name = "humidity")
    private String humidity;
    @ColumnInfo(name = "visibility")
    private String visibility;
    @ColumnInfo(name = "dew_point")
    private String dewPoint;

    @ColumnInfo(name = "wind_deg")
    private String windDeg;
    @ColumnInfo(name = "wind_deg_text")
    private String windDegText;
    @ColumnInfo(name = "wind_gust")
    private String windGust;
    @ColumnInfo(name = "wind_speed")
    private String windSpeed;

    @ColumnInfo(name = "icon")
    private String weatherIconUrl;
    @ColumnInfo(name = "description")
    private String weatherDescription;
    @ColumnInfo(name = "main")
    private String weatherMain;
    @ColumnInfo(name = "weather_id")
    private String weatherId;

    public HourlyWeather(int hourly_weather_id, String dateTime, String temperature, String feelLike, String uvi, String pressure, String clouds, String rainPercentage, String humidity, String visibility, String dewPoint, String windDeg, String windDegText, String windGust, String windSpeed, String weatherIconUrl, String weatherDescription, String weatherMain, String weatherId) {
        this.hourly_weather_id = hourly_weather_id;
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.feelLike = feelLike;
        this.uvi = uvi;
        this.pressure = pressure;
        this.clouds = clouds;
        this.rainPercentage = rainPercentage;
        this.humidity = humidity;
        this.visibility = visibility;
        this.dewPoint = dewPoint;
        this.windDeg = windDeg;
        this.windDegText = windDegText;
        this.windGust = windGust;
        this.windSpeed = windSpeed;
        this.weatherIconUrl = weatherIconUrl;
        this.weatherDescription = weatherDescription;
        this.weatherMain = weatherMain;
        this.weatherId = weatherId;
    }

    @Ignore
    public HourlyWeather() {
    }

    public int getHourly_weather_id() {
        return hourly_weather_id;
    }

    public void setHourly_weather_id(int hourly_weather_id) {
        this.hourly_weather_id = hourly_weather_id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getFeelLike() {
        return feelLike;
    }

    public void setFeelLike(String feelLike) {
        this.feelLike = feelLike;
    }

    public String getUvi() {
        return uvi;
    }

    public void setUvi(String uvi) {
        this.uvi = uvi;
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

    public String getRainPercentage() {
        return rainPercentage;
    }

    public void setRainPercentage(String rainPercentage) {
        this.rainPercentage = rainPercentage;
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

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
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

    public String getWindGust() {
        return windGust;
    }

    public void setWindGust(String windGust) {
        this.windGust = windGust;
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
