package com.example.api;

import com.example.model.Imageupload;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

public interface UploadAPIs {
    @Multipart
    @POST("/upload")
    Call<Imageupload>
    uploadImage(@Header("Authorization") String token,
                @QueryMap Map<String, String> information,
                @Part MultipartBody.Part uploaded_file,
                @Part MultipartBody.Part imagename,
                @Part MultipartBody.Part user_id,
                @Part MultipartBody.Part gyroscope_x);
}