package com.aswdc.archdaily.api;

//import com.aswdc.archdaily.api.models.DefaultResponse;
//import net.simplifiedcoding.retrofitandroidtutorial.models.LoginResponse;
//import net.simplifiedcoding.retrofitandroidtutorial.models.UsersResponse;

import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.models.ApiResponseWhitoutResData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {

    @FormUrlEncoded
    @POST("CheckMailExists")
    Call<ApiResponseWhitoutResData> getCheckMailExists(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("CheckMoileExists")
    Call<ApiResponseWhitoutResData> getCheckMoileExists(
            @Field("mobile") String mobile
    );

    @FormUrlEncoded
    @POST("CheckUsernameExists")
    Call<ApiResponseWhitoutResData> CheckUsernameExists(
            @Field("user_name") String UsreName
    );
    @FormUrlEncoded
    @POST("getOtp")
    Call<ApiResponse> getOtp(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("VerifyOtp")
    Call<ApiResponseWhitoutResData> VerifyOtp(
            @Field("user_id") int UserId,
            @Field("otp") int OTP,
            @Field("otp_for") String OtpFor
    );
    @FormUrlEncoded
    @POST("signup")
    Call<ApiResponse> signup(
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("email") String email,
            @Field("password") String password
//            @Field("gender") String gender,
//            @Field("company_name") String company_name,
//            @Field("web_url") String web_url,
//            @Field("profession") String profession,
//            @Field("address") String address,
//            @Field("city") String city,
//            @Field("state") String state,
//            @Field("country") String country,
//            @Field("pin_code") String pin_code
    );





    @FormUrlEncoded
    @POST("login")
    Call<ApiResponse> login(
            @Field("email_or_phones") String email,
            @Field("password") String password
    );


    @POST("listOfEvent")
    Call<ApiResponse> geteventlists();


    @FormUrlEncoded
    @POST("eventDetail")
    Call<ApiResponse> geteventDetail(
            @Field("event_id") int eventId
    );



    @FormUrlEncoded
    @POST("getUserFiles")
    Call<ApiResponse> getUserFiles(
            @Field("user_id") int UserID
    );

    @FormUrlEncoded
    @POST("addToMapping")
    Call<ApiResponseWhitoutResData> getJoinUserInEvent(
            @Field("event_id") int eventId,
            @Field("user_id") int userId

    );

    @Multipart
    @POST("uploadFile")
    Call<ApiResponseWhitoutResData>uplodeFile(
            @Part("user_id") RequestBody UserID,
            @Part("event_id") RequestBody EventID,
            @Part MultipartBody.Part main_file,
            @Part MultipartBody.Part sub_file

    );
//    @Multipart
//    @POST("addSubFile")
//    Call<ApiResponseWhitoutResData>uplodeFile(
//            @Part("user_id") RequestBody UserID,
//            @Part("event_id") RequestBody EventID,
//            @Part MultipartBody.Part sub_file
//
//
//    );

    @FormUrlEncoded
    @POST("editUser")
    Call<ApiResponse> EditeUser(
            @Field("user_id") int user_id,
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("email") String email,
            @Field("city") String city

    );

    @FormUrlEncoded
    @POST("addVote")
    Call<ApiResponse> addVote(
            @Field("voter_id") String voterID,
            @Field("to_vote_id") String ToVoteID,
            @Field("event_id") String EventID,
            @Field("vote") int Vote
    );


    @FormUrlEncoded
    @POST("listOfUserdetailOfEvent")
    Call<ApiResponse> getlistOfUserdetailOfEvent(
            @Field("event_id") int EventID,
            @Field("user_id") int user_id
    );
    @POST("allSubFiles")
    Call<ApiResponse> getallsubfile();

    @POST("listOfAllWinner")
    Call<ApiResponse> getlistOfAllWinner();

    @FormUrlEncoded
    @POST("userWinningList")
    Call<ApiResponse> getuserWinningList(
            @Field("user_id") int user_id
    );
}
