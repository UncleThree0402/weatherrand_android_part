package fragments;

import android.content.Context;
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
    private String aqiText;
    private String coText;
    private String noText;
    private String no2Text;
    private String o3Text;
    private String so2Text;
    private String pm2_5Text;
    private String pm10Text;
    private String nh3Text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.air_pollution_fragment,container,false);

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
        UserDataViewModel mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);

        mAirPollutionViewModel.getAirPollution().observe(this.getViewLifecycleOwner(), new Observer<List<AirPollution>>() {
            @Override
            public void onChanged(List<AirPollution> airPollutions) {
                if (airPollutions.size() > 0) {
                    aqiText = airPollutions.get(0).getAqi();
                    coText = airPollutions.get(0).getCo();
                    noText = airPollutions.get(0).getNo();
                    no2Text = airPollutions.get(0).getNoTwo();
                    o3Text = airPollutions.get(0).getOThree();
                    so2Text = airPollutions.get(0).getSoTwo();
                    pm2_5Text = airPollutions.get(0).getPmTwoPFive();
                    pm10Text = airPollutions.get(0).getPmTen();
                    nh3Text = airPollutions.get(0).getNhThree();
                }
            }
        });


        mUserDataViewModel.getUserData().observe(this.getViewLifecycleOwner(), new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                if(!userData.get(0).isUpdateStatus()) {
                    if (coText != null) {
                        co.setText(NumberFormatter.correctToSig(coText));
                        no.setText(NumberFormatter.correctToSig(noText));
                        no2.setText(NumberFormatter.correctToSig(no2Text));
                        o3.setText(NumberFormatter.correctToSig(o3Text));
                        so2.setText(NumberFormatter.correctToSig(so2Text));
                        pm2_5.setText(NumberFormatter.correctToSig(pm2_5Text));
                        pm10.setText(NumberFormatter.correctToSig(pm10Text));
                        nh3.setText(NumberFormatter.correctToSig(nh3Text));
                        setAqiIcon(aqiText);
                    }
                }
            }
        });
        return view;
    }

    public void setAqiIcon(String aqiText) {
        int aqiInt = NumberFormatter.stringToNumber(aqiText);
        switch (aqiInt){
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
