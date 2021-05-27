package fragments;

import adapter.DailyRecycleViewAdapter;
import adapter.HourlyRecycleViewAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nckupd2.weatherrand.R;
import models.DailyWeather;
import models.HourlyWeather;
import util.DailyWeatherDecorator;

import java.util.ArrayList;

public class DailyPageFragment extends Fragment {

    //UI
    private RecyclerView mDailyRecycleView;

    //Var
    private ArrayList<DailyWeather> mDailyWeathers = new ArrayList<>();
    private DailyRecycleViewAdapter mDailyRecycleViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_page_fragment,container,false);

        mDailyRecycleView = view.findViewById(R.id.seven_day_recycle_view);

        return view;
    }

    private void initDailyRecycleView (){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        mDailyRecycleView.setLayoutManager(linearLayoutManager);
        DailyWeatherDecorator dailyWeatherDecorator = new DailyWeatherDecorator(16);
        mDailyRecycleView.addItemDecoration(dailyWeatherDecorator);
        mDailyRecycleViewAdapter = new DailyRecycleViewAdapter(mDailyWeathers,this.getContext());
        mDailyRecycleView.setAdapter(mDailyRecycleViewAdapter);
    }


}
