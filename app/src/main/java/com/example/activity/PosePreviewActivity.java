package com.example.activity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.R;
import com.example.adapter.UltraPagerAdapter;
import com.example.api.Urls;
import com.example.util.CommonFunctions;
import com.example.util.Constants;
import com.tmall.ultraviewpager.UltraViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PosePreviewActivity extends AppCompatActivity {
    @BindView(R.id.llPosePreviewConfirm)
    LinearLayout llPosePreviewConfirm;
    @BindView(R.id.ivRetake)
    ImageView ivRetake;
    @BindView(R.id.tbMeasurementPose)
    Toolbar tbMeasurementPose;
    @BindView(R.id.tvPose)
    TextView tvPose;
    @BindView(R.id.cvTitle)
    CardView cvTitle;
    @BindView(R.id.upPosePreview)
    UltraViewPager upPosePreview;
    @BindView(R.id.cvUltraView)
    CardView cvUltraView;
    @BindView(R.id.ivIndicator1)
    ImageView ivIndicator1;
    @BindView(R.id.ivPmBack)
    ImageView ivPmBack;
    @BindView(R.id.ivIndicator2)
    ImageView ivIndicator2;
    @BindView(R.id.ivIndicator3)
    ImageView ivIndicator3;
    @BindView(R.id.ivIndicator4)
    ImageView ivIndicator4;
    @BindView(R.id.ivGallery)
    ImageView ivGallery;
    @BindView(R.id.tvPreviousMeasurement)
    TextView tvPreviousMeasurement;
    @BindView(R.id.llPreviousMeasurement)
    LinearLayout llPreviousMeasurement;
    @BindView(R.id.tvHelp)
    TextView tvHelp;
    @BindView(R.id.llHelp)
    LinearLayout llHelp;
    @BindView(R.id.tvCustomerSupport)
    TextView tvCustomerSupport;
    @BindView(R.id.llBottomBar)
    LinearLayout llBottomBar;
    public static final String Name = "nameKey";
    String[] error, message, orderId, result;
    private ImageView[] indicators;
    private int previousPosition = 1;
    String[] title = new String[]{"Left", "Neck Left", "Back", "Neck Front"};
    private String fabricId = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pose_preview);
        ButterKnife.bind(this);

        //--view pager adapter
        UltraViewPager ultraViewPager = findViewById(R.id.upPosePreview);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        PagerAdapter adapter = new UltraPagerAdapter(
                PosePreviewActivity.this, getSupportFragmentManager());
        ultraViewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        indicators = new ImageView[]{
                findViewById(R.id.ivIndicator1), findViewById(R.id.ivIndicator2),
                findViewById(R.id.ivIndicator3), findViewById(R.id.ivIndicator4)
        };

        ultraViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tvPose.setText(title[position]);
                setIndicatorBackgroundColor(indicators[previousPosition], R.color.grayDark);
                setIndicatorBackgroundColor(indicators[position], R.color.colorPrimary);
                previousPosition = position;

                Log.d("pageSelect", String.valueOf(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ivPmBack.setOnClickListener(v -> PosePreviewActivity.this.finish());

       /* //-- > indicator orientation
        ultraViewPager.initIndicator();
        ultraViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.GRAY)
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
                 getResources().getDisplayMetrics()));
        ultraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        ultraViewPager.getIndicator().build();
        ultraViewPager.setInfiniteLoop(false);*/

        llPosePreviewConfirm.setOnClickListener(view -> poseConfirm());

        ivRetake.setOnClickListener(v -> Constants.poseDetector.StartPoseDetection(
                PosePreviewActivity.this, ultraViewPager.getCurrentItem() + 1));

    }


    private void setIndicatorBackgroundColor(ImageView indicator, int colorId) {
        Drawable background = indicator.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(ContextCompat.getColor(
                    PosePreviewActivity.this, colorId));
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(ContextCompat.getColor(
                    PosePreviewActivity.this, colorId));
        } else if (background instanceof ColorDrawable) {
            ((                //indicators[position].setImageDrawable(trans);
                    ColorDrawable) background).setColor(ContextCompat.getColor(
                    PosePreviewActivity.this, colorId));
        }
    }

    private void poseConfirm() {

        StringRequest request = new StringRequest(StringRequest.Method.POST, "" +
                Urls.claidorderconfirm, response -> {
            try {
                orderConfirm_json("[" + response + "]");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> System.out.println("error pose page")) {

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", " Bearer " + Constants.login_api_key);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", Constants.user_id);
                params.put("fabric_order_id", fabricId);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }

    private void orderConfirm_json(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        error = new String[jsonArray.length()];
        message = new String[jsonArray.length()];
        orderId = new String[jsonArray.length()];
        result = new String[jsonArray.length()];
        Log.d("confirm :", String.valueOf(jsonArray));
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            error[i] = object.getString("error");
            message[i] = object.getString("message");
            orderId[i] = object.getString("order_id");
            result[i] = object.getString("result");
        }

        try {
            if (error[0].equals("0")) {
                //Message
                CommonFunctions.getInstance().validationInfoError(
                        PosePreviewActivity.this, String.valueOf(message[0]));
                //-- Next page
                CommonFunctions.getInstance().newIntent(PosePreviewActivity.this,
                        MeasurementDetailsActivity.class, Bundle.EMPTY, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}


