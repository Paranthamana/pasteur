package com.example.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.util.CommonFunctions;
import com.example.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OTPVerificationActivity extends AppCompatActivity {

    @BindView(R.id.tvEnterOtp)
    TextView tvEnterOtp;
    @BindView(R.id.ivOTP)
    ImageView ivOTP;
    @BindView(R.id.tvWehaveSendAccessCode)
    TextView tvWehaveSendAccessCode;
    @BindView(R.id.tvPleaseEnterDigitCode)
    TextView tvPleaseEnterDigitCode;
    @BindView(R.id.llOtpSubmit)
    LinearLayout llOtpSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        //setText
        tvWehaveSendAccessCode.setText(Constants.weHaveSendAccessCode);
        tvPleaseEnterDigitCode.setText(Constants.pleaseEnterYour6DigitOtpNumber);

    }

    @OnClick(R.id.llOtpSubmit)
    public void onViewClicked(View view) {
        if (view.getId() == R.id.llOtpSubmit) {
            CommonFunctions.getInstance().newIntent(OTPVerificationActivity.this,
                    CreatePasswordActivity.class, Bundle.EMPTY, false);
        }
    }
}
