package com.aswdc.archdaily.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc.archdaily.R;
import com.aswdc.archdaily.adapter.HomeEventUserDetailAdapter;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.models.ApiResponseWhitoutResData;
import com.aswdc.archdaily.models.EventDetail;
import com.aswdc.archdaily.models.ListEvent;
import com.aswdc.archdaily.models.ProfileDetail;
//import com.aswdc.archdaily.models.UserDetail;
import com.aswdc.archdaily.models.UserList;
import com.aswdc.archdaily.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailActivity extends AppCompatActivity {

//    globley declare

    RecyclerView rcvEventDetail , rcvUserEventDetail ;
    Context context;
    Button btnJoin;
    TextView textEventName,textFees,textStatus,textStartDate,textEndDate,textDescrotion,textUserName ,textVoteCount;
    LinearLayout linearlayoutvotebutton;
    ImageView imgProjHome ,backButton ;
    EventDetail eventDetail = new EventDetail();
    UserList userDetail = new UserList();
    public ArrayList<ListEvent> listEvents = new ArrayList<>(  );



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_event_detail );

//      find all field using findViewById

        rcvUserEventDetail = (RecyclerView) findViewById( R.id.rcvUserEventDetail );
        textEventName = findViewById( R.id.textEventName );
        textStartDate = findViewById( R.id.textStartDate);
        textEndDate = findViewById( R.id.textEndDate);
        textDescrotion = findViewById( R.id.textDescrotion);
        textUserName = findViewById( R.id.textUserName);
        imgProjHome = findViewById( R.id.imgProjHome );
        textFees = findViewById( R.id.textFees);
        textStatus = findViewById( R.id.textStatus);
        btnJoin = findViewById( R.id.btnJoin );
        linearlayoutvotebutton = findViewById( R.id.linearlayoutvotebutton );
        textVoteCount = findViewById( R.id.textVoteCount );

//        backButton = findViewById( R.id.backButton );


        textDescrotion.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( EventDetailActivity.this , EventDiscriptionActivity.class );
                startActivity( intent );
            }
        } );


//        setSupportActionBar( toolbar );
//        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
//        getSupportActionBar().setDisplayShowHomeEnabled( true );



//        set Layout
        rcvUserEventDetail.setLayoutManager(
                new GridLayoutManager(getApplicationContext(),
                        1));
//          call method
        initReference();
    }
    void initReference( ){

        ProgressDialog progress = new ProgressDialog( EventDetailActivity.this );
        progress.setTitle("Loading");
        //                getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_home );
        progress.setIcon( R.drawable.ic_home );
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();



        btnJoin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                intent.putExtra( pd.getUserId(), eventDetail.getEventId());
//                   Intent intent = new Intent( EventDetailActivity.this, PopActivity.class );
//

                SharedPrefManager sfm = SharedPrefManager.getInstance(getApplication());
                ProfileDetail pd = sfm.getUser();



                int eventID=getIntent().getIntExtra( eventDetail.getEventId(),0);

                Intent intent = new Intent( EventDetailActivity.this,PopActivity.class );
                intent.putExtra( eventDetail.getEventId(),  eventID);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( intent );



                Api api = RetrofitClient.getApi().create(Api.class);
                Call<ApiResponseWhitoutResData> call = api.getJoinUserInEvent( eventID,pd.getUserId());

                call.enqueue( new Callback<ApiResponseWhitoutResData>() {
                    @Override
                    public void onResponse(Call<ApiResponseWhitoutResData> call, Response<ApiResponseWhitoutResData> response) {
                        if (response.body().getResCode() == 1){
                            Log.d( "Tom",""+response.body().getResMessage() );
                            Log.d( "eventId",""+eventID);
                            Toast.makeText(EventDetailActivity.this, response.body().getResMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponseWhitoutResData> call, Throwable t) {
                        Log.d( "Dog",""+t.getLocalizedMessage() );

                    }
                } );


//                call.enqueue( new Callback<ApiResponse>() {
//                    @Override
//                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                        Log.d( "Pin",""+pd.getUserId() );
//                        Log.d( "tag",""+eventID );
//                        Toast.makeText(EventDetailActivity.this, response.body().getResMessage(), Toast.LENGTH_LONG).show();
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ApiResponse> call, Throwable t) {
//                        Log.d( "Dog",""+t.getLocalizedMessage() );
//
//                    }
//                } );
            }
        } );



        SharedPrefManager sfm = SharedPrefManager.getInstance(context);
        ProfileDetail pd = sfm.getUser();
        int eventID=getIntent().getIntExtra( eventDetail.getEventId(),0);
        int UserId=getIntent().getIntExtra( String.valueOf( pd.getUserId() ),0);

        Api api = RetrofitClient.getApi().create( Api.class);
        Call<ApiResponse> call = api.geteventDetail(eventID);

        call.enqueue( new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                Log.d( "Done",""+response.body().getResData().getUserDetail( UserId ) );


//                event arraylist
                ArrayList<EventDetail> eventDetails = (ArrayList<EventDetail>) response.body().getResData().getEventDetail(eventID);

                //  set Toolbar  //
                Toolbar toolbar;
                TextView toolbartext;
                toolbar=  findViewById( R.id.toolbar);
                toolbartext=  findViewById( R.id.toolbartext);
                toolbartext.setText( "" );

                setSupportActionBar( toolbar );
                getSupportActionBar().setTitle( eventDetails.get( 0 ).getEventName() );
                getSupportActionBar().setDisplayShowHomeEnabled( true );
                getSupportActionBar().setDisplayHomeAsUpEnabled( true );
//                getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_home );


                textFees.setText( eventDetails.get(0).getFees() );
                textStatus.setText( eventDetails.get( 0 ).getStatus() );
                textStartDate.setText( eventDetails.get( 0 ).getStartDate() );
                textEndDate.setText( eventDetails.get( 0 ).getEndDate());

//                String today = eventDetails.get( 0 ).getStatus();//getting date
//                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formating according to my need
//                String date = formatter.format(today);
//                textStartDate.setText( date );



//                textEventName.setText( eventDetails.get( 0 ).getEventName() );
                Picasso.with( context ).load( eventDetails.get(0).getMainBannerPath() ).fit().centerCrop().into( imgProjHome );
//                user array list

                ArrayList<UserList> userLists = (ArrayList<UserList>) response.body().getResData().getUserList( UserId );

                rcvUserEventDetail.setAdapter(new HomeEventUserDetailAdapter( EventDetailActivity.this, userLists ,eventID));
               

                progress.dismiss();
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        } );



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
