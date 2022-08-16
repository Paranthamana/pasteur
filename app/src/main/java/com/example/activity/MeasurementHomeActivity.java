package com.example.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.R;
import com.example.api.Urls;
import com.example.util.CommonFunctions;
import com.example.util.Constants;
import com.example.util.CustomProgressDialog;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeasurementHomeActivity extends Activity {


    String[] id, error, message, api_key;
    String sex = "male";

    @BindView(R.id.ivProfileBack)
    ImageView ivProfileBack;
    @BindView(R.id.ivDotView)
    ImageView ivDotView;
    @BindView(R.id.ivKeyPad)
    ImageView ivKeyPad;
    @BindView(R.id.edtQrCodeResult)
    EditText edtQrCodeResult;
    @BindView(R.id.ivQR)
    ImageView ivQR;
    @BindView(R.id.llQrHeader)
    LinearLayout llQrHeader;
    @BindView(R.id.llCamera)
    LinearLayout llCamera;
    @BindView(R.id.llVideo)
    LinearLayout llVideo;
    @BindView(R.id.edtProfileName)
    EditText edtProfileName;
    @BindView(R.id.edtAgeValue)
    EditText edtAgeValue;
    @BindView(R.id.ivCalender)
    ImageView ivCalender;
    @BindView(R.id.llInnerView)
    LinearLayout llInnerView;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.llAge)
    CardView llAge;
    @BindView(R.id.llNext)
    LinearLayout llNext;
    @BindView(R.id.ivGallery)
    ImageView ivGallery;
    @BindView(R.id.edtHeightValue)
    EditText edtHeightValue;
    @BindView(R.id.ivHeight)
    ImageView ivHeight;
    @BindView(R.id.tvHeight)
    TextView tvHeight;
    @BindView(R.id.llHeight)
    CardView llHeight;
    @BindView(R.id.edtWeightValue)
    EditText edtWeightValue;
    @BindView(R.id.ivWeight)
    ImageView ivWeight;
    @BindView(R.id.tvWeight)
    TextView tvWeight;
    @BindView(R.id.llWeight)
    CardView llWeight;
    @BindView(R.id.ivVideo)
    ImageView ivVideo;
    @BindView(R.id.llHelp)
    LinearLayout llHelp;
    @BindView(R.id.tvToolbarMeasurement)
    TextView tvToolbarMeasurement;
    @BindView(R.id.tvPreviousMeasurement)
    TextView tvPreviousMeasurement;
    @BindView(R.id.tvHelp)
    TextView tvHelp;
    @BindView(R.id.tvCustomerSupport)
    TextView tvCustomerSupport;
    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.flQRCodeValue)
    FrameLayout flQRCodeValue;
    @BindView(R.id.llPreviousMeasurement)
    LinearLayout llPreviousMeasurement;
    @BindView(R.id.Toolbar)
    androidx.appcompat.widget.Toolbar Toolbar;
    @BindView(R.id.rlScanView)
    RelativeLayout rlScanView;

    private final String LOG_TAG = "QRScannerQRCodeActivity";
    private static final int REQUEST_CODE_QR_SCAN = 101;
    public static final String Name = "nameKey";
    Boolean doubleBackToExitPressedOnce = false;
    Vibrator v;
    Animation shake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_measurement);
        ButterKnife.bind(this);
        initView();
        ivQR.setImageAlpha(200);
        shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
       /* edtProfileName.setText("Vinoth");
        edtAgeValue.setText("25");
        edtWeightValue.setText("66");
        edtHeightValue.setText("166");*/


        ivKeyPad.setOnClickListener(v -> {
            edtQrCodeResult = findViewById(R.id.edtQrCodeResult);
            edtQrCodeResult.requestFocus();
            edtQrCodeResult.setFocusable(true);
            edtQrCodeResult.setCursorVisible(true);
            edtQrCodeResult.setInputType(InputType.TYPE_CLASS_NUMBER |
                    InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            ivQR.setClickable(false);
            ivQR.setEnabled(false);
            ivQR.setImageAlpha(50);
            btnClose.setVisibility(View.VISIBLE);
            btnClose.setBackgroundResource(R.drawable.ic_close);
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    toggleSoftInput(InputMethodManager.SHOW_FORCED,
                            InputMethodManager.HIDE_IMPLICIT_ONLY);
        });


        llAge.setOnClickListener(view -> {
           /* ImageSpan imageHint = new ImageSpan(MeasurementHomeActivity.this, R.drawable.ic_calander);
            SpannableString spannableString = new SpannableString("Age");
            spannableString.setSpan(imageHint, 0, 5, Spanned.SPAN_USER_SHIFT);
            edtAgeValue.setHint(spannableString);*/
            edtAgeValue.setVisibility(View.VISIBLE);
            edtAgeValue.requestFocus();
            ivCalender.setVisibility(View.GONE);
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    toggleSoftInput(InputMethodManager.SHOW_FORCED,
                            InputMethodManager.HIDE_IMPLICIT_ONLY);
        });


        llHeight.setOnClickListener(view -> {
            edtHeightValue.setVisibility(View.VISIBLE);
            edtHeightValue.requestFocus();
            ivHeight.setVisibility(View.GONE);
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    toggleSoftInput(InputMethodManager.SHOW_FORCED,
                            InputMethodManager.HIDE_IMPLICIT_ONLY);
        });

        llWeight.setOnClickListener(view -> {
            edtWeightValue.setVisibility(View.VISIBLE);
            edtWeightValue.requestFocus();
            ivWeight.setVisibility(View.GONE);
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .toggleSoftInput(InputMethodManager.SHOW_FORCED,
                            InputMethodManager.HIDE_IMPLICIT_ONLY);
        });

        btnClose.setOnClickListener(view -> {
            edtQrCodeResult.setText("");
            ivQR.setClickable(true);
            ivQR.setEnabled(true);
            ivQR.setImageAlpha(200);
            hideSoftKeyboard(this);
        });

       /* rlScanView.setOnClickListener(view -> {
            ivQR.setClickable(false);
            ivQR.setEnabled(false);
            ivQR.setImageAlpha(50);
            btnClose.setBackgroundResource(R.drawable.ic_close);
            btnClose.setVisibility(View.VISIBLE);
        });*/

      /*  edtQrCodeResult.setOnClickListener(view -> {
            ivQR.setClickable(false);
            ivQR.setEnabled(false);
            ivQR.setImageAlpha(50);
            btnClose.setVisibility(View.VISIBLE);
            btnClose.setBackgroundResource(R.drawable.ic_close);
        });*/


        /*llHelp.setOnClickListener(view ->
                CommonFunctions.getInstance().newIntent(MeasurementHomeActivity.this,
                        HelpActivity.class, Bundle.EMPTY, false));*/

        /*flQRCodeValue.setOnClickListener(view -> {
            btnClose.setBackgroundResource(R.drawable.ic_close);
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .toggleSoftInput(InputMethodManager.SHOW_FORCED,
                            InputMethodManager.HIDE_IMPLICIT_ONLY);
        });*/

        ivProfileBack.setOnClickListener(v -> {
            hideSoftKeyboard(MeasurementHomeActivity.this);
            MeasurementHomeActivity.this.finish();
        });


        llPreviousMeasurement.setOnClickListener(view ->
                CommonFunctions.getInstance().newIntent(MeasurementHomeActivity.this,
                        PreviousMeasurementActivity.class, Bundle.EMPTY, false));

        ivDotView.setOnClickListener(view -> showOptions(MeasurementHomeActivity.this));


        ivQR.setOnClickListener(view -> {
            IntentIntegrator integrator = new IntentIntegrator(MeasurementHomeActivity.this);
            integrator.setOrientationLocked(false);
            integrator.setCaptureActivity(QRCodeActivity.class);
            integrator.initiateScan();
        });


        llNext.setOnClickListener(view -> {
            String heightValueStr = edtHeightValue.getText().toString().trim();
            if (edtProfileName.getText().toString().trim().isEmpty()) {
                v.vibrate(400);
                edtProfileName.startAnimation(shake);
            } else if (edtAgeValue.getText().toString().trim().isEmpty()) {
                v.vibrate(400);
                llAge.startAnimation(shake);
            } else if (edtAgeValue.getText().toString().trim().length() > 100) {
                v.vibrate(400);
                llAge.startAnimation(shake);
                CommonFunctions.getInstance().validationInfoError(
                        MeasurementHomeActivity.this, Constants.maxAge100);
            } else if (heightValueStr.isEmpty()) {
                v.vibrate(400);
                llHeight.startAnimation(shake);
                CommonFunctions.getInstance().validationInfoError(
                        MeasurementHomeActivity.this, Constants.heightFieldIsEmpty);
            } else if (195 < Integer.parseInt(heightValueStr)) {
                v.vibrate(400);
                CommonFunctions.getInstance().validationInfoError(
                        MeasurementHomeActivity.this, Constants.heightIsNotMatched);
            } else if (edtWeightValue.getText().toString().trim().isEmpty()) {
                v.vibrate(400);
                llWeight.startAnimation(shake);
                CommonFunctions.getInstance().validationInfoError(
                        MeasurementHomeActivity.this, Constants.weightFieldIsEmpty);
            } else if (150 < Integer.parseInt(edtWeightValue.getText().toString().trim())) {
                v.vibrate(400);
                llWeight.startAnimation(shake);
                CommonFunctions.getInstance().validationInfoError(
                        MeasurementHomeActivity.this, Constants.weightIsNotMatched);
            } else {
                //-- Api call
                createProfile();
            }
        });

        llVideo.setOnClickListener(view -> {
            if (edtProfileName.getText().length() == 0) {
                CommonFunctions.getInstance().validationInfoError
                        (MeasurementHomeActivity.this,
                                Constants.userNameNotEmpty);
                edtProfileName.requestFocus();
            } else {
                if (edtAgeValue.getText().length() == 0) {
                    CommonFunctions.getInstance().validationInfoError
                            (MeasurementHomeActivity.this,
                                    Constants.ageFiledIsEmpty);
                    edtAgeValue.requestFocus();
                } else {
                    if (edtHeightValue.getText().length() == 0) {
                        CommonFunctions.getInstance().validationInfoError
                                (MeasurementHomeActivity.this,
                                        Constants.heightFieldIsEmpty);
                        edtHeightValue.requestFocus();
                    } else {
                        if (edtWeightValue.getText().length() == 0) {
                            CommonFunctions.getInstance().validationInfoError
                                    (MeasurementHomeActivity.this,
                                            Constants.weightFieldIsEmpty);
                            edtWeightValue.requestFocus();
                        } else {
                            Constants.mname = edtProfileName.getText().toString();
                            Constants.age = edtAgeValue.getText().toString();
                            Constants.height = edtHeightValue.getText().toString();
                            Constants.weight = edtWeightValue.getText().toString();

                            edtHeightValue.clearFocus();
                            edtWeightValue.clearFocus();
                            edtAgeValue.clearFocus();

                            edtProfileName.clearFocus();
                            photoTwo();

                        }

                    }

                }

            }
        });
    }

    private void showOptions(Context mContext) {
        try {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService
                    (Activity.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") View layout
                    = inflater.inflate(R.layout.item_flow_view, null);
            ImageView imageView = layout.findViewById(R.id.ivHomeClose);
            TextView tvProfile = layout.findViewById(R.id.tvProfile);
            TextView tvTermsAndCondition = layout.findViewById(R.id.tvTermsAndCondition);
            TextView tvLogout = layout.findViewById(R.id.tvLogout);
            LinearLayout llLogout = layout.findViewById(R.id.llLogout);
            RelativeLayout rlPopUp = layout.findViewById(R.id.rlPopUp);
            LinearLayout llTerms = layout.findViewById(R.id.llTerms);
            LinearLayout llProfile = layout.findViewById(R.id.llProfile);
            PopupWindow optionSpu = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            optionSpu.setAnimationStyle(R.style.popup_window_animation);
            //optionSpu.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            optionSpu.setFocusable(true);
            optionSpu.setOutsideTouchable(true);
            optionSpu.update(20, 20, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            optionSpu.showAtLocation(layout, Gravity.END
                    | Gravity.TOP, 0, 0);

            imageView.setOnClickListener(v -> optionSpu.dismiss());

            tvLogout.setOnClickListener(v ->
                    CommonFunctions.getInstance().newIntent(MeasurementHomeActivity.this,
                            LoginActivity.class, Bundle.EMPTY, false));
            llTerms.setOnClickListener(v -> {

            });

            llProfile.setOnClickListener(v -> {

            });
            tvProfile.setText(Constants.profile);
            tvTermsAndCondition.setText(Constants.termsAndCondition);
            tvLogout.setText(Constants.logout);

            AnimationDrawable animationDrawable = (AnimationDrawable) rlPopUp.getBackground();
            animationDrawable.setEnterFadeDuration(1000);
            animationDrawable.setExitFadeDuration(1000);
            animationDrawable.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createProfile() {
        StringRequest request = new StringRequest(StringRequest.Method.POST, "" +
                Urls.crear_child, response -> {
            try {
                details_json("[" + response + "]");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            // Toast.makeText(MainActivity.this, "無效的用戶名或密碼", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", " Bearer " + Constants.login_api_key);
                Log.d("bear", Constants.login_api_key);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", edtProfileName.getText().toString());
                params.put("age", edtAgeValue.getText().toString());
                params.put("weight", edtWeightValue.getText().toString());
                params.put("height", edtHeightValue.getText().toString());
                params.put("sex", "" + sex);
                return params;
            }

        };

        Volley.newRequestQueue(this).add(request);
    }

    private void details_json(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);

        id = new String[jsonArray.length()];
        error = new String[jsonArray.length()];
        message = new String[jsonArray.length()];
        api_key = new String[jsonArray.length()];
        Log.d("jsonresponse", String.valueOf(jsonArray));
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            id[i] = obj.getString("id");
            error[i] = obj.getString("error");
            message[i] = obj.getString("message");
        }


        try {
            Constants.user_id = id[0];
            if (error[0].equals("0")) {
                CommonFunctions.getInstance().validationInfoError(
                        MeasurementHomeActivity.this, String.valueOf(message[0]));
                Constants.poseDetector.StartPoseDetection(MeasurementHomeActivity.this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        ivVideo.setImageAlpha(100);
        //llVideo.getBackground().setAlpha(190);
        //llVideo.setEnabled(false);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // -- Set Text
        edtQrCodeResult.setText(Constants.fabric_order_id);
        tvToolbarMeasurement.setText(Constants.Measurement);
        tvNext.setText(Constants.next);
        tvAge.setText(Constants.age);
        tvHeight.setText(Constants.height);
        tvWeight.setText(Constants.weight);
        tvPreviousMeasurement.setText(Constants.previousMeasurement);
        tvHelp.setText(Constants.help);
        tvCustomerSupport.setText(Constants.customerSupport);

        //SetHint
        edtProfileName.setHint(Constants.Name);

    }

    void photoTwo() {

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            /*Intent myIntent = new Intent(MeasurementHomeActivity.this, PhotographTwo.class);
            MeasurementHomeActivity.this.startActivity(myIntent);
            finish();*/
        }, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        edtQrCodeResult = findViewById(R.id.edtQrCodeResult);
        IntentResult resultcamera = IntentIntegrator.parseActivityResult
                (requestCode, resultCode, data);
        if (resultcamera != null) {
            if (resultcamera.getContents() == null) {
                CommonFunctions.getInstance().validationInfoError(
                        MeasurementHomeActivity.this, Constants.cancelled);
            } else {
                String str = resultcamera.getContents();
                Integer l = str.length();
                Matcher matcher = pattern.matcher(str);
                if (!matcher.matches()) {
                    if (l.equals(Constants.scannedcharlimit)) {
                        CommonFunctions.getInstance().validationInfoError(
                                MeasurementHomeActivity.this,
                                Constants.scanningFailedTryAgain);
                        edtQrCodeResult.setText("");
                    } else {
                        CommonFunctions.getInstance().validationInfoError(
                                MeasurementHomeActivity.this,
                                Constants.scanningFailedTryAgain);
                        edtQrCodeResult.setText("");
                    }
                } else {
                    if (l.equals(Constants.scannedcharlimit)) {
                        Constants.fabric_order_id = resultcamera.getContents();
                        edtQrCodeResult.setText(resultcamera.getContents());
                        edtQrCodeResult.setTextColor(Color.parseColor("#000000"));
                        //Toast.makeText(this, "Scanning successfully", Toast.LENGTH_LONG).show();
                        CommonFunctions.getInstance().validationInfoError(
                                MeasurementHomeActivity.this,
                                Constants.scanningSuccessFully);

                    } else {
                        CommonFunctions.getInstance().validationInfoError(
                                MeasurementHomeActivity.this,
                                Constants.scanningCharLengthShouldBe + " " +
                                        Constants.scannedcharlimit + " " +
                                        Constants.characterOnlyAccepted);
                    }
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

        if (resultCode != Activity.RESULT_OK) {
            Log.d(LOG_TAG, "COULD NOT GET A GOOD RESULT.");
            if (data == null)
                return;
            //Getting the passed result
            String result = data.getStringExtra
                    ("com.blikoon.qrcodescanner.error_decoding_image");
            if (result != null) {
                AlertDialog alertDialog = new AlertDialog.Builder
                        (MeasurementHomeActivity.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        (dialog, which) -> dialog.dismiss());
                alertDialog.show();
            }
            return;

        }
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (data == null)
                return;
            //Getting the passed result
            String result = data.getStringExtra
                    ("com.blikoon.qrcodescanner.got_qr_scan_relult");
            Log.d(LOG_TAG, "Have scan result in your app activity :" + result);


            if (result.equals("e")) {
                AlertDialog alertDialog = new AlertDialog.Builder
                        (MeasurementHomeActivity.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        (dialog, which) -> dialog.dismiss());
                alertDialog.show();


                edtQrCodeResult.setText(Constants.viewOrderId);
                edtQrCodeResult.setTextColor(Color.parseColor("#ffffff"));

                Log.d(LOG_TAG, "Error Color calling.");
            } else {
                edtQrCodeResult.setText(result);
                edtQrCodeResult.setTextColor(Color.parseColor("#ffffff"));
                Log.d(LOG_TAG, "Error Color not calling.");
            }

        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getFragmentManager();
        int count = manager.getBackStackEntryCount();
        if (count == 1) {

            super.onBackPressed();
        }

        if (count == 0) {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            CommonFunctions.getInstance().validationInfoError
                    (MeasurementHomeActivity.this, Constants.pleaseClickBackAgainToExit);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
