package fragments;

import SqlServerData.SqlServerRetrieveData;
import adapter.SheetBtnAdapter;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nckupd2.weatherrand.R;
import models.UserData;
import thread.UpdateDataHandle;
import thread.UpdateDataHandlerThread;
import thread.UpdateDataMethod;
import util.SheetBtnDecorator;
import viewmodels.UserDataViewModel;

import java.util.List;

public class SheetBtmFragment extends BottomSheetDialogFragment implements SheetBtnAdapter.locationClickListener {
    private static final String TAG = "SheetBtnController";

    //ui
    private RecyclerView mRecycleView;
    private SheetBtnAdapter mSheetBtnAdapter;
    private TextView mLocation;
    //var
    private ViewModelStoreOwner viewModelStoreOwner;
    private LifecycleOwner lifecycleOwner;

    private UpdateDataHandlerThread mUpdateDataHandlerThread;
    private UpdateDataHandle testHandle;

    private UpdateDataMethod mUpdateDataMethod;
    private UserDataViewModel mUserDataViewModel;

    private UserData mUserData;

    public SheetBtmFragment(ViewModelStoreOwner viewModelStoreOwner, LifecycleOwner lifecycleOwner) {
        this.viewModelStoreOwner = viewModelStoreOwner;
        this.lifecycleOwner = lifecycleOwner;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_bottom, container, false);

        mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        SheetBtnDecorator sheetBtnDecorator = new SheetBtnDecorator(16);


        mLocation = getActivity().findViewById(R.id.location_text);
        mRecycleView = view.findViewById(R.id.sheet_btn_recycle_view);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mSheetBtnAdapter = new SheetBtnAdapter(this);
        mRecycleView.addItemDecoration(sheetBtnDecorator);
        mRecycleView.setAdapter(mSheetBtnAdapter);

//        mUpdateDataHandlerThread = new UpdateDataHandlerThread();
//        mUpdateDataHandlerThread.start();
//        testHandle = new UpdateDataHandle(mUpdateDataHandlerThread.getLooper(), viewModelStoreOwner,lifecycleOwner);
//        mUpdateDataMethod = new UpdateDataMethod(testHandle);



        Log.d(TAG, "onCreateView: mLocation location = " + mLocation);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        mUpdateDataHandlerThread.quitSafely();
    }

    @Override
    public void locationOnclick(int position) {
        mUserDataViewModel.getUserData().observe(getViewLifecycleOwner(), new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                mUserData = userData.get(0);
                switch (position) {
                    case 0:
                        mLocation.setText("ChangHua County");
                        mLocation.setTextSize(24);
                        mUserData.setLocation("ChangHuaCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 1:
                        mLocation.setText("ChiaYi City");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("ChiaYiCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 2:
                        mLocation.setText("ChiaYi County");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("ChiaYiCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 3:
                        mLocation.setText("Heng Chun");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("HengChun");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 4:
                        mLocation.setText("HsinChu County");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("HsinChuCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 5:
                        mLocation.setText("HsinChu City");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("HsinChuCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 6:
                        mLocation.setText("Hualien Country");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("HualienCountry");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 7:
                        mLocation.setText("KaohSiung City");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("KaohSiungCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 8:
                        mLocation.setText("KeeLung City");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("KeeLungCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 9:
                        mLocation.setText("KinMen");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("KinMen");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 10:
                        mLocation.setText("LienChiang");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("LienChiang");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 11:
                        mLocation.setText("MiaoLi County");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("MiaoLiCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 12:
                        mLocation.setText("NanTou County");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("NanTouCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 13:
                        mLocation.setText("NewTaipei Cityy");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("NewTaipei Cityy");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 14:
                        mLocation.setText("PengHu");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("PengHu");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 15:
                        mLocation.setText("PingTung County");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("PingTungCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 16:
                        mLocation.setText("TaiChung City");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("TaiChungCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 17:
                        mLocation.setText("Tainan City");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("TainanCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 18:
                        mLocation.setText("Taipei City");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("TaipeiCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 19:
                        mLocation.setText("Taitung County");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("TaiTung ounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 20:
                        mLocation.setText("TaoYuan City");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("TaoYuanCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 21:
                        mLocation.setText("YiLan County");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("YiLanCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 22:
                        mLocation.setText("YunLin County");
                        mLocation.setTextSize(28);
                        mUserData.setLocation("YunLinCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                }
            }
        });

    }
}