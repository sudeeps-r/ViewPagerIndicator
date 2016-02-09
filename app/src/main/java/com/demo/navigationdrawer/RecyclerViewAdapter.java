package com.demo.navigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by aditya.amartya on 9/11/2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public OnItemClickListener mItemClickListener;

    private LayoutInflater inflater;

    private static final String options[] = new String[]{"Search", "Pins", "Tasks", "Notifications", "New Applicants", "Logs", "Settings",};
    private static final String counts[] = new String[]{"", "3", "12", "", "6", "", ""};

    public RecyclerViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public interface OnItemClickListener {
        public void onItemClick(String option);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.item_recycle_view, null);
        DataHolder dataHolder = new DataHolder(view);
        return dataHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        DataHolder dataHolder = (DataHolder) viewHolder;
        TextView option = dataHolder.tvAction;
        TextView count = dataHolder.tvCount;
        option.setText(options[position]);
        count.setText(counts[position]);
    }

    @Override
    public int getItemCount() {
        return options.length;
    }

    public class DataHolder extends RecyclerView.ViewHolder {
        TextView tvAction, tvCount;

        public DataHolder(View itemView) {
            super(itemView);
            tvAction = (TextView) itemView.findViewById(R.id.tv_action);
            tvCount = (TextView) itemView.findViewById(R.id.tv_count);
            itemView.setOnClickListener(new ClickListener());
        }

        public class ClickListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(tvAction.getText().toString());
                }
            }
        }
    }

    public void setOnTupleClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}
