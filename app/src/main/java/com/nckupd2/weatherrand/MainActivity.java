package com.nckupd2.weatherrand;

import android.os.Message;
import thread.UpdateDataHandlerThread;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import thread.UpdateDataMethod;

public class MainActivity extends AppCompatActivity {

    private String currentLocation;

    private UpdateDataHandlerThread mUpdateDataHandlerThread = new UpdateDataHandlerThread(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUpdateDataHandlerThread.start();
        UpdateDataMethod updateDataMethod = new UpdateDataMethod(mUpdateDataHandlerThread);
        updateDataMethod.init(currentLocation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUpdateDataHandlerThread.quitSafely();
    }




}