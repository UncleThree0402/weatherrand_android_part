package thread;

import android.os.Message;

public class UpdateDataMethod {

    private UpdateDataHandlerThread updateDataHandlerThread;


    public UpdateDataMethod(UpdateDataHandlerThread updateDataHandlerThread) {
        this.updateDataHandlerThread = updateDataHandlerThread;
    }

//    public void init(String location){
//        updateCurrentWeatherNAirPollution(location);
//        updateHourlyWeather(location);
//        updateDailyWeather(location);
//        updateMonthlyWeather(location);
//    }

//    public void updateCurrentWeatherNAirPollution(String currentLocation){
//        Message msg = Message.obtain();
//        msg.what = 1;
//        msg.obj = currentLocation;
//        updateDataHandlerThread.getHandler().sendMessage(msg);
//    }
//
//    public void updateHourlyWeather(String currentLocation){
//        Message msg = Message.obtain();
//        msg.what = 2;
//        msg.obj = currentLocation;
//        updateDataHandlerThread.getHandler().sendMessage(msg);
//    }
//
//    public void updateDailyWeather(String currentLocation){
//        Message msg = Message.obtain();
//        msg.what = 3;
//        msg.obj = currentLocation;
//        updateDataHandlerThread.getHandler().sendMessage(msg);
//    }
//
//    public void updateMonthlyWeather(String currentLocation){
//        Message msg = Message.obtain();
//        msg.what = 4;
//        msg.obj = currentLocation;
//        updateDataHandlerThread.getHandler().sendMessage(msg);
//    }


}
