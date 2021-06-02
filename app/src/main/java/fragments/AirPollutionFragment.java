package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.nckupd2.weatherrand.R;
import dataformatter.NumberFormatter;
import models.AirPollution;
import models.UserData;
import viewmodels.AirPollutionViewModel;
import viewmodels.UserDataViewModel;

import java.util.List;

public class AirPollutionFragment extends Fragment {

    //ui
    private ImageView aqiIcon;
    private TextView co;
    private TextView no;
    private TextView no2;
    private TextView o3;
    private TextView so2;
    private TextView pm2_5;
    private TextView pm10;
    private TextView nh3;

    //var
    private AirPollution mAirPollution;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.air_pollution_fragment, container, false);

        aqiIcon = view.findViewById(R.id.aqi_icon);
        co = view.findViewById(R.id.co_text);
        no = view.findViewById(R.id.no_text);
        no2 = view.findViewById(R.id.no2_text);
        o3 = view.findViewById(R.id.o3_text);
        so2 = view.findViewById(R.id.so2_text);
        pm2_5 = view.findViewById(R.id.pm2_5_text);
        pm10 = view.findViewById(R.id.pm10_text);
        nh3 = view.findViewById(R.id.nh3_text);

        AirPollutionViewModel mAirPollutionViewModel = new ViewModelProvider(this).get(AirPollutionViewModel.class);

        mAirPollutionViewModel.getAirPollution().observe(this.getViewLifecycleOwner(), new Observer<List<AirPollution>>() {
            @Override
            public void onChanged(List<AirPollution> airPollutions) {
                if (airPollutions.size() > 0) {
                    mAirPollution = airPollutions.get(0);
                    if(mAirPollution != null){
                        co.setText(NumberFormatter.correctToSig(mAirPollution.getCo()));
                        no.setText(NumberFormatter.correctToSig(mAirPollution.getNo()));
                        no2.setText(NumberFormatter.correctToSig(mAirPollution.getNoTwo()));
                        o3.setText(NumberFormatter.correctToSig(mAirPollution.getOThree()));
                        so2.setText(NumberFormatter.correctToSig(mAirPollution.getSoTwo()));
                        pm2_5.setText(NumberFormatter.correctToSig(mAirPollution.getPmTwoPFive()));
                        pm10.setText(NumberFormatter.correctToSig(mAirPollution.getPmTen()));
                        nh3.setText(NumberFormatter.correctToSig(mAirPollution.getNhThree()));
                        setAqiIcon(mAirPollution.getAqi());
                    }
                }

            }
        });


        return view;
    }

    public void setAqiIcon(String aqiText) {
        int aqiInt = NumberFormatter.stringToNumber(aqiText);
        switch (aqiInt) {
            case 1:
                aqiIcon.setBackgroundResource(R.drawable.aqi1_icon);
                break;
            case 2:
                aqiIcon.setBackgroundResource(R.drawable.aqi2_icon);
                break;
            case 3:
                aqiIcon.setBackgroundResource(R.drawable.aqi3_icon);
                break;
            case 4:
                aqiIcon.setBackgroundResource(R.drawable.aqi4_icon);
                break;
            case 5:
                aqiIcon.setBackgroundResource(R.drawable.aqi5_icon);
                break;
        }

    }
}
