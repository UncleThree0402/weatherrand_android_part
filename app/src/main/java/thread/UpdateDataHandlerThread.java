package thread;

import SqlServerData.SqlServerRetrieveData;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import viewmodels.CurrentWeatherViewModel;

public class UpdateDataHandlerThread extends HandlerThread {

    public static final int UPDATE_CURRENT_WEATHER_AND_AIR_POLLUTION = 1;
    public static final int UPDATE_HOURLY_WEATHER = 2;
    public static final int UPDATE_DAILY_WEATHER = 3;
    public static final int UPDATE_MONTHLY_WEATHER = 4;

    private ViewModelStoreOwner viewModelStoreOwner;

    private SqlServerRetrieveData mSqlServerRetrieveData;
    private Handler handler;

    public UpdateDataHandlerThread(ViewModelStoreOwner viewModelStoreOwner) {
        super("UpdateDataHandlerThread", Process.THREAD_PRIORITY_DISPLAY);
        this.viewModelStoreOwner = viewModelStoreOwner;
        mSqlServerRetrieveData = new SqlServerRetrieveData(viewModelStoreOwner);
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
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
        };
    }

    public Handler getHandler() {
        return handler;
    }
}
