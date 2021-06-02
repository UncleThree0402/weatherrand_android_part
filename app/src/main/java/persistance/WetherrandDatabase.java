package persistance;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import models.*;

@Database(entities = {CurrentWeather.class, DailyWeather.class, HourlyWeather.class, MonthlyWeather.class, AirPollution.class, UserData.class, Earthquake.class}, version = 1)
public abstract class WetherrandDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "weatherrandVer2_db";

    private static WetherrandDatabase instance;

    static WetherrandDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),WetherrandDatabase.class,DATABASE_NAME).build();
        }
        return instance;
    }

    public abstract WeatherrandDao getWeatherrandDao();
}
