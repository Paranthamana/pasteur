package com.example.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.R;
import com.example.camera.CameraManager;
import com.example.decode.DecodeManager;
import com.example.decode.InactivityTimer;
import com.example.util.CommonFunctions;
import com.example.util.Constants;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QRCodeActivity extends CaptureActivity {

    @BindView(R.id.qr_code_header_black_pic)
    Button qrCodeHeaderBlackPic;
    @BindView(R.id.qr_code_header_bar)
    RelativeLayout qrCodeHeaderBar;
    @BindView(R.id.zxing_barcode_scanner)
    DecoratedBarcodeView zxingBarcodeScanner;
    @BindView(R.id.btnQRBack)
    Button btnQRBack;
    private Executor mQrCodeExecutor;
    private InactivityTimer mInactivityTimer;
    private static final int SELECT_PHOTO = 100;
    public static final int MSG_DECODE_SUCCEED = 1;
    public static final int MSG_DECODE_FAIL = 2;
    public String barcode;
    private Handler mHandler;

    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.activity_qrcode);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        qrCodeHeaderBlackPic = findViewById(R.id.qr_code_header_black_pic);
        qrCodeHeaderBlackPic.setOnClickListener(v -> {
            initData();
            openSystemAlbum();
        });
        return (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);
    }

    private void initData() {
        CameraManager.init(this);
        mInactivityTimer = new InactivityTimer(QRCodeActivity.this);
        mQrCodeExecutor = Executors.newSingleThreadExecutor();
        mHandler = new WeakHandler(this);
    }

    private void openSystemAlbum() {
        Intent photoPic = new Intent(Intent.ACTION_PICK);
        photoPic.setType("image/*");
        startActivityForResult(photoPic, SELECT_PHOTO);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnQRBack)
    public void onViewClicked(View view) {
        if (view.getId() == R.id.btnQRBack) {
            finish();
        }
    }

    private static class WeakHandler extends Handler {
        private WeakReference<QRCodeActivity> mWeakQrCodeActivity;
        private DecodeManager mDecodeManager = new DecodeManager();

        WeakHandler(QRCodeActivity imagePickerActivity) {
            super();
            this.mWeakQrCodeActivity = new WeakReference<>(imagePickerActivity);
        }


        @Override
        public void handleMessage(Message msg) {
            QRCodeActivity qrCodeActivity = mWeakQrCodeActivity.get();
            switch (msg.what) {
                case MSG_DECODE_SUCCEED:
                    Result result = (Result) msg.obj;
                    if (null == result) {
                        mDecodeManager.showCouldNotReadQrCodeFromPicture(qrCodeActivity);
                    } else {
                        String resultString = result.getText();
                        handleResult(resultString);
                    }
                    break;
                case MSG_DECODE_FAIL:
                    mDecodeManager.showCouldNotReadQrCodeFromPicture(qrCodeActivity);
                    break;
            }
            super.handleMessage(msg);
        }

        private void handleResult(String resultString) {
            QRCodeActivity imagePickerActivity = mWeakQrCodeActivity.get();
            mDecodeManager.showResultDialog(imagePickerActivity, resultString, (dialog, which) -> dialog.dismiss());
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    //doing some uri parsing
                    Uri selectedImage = imageReturnedIntent.getData();
                    InputStream imageStream = null;
                    try {
                        //getting the image
                        assert selectedImage != null;
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "File not found", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    //decoding bitmap
                    Bitmap bMap = BitmapFactory.decodeStream(imageStream);
                    //Scan.setImageURI(selectedImage);// To display selected image in image view
                    int[] intArray = new int[bMap.getWidth() * bMap.getHeight()];
                    // copy pixel data from the Bitmap into the 'intArray' array
                    bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(),
                            bMap.getHeight());

                    LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(),
                            bMap.getHeight(), intArray);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                    Reader reader = new MultiFormatReader();// use this otherwise
                    // ChecksumException
                    try {
                        Hashtable<DecodeHintType, Object> decodeHints = new Hashtable<>();
                        decodeHints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
                        decodeHints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);

                        Result result = reader.decode(bitmap, decodeHints);
                        //*I have created a global string variable by the name of barcode to easily manipulate data across the application*//
                        barcode = result.getText();

                        //do something with the results for demo i created a popup dialog
                        if (barcode != null) {
                            Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
                            if (result.getText() == null) {
                                CommonFunctions.getInstance().validationInfoError(
                                        QRCodeActivity.this,Constants.cancelled);
                            } else {
                                String str = result.getText();
                                Integer l = str.length();
                                Matcher matcher = pattern.matcher(str);
                                if (!matcher.matches()) {
                                    if (l.equals(Constants.scannedcharlimit)) {
                                        Toast.makeText(this, "Scanning failed, Try Again", Toast.LENGTH_LONG).show();
                                        //qrresult.setText("");
                                        Constants.fabric_order_id = "";
                                    } else {
                                        Toast.makeText(this, "Scanning failed, Try Again", Toast.LENGTH_LONG).show();
                                        Constants.fabric_order_id = "";
                                    }
                                } else {
                                    if (l.equals(Constants.scannedcharlimit)) {
                                        Constants.fabric_order_id = result.getText();

                                        //setResult(Profile.RESULT_OK,data);

                                        Intent i = new Intent(getBaseContext(), MeasurementHomeActivity.class);
                                        startActivity(i);
                                        finish();
                                        Toast.makeText(this, "Scanning successfully", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(this, "Scanning character length should be " + Constants.scannedcharlimit + " Characters only accepted", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle("Scan Result");
                            builder.setIcon(R.mipmap.ic_launcher);
                            builder.setMessage("Nothing found try a different image or try again");
                            AlertDialog alert1 = builder.create();
                            alert1.setButton(DialogInterface.BUTTON_POSITIVE, "Done", (dialog, which) -> {
                                Intent i = new Intent(getBaseContext(), LoginActivity.class);
                                startActivity(i);
                            });

                            alert1.setCanceledOnTouchOutside(false);

                            alert1.show();

                        }
                        //the end of do something with the button statement.
                    } catch (NotFoundException e) {
                        Toast.makeText(getApplicationContext(), "Nothing Found", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (ChecksumException | NullPointerException e) {
                        Toast.makeText(getApplicationContext(), "Something weird happen, i was probably tired to solve this issue", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (FormatException e) {
                        Toast.makeText(getApplicationContext(), "Wrong Barcode/QR format", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }
}
