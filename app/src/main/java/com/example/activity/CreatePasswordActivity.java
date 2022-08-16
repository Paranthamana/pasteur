package com.example.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.util.CommonFunctions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatePasswordActivity extends AppCompatActivity {

    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.tvForgotpasswordView)
    TextView tvForgotpasswordView;
    @BindView(R.id.tvPleaseenternewpassword)
    TextView tvPleaseenternewpassword;
    @BindView(R.id.tvMobileNumber)
    TextView tvMobileNumber;
    @BindView(R.id.edtMobileNo)
    EditText edtMobileNo;
    @BindView(R.id.tvConfirmpassword)
    TextView tvConfirmpassword;
    @BindView(R.id.edtConfirmPassword)
    EditText edtConfirmPassword;
    @BindView(R.id.tvSubmit)
    TextView tvSubmit;
    @BindView(R.id.llCreatePasswordSubmit)
    LinearLayout llCreatePasswordSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.llCreatePasswordSubmit)
    public void onViewClicked(View view) {
        if (view.getId() == R.id.llCreatePasswordSubmit) {
            CommonFunctions.getInstance().newIntent(CreatePasswordActivity.this,
                    MeasurementHomeActivity.class, Bundle.EMPTY, false);
        }
    }
}
