package com.example.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.R;
import com.example.api.Urls;
import com.example.util.CommonFunctions;
import com.example.util.Constants;
import com.example.util.CustomProgressDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpActivity extends AppCompatActivity {


    @BindView(R.id.vvHelp)
    VideoView vvHelp;
    @BindView(R.id.ivHelpBack)
    ImageView ivHelpBack;
    String[] error, video, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(this);
        }
      //  playVideo();
    }

    private void playVideo() {
        StringRequest request = new StringRequest(StringRequest.Method.POST, ""
                + Urls.helpVideo, response -> {
            try {
                if (!CustomProgressDialog.getInstance().isShowing()) {
                    CustomProgressDialog.getInstance().show(this);
                }
                webView("[" + response + "]");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            Toast.makeText(HelpActivity.this,
                    "無效的用戶名或密碼", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", " Bearer " + Constants.login_api_key);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    private void webView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        error = new String[jsonArray.length()];
        video = new String[jsonArray.length()];
        message = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            if (CustomProgressDialog.getInstance().isShowing()) {
                CustomProgressDialog.getInstance().dismiss();
            }
            JSONObject obj = jsonArray.getJSONObject(i);
            video[i] = obj.getString("video");
            try {
                video[i] = obj.getString("video");
                if (video[i] != null) {
                    if (CustomProgressDialog.getInstance().isShowing()) {
                        CustomProgressDialog.getInstance().dismiss();
                    }
                    vvHelp.setVideoPath(video[i]);
                    vvHelp.setMediaController(new MediaController(this));
                    vvHelp.requestFocus();
                    vvHelp.start();
                    CustomProgressDialog.getInstance().dismiss();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CustomProgressDialog.getInstance().dismiss();
        vvHelp.setOnErrorListener((mp, what, extra) -> {
            Log.d("video", "setOnErrorListener ");
            return true;
        });

    }


    @OnClick(R.id.ivHelpBack)
    public void onViewClicked(View view) {
        if (view.getId() == R.id.ivHelpBack) {
            CommonFunctions.getInstance().newIntent(HelpActivity.this, MeasurementHomeActivity.class, Bundle.EMPTY, false);
        }
    }
}
