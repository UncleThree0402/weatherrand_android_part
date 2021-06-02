package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nckupd2.weatherrand.R;

import java.util.Arrays;
import java.util.List;

public class SheetBtnAdapter extends RecyclerView.Adapter<SheetBtnAdapter.ViewHolder> {

    private List<String> locationList = Arrays.asList("Changhua County", "Chiayi City", "Chiayi County", "Hengchun", "Hsinchu County", "Hsinchu City",
            "Hualien Country", "Kaohsiung City", "Keelung City", "Kinmen Islands", "Matsu Islands", "Miaoli County",
            "Nantou County", "New Taipei City", "Penghu County", "Pingtung County", "Taichung City", "Tainan City",
            "Taipei City", "Taitung City","Taoyuan City", "Yilan County", "Yunlin County");
    private locationClickListener mLocationListener;

    public SheetBtnAdapter(locationClickListener locationListener) {
        this.mLocationListener = locationListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sheet_bottom_list_item, parent, false);
        return new ViewHolder(view, mLocationListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.location.setText(locationList.get(position));
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView location;
        locationClickListener mLocationClickListener;

        public ViewHolder(@NonNull View itemView, locationClickListener locationClickListener) {
            super(itemView);
            location = itemView.findViewById(R.id.sheet_btn_location_item);
            this.mLocationClickListener = locationClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mLocationClickListener.locationOnclick(getPosition());
        }
    }

    public interface locationClickListener {
        void locationOnclick(int position);
    }

}
