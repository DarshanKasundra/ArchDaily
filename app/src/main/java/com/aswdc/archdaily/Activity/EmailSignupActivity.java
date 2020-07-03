package com.aswdc.archdaily.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aswdc.archdaily.R;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.models.ApiResponseWhitoutResData;
import com.aswdc.archdaily.models.EventDetail;
import com.aswdc.archdaily.models.ResData;

import java.util.ArrayList;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public class EmailSignupActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail,editTextPhoneNumber;
    Button btnnext;
    String email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_email_signup );

        editTextEmail = findViewById( R.id.editTextEmail );
        editTextPhoneNumber = findViewById( R.id.editTextPhoneNumber );
        btnnext = findViewById( R.id.btnnext );
        btnnext.setOnClickListener( this );
    }
    public void CheckMailExists(){
        this.email = editTextEmail.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError( "Email is required" );
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        Api api = RetrofitClient.getApi().create( Api.class );
        Call<ApiResponseWhitoutResData> call = api.getCheckMailExists( email );
        call.enqueue( new Callback<ApiResponseWhitoutResData>() {
            @Override
            public void onResponse(Call<ApiResponseWhitoutResData> call, Response<ApiResponseWhitoutResData> response) {
                if (response.body().getResCode() == 1){
                    CheckMoileExists();
                    Toast.makeText( EmailSignupActivity.this,response.body().getResMessage(), Toast.LENGTH_LONG).show();
                }
                else {
                          Toast.makeText( EmailSignupActivity.this, response.body().getResMessage(), Toast.LENGTH_LONG ).show();

                }
            }
            @Override
            public void onFailure(Call<ApiResponseWhitoutResData> call, Throwable t) {

            }
        } );
    }

    public void CheckMoileExists(){
        String mobile = editTextPhoneNumber.getText().toString().trim();

        if(isEmpty(mobile)){
            editTextPhoneNumber.setError("Enter Mobile No.");
            return ;
        }
        if (!isValidMobile(mobile))
        {
            editTextPhoneNumber.setError("Enter valid Mobile No.");
            editTextPhoneNumber.requestFocus();
            return ;
        }
        Api api = RetrofitClient.getApi().create( Api.class );
        Call<ApiResponseWhitoutResData> call = api.getCheckMoileExists( mobile );

        call.enqueue( new Callback<ApiResponseWhitoutResData>() {
            @Override
            public void onResponse(Call<ApiResponseWhitoutResData> call, Response<ApiResponseWhitoutResData> response) {
                if (response.body().getResCode() == 1){
                    Intent intent = new Intent( EmailSignupActivity.this, SignupUserNameActivity.class);
                    intent.putExtra( "mobile", mobile);
                    intent.putExtra( "email", email);
                    Log.d( "mobilenumber::1::",""+mobile );
                    Log.d( "email::1::",""+email );

                    startActivity( intent );
                    Toast.makeText( EmailSignupActivity.this,response.body().getResMessage(),Toast.LENGTH_LONG ).show();
                }
                else {
                    Toast.makeText( EmailSignupActivity.this,response.body().getResMessage(),Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseWhitoutResData> call, Throwable t) {

            }
        } );
    }


    private boolean isValidMobile(String phone) {
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() == 10 ;
        }
        return false;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnnext:
                CheckMailExists();
                break;

        }
    }
}
