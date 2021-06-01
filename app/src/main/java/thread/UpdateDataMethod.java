package thread;

import android.os.Message;

public class UpdateDataMethod {

    private UpdateDataHandle updateDataHandler;

    public UpdateDataMethod(UpdateDataHandle updateDataHandler) {
        this.updateDataHandler = updateDataHandler;
    }

    public void updateCurrentWeatherNAirPollution(String currentLocation){
        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = currentLocation;
        updateDataHandler.sendMessage(msg);
    }

    public void updateHourlyWeather(String currentLocation){
        Message msg = Message.obtain();
        msg.what = 2;
        msg.obj = currentLocation;
        updateDataHandler.sendMessage(msg);
    }

    public void updateDailyWeather(String currentLocation){
        Message msg = Message.obtain();
        msg.what = 3;
        msg.obj = currentLocation;
        updateDataHandler.sendMessage(msg);
    }

    public void updateMonthlyWeather(String currentLocation){
        Message msg = Message.obtain();
        msg.what = 4;
        msg.obj = currentLocation;
        updateDataHandler.sendMessage(msg);
    }

    public void updateAll(String currentLocation){
        Message msg = Message.obtain();
        msg.what = 5;
        msg.obj = currentLocation;
        updateDataHandler.sendMessage(msg);
    }


}
