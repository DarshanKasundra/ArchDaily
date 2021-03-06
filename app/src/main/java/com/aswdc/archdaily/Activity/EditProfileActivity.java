package com.aswdc.archdaily.Activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.aswdc.archdaily.Fragment.NavProfileFragment;
import com.aswdc.archdaily.MainNavActivity;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.models.ProfileDetail;
import com.aswdc.archdaily.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener  {
    SharedPrefManager sfm = SharedPrefManager.getInstance(getApplication());
    ProfileDetail pd = sfm.getUser();
    private EditText editTextEmail, editTextName, editTextPhoneNumber,editTextCity;
    private EditText editTextCurrentPassword, editTextNewPassword;
            Button btnLogout;
            TextView toolbartextDone;
            RelativeLayout relativeLayoutprofile;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.edit_profile_activity );
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextName = findViewById(R.id.editTextName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextCity = findViewById(R.id.editTextCity);
        btnLogout = findViewById(R.id.btnLogout);
        relativeLayoutprofile = findViewById( R.id.relativeLayoutprofile );




        Toolbar toolbar;
        toolbar=  findViewById( R.id.toolbar);
        toolbartextDone=  findViewById( R.id.toolbartextDone);
        toolbartextDone.setText( "Done" );

        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( "update" );
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );





        editTextName.setText( pd.getName() );
        editTextPhoneNumber.setText( pd.getMobile() );
        editTextCity.setText( pd.getCity() );
        editTextEmail.setText( pd.getEmail() );
        toolbartextDone.setOnClickListener( this );

//        buttonSave.setOnClickListener(this);

    }

     void updateProfile() {



        String name = editTextName.getText().toString().trim();
        String mobile = editTextPhoneNumber.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();


//        if (email.isEmpty()) {
//            editTextEmail.setError("Email is required");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            editTextEmail.setError("Enter a valid email");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if (name.isEmpty()) {
//            editTextName.setError("Name required");
//            editTextName.requestFocus();
//            return;
//        }


        ProfileDetail pd = SharedPrefManager.getInstance(getApplicationContext()).getUser();

//        ProfileDetail pd = sfm.getI.getUser();

        Api api = RetrofitClient.getApi().create(Api.class);
        Call<ApiResponse> call = api.EditeUser( pd.getUserId(),name ,mobile,email,city );


        call.enqueue( new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                Toast.makeText(context, response.body().getResCode(), Toast.LENGTH_LONG).show();
                Log.d("test",""+pd.getUserId());
//                    SharedPrefManager.getInstance(context).saveUser(response.body().getResData().getProfileDetails().get( 0 ));
                    ApiResponse apiResponse = response.body();
                    if (response.body().getResCode() == 1) {
//                        editTextName.setText(  eventDetails.get(0).getFees() );
                        sfm = SharedPrefManager.getInstance( getApplicationContext() );
                        sfm.saveUser( apiResponse.getResData().getProfileDetails().get( 0 ) );
                        Toast.makeText(EditProfileActivity.this, apiResponse.getResMessage(), Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().add( R.id.fragment_container ,new NavProfileFragment()).commit();

//                        getSupportFragmentManager().popBackStack();



                    }
                    else {
                        Toast.makeText(EditProfileActivity.this, apiResponse.getResMessage(), Toast.LENGTH_LONG).show();
                    }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "faild", Toast.LENGTH_LONG).show();

            }
        } );


    }


//    private void logout() {
//        SharedPrefManager.getInstance(getActivity()).clear();
//        Intent intent = new Intent(getActivity(), LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }
//
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbartextDone:
                updateProfile();
                break;

        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return super.onOptionsItemSelected( item );
            default:
                return super.onOptionsItemSelected( item );
        }
    }
}