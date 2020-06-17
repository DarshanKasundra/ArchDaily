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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc.archdaily.Activity.AutoFitGridLayoutManager;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.adapter.UserWinnerListAdapter;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.models.ProfileDetail;
import com.aswdc.archdaily.models.UserWinnerList;
import com.aswdc.archdaily.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserWinnerListFragment extends Fragment {
    RecyclerView rcvUserWinnerList;
    Context context;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_user_winner_list, container, false );


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

//        getChildFragmentManager().beginTransaction().add(R.id.filter, new Filter()).commit();

        rcvUserWinnerList = view.findViewById( R.id.rcvUserWinnerList );
        initReference();

    }



    void initReference() {
        ProgressDialog progress = new ProgressDialog( getActivity() );
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        rcvUserWinnerList.setLayoutManager(
                new GridLayoutManager(getActivity(),
                        1));

        SharedPrefManager sfm = SharedPrefManager.getInstance(context);
        ProfileDetail pd = SharedPrefManager.getInstance(context).getUser();

//        int UserId=getArguments().getInt( String.valueOf( pd.getUserId() ),0);
        Api api = RetrofitClient.getApi().create( Api.class);
        Call<ApiResponse> call = api.getuserWinningList(pd.getUserId());

        call.enqueue( new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                ArrayList<WinnerList> winnerLists = response.body().getResData().getUserWinnerList();
                List<UserWinnerList> userWinnerLists = response.body().getResData().getUserWinnerList();
                rcvUserWinnerList.setAdapter(new UserWinnerListAdapter( getActivity(), userWinnerLists ));

                progress.dismiss();
//                Picasso.with( context ).load( eventDetails.get(0).getMainBannerPath() ).fit().centerCrop().into( imgProjHome );

            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d( "out",""+t.getLocalizedMessage() );

            }
        } );

    }
}
