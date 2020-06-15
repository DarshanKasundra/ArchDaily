package com.aswdc.archdaily.api;

import com.aswdc.archdaily.models.ApiResponseWhitoutResData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileUploadService {
    @Multipart
    @POST("upload")
    Call<ApiResponseWhitoutResData> upload(
            @Field("user_id") RequestBody UserID,
            @Field("event_id") RequestBody EventID,
            @Part MultipartBody.Part main_file,
            @Part MultipartBody.Part sub_file

    );
}