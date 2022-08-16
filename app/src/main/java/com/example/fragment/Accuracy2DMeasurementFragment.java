package com.example.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.R;
import com.example.adapter.Accuracy2DMeasurementAdapter;
import com.example.api.Urls;
import com.example.model.Accuracy2DMeasurementModel;
import com.example.util.Constants;
import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Accuracy2DMeasurementFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private AppCompatActivity activity;
    private String[] name, ename, mvalues, url_path, color, help;
    private int json_val;
    private List<Accuracy2DMeasurementModel> measurementModelList = new ArrayList<>();

    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;
    private RecyclerView rvMeasurementTwoDList;
    private int[] poseImage = {R.drawable.ic_measure_one, R.drawable.ic_measure_two,
            R.drawable.ic_measure_three, R.drawable.ic_measure_four, R.drawable.ic_measure_five,
            R.drawable.ic_measure_six, R.drawable.ic_measure_seven, R.drawable.ic_measure_eight,
            R.drawable.ic_measure_nine, R.drawable.ic_measure_ten, R.drawable.ic_measure_eleven,
            R.drawable.ic_measure_twelve, R.drawable.ic_measure_thirteen,
            R.drawable.ic_measure_fourteen, R.drawable.ic_measure_fifteen,
            R.drawable.ic_measure_sixteen, R.drawable.ic_measure_seveteen,
            R.drawable.ic_measure_eighteen, R.drawable.ic_measure_nineteen,
            R.drawable.ic_measure_twenty, R.drawable.ic_measure_twentyone,
            R.drawable.ic_measure_twentytwo};
    private ImageView ivTwoDPose;
    private HTextView tvMeasurementCM;
    private TextView tvCM;
    private Animation animCrossFadeIn;
    private Accuracy2DMeasurementAdapter.ViewHolder selectedAdapter;

    public Accuracy2DMeasurementFragment() {

    }

    public static final String ARG_PAGE = "ARG_PAGE";

    public static Accuracy2DMeasurementFragment newInstance(int page) {
        Accuracy2DMeasurementFragment fragment = new Accuracy2DMeasurementFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_accuracy2_dmeasurement, container,
                false);
        rvMeasurementTwoDList = view.findViewById(R.id.rvMeasurementTwoDList);
        ivTwoDPose = view.findViewById(R.id.ivTwoDPose);
        tvMeasurementCM = view.findViewById(R.id.tvMeasurementCM);
        tvCM = view.findViewById(R.id.tvCM);
        try {
            ivTwoDPose.setImageDrawable(getResources().getDrawable(poseImage[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
       /* Accuracy2DMeasurementAdapter adapter = new Accuracy2DMeasurementAdapter
                (activity, null);
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMeasurementTwoDList.setLayoutManager(llm);
        rvMeasurementTwoDList.setAdapter(adapter);
        animCrossFadeIn = AnimationUtils.loadAnimation(activity,
                R.anim.anim_scale);
        Log.d("adapter", "asd");
        adapter.setOnClickedListener((adapter1,index, weight) -> {
            if (selectedAdapter != null){
                selectedAdapter.Deselect();
            }
            selectedAdapter = adapter1;
            tvMeasurementCM.startAnimation(animCrossFadeIn);
            tvMeasurementCM.setVisibility(View.VISIBLE);
            ivTwoDPose.setImageDrawable(getResources().getDrawable(poseImage[index]));
            tvMeasurementCM.setText(weight + " " + Constants.cm);
        });
        adapter.notifyDataSetChanged();*/
        measurementDetails();
        return view;
    }

    private void measurementDetails() {
        StringRequest request = new StringRequest(StringRequest.Method.POST,
                "" + Urls.getmeasurements, response -> {
            try {
                detailJson(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            // Toast.makeText(MainActivity.this, "無效的用戶名或密碼", Toast.LENGTH_SHORT).show();
        }) {

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", " Bearer " + Constants.login_api_key);
                return headers;
            }

            /*  Confirm ID check here */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("cid", Constants.child_id);
                params.put("oid", Constants.order_id);
                return params;
            }
        };
        Volley.newRequestQueue(activity).add(request);
    }

    private void detailJson(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        name = new String[jsonArray.length()];
        ename = new String[jsonArray.length()];
        mvalues = new String[jsonArray.length()];
        url_path = new String[jsonArray.length()];
        color = new String[jsonArray.length()];
        help = new String[jsonArray.length()];
        measurementModelList.clear();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            /*name[i] = obj.getString("name");
            mvalues[i] = obj.getString("mvalues");
            url_path[i] = obj.getString("url_path");
            color[i] = obj.getString("color");
            help[i] = obj.getString("help");*/

            Accuracy2DMeasurementModel measurementModel = new Accuracy2DMeasurementModel();
            measurementModel.setHeight(obj.getString("name"));
            measurementModel.setWeight(obj.getString("mvalues"));
            measurementModel.setImageaccuracy(obj.getString("url_path"));
            measurementModelList.add(measurementModel);


        }
        Accuracy2DMeasurementAdapter adapter = new Accuracy2DMeasurementAdapter
                (activity, measurementModelList);
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMeasurementTwoDList.setLayoutManager(llm);
        rvMeasurementTwoDList.setAdapter(adapter);
        animCrossFadeIn = AnimationUtils.loadAnimation(activity,
                R.anim.anim_scale);
        Log.d("adapter", "asd");
        adapter.setOnClickedListener((adapter1, index, weight) -> {
            if (selectedAdapter != null) {
                selectedAdapter.Deselect();
            }
            selectedAdapter = adapter1;
            //tvMeasurementCM.startAnimation(animCrossFadeIn);
            tvMeasurementCM.setVisibility(View.VISIBLE);
            tvMeasurementCM.setAnimateType(HTextViewType.EVAPORATE);
            tvMeasurementCM.animateText(weight);
            //tvMeasurementCM.setText(weight);
            tvCM.setText(Constants.cm);
            try {
                ivTwoDPose.setImageDrawable(getResources().getDrawable(poseImage[index]));

            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }

        });
        adapter.notifyDataSetChanged();
        json_val = jsonArray.length();

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
        if (context instanceof Activity) {
            activity = (AppCompatActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
