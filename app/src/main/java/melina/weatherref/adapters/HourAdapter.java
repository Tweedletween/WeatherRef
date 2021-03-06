package melina.weatherref.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import melina.weatherref.R;
import melina.weatherref.model.HourData;

/**
 * Created by melina on 4/10/17.
 */

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {
    private HourData[] mHourDatas;
    private Context mContext;

    public HourAdapter(Context context, HourData[] hourDatas) {
        mContext = context;
        mHourDatas = hourDatas;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_list_item, parent, false);
        HourViewHolder hourViewHolder = new HourViewHolder(view);

        return hourViewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        holder.bindHour(mHourDatas[position]);
    }

    @Override
    public int getItemCount() {
        return mHourDatas.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTimeLabel;
        public TextView mSummaryLabel;
        public TextView mTemperatureLabel;
        public ImageView mIconImageView;
        public ImageView mCircleImageView;

        public HourViewHolder(View itemView) {
            super(itemView);

            mTimeLabel = (TextView) itemView.findViewById(R.id.timeLabel);
            mSummaryLabel = (TextView) itemView.findViewById(R.id.summaryLabel);
            mTemperatureLabel = (TextView) itemView.findViewById(R.id.temperatureLabel);
            mIconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
            mCircleImageView = (ImageView) itemView.findViewById(R.id.circleImageView);

            itemView.setOnClickListener(this);
        }

        public void bindHour(HourData hourData) {
            mTimeLabel.setText(hourData.getFormattedTime());
            mSummaryLabel.setText(hourData.getSummary());
            mTemperatureLabel.setText(hourData.getTemperature()+ "");
            mIconImageView.setImageResource(R.drawable.bg_temperature);
            mIconImageView.setImageResource(hourData.getIconId());
        }

        @Override
        public void onClick(View v) {
            String time = mTimeLabel.getText().toString();
            String temperature = mTemperatureLabel.getText().toString();
            String summery = mSummaryLabel.getText().toString();
            String message = String.format("At %s,\nThe temperature will be %s, \nAnd it will be %s",
                    time,
                    temperature,
                    summery);
            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();

        }
    }
}
