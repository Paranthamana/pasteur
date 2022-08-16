package com.example.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.util.CommonFunctions;
import com.example.util.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.tvSignup)
    TextView tvSignUp;
    @BindView(R.id.tvEntermobileToContinue)
    TextView tvEnterMobileToContinue;
    @BindView(R.id.edtMobileNumber)
    EditText edtMobileNumber;
    @BindView(R.id.tvHaveAccount)
    TextView tvHaveAccount;
    @BindView(R.id.tvMobileNumberView)
    TextView tvMobileNumberView;
    @BindView(R.id.tvSendView)
    TextView tvSendView;
    @BindView(R.id.llSend)
    LinearLayout llSend;
    //String[] name, st_id, error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        initView();
        //getState();
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
        //--TextView with double color
        String text = "<font color=#868686>Already have account ?</font> <font color=#2699fb> Login</font>";
        tvHaveAccount.setText(Html.fromHtml(text));
        tvSignUp.setText(Constants.signUp);
        tvEnterMobileToContinue.setText(Constants.pleaseEnterMobileNumberToContinue);
        tvMobileNumberView.setText(Constants.mobileNumber);
        tvSendView.setText(Constants.send);
        //tvHaveAccount.setText(Constants.alreadyHaveAccountLogin);
        //  spinner_state.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //  Toast.makeText(this, "id"+st_id[position], Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @OnClick({R.id.llSend, R.id.tvHaveAccount, R.id.edtMobileNumber})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSend:
                CommonFunctions.getInstance().newIntent(SignUpActivity.this, OTPVerificationActivity.class, Bundle.EMPTY, false);
                break;
            case R.id.tvHaveAccount:
                CommonFunctions.getInstance().newIntent(SignUpActivity.this, LoginActivity.class, Bundle.EMPTY, true);
                break;
            case R.id.edtMobileNumber:
                edtMobileNumber.setCursorVisible(true);
                break;
        }
    }

    /*class AppointmentAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return st_id.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {

            convertView = null;

            if (convertView == null) {
                //convertView = getLayoutInflater().inflate(R.layout.sta, null);
                //TextView qst = convertView.findViewById(R.id.textView13);

                //qst.setText(name[position]);
                ViewGroup vg = (ViewGroup) convertView;
            }

            return convertView;
        }

    }*/

    /*void log_json(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);
        error = new String[jsonArray.length()];

        String[] date = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            error[i] = obj.getString("error");

        }
        try {

            if (error[0].equals("0")) {
                // Toast.makeText(this, "登陸成功", Toast.LENGTH_SHORT).show();
                CommonFunctions.getInstance().newIntent(SignUpActivity.this, LoginActivity.class, Bundle.EMPTY, true);


            } else {
                //Toast.makeText(this, "登陸成功", Toast.LENGTH_SHORT).show();
            }
            // Toast.makeText ( this, "vv"+constant.sms_states, Toast.LENGTH_SHORT ).show ( );

        } catch (Exception e) {

            //Toast.makeText(this, "Error Code : Msg-201 ", Toast.LENGTH_SHORT).show();
        }
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CommonFunctions.getInstance().newIntent(SignUpActivity.this, LoginActivity.class, Bundle.EMPTY, false);
    }
}
