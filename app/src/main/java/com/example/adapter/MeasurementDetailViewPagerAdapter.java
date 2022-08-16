package com.example.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fragment.Accuracy2DMeasurementFragment;
import com.example.fragment.MeasurementDetailPoseFragment;
import com.example.fragment.MessageDetailsFragment;
import com.example.fragment.WebView360DegreeFragment;

public class MeasurementDetailViewPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    final int PAGE_COUNT = 4;

    public MeasurementDetailViewPagerAdapter(FragmentManager supportFragmentManager,
                                             Context context) {
        super(supportFragmentManager);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MeasurementDetailPoseFragment.newInstance(position);
            case 1:
                return Accuracy2DMeasurementFragment.newInstance(position);
            case 2:
                return WebView360DegreeFragment.newInstance(position);
            case 3:
                return MessageDetailsFragment.newInstance(position);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
