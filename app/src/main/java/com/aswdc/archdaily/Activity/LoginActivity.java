package com.aswdc.archdaily.Activity;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aswdc.archdaily.MainNavActivity;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.storage.SharedPrefManager;

//import net.simplifiedcoding.retrofitandroidtutorial.R;
//import net.simplifiedcoding.retrofitandroidtutorial.api.RetrofitClient;
//import net.simplifiedcoding.retrofitandroidtutorial.models.LoginResponse;
//import net.simplifiedcoding.retrofitandroidtutorial.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.ProgressDialog.show;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editTextEmail;
    private EditText editTextPassword;
    private SharedPrefManager sfm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        editTextEmail = findViewById( R.id.editTextEmail );
        editTextPassword = findViewById( R.id.editTextPassword );

        findViewById( R.id.cirLoginButton ).setOnClickListener( this );
        findViewById( R.id.textViewRegister ).setOnClickListener( this );
    }
    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance( this ).isLoggedIn()) {
            Intent intent = new Intent( this, MainNavActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity( intent );
            finish();
        }
    }

    private void userLogin() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError( "Email is required" );
            editTextEmail.requestFocus();
            return;
        }

//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            editTextEmail.setError("Enter a valid email");
//            editTextEmail.requestFocus();
//            return;
//        }

        if (password.isEmpty()) {
            editTextPassword.setError( "Password required" );
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 8) {
            editTextPassword.setError( "Password should be atleast 8 character long" );
            editTextPassword.requestFocus();
            return;
        }

        Api api = RetrofitClient.getApi().create( Api.class );
        Call<ApiResponse> call = api.login( email, password );


        call.enqueue( new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse apiResponse = response.body();

                if (apiResponse.getResCode() == 1) {
                    Log.d( "Dk", "" + apiResponse.getResMessage() );
                    Toast.makeText( LoginActivity.this, apiResponse.getResMessage(), Toast.LENGTH_LONG ).show();
                    sfm = SharedPrefManager.getInstance( getApplicationContext() );
                    sfm.saveUser( apiResponse.getResData().getProfileDetails().get( 0 ) );

                    Intent intent = new Intent( LoginActivity.this, MainNavActivity.class );
                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    startActivity( intent );
                } else {
                    Toast.makeText( LoginActivity.this, apiResponse.getResMessage(), Toast.LENGTH_LONG ).show();

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d( "Login", "" + t.getLocalizedMessage() );

                Toast.makeText( LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG ).show();

            }
        } );

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cirLoginButton:
                userLogin();
                break;
            case R.id.textViewRegister:
                startActivity( new Intent( this, EmailSignupActivity.class ) );
                break;
        }
    }
}
