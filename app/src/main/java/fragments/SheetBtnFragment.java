package fragments;

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
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nckupd2.weatherrand.R;
import thread.UpdateDataHandle;
import thread.UpdateDataHandlerThread;
import thread.UpdateDataMethod;
import util.SheetBtnDecorator;

public class SheetBtnFragment extends BottomSheetDialogFragment implements SheetBtnAdapter.locationClickListener {
    private static final String TAG = "SheetBtnController";

    //ui
    private RecyclerView mRecycleView;
    private SheetBtnAdapter mSheetBtnAdapter;
    private TextView mLocation;
    //var
    private ViewModelStoreOwner viewModelStoreOwner;

    private UpdateDataHandlerThread mUpdateDataHandlerThread;
    private UpdateDataHandle testHandle;

    private UpdateDataMethod mUpdateDataMethod;

    public SheetBtnFragment(ViewModelStoreOwner viewModelStoreOwner) {
        this.viewModelStoreOwner = viewModelStoreOwner;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_bottom, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        SheetBtnDecorator sheetBtnDecorator = new SheetBtnDecorator(16);


        mLocation = getActivity().findViewById(R.id.location_text);
        mRecycleView = view.findViewById(R.id.sheet_btn_recycle_view);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mSheetBtnAdapter = new SheetBtnAdapter(this);
        mRecycleView.addItemDecoration(sheetBtnDecorator);
        mRecycleView.setAdapter(mSheetBtnAdapter);

        mUpdateDataHandlerThread = new UpdateDataHandlerThread();
        mUpdateDataHandlerThread.start();
        testHandle = new UpdateDataHandle(mUpdateDataHandlerThread.getLooper(), viewModelStoreOwner);
        mUpdateDataMethod = new UpdateDataMethod(testHandle);

        Log.d(TAG, "onCreateView: mLocation location = " + mLocation);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUpdateDataHandlerThread.quitSafely();
    }

    @Override
    public void locationOnclick(int position) {
        Log.d(TAG, "locationOnclick: position : " + position);
        switch (position) {
            case 0:
                mLocation.setText("ChangHua County");
                mUpdateDataMethod.init("ChangHuaCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 1:
                mLocation.setText("ChiaYi City");
                mUpdateDataMethod.init("ChiaYiCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 2:
                mLocation.setText("ChiaYi County");
                mUpdateDataMethod.init("ChiaYiCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 3:
                mLocation.setText("Heng Chun");
                mUpdateDataMethod.init("HengChun");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 4:
                mLocation.setText("HsinChu County");
                mUpdateDataMethod.init("HsinChuCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 5:
                mLocation.setText("HsinChu City");
                mUpdateDataMethod.init("HsinChuCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 6:
                mLocation.setText("Hualien Country");
                mUpdateDataMethod.init("HualienCountry");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 7:
                mLocation.setText("KaohSiung City");
                mUpdateDataMethod.init("KaohSiungCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 8:
                mLocation.setText("KeeLung City");
                mUpdateDataMethod.init("KeeLungCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 9:
                mLocation.setText("KinMen");
                mUpdateDataMethod.init("KinMen");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 10:
                mLocation.setText("LienChiang");
                mUpdateDataMethod.init("LienChiang");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 11:
                mLocation.setText("MiaoLi County");
                mUpdateDataMethod.init("MiaoLiCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 12:
                mLocation.setText("NanTou County");
                mUpdateDataMethod.init("NanTouCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 13:
                mLocation.setText("NewTaipei Cityy");
                mUpdateDataMethod.init("NewTaipeiCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 14:
                mLocation.setText("PengHu");
                mUpdateDataMethod.init("PengHu");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 15:
                mLocation.setText("CPingTung County");
                mUpdateDataMethod.init("PingTungCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 16:
                mLocation.setText("TaiChung City");
                mUpdateDataMethod.init("TaiChungCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 17:
                mLocation.setText("TaiNan City");
                mUpdateDataMethod.init("TaiNanCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 18:
                mLocation.setText("Taipei City");
                mUpdateDataMethod.init("TaipeiCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 19:
                mLocation.setText("TaoYuan City");
                mUpdateDataMethod.init("TaoYuanCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 20:
                mLocation.setText("YiLan County");
                mUpdateDataMethod.init("YiLanCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 21:
                mLocation.setText("YunLin County");
                mUpdateDataMethod.init("YunLinCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
        }
    }
}