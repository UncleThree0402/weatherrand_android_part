package fragments;

import adapter.SheetBtnAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    RecyclerView mRecycleView;
    SheetBtnAdapter mSheetBtnAdapter;

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
        View view = inflater.inflate(R.layout.sheet_bottom,container,false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        SheetBtnDecorator sheetBtnDecorator = new SheetBtnDecorator(16);

        mRecycleView = view.findViewById(R.id.sheet_btn_recycle_view);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mSheetBtnAdapter = new SheetBtnAdapter(this);
        mRecycleView.addItemDecoration(sheetBtnDecorator);
        mRecycleView.setAdapter(mSheetBtnAdapter);

        mUpdateDataHandlerThread = new UpdateDataHandlerThread();
        mUpdateDataHandlerThread.start();
        testHandle = new UpdateDataHandle(mUpdateDataHandlerThread.getLooper(), viewModelStoreOwner);
        mUpdateDataMethod = new UpdateDataMethod(testHandle);


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
        switch (position){
            case 0:
                mUpdateDataMethod.init("ChangHuaCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 1:
                mUpdateDataMethod.init("ChiaYiCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 2:
                mUpdateDataMethod.init("ChiaYiCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 3:
                mUpdateDataMethod.init("HengChun");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 4:
                mUpdateDataMethod.init("HsinChuCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 5:
                mUpdateDataMethod.init("HsinChuCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 6:
                mUpdateDataMethod.init("HualienCountry");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 7:
                mUpdateDataMethod.init("KaohSiungCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 8:
                mUpdateDataMethod.init("KeeLungCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 9:
                mUpdateDataMethod.init("KinMen");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 10:
                mUpdateDataMethod.init("LienChiang");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 11:
                mUpdateDataMethod.init("MiaoLiCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 12:
                mUpdateDataMethod.init("NanTouCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 13:
                mUpdateDataMethod.init("NewTaipeiCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 14:
                mUpdateDataMethod.init("PengHu");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 15:
                mUpdateDataMethod.init("PingTungCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 16:
                mUpdateDataMethod.init("TaiChungCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 17:
                mUpdateDataMethod.init("TaiNanCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 18:
                mUpdateDataMethod.init("TaipeiCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 19:
                mUpdateDataMethod.init("TaoYuanCity");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 20:
                mUpdateDataMethod.init("YiLanCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
            case 21:
                mUpdateDataMethod.init("YunLinCounty");
                mUpdateDataHandlerThread.quitSafely();
                dismiss();
                break;
        }
    }
}