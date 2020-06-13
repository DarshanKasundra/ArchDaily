package com.aswdc.archdaily.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.mbms.MbmsErrors;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aswdc.archdaily.R;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponseWhitoutResData;
import com.aswdc.archdaily.models.EventDetail;
import com.aswdc.archdaily.models.ProfileDetail;
import com.aswdc.archdaily.storage.SharedPrefManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDiscriptionActivity extends AppCompatActivity{
    Context context;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.pop_event_description );
        //  set Toolbar  //
//        Toolbar toolbar;
//        TextView toolbartext;
//        toolbar = findViewById( R.id.toolbar );
//        toolbartext = findViewById( R.id.toolbartext );
//        toolbartext.setText( "Event Discription" );



//        set Pop Activity
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics( displayMetrics );

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout( (int) (width * .8), (int) (height * .6) );

        intdiscription();
    }
    void intdiscription(){

    }
}
