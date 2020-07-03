package com.aswdc.archdaily.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc.archdaily.Activity.AutoFitGridLayoutManager;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.adapter.MainFileAdapter;
import com.aswdc.archdaily.adapter.SubFileAdapter;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.models.MainFile;
import com.aswdc.archdaily.models.ProfileDetail;
import com.aswdc.archdaily.models.SubFile;
import com.aswdc.archdaily.storage.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFileListFragment extends Fragment {
    RecyclerView rcvMainFileList;
    Context context;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_main_file_list, container, false );


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

//        getChildFragmentManager().beginTransaction().add(R.id.filter, new Filter()).commit();

        rcvMainFileList = view.findViewById( R.id.rcvMainFileList );
        initReference();

    }



    void initReference() {

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager( context, 360 );
        rcvMainFileList.setLayoutManager( layoutManager );

        SharedPrefManager sfm = SharedPrefManager.getInstance(context);
        ProfileDetail pd = SharedPrefManager.getInstance(context).getUser();

//        int UserId=getArguments().getInt( String.valueOf( pd.getUserId() ),0);
        Api api = RetrofitClient.getApi().create( Api.class);
        Call<ApiResponse> call = api.getUserFiles(pd.getUserId());

        call.enqueue( new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.d( "Done",""+pd.getUserId() );


//                event arraylist
                ArrayList<MainFile> mainFiles = (ArrayList<MainFile>) response.body().getResData().getMainFiles();

                rcvMainFileList.setAdapter(new MainFileAdapter( getActivity(), mainFiles ));

//                Picasso.with( context ).load( eventDetails.get(0).getMainBannerPath() ).fit().centerCrop().into( imgProjHome );

            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d( "out",""+t.getLocalizedMessage() );

            }
        } );

    }
}
