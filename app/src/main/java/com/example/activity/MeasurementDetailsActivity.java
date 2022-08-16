package com.example.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.R;
import com.example.adapter.MeasurementDetailViewPagerAdapter;
import com.example.util.CommonFunctions;
import com.example.util.Constants;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeasurementDetailsActivity extends AppCompatActivity {

    @BindView(R.id.ivAccuracyBack)
    ImageView ivAccuracyBack;
    @BindView(R.id.tvAccuracyToolbarMeasurement)
    TextView tvAccuracyToolbarMeasurement;
    @BindView(R.id.ivAccuracyDotView)
    ImageView ivAccuracyDotView;
    @BindView(R.id.DetailsToolbar)
    Toolbar DetailsToolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vpMeasurementDetails)
    ViewPager vpMeasurementDetails;
    @BindView(R.id.ivDetailGallery)
    ImageView ivDetailGallery;
    @BindView(R.id.tvDetailPreviousMeasurement)
    TextView tvDetailPreviousMeasurement;
    @BindView(R.id.llDetailPreviousMeasurement)
    LinearLayout llDetailPreviousMeasurement;
    @BindView(R.id.tvDetailHelp)
    TextView tvDetailHelp;
    @BindView(R.id.llDetailHelp)
    LinearLayout llDetailHelp;
    @BindView(R.id.tvDetailCustomerSupport)
    TextView tvDetailCustomerSupport;
    @BindView(R.id.llDetailCustomerSupport)
    LinearLayout llDetailCustomerSupport;
    @BindView(R.id.llDetailMeasurement)
    LinearLayout llDetailMeasurement;
    private int[] imageResId = {
            R.drawable.ic_measurement,
            R.drawable.ic_two_d,
            R.drawable.ic_degree,
            R.drawable.ic_message};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_details);
        ButterKnife.bind(this);
        initView();

        vpMeasurementDetails.setAdapter(new MeasurementDetailViewPagerAdapter
                (getSupportFragmentManager(), MeasurementDetailsActivity.this));
        tabLayout.setupWithViewPager(vpMeasurementDetails);

        //--- custom tab icons
        for (int i = 0; i < imageResId.length; i++) {
            @SuppressLint("InflateParams")
            View view = getLayoutInflater().inflate(R.layout.customtab, null);
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            view.findViewById(R.id.icon).setBackgroundResource(imageResId[i]);
            if (tab != null) tab.setCustomView(view);
        }

        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setSize(2, 1);
        linearLayout.setDividerPadding(10);
        linearLayout.setDividerDrawable(drawable);

        //--> Click Listeners
        ivAccuracyBack.setOnClickListener(view -> finish());
        ivAccuracyDotView.setOnClickListener(v ->
                showOptions(MeasurementDetailsActivity.this));
    }

    private void initView() {
        tvAccuracyToolbarMeasurement.setText(Constants.accuracyMeasurement);
    }


    private void showOptions(Context mContext) {
        try {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService
                    (Activity.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") View layout
                    = inflater.inflate(R.layout.item_flow_view, null);
            ImageView imageView = layout.findViewById(R.id.ivHomeClose);
            TextView tvProfile = layout.findViewById(R.id.tvProfile);
            TextView tvTermsAndCondition = layout.findViewById(R.id.tvTermsAndCondition);
            TextView tvLogout = layout.findViewById(R.id.tvLogout);
            TextView tvHome = layout.findViewById(R.id.tvHome);
            LinearLayout llHome = layout.findViewById(R.id.llHome);
            View view1 = layout.findViewById(R.id.view1);
            RelativeLayout rlPopUp = layout.findViewById(R.id.rlPopUp);
            llHome.setVisibility(View.VISIBLE);
            view1.setVisibility(View.VISIBLE);
            PopupWindow optionSpu = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            optionSpu.setAnimationStyle(R.style.popup_window_animation);
            //optionSpu.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            optionSpu.setFocusable(true);
            optionSpu.setOutsideTouchable(true);
            optionSpu.update(20, 20, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            optionSpu.showAtLocation(layout, Gravity.END
                    | Gravity.TOP, 0, 0);

            imageView.setOnClickListener(v -> optionSpu.dismiss());

            tvLogout.setOnClickListener(v ->
                    CommonFunctions.getInstance().newIntent(MeasurementDetailsActivity.this,
                            LoginActivity.class, Bundle.EMPTY, true));

            tvHome.setOnClickListener(v ->
                    CommonFunctions.getInstance().newIntent(MeasurementDetailsActivity.this,
                    MeasurementHomeActivity.class, Bundle.EMPTY, true));



            tvProfile.setText(Constants.profile);
            tvTermsAndCondition.setText(Constants.termsAndCondition);
            tvLogout.setText(Constants.logout);
            tvHome.setText(Constants.home);

            AnimationDrawable animationDrawable = (AnimationDrawable) rlPopUp.getBackground();
            animationDrawable.setEnterFadeDuration(1000);
            animationDrawable.setExitFadeDuration(1000);
            animationDrawable.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
