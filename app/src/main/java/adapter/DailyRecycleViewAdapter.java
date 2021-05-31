package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.nckupd2.weatherrand.R;
import dataformatter.NumberFormatter;
import dataformatter.TimeFormatter;
import models.DailyWeather;

import java.util.ArrayList;

public class DailyRecycleViewAdapter extends RecyclerView.Adapter<DailyRecycleViewAdapter.ViewHolder> {
    private static final String TAG = "DailyRecycleViewAdapter";

    private ArrayList<DailyWeather> mDailyWeatherList = new ArrayList<>();
    private Context mContext;

    public DailyRecycleViewAdapter(ArrayList<DailyWeather> mDailyWeatherList, Context mContext) {
        this.mDailyWeatherList = mDailyWeatherList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.day_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).asBitmap().load(mDailyWeatherList.get(position).getWeatherIconUrl()).into(holder.weather_icon);
        holder.date.setText(TimeFormatter.timeStringToTomorrow(mDailyWeatherList.get(position).getDatetime()));
        holder.weather.setText(mDailyWeatherList.get(position).getWeatherDescription());
        holder.rain.setText(NumberFormatter.percentageFormat(mDailyWeatherList.get(position).getRainPercentage()));
        holder.temp.setText(NumberFormatter.roundNumber(mDailyWeatherList.get(position).getDayTemperature()) + "°");
    }

    @Override
    public int getItemCount() {
        return mDailyWeatherList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, temp, weather, rain;
        ImageView weather_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.seven_day_date_text);
            temp = itemView.findViewById(R.id.seven_day_temp_text);
            weather = itemView.findViewById(R.id.seven_day_weather_text);
            rain = itemView.findViewById(R.id.seven_day_cloud_text);
            weather_icon = itemView.findViewById(R.id.seven_day_weather_icon);
        }
    }
}
