package com.example.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.R;
import com.example.adapter.PreviousMeasurementAdapter;
import com.example.api.Urls;
import com.example.model.CustomerPreviousMeasurementModel;
import com.example.util.Constants;
import com.example.util.CustomProgressDialog;
import com.mlsdev.animatedrv.AnimatedRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreviousMeasurementActivity extends AppCompatActivity {
    @BindView(R.id.ivPreviousBack)
    ImageView ivPreviousBack;
    @BindView(R.id.tvMeasurement)
    TextView tvMeasurement;
    @BindView(R.id.ivDotView)
    ImageView ivDotView;
    @BindView(R.id.llPreviousToolbar)
    LinearLayout llPreviousToolbar;
    @BindView(R.id.rvPreviousMeasurement)
    AnimatedRecyclerView rvPreviousMeasurement;
    @BindView(R.id.tvHelp)
    TextView tvHelp;
    @BindView(R.id.llHelp)
    LinearLayout llHelp;
    @BindView(R.id.ivSupport)
    ImageView ivSupport;
    @BindView(R.id.tvCustomerSupport)
    TextView tvCustomerSupport;
    @BindView(R.id.llListBottomBar)
    RelativeLayout llListBottomBar;
    private List<CustomerPreviousMeasurementModel> listPrevious = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_previous);
        ButterKnife.bind(this);
        CustomProgressDialog.getInstance().show(PreviousMeasurementActivity.this);
        details();
        initView();
        AnimatedRecyclerView recyclerView = new AnimatedRecyclerView.Builder(this)
                .orientation(LinearLayoutManager.VERTICAL)
                .layoutManagerType(AnimatedRecyclerView.LayoutManagerType.LINEAR)
                .animation(R.anim.layout_animation_from_bottom)
                .animationDuration(1000)
                .reverse(false)
                .build();
        recyclerView.scheduleLayoutAnimation();
        ivPreviousBack.setOnClickListener(view -> finish());
    }

    private void initView() {
        tvMeasurement.setText(Constants.previousMeasurement);
        tvHelp.setText(Constants.help);
        tvCustomerSupport.setText(Constants.customerSupport);
    }

    void details() {
        StringRequest request = new StringRequest(StringRequest.Method.POST,
                "" + Urls.get_child_profile, response -> {
            if (!CustomProgressDialog.getInstance().isShowing()) {
                CustomProgressDialog.getInstance().show(PreviousMeasurementActivity.this);
            }
            Log.e("onResponse", response);
            JSONObject myResponse;
            try {
                myResponse = new JSONObject(response);
                if (myResponse.getString("error").equals("1")) {
                    System.out.println("error");
                } else {
                    details_json(response);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            if (error.getMessage() != null) {
                Log.d("error", error.getMessage());
            }

        }) {

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                Log.d("Token", Constants.login_api_key);
                Log.d("order_id", Constants.order_id);
                Log.d("user_id", Constants.user_id);
                headers.put("Authorization", " Bearer " + Constants.login_api_key);
                return headers;
            }


           /* @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("order_id", Constants.order_id);
                return params;
            }*/
        };
        Volley.newRequestQueue(this).add(request);

    }

    private void details_json(String json) throws JSONException {
        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(this);
        }
        JSONObject jsonObj = new JSONObject(json);
        JSONArray jsonArray = jsonObj.getJSONArray("userdata");
        Log.d("jsonArray", String.valueOf(jsonArray));
        for (int i = 0; i < jsonArray.length(); i++) {
            CustomProgressDialog.getInstance().dismiss();
            JSONObject obj = jsonArray.getJSONObject(i);
            CustomerPreviousMeasurementModel Previous = new CustomerPreviousMeasurementModel();
            Previous.setName(obj.getString("name"));
            Previous.setAge(obj.getString("age"));
            Previous.setHeight(obj.getString("height"));
            Previous.setWeight(obj.getString("weight"));
            Previous.setFronturl(String.valueOf(obj.get("fronturl")));
            Previous.setSideurl(obj.getString("sideurl"));
            Previous.setUserId(obj.getString("userid"));
            Previous.setOid(obj.getString("oid"));
            Previous.setMeasurement_type("measurement_type");
            Previous.setDate(obj.getString("date"));
            listPrevious.add(Previous);

            RecyclerView.Adapter adapter = new PreviousMeasurementAdapter(
                    PreviousMeasurementActivity.this, listPrevious);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rvPreviousMeasurement.setLayoutManager(llm);

            if (listPrevious.size() == 0) {
                try {
                    LayoutInflater inflater = (LayoutInflater) this.getSystemService
                            (Activity.LAYOUT_INFLATER_SERVICE);
                    @SuppressLint("InflateParams") View layout
                            = inflater.inflate(R.layout.empty_data, null);
                    TextView tvNoDataMsg = layout.findViewById(R.id.tvNoDataMsg);
                    Button buttonOk = layout.findViewById(R.id.buttonOk);
                    PopupWindow optionSpu = new PopupWindow(layout,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    optionSpu.setAnimationStyle(R.style.popup_window_animation);
                    optionSpu.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                    optionSpu.setFocusable(true);
                    optionSpu.setOutsideTouchable(true);
                    optionSpu.update(20, 20, ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    optionSpu.showAtLocation(layout, Gravity.END
                            | Gravity.TOP, 0, 0);
                    buttonOk.setOnClickListener(v -> optionSpu.dismiss());

                    tvNoDataMsg.setText(Constants.noDataFound);
                    buttonOk.setText(Constants.ok);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            rvPreviousMeasurement.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            rvPreviousMeasurement.scheduleLayoutAnimation();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().dismiss();
        }
    }
}
