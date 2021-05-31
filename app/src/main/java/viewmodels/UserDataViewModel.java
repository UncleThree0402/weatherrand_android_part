package viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import models.UserData;
import persistance.WeatherrandRepositoty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserDataViewModel extends AndroidViewModel {
    private WeatherrandRepositoty mWeatherrandRepositoty;
    private LiveData<List<UserData>> mUserData;
    public UserDataViewModel(@NonNull Application application) {
        super(application);
        mWeatherrandRepositoty = new WeatherrandRepositoty(application);
        mUserData = mWeatherrandRepositoty.getUserData();
    }

    public void insertUserData(UserData userData){
        mWeatherrandRepositoty.insertUserData(userData);
    }

    public LiveData<List<UserData>> getUserData(){
        return mUserData;
    }

    public LiveData<List<Integer>> getUserTableCount(){
        return mWeatherrandRepositoty.getUserTableCount();
    }

    public void updateUserData(UserData userData){
        mWeatherrandRepositoty.updateUserData(userData);
    }

    public void deleteAllUserData(){
        mWeatherrandRepositoty.deleteAllUserData();
    }

}
