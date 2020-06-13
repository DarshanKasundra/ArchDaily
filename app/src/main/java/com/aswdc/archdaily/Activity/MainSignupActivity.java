package com.aswdc.archdaily.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.aswdc.archdaily.R;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.storage.SharedPrefManager;


import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public class MainSignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName,editTextMobileNo,editTextEmail,editTextPassword,editTextCompanyName,
    editTextProfession , editTextAddress , editTextCity ,editTextState, editTextCountry ,editTextWeburl, editTextPinCode ,editTextConfirmPassword, textViewLogin;
    private RadioButton radiobuttonFmale , radiobuttonMale ;
    private CheckBox checkboxTnC, checkboxPnP ;
    private Button buttonSignup;
    private RadioGroup Radiogender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        editTextName = findViewById(R.id.editTextName);
        editTextMobileNo = findViewById(R.id.editTextMobileNo);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
//        radiobuttonFmale = findViewById(R.id.radiobuttonMale);
//        radiobuttonFmale = findViewById(R.id.radiobuttonFmale);
//        editTextCompanyName = findViewById(R.id.editTextCompanyName);
//        editTextWeburl = findViewById(R.id.editTextWeburl);
//        editTextProfession = findViewById(R.id.editTextProfession);
//        editTextAddress = findViewById(R.id.editTextAddress);
        editTextCity = findViewById(R.id.editTextCity);
        editTextState = findViewById(R.id.editTextState);
        editTextCountry = findViewById(R.id.editTextCountry);
        editTextPinCode = findViewById(R.id.editTextPinCode);
        checkboxTnC = findViewById(R.id.checkboxTnC);
        checkboxPnP = findViewById(R.id.checkboxPnP);
//        Radiogender = findViewById(R.id.Radiogender);
//        buttonSignup = findViewById( R.id.buttonSignup );


        findViewById(R.id.buttonSignup).setOnClickListener(  this );
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userSignUp() {
        String name = editTextName.getText().toString().trim();
        String mobile =  editTextMobileNo.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String ConfirmPassword = editTextConfirmPassword.getText().toString().trim();
//        String gender = Radiogender.toString().trim();
//        String company_name = editTextCompanyName.getText().toString().trim();
//        String web_url = editTextWeburl.getText().toString().trim();
//        String profession = editTextProfession.getText().toString().trim();
//        String address = editTextAddress.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();
        String state = editTextState.getText().toString().trim();
        String country = editTextCountry.getText().toString().trim();
        String pin_code = editTextPinCode.getText().toString().trim();



        if (name.isEmpty()) {
            editTextName.setError("Name required");
            return;
        }
        if(isEmpty(mobile)){
            editTextMobileNo.setError("Enter Mobile No.");
            return ;
        }
        if (!isValidMobile(mobile))
        {
            editTextMobileNo.setError("Enter valid Mobile No.");
            editTextMobileNo.requestFocus();
            return ;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 8) {
            editTextPassword.setError("Password should be atleast 8 character long");
            editTextPassword.requestFocus();
            return;
        }
        if(isEmpty(ConfirmPassword) || !ConfirmPassword.equals(password))
        {
            editTextConfirmPassword.setError("Password and Confirm Password are dose't match ");
            return;
        }

        if (pin_code.length() != 6) {
            editTextPinCode.setError("Enter Valid PinCode");
            editTextPinCode.requestFocus();
            return;
        }

//        if (!checkboxTnC.isChecked()){
//            checkboxTnC.setError( "please Agree T&c" );
//            checkboxTnC.requestFocus();
//            return;
//        }
//        if ( !checkboxPnP.isChecked()){
//            checkboxPnP.setError( "please Agree P&P" );
//            checkboxPnP.requestFocus();
//            return;
//        }


//        if (ConfirmPassword.isEmpty() && password==ConfirmPassword) {
//            editTextPassword.setError("Password required");
//            editTextPassword.requestFocus();
//            return;
//        }




        Api api = RetrofitClient.getApi().create( Api.class);
        Call<ApiResponse> call = api.signup(name,mobile,email, password,city,state,country,pin_code);



        call.enqueue( new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Toast.makeText( MainSignupActivity.this, response.body().getResMessage(), Toast.LENGTH_LONG).show();

                if (response.code() == 1)
                {
                    Toast.makeText( MainSignupActivity.this, response.body().getResMessage(), Toast.LENGTH_LONG).show();
                }

                else if (response.code() == 0)
                {
                    Toast.makeText( MainSignupActivity.this, response.body().getResMessage() , Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent( MainSignupActivity.this, LoginActivity.class);
                intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Hello",""+t.getLocalizedMessage());
                    Toast.makeText( MainSignupActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

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
            case R.id.buttonSignup:
                userSignUp();
                break;
            case R.id.textViewLogin:

                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }
}
