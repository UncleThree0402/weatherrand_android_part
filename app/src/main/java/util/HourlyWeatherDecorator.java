package util;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HourlyWeatherDecorator extends RecyclerView.ItemDecoration {

    private final int hoz_spacing;

    public HourlyWeatherDecorator(int hoz_spacing) {
        this.hoz_spacing = hoz_spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = hoz_spacing;
        outRect.right = hoz_spacing;
    }
}
