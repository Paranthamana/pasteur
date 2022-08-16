/*
 * Copyright 2018 Zihua Zeng (edvard_hua@live.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.poseestimationopencv;

import android.app.Activity;
import android.os.Bundle;

import com.example.R;
import com.example.util.Constants;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import java.util.concurrent.CancellationException;

/**
 * Main {@code Activity} class for the Camera app.
 */
public class CameraActivity extends Activity {

    static {
//        System.loadLibrary("opencv_java");
        System.loadLibrary("opencv_java3");
    }

    public static boolean isOpenCVInit = false;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                    isOpenCVInit = true;
                    break;
                case LoaderCallbackInterface.INCOMPATIBLE_MANAGER_VERSION:
                    break;
                case LoaderCallbackInterface.INIT_FAILED:
                    break;
                case LoaderCallbackInterface.INSTALL_CANCELED:
                    break;
                case LoaderCallbackInterface.MARKET_ERROR:
                    break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (null == savedInstanceState) {
            Camera2BasicFragment camera2BasicFragment = Camera2BasicFragment.newInstance();
            camera2BasicFragment.SetModelDataCallback(Constants.dataCallback);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, camera2BasicFragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, mLoaderCallback);
        } else {
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
