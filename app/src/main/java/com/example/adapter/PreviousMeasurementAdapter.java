package com.example.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.R;
import com.example.activity.MeasurementDetailsActivity;
import com.example.model.CustomerPreviousMeasurementModel;
import com.example.util.CommonFunctions;
import com.example.util.Constants;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class PreviousMeasurementAdapter extends RecyclerView.Adapter<PreviousMeasurementAdapter
        .Holder> {

    private Context context;
    private List<CustomerPreviousMeasurementModel> list;


    public PreviousMeasurementAdapter(Context context, List<CustomerPreviousMeasurementModel>
            listPrevious) {
        this.context = context;
        this.list = listPrevious;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View previousList = layoutInflater.inflate(R.layout.adapter_previous_list, parent,
                false);
        return new Holder(previousList);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PreviousMeasurementAdapter.Holder holder, int position) {
        if (!list.get(position).getAge().isEmpty()) {
            holder.age.setText(list.get(position).getAge());
        } else {
            holder.age.setText("24");
            holder.age.setVisibility(View.INVISIBLE);
        }
        if (!list.get(position).getWeight().isEmpty()) {
            holder.weight.setText(list.get(position).getWeight()+ "" + Constants.kg);
        } else {
            holder.weight.setText("166.5");
            holder.weight.setVisibility(View.INVISIBLE);
        }
        if (!list.get(position).getHeight().isEmpty()) {
            holder.height.setText(list.get(position).getHeight()+ "" + Constants.cm);
        } else {
            holder.height.setText("166.5");
            holder.height.setVisibility(View.INVISIBLE);
        }

        holder.date.setText(Constants.date + ":" + " " + list.get(position).getDate());

        //--- imageView
        Picasso.with(context)
                .load(list.get(position).getFronturl()).memoryPolicy(MemoryPolicy.NO_CACHE)
                .error(R.drawable.ic_noimage)
                .placeholder(R.drawable.progress_animation).into(holder.poseOne);
        Picasso.with(context)
                .load(list.get(position).getSideurl()).memoryPolicy(MemoryPolicy.NO_CACHE)
                .error(R.drawable.ic_noimage)
                .placeholder(R.drawable.progress_animation).into(holder.poseTwo);

        //--- SetText
        holder.tvAgeView.setText(Constants.age);
        holder.tvHeightView.setText(Constants.height);
        holder.tvWeightView.setText(Constants.weight);

        holder.rlPreviousList.setOnClickListener(view -> {
            Constants.order_id = list.get(position).getOid();
            Constants.child_id = list.get(position).getUserId();
            CommonFunctions.getInstance().newIntent(context, MeasurementDetailsActivity.class,
                    Bundle.EMPTY, true);
        });

        if (list.size() == 0) {
            holder.llNoDate.setVisibility(View.VISIBLE);
        } else {
            holder.llNoDate.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView poseOne, poseTwo;
        TextView age, height, weight, date, tvAgeView, tvHeightView,
                tvWeightView;
        RelativeLayout rlPreviousList;
        LinearLayout llNoDate;

        Holder(View itemView) {
            super(itemView);

            poseOne = itemView.findViewById(R.id.ivPoseOne);
            poseTwo = itemView.findViewById(R.id.ivPoseTwo);
            age = itemView.findViewById(R.id.tvAgeValue);
            height = itemView.findViewById(R.id.tvHeightValue);
            weight = itemView.findViewById(R.id.tvWeightValue);
            date = itemView.findViewById(R.id.tvDate);
            tvAgeView = itemView.findViewById(R.id.tvAgeView);
            tvHeightView = itemView.findViewById(R.id.tvHeightView);
            tvWeightView = itemView.findViewById(R.id.tvWeightView);
            rlPreviousList = itemView.findViewById(R.id.rlPreviousList);
            llNoDate = itemView.findViewById(R.id.llNoDate);

        }
    }
}
