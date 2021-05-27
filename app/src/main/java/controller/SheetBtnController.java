package controller;

import adapter.SheetBtnAdapter;
import android.content.Context;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nckupd2.weatherrand.R;

public class SheetBtnController extends BottomSheetDialogFragment implements SheetBtnAdapter.locationClickListener {
    private static final String TAG = "SheetBtnController";

    RecyclerView mRecycleView;
    SheetBtnAdapter mSheetBtnAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_button,container,false);

        mRecycleView = view.findViewById(R.id.sheet_btn_recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        mRecycleView.setLayoutManager(linearLayoutManager);
        mSheetBtnAdapter = new SheetBtnAdapter(this);
        mRecycleView.setAdapter(mSheetBtnAdapter);
        return view;
    }


    @Override
    public void locationOnclick(int position) {
        Log.d(TAG, "locationOnclick: position : " + position);
    }
}