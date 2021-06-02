package services;

import SqlServerData.SqlServerRetrieveData;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import com.nckupd2.weatherrand.MainActivity;
import com.nckupd2.weatherrand.R;
import models.Earthquake;
import persistance.WeatherrandRepositoty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EarthquakeService extends JobService {

    private static final String TAG = "ExampleJobService";
    private final WeatherrandRepositoty mWeatherrandRepositoty = new WeatherrandRepositoty(this);
    private boolean jobCancelled = false;
    private Long earthquakeId;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        mWeatherrandRepositoty.getEarthquake().observeForever(new Observer<List<Earthquake>>() {
            @Override
            public void onChanged(List<Earthquake> earthquakes) {
                if (earthquakes.size() > 0) {
                    earthquakeId = earthquakes.get(0).getEarthquakeId();
                }
                doBackgroundWork(params);
            }
        });
        return true;
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SqlServerRetrieveData sqlServerRetrieveData = new SqlServerRetrieveData();
                ResultSet resultSet = sqlServerRetrieveData.retrieveEarthquake();
                Earthquake earthquake = new Earthquake();
                try {
                    while (resultSet.next()) {
                        earthquake.setEarthquakeId(Long.parseLong(resultSet.getString("earthquakeNo")));
                        earthquake.setReportColour(resultSet.getString("reportColor"));
                        earthquake.setReportContent(resultSet.getString("reportContent"));
                        earthquake.setOriginTime(resultSet.getString("originTime"));
                        earthquake.setDepth(Double.parseDouble(resultSet.getString("depth_value")));
                        earthquake.setMagnitude(Double.parseDouble(resultSet.getString("magnitudeValue")));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (jobCancelled) {
                    return;
                }
                if (earthquakeId == null) {
                    mWeatherrandRepositoty.insertEarthquake(earthquake);
                } else if (earthquake.getEarthquakeId() == earthquakeId) {
                    return;
                } else if (earthquakeId != earthquake.getEarthquakeId()) {
                    NotificationManager mNotificationManager;

                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "1");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                    NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                    bigText.bigText(earthquake.getReportContent());
                    bigText.setBigContentTitle("Earthquake! ");
                    bigText.setSummaryText(earthquake.getReportColour());

                    mBuilder.setContentIntent(pendingIntent);
                    mBuilder.setSmallIcon(R.mipmap.weatherrand_icon_rounded);
                    mBuilder.setContentTitle("Earthquake! " + earthquake.getReportColour());
                    mBuilder.setContentText(earthquake.getReportContent());
                    mBuilder.setPriority(Notification.PRIORITY_MAX);
                    mBuilder.setStyle(bigText);

                    mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        String channelId = "1";
                        NotificationChannel channel = new NotificationChannel(
                                channelId,
                                "Earthquake",
                                NotificationManager.IMPORTANCE_HIGH);
                        mNotificationManager.createNotificationChannel(channel);
                        mBuilder.setChannelId(channelId);
                    }

                    mNotificationManager.notify(0, mBuilder.build());
                    mWeatherrandRepositoty.deleteAllEarthquake();
                    mWeatherrandRepositoty.insertEarthquake(earthquake);
                }
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }

}
