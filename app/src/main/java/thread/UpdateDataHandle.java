package thread;

import SqlServerData.SqlServerRetrieveData;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStoreOwner;

public class UpdateDataHandle extends Handler {

    private SqlServerRetrieveData mSqlServerRetrieveData;

    public UpdateDataHandle(@NonNull Looper looper, ViewModelStoreOwner viewModelStoreOwner) {
        super(looper);
        this.mSqlServerRetrieveData = new SqlServerRetrieveData(viewModelStoreOwner);
    }

    public static final int UPDATE_CURRENT_WEATHER_AND_AIR_POLLUTION = 1;
    public static final int UPDATE_HOURLY_WEATHER = 2;
    public static final int UPDATE_DAILY_WEATHER = 3;
    public static final int UPDATE_MONTHLY_WEATHER = 4;

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case UPDATE_CURRENT_WEATHER_AND_AIR_POLLUTION:
                mSqlServerRetrieveData.insertCurrentWeatherNAirPollution((String) msg.obj);
                break;
            case UPDATE_HOURLY_WEATHER:
                mSqlServerRetrieveData.insertHourlyWeather((String) msg.obj);
                break;
            case  UPDATE_DAILY_WEATHER:
                mSqlServerRetrieveData.insertDailyWeather((String) msg.obj);
                break;
            case  UPDATE_MONTHLY_WEATHER:
                mSqlServerRetrieveData.insertMonthlyWeather((String) msg.obj);
                break;

        }
    }

}
