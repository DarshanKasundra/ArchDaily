package com.aswdc.archdaily.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.models.ApiResponseWhitoutResData;
import com.aswdc.archdaily.models.EventDetail;
import com.aswdc.archdaily.models.ProfileDetail;
import com.aswdc.archdaily.storage.SharedPrefManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopActivity extends AppCompatActivity implements View.OnClickListener {
    Button choose_image,choose_DWG;
    ImageView view_image;
    TextView view_Dwg_File;
    Intent stroge;
    String file_path=null;
    private static final int PICK_IMAGE = 1;
    private static final int PICK_IMAGE2 = 2;
    Context context;
    Button btnUplode;
    Uri imgpath;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout
                .pop_window );
        //  set Toolbar  //
        Toolbar toolbar;
        TextView toolbartext;
        toolbar = findViewById( R.id.toolbar );
        toolbartext = findViewById( R.id.toolbartext );
        toolbartext.setText( "Upload Images & Files" );


//        find id for pick image

        choose_image = findViewById( R.id.choose_image );
        view_image = findViewById( R.id.view_image );

        choose_DWG = findViewById( R.id.choose_DWG );
        view_Dwg_File = findViewById( R.id.view_Dwg_File );

        btnUplode = findViewById( R.id.btnUplode );


        choose_image.setOnClickListener( this );
        choose_DWG.setOnClickListener( this );
        btnUplode.setOnClickListener( this );

//        pickimage2.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent gallery = new Intent();
//                gallery.setType( "image/*" );
//                gallery.setAction( Intent.ACTION_GET_CONTENT );
//                startActivityForResult( Intent.createChooser( gallery, "sellect picture" ), PICK_IMAGE2 );
//            }
//        } );
//        set Pop Activity
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics( displayMetrics );

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout( (int) (width * .8), (int) (height * .6) );

    }


        private void selectImage(){
        Intent gallery = new Intent();
        gallery.setType( "image/*" );
        gallery.setAction( Intent.ACTION_GET_CONTENT );
        startActivityForResult( Intent.createChooser( gallery, "sellect picture" ), PICK_IMAGE );
    }
    private void selectDwg(){
        stroge = new Intent(Intent.ACTION_GET_CONTENT);
        stroge.setType( "*/*" );
        startActivityForResult( stroge,10 );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        switch (requestCode)
        {
            case PICK_IMAGE :
               if (resultCode == RESULT_OK) {
                   imgpath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap( getContentResolver(), imgpath );
                    view_image.setImageBitmap( bitmap );
                    btnUplode.setEnabled( true );
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
                break;
            case 10 :
                if (resultCode == RESULT_OK) {
//                    String filePath=getRealPathFromUri(data.getData(),MainActivity.this);

                    String filePath = data.getData().getPath();
                    view_Dwg_File.setText( file_path );
                    this.file_path=filePath;

                    File file=new File(filePath);
                    view_Dwg_File.setText(file.getName());
                }
                break;
        }

    }


    private void updateImage(){
        String Image1 = imageToString();

        EventDetail eventDetail = new EventDetail();
        SharedPrefManager sfm = SharedPrefManager.getInstance( context );
        ProfileDetail pd = sfm.getUser();


        int eventID=getIntent().getIntExtra( eventDetail.getEventId(),0);

        Api api = RetrofitClient.getApi().create(Api.class);
        Call<ApiResponseWhitoutResData> call = api.uplodeFile(pd.getUserId(),eventID ,file_path,Image1);
        call.enqueue( new Callback<ApiResponseWhitoutResData>() {
            @Override
            public void onResponse(Call<ApiResponseWhitoutResData> call, Response<ApiResponseWhitoutResData> response) {
                Log.d( "image",""+eventID );
                Log.d( "pic",""+Image1 );

                if (response.body().getResCode()  ==  1){
                    Toast.makeText( PopActivity.this, response.body().getResMessage(), Toast.LENGTH_LONG ).show();
                }


            }

            @Override
            public void onFailure(Call<ApiResponseWhitoutResData> call, Throwable t) {
                Log.d( "fail",""+t.getLocalizedMessage() );


            }
        } );
    }


    private String imageToString()
    {
        Bitmap bitmap = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(  );
        bitmap.compress( Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream );
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString( imgByte,Base64.DEFAULT );
    }
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.choose_image:
                selectImage();
                break;
            case R.id.choose_DWG:
                selectDwg();
                break;
            case R.id.btnUplode:
                updateImage();
                break;
        }
    }



}
