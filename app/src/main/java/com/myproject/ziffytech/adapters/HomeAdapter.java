package com.myproject.ziffytech.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myproject.ziffytech.R;
import com.myproject.ziffytech.models.List;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private ArrayList<List> mArrayList;

    public HomeAdapter(ArrayList<List> arrayListStatus) {
        mArrayList = arrayListStatus;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvDateTime,tvHumidity,tvSpeed,tvDegree;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDateTime =  itemView.findViewById(R.id.item_view_dt);
            tvHumidity =  itemView.findViewById(R.id.item_view_humidity);
            tvSpeed =  itemView.findViewById(R.id.item_view_speed);
            tvDegree =  itemView.findViewById(R.id.item_view_deg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemViewClickListner != null) {
                        mOnItemViewClickListner.onItemClick(
                                getAdapterPosition(),
                                mArrayList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_view, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        List model = mArrayList.get(position);
        myViewHolder.tvDateTime.setText(String.valueOf(model.getDt()));
        myViewHolder.tvHumidity.setText(String.valueOf(model.getHumidity()));
        myViewHolder.tvSpeed.setText(String.valueOf(model.getSpeed()));
        myViewHolder.tvDegree.setText(String.valueOf(model.getDeg()));


    }


    @Override
    public int getItemCount() {
        if (mArrayList != null) {
            return mArrayList.size();
        }
        return 0;
    }

    public interface OnItemViewClickListner {
        void onItemClick(int position ,List item);
    }

    private OnItemViewClickListner mOnItemViewClickListner;

    public void SetOnItemClick(OnItemViewClickListner onItemViewClickListner) {
        mOnItemViewClickListner = onItemViewClickListner;
    }

}
