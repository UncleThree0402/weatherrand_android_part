package util;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SheetBtnDecorator extends RecyclerView.ItemDecoration {

    private final int ver_spacing;

    public SheetBtnDecorator(int ver_spacing) {
        this.ver_spacing = ver_spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = ver_spacing;
        outRect.bottom = ver_spacing;
    }
}
