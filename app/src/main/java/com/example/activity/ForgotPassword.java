package com.example.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
    EditText editText_phone;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.tvForgotpassword)
    TextView tvForgotpassword;
    @BindView(R.id.tvSigintocontinue)
    TextView tvSigintocontinue;
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
    @BindView(R.id.llSubmit)
    LinearLayout llSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        initView();

        llSubmit.setOnClickListener(view -> {

        });
    }

    private void initView() {
        //Set Text views
        tvForgotpassword.setText(Constants.forgotPassword);
        tvSigintocontinue.setText(Constants.pleaseEnterMobileNumberToContinue);
        tvMobileNumber.setText(Constants.mobileNumber);
        tvSubmit.setText(Constants.send);

    }

    @Override
    public void onClick(View view) {

    }
}
