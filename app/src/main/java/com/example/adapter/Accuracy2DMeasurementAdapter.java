package com.example.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.R;
import com.example.model.Accuracy2DMeasurementModel;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Accuracy2DMeasurementAdapter extends RecyclerView.Adapter<Accuracy2DMeasurementAdapter
        .ViewHolder> {

    private Context mContext;
    private List<Accuracy2DMeasurementModel> list;
    private IItemClickListener itemClickListener;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    public Accuracy2DMeasurementAdapter(Context context, List<Accuracy2DMeasurementModel>
            measurementModelList) {
        this.mContext = context;
        this.list = measurementModelList;
    }

    @NonNull
    @Override
    public Accuracy2DMeasurementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                      int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View twoDList = layoutInflater.inflate(R.layout.adapter_twod_measurement, parent,
                false);
        return new ViewHolder(twoDList);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(Accuracy2DMeasurementAdapter.ViewHolder holder, int position) {
        holder.setOnClickListener(position,
                (holder1, index, weight) -> Accuracy2DMeasurementAdapter.
                        this.itemClickListener.OnItemClicked(holder1, index,
                        list.get(position).getWeight()));
        holder.tvMeasurementText.setText(list.get(position).getHeight());
        //holder.tvMeasurementText.setText("casdjkasdk");
        Picasso.with(mContext)
                .load(list.get(position).getImageaccuracy())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .error(R.drawable.ic_noimage)
                .placeholder(R.drawable.progress_animation).into(holder.ivPoseTwo);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setOnClickedListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout llMeasurementTwoDList;
        RelativeLayout rlContainer;
        ImageView ivPoseTwo;
        TextView tvMeasurementText;
        int index = 0;
        IItemClickListener clickListener;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            llMeasurementTwoDList = itemView.findViewById(R.id.llMeasurementTwoDList);
            ivPoseTwo = itemView.findViewById(R.id.ivPoseTwo);
            tvMeasurementText = itemView.findViewById(R.id.tvMeasurementText);
            rlContainer = itemView.findViewById(R.id.rlContainer);
            itemView.setOnClickListener(this);
            llMeasurementTwoDList.setOnClickListener(this);
            ivPoseTwo.setOnClickListener(this);
            tvMeasurementText.setOnClickListener(this);


        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            clickListener.OnItemClicked(ViewHolder.this, index, list.get(index).getWeight());
            if (selectedItems.get(getAdapterPosition(), false)) {
                selectedItems.delete(getAdapterPosition());
                llMeasurementTwoDList.setSelected(false);
                tvMeasurementText.setTextColor(mContext.getColor(R.color.grayDark));
            } else {
                selectedItems.clear();
                selectedItems.put(getAdapterPosition(), true);
                llMeasurementTwoDList.setSelected(true);
            }
        }

        public void setOnClickListener(int index, IItemClickListener clickLister) {
            clickListener = clickLister;
            this.index = index;
        }

        public void Deselect() {
            llMeasurementTwoDList.setSelected(false);
        }
    }

    public interface IItemClickListener {
        void OnItemClicked(ViewHolder holder, int index, String weight);

    }
}
