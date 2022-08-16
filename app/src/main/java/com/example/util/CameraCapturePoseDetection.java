package com.example.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SizeF;
import android.webkit.CookieManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.activity.MeasurementDetailsActivity;
import com.example.activity.PosePreviewActivity;
import com.example.api.UploadAPIs;
import com.example.api.Urls;
import com.example.model.Imageupload;
import com.example.poseestimationopencv.Camera2BasicFragment;
import com.example.poseestimationopencv.CameraActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.api.Urls.upload;

public class CameraCapturePoseDetection implements Camera2BasicFragment.IModelDataCallback {
    Context parentContext;
    int detectionCount = 0;
    int positionNumber;
    int previousPosition;
    int poseNumber;
    Boolean isPoseDetection = false;
    int numberOfPoseUploaded = 0;

    private String output_file_name;
    private static final String TAG = "Test Camera";
    String upLoadServerHttpsUri = upload;
    Camera.Parameters parameters;
    private int position = 0;
    private String gyroscope_x = "90";
    private String imagename;
    private Boolean isInitialized = false;
    Camera2BasicFragment.ImageData imageData;
    double verticalangle = 0;
    String[] error, message, orderId, result;
    private String fabricId = "123";
    boolean results = false;

    public CameraCapturePoseDetection() {
        Constants.dataCallback = this;
    }

    private void Initialize() {
        if (isInitialized) return;
        isInitialized = true;

        Camera camera = Camera.open();
        parameters = camera.getParameters();
        camera.release();
        output_file_name = Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DCIM) + File.separator;
    }

    public void StartPoseDetection(Context context) {
        Initialize();
        for (int i = 0; i < Constants.isPoseDetected.length; i++) {
            Constants.isPoseDetected[i] = false;
        }
        parentContext = context;
        isPoseDetection = false;
        CommonFunctions.getInstance().newIntent(parentContext, CameraActivity.class,
                Bundle.EMPTY, false);
        Log.d("sss", String.valueOf(isPoseDetection));
        Log.d("sss", String.valueOf(positionNumber));
    }

    public void StartPoseDetection(Context context, int poseNumber) {
        Initialize();
        parentContext = context;
        this.positionNumber = poseNumber;
        isPoseDetection = true;
        CommonFunctions.getInstance().newIntent(parentContext, CameraActivity.class,
                Bundle.EMPTY, false);
        Log.d("StartPoseDetection", String.valueOf(positionNumber));
        Log.d("StartPoseDetection", String.valueOf(isPoseDetection));
    }


    @Override
    public boolean OnImageProcessed(Camera2BasicFragment.ImageData imageData, float[][] modelDataPoints, Camera2BasicFragment activity) {
        verticalangle = imageData.getVerticalAngle();
        Boolean isDetected = false;
        if (isPoseDetection) {
            Log.d("StartPoseDetection", "messaa");
            switch (positionNumber) {
                case 1:
                    isDetected = CameraPoseDetection.Pose1Detecttion(modelDataPoints);
                    break;
                case 2:
                    isDetected = CameraPoseDetection.Pose2Detecttion(modelDataPoints);
                    break;
                case 3:
                    isDetected = CameraPoseDetection.Pose3Detecttion(modelDataPoints);
                    break;
                case 4:
                    isDetected = CameraPoseDetection.Pose4Detecttion(modelDataPoints);
                    break;
                default:
                    return true;
            }
        } else {
            if (!Constants.isPoseDetected[0]) {
                if (CameraPoseDetection.Pose1Detecttion(modelDataPoints)) {
                    positionNumber = 1;
                    isDetected = true;
                }
            } else {
                if (!Constants.isPoseDetected[1] && CameraPoseDetection.Pose2Detecttion(modelDataPoints)) {
                    positionNumber = 2;
                    isDetected = true;
                } else if (!Constants.isPoseDetected[2] && CameraPoseDetection.Pose3Detecttion(modelDataPoints)) {
                    positionNumber = 3;
                    isDetected = true;
                } else if (!Constants.isPoseDetected[3] && CameraPoseDetection.Pose4Detecttion(modelDataPoints)) {
                    positionNumber = 4;
                    isDetected = true;
                }
            }
        }

        if (!isDetected) {
            detectionCount = 0;
            activity.Message("Please wait");
            return false;
        }
        detectionCount++;
        if (previousPosition != positionNumber) {
            previousPosition = positionNumber;
            detectionCount = 1;
            return false;
        } else {
            if (detectionCount < 3) {
                activity.Message("Please wait");
                //Toast.makeText(parentContext, "Please wait", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                activity.Message("Please wait for processing" + " " + positionNumber);
                //Toast.makeText(parentContext, poseNumber + "capture success", Toast.LENGTH_SHORT).show();
                //Constants.Pose.replace(positionNumber, modelDataPoints); //--- camera pose position
                //Toast.makeText(parentContext, "Please wait", Toast.LENGTH_SHORT).show();
                //poseSaveBitMap();

                /*int width = bm.getWidth();
                int height = bm.getHeight();
                float scaleWidth = ((float) newWidth) / width;
                float scaleHeight = ((float) newHeight) / height;
                // create a matrix for the manipulation
                Matrix matrix = new Matrix();
                // resize the bit map
                matrix.postScale(scaleWidth, scaleHeight);
                // recreate the new Bitmap
                Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
                return resizedBitmap;*/

                Bitmap image = imageData.getImage();
                /*image = Bitmap.createBitmap(image, 0, 0,
                        image.getWidth(), image.getHeight(), new Matrix(), true);*/
                PoseSaveBitMap poseSaveBitMap = new PoseSaveBitMap();
                poseSaveBitMap.execute(new PoseData(positionNumber,
                        getResizedBitmap(image, image.getHeight(), image.getWidth()), activity));
                Constants.noofpostook++;
                Constants.isPoseDetected[positionNumber - 1] = true;
                Constants.posImages[positionNumber - 1] = image;
                if (numberOfPoseUploaded == 3) {
                    CustomProgressDialog.getInstance().show(parentContext);
                }
            }
        }
        if (isPoseDetection) {
            CommonFunctions.getInstance().newIntent(parentContext,
                    PosePreviewActivity.class, Bundle.EMPTY, false);
            activity.getActivity().finish();
            return true;
        }
//        Boolean isallPoseDetected = true;
//        for (int i = 0; i < Constants.isPoseDetected.length; i++) {
//            isallPoseDetected &= Constants.isPoseDetected[i];
//        }
        Log.d("nofpose", String.valueOf(numberOfPoseUploaded));
        if (numberOfPoseUploaded == 4 || positionNumber == 4) {
            Log.d("nofpose", String.valueOf(numberOfPoseUploaded));
            CommonFunctions.getInstance().newIntent(parentContext,
                    PosePreviewActivity.class, Bundle.EMPTY, false);
            activity.getActivity().finish();
        }
        return true;
    }

    public Bitmap getResizedBitmap(Bitmap bitmap, int newHeight, int newWidth) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float scaleX = newWidth / (float) bitmap.getWidth();
        float scaleY = newHeight / (float) bitmap.getHeight();
        float pivotX = 0;
        float pivotY = 0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }

    private class PoseSaveBitMap extends AsyncTask<PoseData, String, PoseData> {
        @Override
        protected PoseData doInBackground(PoseData... poseData) {
            poseData[0].Result = saveBitmap(poseData[0].Image,
                    "pose_" + poseData[0].PoseNumber + ".jpeg", poseData[0].PoseNumber);
            return poseData[0];
        }

        @Override
        protected void onPostExecute(PoseData resData) {
            super.onPostExecute(resData);
            if (resData.Result) {
                Log.d("pose", "Success- " + resData.PoseNumber);
                resData.Activity.Message("Upload Success " + resData.PoseNumber);
                //Toast.makeText(parentContext, "sucess-" + resData.PoseNumber, Toast.LENGTH_LONG).show();
                numberOfPoseUploaded++;
            } else {
                Constants.isPoseDetected[resData.PoseNumber - 1] = true;
                Constants.posImages[resData.PoseNumber - 1] = null;
                Constants.noofpostook--;
                numberOfPoseUploaded--;
                if (numberOfPoseUploaded < 0) numberOfPoseUploaded = 0;
                resData.Activity.Message("retake please");
                //Toast.makeText(parentContext, "retake please", Toast.LENGTH_SHORT).show();
                if (CustomProgressDialog.getInstance().isShowing()) {
                    CustomProgressDialog.getInstance().dismiss();
                }
                Log.d("Camera :", "Upload failed");
            }
        }
    }

    private class PoseData {
        public PoseData(int poseNumber, Bitmap image, Camera2BasicFragment activity) {
            PoseNumber = poseNumber;
            Image = image;
            Activity = activity;
        }

        public Camera2BasicFragment Activity;
        public int PoseNumber = -1;
        public Bitmap Image = null;
        public Boolean Result = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean OnImageProcessedOne(Bitmap image, float[][] modelDataPoints) {
        boolean isDetected = false;
        Log.d("position", position + " ");
        switch (position + 1) {
            case 1:
                isDetected = CameraPoseDetection.Pose1Detecttion(modelDataPoints);
                break;
            case 2:
                isDetected = CameraPoseDetection.Pose2Detecttion(modelDataPoints);
                break;
            case 3:
                isDetected = CameraPoseDetection.Pose3Detecttion(modelDataPoints);
                break;
            case 4:
                isDetected = CameraPoseDetection.Pose4Detecttion(modelDataPoints);
                break;
        }
        if (isDetected) {
            if (detectionCount++ < 2)
                return false;
            detectionCount = 0;
            Constants.FIRST_PAGE_EIGHT = position + 1;
           /* if (Constants.FIRST_PAGE_EIGHT >= 4) {
                Constants.FIRST_PAGE_EIGHT = position;
            }*/
            Constants.noofpostook++;
            Constants.Pose.replace(position, modelDataPoints); //--- camera pose position
            final Bitmap realImage = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), new Matrix(), true);
            new Thread(() -> saveBitmap(realImage, "pose_" + position + ".jpeg", positionNumber)).start();
            //setBitmapactivity(image);
            Log.d("pose", "sucess- " + position);
            Toast.makeText(parentContext, "sucess-" + position, Toast.LENGTH_LONG).show();
            position++;
        } else {
            detectionCount = 0;
        }
        return isDetected;
    }

    private boolean saveBitmap(Bitmap realImage, String filename, int position) {
        boolean results = false;
        try {
            File pictureFile = new File(output_file_name + filename);
            if (pictureFile.exists()) {
                pictureFile.delete();
            }

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            realImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

           /* if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {*/

            FileOutputStream fo = new FileOutputStream(pictureFile);
            fo.write(bytes.toByteArray());
            fo.close();

           /* } else {
                //    Request permission from the user
                ActivityCompat.requestPermissions(parentContext,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

            }*/

            if (positionNumber == 1) {
                imagename = "LEFT_SIDE";
            } else if (positionNumber == 2) {
                imagename = "NECK_LEFT";
                gyroscope_x = "87";
            } else if (positionNumber == 3) {
                imagename = "BACK_POSE";
            } else if (positionNumber == 4) {
                imagename = "NECK_FRONT";
            }
            results =
                    uploadFileHttpsPost(output_file_name + filename, imagename,
                            Constants.login_api_key, Constants.user_id, position, String.valueOf(verticalangle));

            /*result = uploadToServer(output_file_name + filename, imagename,
                    Constants.login_api_key, Constants.user_id, position, String.valueOf(verticalangle));*/
            //CustomProgressDialog.getInstance().show(parentContext);

        } catch (FileNotFoundException e) {
            Log.d("info", "File not found" + e.getMessage());
            Log.d("Info", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("TAG", "Error accessing file: " + e.getMessage());
        }
        return results;
    }

    private boolean uploadToServer(String uploaded_file, String imagename,
                                   String token, String user_id, int position, String gyroscope_x) {
        boolean result = false;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.COMMON)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UploadAPIs retrofitInterface = retrofit.create(UploadAPIs.class);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"),
                uploaded_file + imagename);
        MultipartBody.Part body = MultipartBody.Part.createFormData
                ("image", "image.jpg", requestFile);
        MultipartBody.Part imagename1 = MultipartBody.Part.createFormData
                ("imagename", imagename);
        MultipartBody.Part user_id1 = MultipartBody.Part.createFormData("user_id", user_id);
        MultipartBody.Part gyroscope_x1 = MultipartBody.Part.createFormData
                ("gyroscope_x", gyroscope_x);

        SizeF sf = new SizeF(0, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sf = getCameraResolution(0);
        }
        String[] separated = sf.toString().split("x");
        String width_dim = separated[0]; // this will contain "Fruit"
        String height_dim = separated[1];

        Map<String, String> data = new HashMap<>();
        data.put("focal_length", String.valueOf(parameters.getFocalLength()));
        data.put("h_v_angle", String.valueOf(parameters.getHorizontalViewAngle()));
        data.put("v_v_angle", String.valueOf(parameters.getVerticalViewAngle()));
        data.put("sensor_h_angle", width_dim);
        data.put("sensor_v_angle", height_dim);
        data.put("mobile_name", Build.MODEL);
        data.put("cam_info_last", String.valueOf(position));

        Call<Imageupload> call = retrofitInterface.uploadImage(token, data, body, imagename1, user_id1, gyroscope_x1);

        call.enqueue(new Callback<Imageupload>() {
            @Override
            public void onResponse(Call<Imageupload> call, retrofit2.Response<Imageupload> response) {
                if (!CustomProgressDialog.getInstance().isShowing()) {
                    CustomProgressDialog.getInstance().dismiss();
                }
                if (response.isSuccessful()) {
                    Log.d(TAG, "Response Length: " + response.toString().length());
                    Log.d(TAG, "Response Result: " + response.toString());

                    System.out.println("result after Reading JSON Response");
                    Imageupload responseBody = response.body();
                    System.out.println("result after Reading JSON Response");
                    System.out.println("Error- " + responseBody.getError());
                    System.out.println("Message- " + responseBody.getMessage());
                    /*System.out.println("Error- " + myResponse.getString("error"));
                    System.out.println("Message- " + myResponse.getString("message"));*/

                    File fdelete = new File(uploaded_file);
                    if (fdelete.exists()) {
                        if (fdelete.delete()) {
                            System.out.println("file Deleted :" + uploaded_file);
                        } else {
                            System.out.println("file not Deleted :" + uploaded_file);
                        }
                    }

                    if (responseBody.getError().equals("0")) {
                        if (CustomProgressDialog.getInstance().isShowing()) {
                            CustomProgressDialog.getInstance().dismiss();
                        }
                        results = true;
                        Toast.makeText(parentContext, "Upload success", Toast.LENGTH_SHORT).show();
                        Constants.uploadSuccess = "upload Success";
                    } else if (responseBody.getError().equals("1")) {
                        if (CustomProgressDialog.getInstance().isShowing()) {
                            CustomProgressDialog.getInstance().dismiss();
                        }
                        Toast.makeText(parentContext, "error", Toast.LENGTH_SHORT).show();
                    }

                    /*Response responseBody = response.body();
                    mBtImageShow.setVisibility(View.VISIBLE);
                    mImageUrl = URL + responseBody.getPath();*/
                    //Snackbar.make(findViewById(R.id.content), responseBody.getMessage(), Snackbar.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(parentContext, "error response", Toast.LENGTH_SHORT).show();
                    /*ResponseBody errorBody = response.errorBody();
                    Gson gson = new Gson();
                    try {
                        Imageupload errorResponse = gson.fromJson(errorBody.string(), Imageupload.class);
                        Toast.makeText(parentContext, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        //Snackbar.make(findViewById(R.id.content), errorResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }
            }

            @Override
            public void onFailure(Call<Imageupload> call, Throwable t) {
                if (CustomProgressDialog.getInstance().isShowing()) {
                    CustomProgressDialog.getInstance().dismiss();
                }
                //mProgressBar.setVisibility(View.GONE);
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

        return result;
    }

    private boolean uploadFileHttpsPostSession(String uploaded_file, String imagename,
                                               String token, String user_id, int position, String gyroscope_x) {
        boolean result = false;
        URL url = null;
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        try {
            url = new URL(upLoadServerHttpsUri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();

            // Fetch and set cookies in requests
            CookieManager cookieManager = CookieManager.getInstance();
            String cookie = cookieManager.getCookie(urlConnection.getURL().toString());
            if (cookie != null) {
                urlConnection.setRequestProperty("Cookie", cookie);
            }
            String boundaryString = "----SomeRandomText";
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
            urlConnection.setRequestProperty("authorization", "Bearer " + token);
            urlConnection.connect();
            String resultone = null;

            OutputStream outputStreamToRequestBody = urlConnection.getOutputStream();
            BufferedWriter httpRequestBodyWriter =
                    new BufferedWriter(new OutputStreamWriter(outputStreamToRequestBody));

            SizeF sf = new SizeF(0, 0);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                sf = getCameraResolution(0);
            }

            String[] separated = sf.toString().split("x");

            String width_dim = separated[0]; // this will contain "Fruit"
            String height_dim = separated[1];

            if (positionNumber == 1) {
                httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"information\"");
                httpRequestBodyWriter.write("\n\n");

                JSONObject json1 = new JSONObject();
                json1.put("focal_length", parameters.getFocalLength());
                json1.put("h_v_angle", parameters.getHorizontalViewAngle());
                json1.put("v_v_angle", parameters.getVerticalViewAngle());
                json1.put("sensor_h_angle", width_dim);
                json1.put("sensor_v_angle", height_dim);
                json1.put("mobile_name", Build.MODEL);
                json1.put("cam_info_last", position);
                Log.i("info", "Data : " + json1);
                httpRequestBodyWriter.write(String.valueOf(json1));

                httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"imagename\"");
                httpRequestBodyWriter.write("\n\n");
                httpRequestBodyWriter.write(imagename);

                httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"user_id\"");
                httpRequestBodyWriter.write("\n\n");
                httpRequestBodyWriter.write(user_id);

                httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"gyroscope_x\"");
                httpRequestBodyWriter.write("\n\n");
                httpRequestBodyWriter.write(gyroscope_x);

            } else {
                httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"imagename\"");
                httpRequestBodyWriter.write("\n\n");
                httpRequestBodyWriter.write(imagename);

                httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"user_id\"");
                httpRequestBodyWriter.write("\n\n");
                httpRequestBodyWriter.write(user_id);

                httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"gyroscope_x\"");
                httpRequestBodyWriter.write("\n\n");
                httpRequestBodyWriter.write(gyroscope_x);


            }
            File uploadFile = new File(uploaded_file);
            FileInputStream fileInputStream = new FileInputStream(uploadFile);

            String fileUrl = uploaded_file;

            File logFileToUpload = new File(fileUrl);


            httpRequestBodyWriter.write("\n--" + boundaryString + "\n");
            httpRequestBodyWriter.write("Content-Disposition: form-data;"
                    + "name=\"uploaded_file\";"
                    + "filename=\"" + logFileToUpload.getName() + "\""
                    + "\nContent-Type: text/plain\n\n");
            httpRequestBodyWriter.flush();

            FileInputStream inputStreamToLogFile = new FileInputStream(uploaded_file);
            byte[] dataBuffer = new byte[1024];
            while ((bytesRead = inputStreamToLogFile.read(dataBuffer)) != -1) {
                outputStreamToRequestBody.write(dataBuffer, 0, bytesRead);
            }

            outputStreamToRequestBody.flush();
            httpRequestBodyWriter.write("\n--" + boundaryString + "--\n");
            httpRequestBodyWriter.flush();
            int maxBufferSize = 1 * 1024 * 1024;
            // Close the streams
            //outputStreamToRequestBody.close();
            //httpRequestBodyWriter.close();
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                // dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
            int serverResponseCode = 0;

            serverResponseCode = urlConnection.getResponseCode();
            String serverResponseMessage = urlConnection.getResponseMessage();

            resultone = urlConnection.getHeaderField("Set-Cookie");
            Log.i("resultone", "resultone : " + resultone);

            Log.i("uploadFile", "HTTP Response is : "
                    + serverResponseMessage + ": " + serverResponseCode);

            if (serverResponseCode == 200) {
                if (CustomProgressDialog.getInstance().isShowing()) {
                    CustomProgressDialog.getInstance().dismiss();
                }
                Log.i("Response", "Response : " + urlConnection);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                Log.d(TAG, "Response Length: " + response.toString().length());
                Log.d(TAG, "Response Result: " + response.toString());


                JSONObject myResponse = new JSONObject(response.toString());
                // String[] res_str = myResponse.getString("result").split("#");
                System.out.println("result after Reading JSON Response");
                System.out.println("Error- " + myResponse.getString("error"));
                System.out.println("Message- " + myResponse.getString("message"));
                //System.out.println("Result- " + myResponse.getString("result"));
                //Constants.claiduploadresult = myResponse.getString("result");
                if (myResponse.getString("error").equals("0")) {
                    if (CustomProgressDialog.getInstance().isShowing()) {
                        CustomProgressDialog.getInstance().dismiss();
                    }
                    result = true;
                    Toast.makeText(parentContext, "Upload success", Toast.LENGTH_SHORT).show();
                    Constants.uploadSuccess = "upload Success";
                    //poseConfirm();


                }

                Constants.vid_cam = 1;

                File fdelete = new File(uploaded_file);
                if (fdelete.exists()) {
                    if (fdelete.delete()) {
                        System.out.println("file Deleted :" + uploaded_file);
                    } else {
                        System.out.println("file not Deleted :" + uploaded_file);
                    }
                }

                if (myResponse.getString("error").equals("1")) {
                    CustomProgressDialog.getInstance().dismiss();
                    Toast.makeText(parentContext, "please retake", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(parentContext, "Please Try again", Toast.LENGTH_SHORT).show();
            }

            Log.i("uploadFile", "HTTP Response is : "
                    + serverResponseMessage + ": " + serverResponseCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;

    }

    private boolean uploadFileHttpsPost(String uploaded_file, String imagename,
                                        String token, String user_id, int position, String gyroscope_x) {

        Log.d(TAG, "uploaded_file: " + uploaded_file);
        Log.d(TAG, "imagename: " + imagename);
        Log.d(TAG, "token: " + token);
        Log.d(TAG, "user_id: " + user_id);
        Log.d(TAG, "gyroscope_x: " + gyroscope_x);

        boolean results = false;
        HttpURLConnection conn = null;
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File uploadFile = new File(uploaded_file);

        if (!uploadFile.isFile()) {
            //Toast.makeText(parentContext, "retake please", Toast.LENGTH_SHORT).show();
        } else {
            int serverResponseCode = 0;
            try {
                String boundaryString = "----SomeRandomText";
                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(uploadFile);
                URL url = new URL(upLoadServerHttpsUri);
                String fileUrl = uploaded_file;
                File logFileToUpload = new File(fileUrl);

                Log.d(TAG, "API URL: " + url);
                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();

                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
                conn.setRequestProperty("authorization", "Bearer " + token);
                OutputStream outputStreamToRequestBody = conn.getOutputStream();
                BufferedWriter httpRequestBodyWriter =
                        new BufferedWriter(new OutputStreamWriter(outputStreamToRequestBody));

                SizeF sf = new SizeF(0, 0);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    sf = getCameraResolution(0);
                }

                String[] separated = sf.toString().split("x");

                String width_dim = separated[0]; // this will contain "Fruit"
                String height_dim = separated[1];

                if (positionNumber == 1) {
                    httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                    httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"information\"");
                    httpRequestBodyWriter.write("\n\n");

                    JSONObject json1 = new JSONObject();
                    json1.put("focal_length", parameters.getFocalLength());
                    json1.put("h_v_angle", parameters.getHorizontalViewAngle());
                    json1.put("v_v_angle", parameters.getVerticalViewAngle());
                    json1.put("sensor_h_angle", width_dim);
                    json1.put("sensor_v_angle", height_dim);
                    json1.put("mobile_name", Build.MODEL);
                    json1.put("cam_info_last", position);
                    //System.out.println("json1: " + json1);git git rm -rf --cached .
                    Log.i("info", "Data : " + json1);
                    httpRequestBodyWriter.write(String.valueOf(json1));

                    httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                    httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"imagename\"");
                    httpRequestBodyWriter.write("\n\n");
                    httpRequestBodyWriter.write(imagename);

                    httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                    httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"user_id\"");
                    httpRequestBodyWriter.write("\n\n");
                    httpRequestBodyWriter.write(user_id);

                    httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                    httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"gyroscope_x\"");
                    httpRequestBodyWriter.write("\n\n");
                    httpRequestBodyWriter.write(gyroscope_x);

                } else {
                    httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                    httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"imagename\"");
                    httpRequestBodyWriter.write("\n\n");
                    httpRequestBodyWriter.write(imagename);

                    httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                    httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"user_id\"");
                    httpRequestBodyWriter.write("\n\n");
                    httpRequestBodyWriter.write(user_id);

                    httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                    httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"gyroscope_x\"");
                    httpRequestBodyWriter.write("\n\n");
                    httpRequestBodyWriter.write(gyroscope_x);

                   /* httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                    httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"information\"");
                    httpRequestBodyWriter.write("\n\n");*/
                    //System.out.println("No information passed");
                }



                /*String infoData = parameters.getFocalLength() + "#" + parameters.getHorizontalViewAngle()
                        + "#" + parameters.getVerticalViewAngle() + "#" + width_dim + "#" + height_dim
                        + "#" + Build.MODEL + "#" + "" + position + "#" + Constants.child_id + "#" + Constants.pose_height + "#" + Constants.child_id;
                httpRequestBodyWriter.write(infoData);

                Log.i("info", "Data : "
                        + infoData);

                Constants.claidlastinformation = infoData;*/


                httpRequestBodyWriter.write("\n--" + boundaryString + "\n");
                httpRequestBodyWriter.write("Content-Disposition: form-data;"
                        + "name=\"uploaded_file\";"
                        + "filename=\"" + logFileToUpload.getName() + "\""
                        + "\nContent-Type: text/plain\n\n");
                httpRequestBodyWriter.flush();

                FileInputStream inputStreamToLogFile = new FileInputStream(uploaded_file);
                byte[] dataBuffer = new byte[1024];
                while ((bytesRead = inputStreamToLogFile.read(dataBuffer)) != -1) {
                    outputStreamToRequestBody.write(dataBuffer, 0, bytesRead);
                }

                outputStreamToRequestBody.flush();
                httpRequestBodyWriter.write("\n--" + boundaryString + "--\n");
                httpRequestBodyWriter.flush();

                // Close the streams
                outputStreamToRequestBody.close();
                httpRequestBodyWriter.close();
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {
                    // dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }


                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();
                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if (serverResponseCode == 200) {
                    if (CustomProgressDialog.getInstance().isShowing()) {
                        CustomProgressDialog.getInstance().dismiss();
                    }
                    Log.i("Response", "Response : " + conn);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    Log.d(TAG, "Response Length: " + response.toString().length());
                    Log.d(TAG, "Response Result: " + response.toString());


                    JSONObject myResponse = new JSONObject(response.toString());
                    // String[] res_str = myResponse.getString("result").split("#");
                    System.out.println("result after Reading JSON Response");
                    System.out.println("Error- " + myResponse.getString("error"));
                    System.out.println("Message- " + myResponse.getString("message"));
                    //System.out.println("Result- " + myResponse.getString("result"));
                    //Constants.claiduploadresult = myResponse.getString("result");
                    if (myResponse.getString("error").equals("0")) {
                        if (CustomProgressDialog.getInstance().isShowing()) {
                            CustomProgressDialog.getInstance().dismiss();
                        }
                        results = true;
                        Toast.makeText(parentContext, "Upload success", Toast.LENGTH_SHORT).show();
                        String requestType = "";
//------here to check
                        if (Constants.noofpostook == 3) {
                            if (results) {
                                poseConfirm();
                            }
                        }

                    }

                    Constants.vid_cam = 1;
                    File fdelete = new File(uploaded_file);
                    if (fdelete.exists()) {
                        if (fdelete.delete()) {
                            System.out.println("file Deleted :" + uploaded_file);
                        } else {
                            System.out.println("file not Deleted :" + uploaded_file);
                        }
                    }

                    if (myResponse.getString("error").equals("1")) {
                        CustomProgressDialog.getInstance().dismiss();
                        Toast.makeText(parentContext, "please retake", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(parentContext, "Please Try again", Toast.LENGTH_SHORT).show();
                }
                fileInputStream.close();

            } catch (MalformedURLException ex) {
                ex.printStackTrace();
                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } // End else block
        return results;
    }

    private void poseConfirm() {
        StringRequest request = new StringRequest(StringRequest.Method.POST, "" +
                Urls.claidorderconfirm, response -> {
            try {
                orderConfirm_json("[" + response + "]");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> System.out.println("error pose page")) {

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", " Bearer " + Constants.login_api_key);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", Constants.user_id);
                params.put("fabric_order_id", fabricId);
                /*if (positionNumber == 3) {
                    params.put("requestType", "2");
                } else*/
                if (positionNumber == 4) {
                    //params.put("requestType", "3");
                    params.put("requestType", "1");
                } /*else if (!results) {
                    params.put("requestType", "1");
                }*/
                Log.d("params", params.toString());
                return params;
            }
        };

        Volley.newRequestQueue(parentContext).add(request);
    }

    private void orderConfirm_json(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        error = new String[jsonArray.length()];
        message = new String[jsonArray.length()];
        orderId = new String[jsonArray.length()];
        result = new String[jsonArray.length()];
        Log.d("confirm :", String.valueOf(jsonArray));
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            error[i] = object.getString("error");
            message[i] = object.getString("message");
            orderId[i] = object.getString("order_id");
            result[i] = object.getString("result");
        }

        try {
            Constants.pm_OrderId = orderId[0];
            if (error[0].equals("0")) {
                //Message
                CommonFunctions.getInstance().validationInfoError(
                        parentContext, String.valueOf(message[0]));
                //-- Next page
                CommonFunctions.getInstance().newIntent(parentContext,
                        MeasurementDetailsActivity.class, Bundle.EMPTY, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SizeF getCameraResolution(int camNum) {
        SizeF size = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            size = new SizeF(0, 0);
        }
        CameraManager manager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            manager = (CameraManager) parentContext.getSystemService(Context.CAMERA_SERVICE);
        }
        try {
            String[] cameraIds = new String[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cameraIds = manager.getCameraIdList();
            }
            if (cameraIds.length > camNum) {
                CameraCharacteristics character = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    character = manager.getCameraCharacteristics(cameraIds[camNum]);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    size = character.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE);
                }
            }
        } catch (CameraAccessException e) {
            Log.e("YourLogString", e.getMessage(), e);
        }
        return size;
    }

    void log() {

        Log.d("zoso_cam3.java", ":" + " void log ()  function calling....");

        StringRequest request = new StringRequest(StringRequest.Method.POST, ""
                + Urls.getcarousel, response -> {
            /*try {
                log_json(response);

            } catch (JSONException e) {

                e.printStackTrace();
            }*/


        }, error -> Toast.makeText(parentContext, "?????????", Toast.LENGTH_SHORT).show()) {
            /* Body value from service url session is 21 */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", Constants.user_id);

                return params;
            }

            /*Headers value from service url Authorization is : eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImMxZjY1....*/
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", " Bearer " + Constants.login_api_key);
                return headers;
            }
        };
        Volley.newRequestQueue(parentContext).add(request);

    }

    private void log_json(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);
        String[] error = new String[jsonArray.length()];
        String[] pos_name = new String[jsonArray.length()];
        Constants.cro_pose = new String[jsonArray.length()];
        String[] file = new String[jsonArray.length()];
        String[] date = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject obj = jsonArray.getJSONObject(i);

            pos_name[i] = obj.getString("name");
            file[i] = obj.getString("file");
            /*Matching the Constants string name with service file name*/
            Constants.cro_pose[i] = obj.getString("file");
            Log.d("cor", ":" + obj.getString("file"));

            // pager.setVisibility(View.VISIBLE);

        }

        // pager.setVisibility(View.VISIBLE);

    }
}
