package com.aswdc.archdaily.Activity;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.aswdc.archdaily.R;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponseWhitoutResData;
import com.aswdc.archdaily.models.EventDetail;
import com.aswdc.archdaily.models.ProfileDetail;
import com.aswdc.archdaily.storage.SharedPrefManager;
import com.aswdc.archdaily.utils.FileUtil;
import com.aswdc.archdaily.utils.FileUtilsNew;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import in.gauriinfotech.commons.Commons;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Comparator;


import static com.aswdc.archdaily.BuildConfig.DEBUG;

public class PopActivity extends AppCompatActivity implements View.OnClickListener {
    Button choose_image, choose_DWG;
    ImageView view_image;
    TextView view_Dwg_File, view_img_name;
    Intent stroge;
    String main_file_path;
    String sub_file_path;

    private static final int PICK_IMAGE = 1;
    private static final int PICK_IMAGE2 = 2;
    Context context;
    Button btnUplode;
    Uri uri, moveimage, movefile;

    MultipartBody.Part imagepart;
    MultipartBody.Part filePart;

    Intent data;

    Bitmap bitmap;

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
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics( displayMetrics );
//
//        int width = displayMetrics.widthPixels;
//        int height = displayMetrics.heightPixels;
//
//        getWindow().setLayout( (int) (width * .8), (int) (height * .6) );

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                    startActivityForResult( galleryIntent, PICK_IMAGE );
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }

    private void selectImage() {
        if (ActivityCompat.checkSelfPermission( PopActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( PopActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_IMAGE );
        } else {
            Intent galleryIntent = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
            startActivityForResult( galleryIntent, PICK_IMAGE );
        }

//        Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
//        gallery.setType( "image/*" );
//        startActivityForResult( Intent.createChooser( gallery, "sellect picture" ), PICK_IMAGE );
    }

    private void selectDwg() {
        stroge = new Intent( Intent.ACTION_GET_CONTENT );
        stroge.setType( "*/*" );
        startActivityForResult( stroge, 10 );
    }

    private String imageToString(String sub_file_path) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream );
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return sub_file_path = Base64.encodeToString( imgByte, Base64.DEFAULT );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    uri = data.getData();
                    this.moveimage = uri;
                    String path = uri.getPath();
                    this.sub_file_path = path;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap( getContentResolver(), uri );
                        view_image.setImageBitmap( bitmap );
                        String imagepath = data.getData().getPath();
//                       view_Dwg_File.setText( main_file_path );
//                       this.sub_file_path=imagepath;
//                       File file=new File(imagepath);
//                       view_Dwg_File.setText(file.getName());
//                       Uri yourUri = Uri.fromFile(file);

//                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file_path);
                        btnUplode.setEnabled( true );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 10:
                if (resultCode == RESULT_OK) {
//                    String filePath=getRealPathFromUri(data.getData(),MainActivity.this);
                    Uri uriFile;
                    uriFile = data.getData();
                    this.movefile = uriFile;
                    String filePath = data.getData().getPath();
                    Log.d( "filePath::1::", "" + filePath );
                    Log.d( "filePath::2::", "" + movefile );
//                    view_Dwg_File.setText( main_file_path );
                    this.main_file_path = filePath;
                    File file = new File( filePath );
                    view_Dwg_File.setText( file.getName() );
                }
                break;
        }

    }

    public void updateImage() {

        EventDetail eventDetail = new EventDetail();
        SharedPrefManager sfm = SharedPrefManager.getInstance( context );
        ProfileDetail pd = sfm.getUser();
        int eventID = getIntent().getIntExtra( eventDetail.getEventId(), 0 );

        RequestBody requestBodyuserid = RequestBody.create( MediaType.parse( "multipart/form-data" ), String.valueOf( pd.getUserId() ) );
        RequestBody requestBodyeventid = RequestBody.create( MediaType.parse( "multipart/form-data" ), String.valueOf( eventID ) );

        imagepart = null;
        filePart = null;
        File file = new File( FileUtil.getPath( moveimage, this ) );
        Log.d( "filePath::3::", "" + movefile );
        String path = movefile.toString();// "file:///mnt/sdcard/FileName.mp3"
        Log.d( "filePath::6::", "" + path );
        Log.d( "filePath::8::", "" +  FileUtilsNew.getPath( this, movefile ));
//        try {
//            File filemain_file = new File( new URI( path ) );
//            Log.d( "filePath::5::", "" + filemain_file.getAbsolutePath() );
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        getPDFPath();
        File filemain_file = new File( FileUtilsNew.getPath( this, movefile ) );
        Log.d( "filePath::4::", "" + filemain_file.getAbsolutePath() );
        RequestBody requestBodysubFile = RequestBody.create( MediaType.parse( "image/*" ), file );
        imagepart = MultipartBody.Part.createFormData( "sub_file", file.getName(), requestBodysubFile );
        RequestBody requestBodymainFile = RequestBody.create( MediaType.parse( "*/*" ), filemain_file );
        filePart = MultipartBody.Part.createFormData( "main_file", filemain_file.getName(), requestBodymainFile );


        Api api = RetrofitClient.getApi().create( Api.class );
        Call<ApiResponseWhitoutResData> call = api.uplodeFile( requestBodyuserid, requestBodyeventid, filePart, imagepart );
        call.enqueue( new Callback<ApiResponseWhitoutResData>() {
            @Override
            public void onResponse(Call<ApiResponseWhitoutResData> call, Response<ApiResponseWhitoutResData> response) {

                if (response.body().getResCode() == 1) {
                    Log.d( "user", String.valueOf( imagepart ) );
                    Log.d( "5", response.body().getResMessage() );
                    Log.d( "1234", "abc" );
                    Log.d( "hiral", String.valueOf( requestBodyuserid ) );
                    Log.d( "success", String.valueOf( response.body().getResMessage() ) );

                    Toast.makeText( PopActivity.this, response.body().getResMessage(), Toast.LENGTH_LONG ).show();
                } else {
                    Toast.makeText( PopActivity.this, response.body().getResMessage(), Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseWhitoutResData> call, Throwable t) {
                Log.d( "fail", "" + t.getLocalizedMessage() );
                Toast.makeText( PopActivity.this, "Faild", Toast.LENGTH_LONG ).show();
            }
        } );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
