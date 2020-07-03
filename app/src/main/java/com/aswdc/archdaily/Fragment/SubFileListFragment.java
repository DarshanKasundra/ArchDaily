package com.aswdc.archdaily.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.aswdc.archdaily.Activity.AutoFitGridLayoutManager;
import com.aswdc.archdaily.Interface.onClickInterface;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.adapter.SubFileAdapter;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponse;
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
public class SubFileListFragment extends Fragment {
    RecyclerView rcvSubFileList;
    Context context;

    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    AutoFitGridLayoutManager manager;
    private onClickInterface onClickInterface;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_sub_file_list, container, false );


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

//        getChildFragmentManager().beginTransaction().add(R.id.filter, new Filter()).commit();

        rcvSubFileList = view.findViewById( R.id.rcvSubFileList );
        manager = new AutoFitGridLayoutManager( context, 360 );
        rcvSubFileList.setLayoutManager( manager );
        initReference();

    }



    void initReference() {

        rcvSubFileList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems))
                {
                    isScrolling = false;
                }
            }
        });
        getData();


    }
    private void getData(){


        SharedPrefManager sfm = SharedPrefManager.getInstance(context);
        ProfileDetail pd = SharedPrefManager.getInstance(context).getUser();

        Api api = RetrofitClient.getApi().create( Api.class);
        Call<ApiResponse> call = api.getUserFiles(pd.getUserId());

        call.enqueue( new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ArrayList<SubFile> subFiles = (ArrayList<SubFile>) response.body().getResData().getSubFiles();
                SubFileAdapter adapter = new SubFileAdapter( getActivity(),subFiles );
                rcvSubFileList.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d( "out",""+t.getLocalizedMessage() );

            }
        } );
    }
}
