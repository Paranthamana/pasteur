package com.example.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.R;
import com.example.activity.LoginActivity;
import com.example.api.Urls;
import com.example.util.Constants;
import com.example.util.CustomProgressDialog;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeasurementDetailPoseFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    MeasurementMessageListDetailAdapter expandableListAdapter;
    private String[] name, ename, mvalues, url_path, color, help;
    private int json_val;
    private int lastExpandedPosition = -1;
    private ExpandableListView evMeasurementListDetails;


    public MeasurementDetailPoseFragment() {
    }

    private AppCompatActivity activity;
    private static final String ARG_PAGE = "ARG_PAGE";

    public static MeasurementDetailPoseFragment newInstance(int page) {
        MeasurementDetailPoseFragment fragment = new MeasurementDetailPoseFragment();
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
        View view = inflater.inflate(R.layout.fragment_measurement_detail_pose,
                container, false);

        measurementDetails();

        evMeasurementListDetails = view.findViewById(R.id.evMeasurementListDetails);

        expandableListAdapter = new MeasurementMessageListDetailAdapter(activity,
                expandableListTitle, expandableListDetail);
        evMeasurementListDetails.setAdapter(expandableListAdapter);
        evMeasurementListDetails.setGroupIndicator(null);

        evMeasurementListDetails.setOnGroupExpandListener(groupPosition -> {
            if (lastExpandedPosition != -1
                    && groupPosition != lastExpandedPosition) {
                evMeasurementListDetails.collapseGroup(lastExpandedPosition);
            }
            lastExpandedPosition = groupPosition;
        });

        evMeasurementListDetails.setOnChildClickListener((expandableListView,
                                                          view1, i, i1, l) -> false);

        return view;
    }

    private void measurementDetails() {
        StringRequest request = new StringRequest(StringRequest.Method.POST,
                "" + Urls.getmeasurements, response -> {
            if (!CustomProgressDialog.getInstance().isShowing()) {
                CustomProgressDialog.getInstance().show(activity);
            }
            try {
                detailJson(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            if (CustomProgressDialog.getInstance().isShowing()) {
                CustomProgressDialog.getInstance().dismiss();
            }
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
        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(activity);
        }
        JSONArray jsonArray = new JSONArray(json);
        name = new String[jsonArray.length()];
        ename = new String[jsonArray.length()];
        mvalues = new String[jsonArray.length()];
        url_path = new String[jsonArray.length()];
        color = new String[jsonArray.length()];
        help = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            if (CustomProgressDialog.getInstance().isShowing()) {
                CustomProgressDialog.getInstance().dismiss();
            }
            JSONObject obj = jsonArray.getJSONObject(i);
            name[i] = obj.getString("name");
            mvalues[i] = obj.getString("mvalues");
            url_path[i] = obj.getString("url_path");
            color[i] = obj.getString("color");
            help[i] = obj.getString("help");
        }
        if (CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().dismiss();
        }
        json_val = jsonArray.length();
        evMeasurementListDetails.setAdapter(expandableListAdapter);
    }


    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(@NonNull Context context) {
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
        void onFragmentInteraction(Uri uri);
    }

    private class MeasurementMessageListDetailAdapter extends BaseExpandableListAdapter {

        public Context context;
        List<String> expandableListTitle;
        HashMap<String, List<String>> expandableListDetail;

        MeasurementMessageListDetailAdapter(Context context, List<String>
                expandableListTitle, HashMap<String, List<String>> expandableListDetail) {
            this.context = context;
            this.expandableListTitle = expandableListTitle;
            this.expandableListDetail = expandableListDetail;
        }

        @Override
        public int getGroupCount() {
            return json_val;
        }

        @Override
        public int getChildrenCount(int position) {
            return 1;
        }

        @Override
        public Object getGroup(int position) {
            return json_val;
        }

        @Override
        public Object getChild(int listPosition, int expandedListPosition) {
            return expandedListPosition;
        }

        @Override
        public long getGroupId(int listPosition) {
            return listPosition;
        }

        @Override
        public long getChildId(int listPosition, int expandedListPosition) {
            return expandedListPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @SuppressLint("InflateParams")
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                                 ViewGroup parent) {
            convertView = null;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.measurement_detail_adapter,
                        null);
                TextView poseHeight = convertView.findViewById(R.id.tvMessagePoseHeight);
                TextView poseName = convertView.findViewById(R.id.tvMessagePoseName);
                ImageView poseImage = convertView.findViewById(R.id.ivMessagePoseList);


                poseName.setText(name[groupPosition]);
                poseHeight.setText(mvalues[groupPosition] + " " + Constants.cm);
                Picasso.with(context)
                        .load(url_path[groupPosition])
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .error(R.drawable.ic_noimage)
                        .into(poseImage);
                //.placeholder(R.drawable.progress_animation).into(poseImage);
            }
            return convertView;
        }

        @SuppressLint("InflateParams")
        @Override
        public View getChildView(int listPosition, final int expandedListPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.measurement_detail_child_adapter,
                        null);
            }

            TextView expandedListTextView = convertView
                    .findViewById(R.id.tvExpandedListItem);
            expandedListTextView.setText("");
            expandedListTextView.setText(help[listPosition]);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int listPosition, int expandedListPosition) {
            return true;
        }
    }
}
