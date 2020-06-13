package com.aswdc.archdaily.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.aswdc.archdaily.MainNavActivity;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.storage.SharedPrefManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_splash);
        SharedPrefManager sfm = SharedPrefManager.getInstance(getApplicationContext());
        if(sfm.isLoggedIn())
        {
            startActivity( new Intent( SplashActivity.this, MainNavActivity.class) );
            finish();
        }else
        {
            startActivity( new Intent( SplashActivity.this, LoginActivity.class) );
            finish();
        }
    }
}
