package com.example.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import com.example.R;
import com.example.util.Constants;

public class UltraPagerAdapter extends PagerAdapter {

    private Context mContext;
    private FragmentManager fragmentManager;
    private int count = 4;

    public UltraPagerAdapter(Context context, FragmentManager supportFragmentManager) {
        this.mContext = context;
        this.fragmentManager = supportFragmentManager;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext())
                .inflate(R.layout.layout_child, null);
        ImageView imageView = linearLayout.findViewById(R.id.ivImage);

        imageView.setImageBitmap((Constants.posImages[position]));
        /*Picasso.with(container.getContext())
                .load(Constants.posImages[position]))
                .memoryPolicy(MemoryPolicy.NO_
                CACHE)
                .into(imageView);*/
        container.addView(linearLayout);
        return linearLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
