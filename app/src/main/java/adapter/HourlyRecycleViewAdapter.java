package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.nckupd2.weatherrand.R;
import models.HourlyWeather;

import java.util.ArrayList;

public class HourlyRecycleViewAdapter extends RecyclerView.Adapter<HourlyRecycleViewAdapter.ViewHolder> {

    private ArrayList<HourlyWeather> mHourlyWeatherList = new ArrayList<>();
    private Context mContext;

    public HourlyRecycleViewAdapter(ArrayList<HourlyWeather> mHourlyWeatherList, Context context) {
        this.mHourlyWeatherList = mHourlyWeatherList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hourly_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).asBitmap().load(mHourlyWeatherList.get(position).getWeatherIconUrl()).into(holder.weather_icon);
        holder.time.setText(mHourlyWeatherList.get(position).getDateTime());
        holder.temp.setText(mHourlyWeatherList.get(position).getTemperature());
        holder.rain.setText(mHourlyWeatherList.get(position).getRainPercentage());
    }

    @Override
    public int getItemCount() {
        return 24;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView time,temp,rain;
        ImageView weather_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.hourly_time_text);
            temp = itemView.findViewById(R.id.hourly_temp_text);
            rain = itemView.findViewById(R.id.hourly_rain_text);
            weather_icon = itemView.findViewById(R.id.hourly_weather_icon);
        }
    }
}
