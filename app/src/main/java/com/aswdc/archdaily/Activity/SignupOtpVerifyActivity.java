package com.aswdc.archdaily.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aswdc.archdaily.R;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.models.ApiResponseWhitoutResData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupOtpVerifyActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnnext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup_verify_otp );
        btnnext = findViewById( R.id.btnnext );
        btnnext.setOnClickListener( (View.OnClickListener) this );
        VerifyOtp();
    }
    public void VerifyOtp(){
        int userId=getIntent().getIntExtra( "user_id",0);
        int OTP=getIntent().getIntExtra( "otp",0);

        Api api = RetrofitClient.getApi().create( Api.class );
        Call<ApiResponseWhitoutResData> call = api.VerifyOtp( userId , OTP,"email" );
        call.enqueue( new Callback<ApiResponseWhitoutResData>() {
            @Override
            public void onResponse(Call<ApiResponseWhitoutResData> call, Response<ApiResponseWhitoutResData> response) {
                Log.d( "userid",""+userId );

            }

            @Override
            public void onFailure(Call<ApiResponseWhitoutResData> call, Throwable t) {

            }
        } );

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnnext:

                break;

        }
    }
}
