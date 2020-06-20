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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopActivity extends AppCompatActivity implements View.OnClickListener {
    Button choose_image,choose_DWG;
    ImageView view_image;
    TextView view_Dwg_File,view_img_name;
    Intent stroge;
    String main_file_path;
    String sub_fail_path;

    private static final int PICK_IMAGE = 1;
    private static final int PICK_IMAGE2 = 2;
    Context context;
    Button btnUplode;
    Uri uri;

    MultipartBody.Part imagepart;
    MultipartBody.Part filePart;
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
        view_img_name = findViewById( R.id.view_img_name );


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
                   uri = data.getData();
                   String subfilepath = uri.getPath();

                   try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap( getContentResolver(), uri );
                    view_image.setImageBitmap( bitmap );
                       this.sub_fail_path=subfilepath;
                       File subfile=new File(subfilepath);
                       Uri yourUri = Uri.fromFile(subfile);

//                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file_path);
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
                    view_Dwg_File.setText( main_file_path );
                    this.main_file_path=filePath;
                    File file=new File(filePath);
                    view_Dwg_File.setText(file.getName());
                }
                break;
        }

    }


    private void updateImage(){


        EventDetail eventDetail = new EventDetail();
        SharedPrefManager sfm = SharedPrefManager.getInstance( context );
        ProfileDetail pd = sfm.getUser();
        int eventID=getIntent().getIntExtra( eventDetail.getEventId(),0);

        RequestBody requestBodyEventID =
                RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf( eventID ) );
        RequestBody requestBodyUserID =
                RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf( pd.getUserId() ) );
//        int eventID=getIntent().getIntExtra( eventDetail.getEventId(),0);
//        File file = new File("/storage/emulated/0/Download/Corrections 6.jpg");


        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), new File( main_file_path ));
        filePart = MultipartBody.Part.createFormData("main_file", String.valueOf( new File( main_file_path ) ), requestFile);
        Log.d( "file_path:::",""+new File( main_file_path ).exists() );
        RequestBody subFile = RequestBody.create(MediaType.parse("multipart/form-data"), new File( sub_fail_path ));
        imagepart = MultipartBody.Part.createFormData("sub_file", String.valueOf( new File( sub_fail_path ) ), subFile);

//        imagepart = MultipartBody.Part.createFormData("sub_file", new File(imgpath).getName(), RequestBody.create(MediaType.parse("multipart/form-data"),  new File(imgpath).getName()));
        Api api = RetrofitClient.getApi().create(Api.class);
        Call<ApiResponseWhitoutResData> call = api.uplodeFile(requestBodyUserID,requestBodyEventID ,filePart,imagepart);
        call.enqueue( new Callback<ApiResponseWhitoutResData>() {
            @Override
            public void onResponse(Call<ApiResponseWhitoutResData> call, Response<ApiResponseWhitoutResData> response) {

                if (response.body().getResCode()  ==  1){
                    Toast.makeText( PopActivity.this, "hii", Toast.LENGTH_LONG ).show();
                }
                else {
                    Toast.makeText( PopActivity.this, response.body().getResMessage(), Toast.LENGTH_LONG ).show();
                    Log.d( "userid",""+response.body() );
//                    Log.d( "eentid",""+requestBodyEventID );
                    Log.d( "img",""+imagepart );
                    Log.d( "file",""+filePart );
                }
            }

            @Override
            public void onFailure(Call<ApiResponseWhitoutResData> call, Throwable t) {
                Log.d( "fail",""+t.getLocalizedMessage() );
                Toast.makeText( PopActivity.this,"Faild" , Toast.LENGTH_LONG ).show();


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
