package thread;

import SqlServerData.SqlServerRetrieveData;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStoreOwner;
import com.nckupd2.weatherrand.MainActivity;

public class TestHandle extends Handler {

    private ViewModelStoreOwner viewModelStoreOwner;
    private SqlServerRetrieveData mSqlServerRetrieveData;

    public TestHandle(@NonNull Looper looper, ViewModelStoreOwner viewModelStoreOwner) {
        super(looper);
        this.viewModelStoreOwner = viewModelStoreOwner;
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
