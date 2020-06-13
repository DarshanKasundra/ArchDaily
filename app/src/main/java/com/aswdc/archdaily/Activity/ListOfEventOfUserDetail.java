package com.aswdc.archdaily.Activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.aswdc.archdaily.R;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.models.EventDetail;
import com.aswdc.archdaily.models.ListUserEvent;
import com.aswdc.archdaily.models.UserList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfEventOfUserDetail extends AppCompatActivity {
    RecyclerView rcvListofEventUserdetail;
    SwipeRefreshLayout swipeHome;
    Toolbar toolbar;
    EventDetail eventDetail = new EventDetail();

    ListUserEvent listUserEvent = new ListUserEvent();
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );


//        swipeHome = findViewById( R.id.swipeHome );
//        toolbar = findViewById( R.id.toolbar );
        setContentView( R.layout.activity_list_user_event_user_detail );

        Toolbar toolbar;
        TextView toolbartextDone;

        toolbar=  findViewById( R.id.toolbar);
        toolbartextDone=  findViewById( R.id.toolbartextDone);
        toolbartextDone.setText( "Done" );

        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( "Post" );
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        initReference();
    }
    void initReference(){

        int eventID=getIntent().getIntExtra( eventDetail.getEventId(),0);
        int UserID =getIntent().getIntExtra( eventDetail.getEventId(),0);

        Api api = RetrofitClient.getApi().create(Api.class);
        Call<ApiResponse> call = api.getlistOfUserdetailOfEvent(eventID,UserID);
        call.enqueue( new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.d("user",""+UserID);
                Log.d("tag",""+eventID);
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
