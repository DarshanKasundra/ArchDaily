package com.aswdc.archdaily.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc.archdaily.R;
import com.aswdc.archdaily.adapter.HomeEventUserDetailAdapter;
import com.aswdc.archdaily.adapter.SubFileDetailListAdapter;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.models.ProfileDetail;
import com.aswdc.archdaily.models.SubFile;
import com.aswdc.archdaily.models.UserList;
import com.aswdc.archdaily.storage.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySubFileDetailList extends AppCompatActivity {
    RecyclerView rcvSubFileDetail;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_subfile_detail_list );
        rcvSubFileDetail = findViewById( R.id.rcvSubFileDetail );
        LinearLayoutManager manager = new LinearLayoutManager( context );
        rcvSubFileDetail.setLayoutManager( manager );

        //  set Toolbar  //
        Toolbar toolbar;
        TextView toolbartext;
        toolbar=  findViewById( R.id.toolbar);
        toolbartext=  findViewById( R.id.toolbartext);
        toolbartext.setText( "" );

        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( "Your Events" );
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        initReference();

    }
    void initReference(){
        SharedPrefManager sfm = SharedPrefManager.getInstance(context);
        ProfileDetail pd = sfm.getUser();

        Api api = RetrofitClient.getApi().create(Api.class);
        Call<ApiResponse> call = api.getUserFiles(pd.getUserId());
        call.enqueue( new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ArrayList<SubFile> subFiles = (ArrayList<SubFile>) response.body().getResData().getSubFiles();

                rcvSubFileDetail.setAdapter(new SubFileDetailListAdapter( ActivitySubFileDetailList.this, subFiles ));
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
