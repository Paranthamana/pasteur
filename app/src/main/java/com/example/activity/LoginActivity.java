package com.example.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.R;
import com.example.api.Urls;
import com.example.application.MyApplication;
import com.example.util.CommonFunctions;
import com.example.util.Constants;
import com.example.util.CustomProgressDialog;
import com.example.util.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    boolean isRemember = false;
    String password, name;
    String[] error, api_key, user_id, message;
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;
    @BindView(R.id.tvLoginView)
    TextView tvLoginView;
    @BindView(R.id.ivRememberChecked)
    ImageView ivRememberChecked;
    @BindView(R.id.llRememberMe)
    LinearLayout llRememberMe;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.tvLogin)
    TextView tvLogin;
    @BindView(R.id.tvSigInToContinue)
    TextView tvSigInToContinue;
    @BindView(R.id.tvAccountId)
    TextView tvAccountId;
    @BindView(R.id.edtUserName)
    EditText edtUserName;
    @BindView(R.id.tvPassword)
    TextView tvPassword;
    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.tvRememberMe)
    TextView tvRememberMe;
    @BindView(R.id.llLogin)
    LinearLayout llLogin;
    @BindView(R.id.llLoginView)
    LinearLayout llLoginView;
    @BindView(R.id.tvCreateAccount)
    TextView tvCreateAccount;
    @BindView(R.id.llBanner)
    LinearLayout llBanner;
    @BindView(R.id.rlLogin)
    RelativeLayout rlLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (MyApplication.context == null) {
            MyApplication.context = getApplicationContext();
        }
        SessionManager.getInstance().setAppLanguageCode("en");
        Constants.getInstance().languageConstants();
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        String user = SessionManager.getData(SessionManager.USERNAME, "");
        String pass = SessionManager.getData(SessionManager.PASSWORD, "");

        if (!user.equals("") && !pass.equals("")) {
           /* CommonFunctions.getInstance().newIntent(LoginActivity.this,
                    LoginActivity.class, Bundle.EMPTY, false);*/
            edtUserName.setText(user);
            edtPassword.setText(pass);
            isRemember = true;
            //finish();
        }
        //SessionManager.getInstance().setAppLanguageCode("zh");


        if (CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().dismiss();
        }
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
       /* edtUserName.setText("m");
        edtPassword.setText("m");*/
        initView();

        llRememberMe.setOnClickListener(view -> {
            if (!isRemember) {
                ivRememberChecked.setImageResource(R.drawable.ic_checked);
                llRememberMe.setSelected(true);
            } else {
                ivRememberChecked.setImageResource(R.drawable.ic_unchecked);
                llRememberMe.setSelected(false);
            }
            isRemember = !isRemember;
        });

        checkConnection();
        Constants.vid_cam = 0;

        llLogin.setOnClickListener(view -> {
            if (edtUserName.getText().toString().trim().equals("")) {
                CommonFunctions.getInstance().validationInfoError(LoginActivity.this,
                        Constants.userNameNotEmpty);
            } else if (edtPassword.getText().toString().trim().equals("")) {
                CommonFunctions.getInstance().validationInfoError(LoginActivity.this,
                        Constants.userPwdCannotEmpty);
            } else {
                if (!CustomProgressDialog.getInstance().isShowing()) {
                    CustomProgressDialog.getInstance().show(LoginActivity.this);
                }
                if (isRemember) {
                    SessionManager.setData(SessionManager.USERNAME, edtUserName.
                            getText().toString());
                    SessionManager.setData(SessionManager.PASSWORD, edtPassword.
                            getText().toString());
                } else {
                    SessionManager.setData(SessionManager.USERNAME, "");
                    SessionManager.setData(SessionManager.PASSWORD, "");
                }
                // Api Call
                log();
            }

        });

    }

    private void initView() {
        tvLogin.setText(Constants.Login);
        tvSigInToContinue.setText(Constants.SignInToContinue);
        String text = "<font color=#868686>Don't have an account ?</font>" +
                " <font color=#2699fb>Sign up</font>";
        tvCreateAccount.setText(Html.fromHtml(text));
        tvAccountId.setText(Constants.accountID);
        tvForgotPassword.setText(Constants.forgot);
        tvPassword.setText(Constants.password);
        tvRememberMe.setText(Constants.rememberMe);
        tvLoginView.setText(Constants.Login);
        //tvCreateAccount.setText(Constants.don'tHaveAccountCreate);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    void log() {
        Constants.fabric_order_id = "";
        password = edtPassword.getText().toString();
        name = edtUserName.getText().toString();
        StringRequest request = new StringRequest(StringRequest.Method.POST, ""
                + Urls.LOGIN, response -> {
            if (!CustomProgressDialog.getInstance().isShowing()) {
                CustomProgressDialog.getInstance().show(LoginActivity.this);
            }
            try {
                log_json("[" + response + "]");
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> {
            if (CustomProgressDialog.getInstance().isShowing()) {
                CustomProgressDialog.getInstance().dismiss();
            }
            Log.i(":Error Res", "無效的用戶名或密碼" + error.getMessage());
            Toast.makeText(LoginActivity.this, "無效的用戶名或密碼" + error.getMessage(),
                    Toast.LENGTH_SHORT).show();

        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", name);
                params.put("password", password);
                //params.put("fcm_token", tok);
                params.put("os", "1");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }

    private void log_json(String json) throws JSONException {
        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(this);
        }
        JSONArray jsonArray = new JSONArray(json);
        error = new String[jsonArray.length()];
        api_key = new String[jsonArray.length()];
        user_id = new String[jsonArray.length()];
        message = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            error[i] = obj.getString("error");
            api_key[i] = obj.getString("access_token");
            message[i] = obj.getString("message");
            Log.i(":Suss Res", "access_token" + obj.getString("access_token"));
            try {
                user_id[i] = obj.getString("user_id");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Constants.login_api_key = api_key[0];
            //Constants.user_id = user_id[0];
            Constants.loginSuccessMessage = message[0];
            if (error[0].equals("0")) {
                if (CustomProgressDialog.getInstance().isShowing()) {
                    CustomProgressDialog.getInstance().dismiss();
                }
                CommonFunctions.getInstance().validationInfoError(LoginActivity.this,
                        Constants.loginSuccessMessage);
                CommonFunctions.getInstance().newIntent(LoginActivity.this,
                        MeasurementHomeActivity.class, Bundle.EMPTY, false);
            } else {
                Toast.makeText(this, "登陸成功", Toast.LENGTH_SHORT).show();
                if (CustomProgressDialog.getInstance().isShowing()) {
                    CustomProgressDialog.getInstance().dismiss();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error Code : Msg-201 ", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkConnection() {
        //boolean isConnected = ConnectivityReceiver.isConnected();
        //showSnack(isConnected);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]
            permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CODE) {
            // When request is cancelled, the results array are empty
            if ((grantResults.length > 0) && (grantResults[0] + grantResults[1] + grantResults[2]
                    == PackageManager.PERMISSION_GRANTED
            )
            ) {
                System.out.println("permission");
                // Permissions are granted
                //Toast.makeText(mContext,"Permissions granted.",Toast.LENGTH_SHORT).show();
            } else {
                System.out.println("else permission");
                // Permissions are denied
                //Toast.makeText(mContext,"Permissions denied.",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick({R.id.tvForgotPassword, R.id.tvCreateAccount, R.id.edtUserName, R.id.edtPassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvForgotPassword:
                CommonFunctions.getInstance().newIntent(LoginActivity.this,
                        ForgotPassword.class, Bundle.EMPTY, false);
                break;
            case R.id.tvCreateAccount:
                CommonFunctions.getInstance().newIntent(LoginActivity.this,
                        SignUpActivity.class, Bundle.EMPTY, true);
                break;
            case R.id.edtUserName:
                edtUserName.setCursorVisible(true);
                break;
            case R.id.edtPassword:
                edtPassword.setCursorVisible(true);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRemember) {
            ivRememberChecked.setImageResource(R.drawable.ic_checked);
        } else {
            ivRememberChecked.setImageResource(R.drawable.ic_unchecked);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LoginActivity.this.finish();
    }
}
