package com.example.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.R;
import com.example.util.CommonFunctions;
import com.example.util.Constants;

public class SplashActivity extends AppCompatActivity {
    Boolean doubleBackToExitPressedOnce = false;
    VideoView videoView;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;
    private static final int REQUEST = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= 23) {
            Log.d("TAG", "@@@ IN IF Build.VERSION.SDK_INT >= 23");
            String[] PERMISSIONS = {android.Manifest.permission.CAMERA,
                    android.Manifest.permission.INTERNET,
                    android.Manifest.permission.ACCESS_NETWORK_STATE,
                    android.Manifest.permission.ACCESS_WIFI_STATE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            };
            if (!hasPermissions(SplashActivity.this, PERMISSIONS)) {
                Log.d("TAG", "@@@ IN IF hasPermissions");
                ActivityCompat.requestPermissions(SplashActivity.this, PERMISSIONS, REQUEST);
            } else {
                Log.d("TAG", "@@@ IN ELSE hasPermissions");
                LoadNextPage();
            }
        } else {
            Log.d("TAG", "@@@ IN ELSE  Build.VERSION.SDK_INT >= 23");
            LoadNextPage();
        }

        videoView = findViewById(R.id.vvSplash);
        videoView.setZOrderOnTop(true);
        videoView.setBackgroundColor(Color.TRANSPARENT);
        Uri uri = Uri.parse("android.resource://" // First start with this,
                + getPackageName()
                + "/"
                + R.raw.splash);

        videoView.setVideoURI(uri);

        videoView.start();

        videoView.setOnPreparedListener(mediaPlayer -> {
            mMediaPlayer = mediaPlayer;
            mMediaPlayer.setLooping(true);

            if (mCurrentVideoPosition != 0) {
                mMediaPlayer.seekTo(mCurrentVideoPosition);
                mMediaPlayer.start();
            } else {
                System.out.println("splash media");
            }
        });

    }

    private void LoadNextPage() {
        new Handler().postDelayed(() -> {
            //if (SessionManager.User.getInstance().getDriverKey().isEmpty()) {
            CommonFunctions.getInstance().newIntent(SplashActivity.this,
                    LoginActivity.class, Bundle.EMPTY, true);
            // } else {
            // CommonFunctions.getInstance().newIntent(SplashActivity.this,
            // HomeActivity.class, Bundle.EMPTY, true);
            // }
        }, Constants.SplashDisplayLength);
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("TAG", "@@@ PERMISSIONS grant");
                LoadNextPage();
            } else {
                Log.d("TAG", "@@@ PERMISSIONS Denied");
                Toast.makeText(SplashActivity.this, "PERMISSIONS Denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(SplashActivity.this, Constants.pleaseClickBackAgainToExit,
                Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        } else {
            System.out.println("splash destroy");
        }
    }
}
