package fragments;

import adapter.SheetBtnAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nckupd2.weatherrand.R;
import models.UserData;
import util.SheetBtnDecorator;
import viewmodels.UserDataViewModel;

import java.util.List;

public class SheetBtmFragment extends BottomSheetDialogFragment implements SheetBtnAdapter.locationClickListener {
    private static final String TAG = "SheetBtnController";
    //var
    private final ViewModelStoreOwner viewModelStoreOwner;
    private final LifecycleOwner lifecycleOwner;
    //ui
    private RecyclerView mRecycleView;
    private SheetBtnAdapter mSheetBtnAdapter;
    private TextView mLocation;

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        SheetBtnDecorator sheetBtnDecorator = new SheetBtnDecorator(16);

        mUserDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);

        mLocation = getActivity().findViewById(R.id.location_text);
        mRecycleView = view.findViewById(R.id.sheet_btn_recycle_view);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mSheetBtnAdapter = new SheetBtnAdapter(this);
        mRecycleView.addItemDecoration(sheetBtnDecorator);
        mRecycleView.setAdapter(mSheetBtnAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void locationOnclick(int position) {
        mUserDataViewModel.getUserData().observe(getViewLifecycleOwner(), new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                mUserData = userData.get(0);
                switch (position) {
                    case 0:
                        mUserData.setLocation("ChangHuaCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 1:
                        mUserData.setLocation("ChiaYiCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 2:
                        mUserData.setLocation("ChiaYiCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 3:
                        mUserData.setLocation("HengChun");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 4:
                        mUserData.setLocation("HsinChuCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 5:
                        mUserData.setLocation("HsinChuCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 6:
                        mUserData.setLocation("HuaLienCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 7:
                        mUserData.setLocation("KaohSiungCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 8:
                        mUserData.setLocation("KeeLungCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 9:
                        mUserData.setLocation("KinMen");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 10:
                        mUserData.setLocation("LienChiang");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 11:
                        mUserData.setLocation("MiaoLiCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 12:
                        mUserData.setLocation("NanTouCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 13:
                        mUserData.setLocation("NewTaipeiCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 14:
                        mUserData.setLocation("PengHu");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 15:
                        mUserData.setLocation("PingTungCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 16:
                        mUserData.setLocation("TaiChungCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 17:
                        mUserData.setLocation("TainanCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 18:
                        mUserData.setLocation("TaipeiCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 19:
                        mUserData.setLocation("TaiTungCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 20:
                        mUserData.setLocation("TaoYuanCity");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 21:
                        mUserData.setLocation("YiLanCounty");
                        mUserData.setUpdateStatus(true);
                        mUserDataViewModel.updateUserData(mUserData);
                        dismiss();
                        break;
                    case 22:
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