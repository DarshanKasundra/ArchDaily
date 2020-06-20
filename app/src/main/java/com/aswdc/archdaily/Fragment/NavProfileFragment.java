package com.aswdc.archdaily.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aswdc.archdaily.Activity.ActivitySubFileDetailList;
import com.aswdc.archdaily.Activity.AddEventsActivity;
import com.aswdc.archdaily.Activity.AddRuppesActivity;
import com.aswdc.archdaily.Activity.EditProfileActivity;
import com.aswdc.archdaily.Activity.LoginActivity;
import com.aswdc.archdaily.Activity.PassbookActivity;
import com.aswdc.archdaily.Activity.WalletToBankAcountActivity;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.adapter.SubFileDetailListAdapter;
import com.aswdc.archdaily.adapter.ViewPageAdapter;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.models.EventDetail;
import com.aswdc.archdaily.models.ProfileDetail;
import com.aswdc.archdaily.models.SubFile;
import com.aswdc.archdaily.models.TotalEventParticipate;
import com.aswdc.archdaily.models.UserTotalVote;
import com.aswdc.archdaily.storage.SharedPrefManager;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 */
public class NavProfileFragment extends  Fragment {
    TextView textUserName ,textPhoneNumber,textEmail,textCity,textState,textCountry,textPincode,texteventCount,texttotalvote;
    ImageView textEdit;
    CircleImageView imgUserProfilePhoto;
    Button btnLogout;
    ViewPager viewPager;
    TabLayout tabLayout;



    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;


    Context context;
    SharedPrefManager sfm = SharedPrefManager.getInstance(getActivity());
    ProfileDetail pd = sfm.getUser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_nav_profile, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

        // Toolbar
        Toolbar toolbar;
        TextView toolbartext;
        toolbar=  view.findViewById(R.id.toolbar);
        toolbartext=  view.findViewById(R.id.toolbartext);
        toolbartext.setText( pd.getName());

        texteventCount = view.findViewById(R.id.texteventCount);
        texttotalvote = view.findViewById(R.id.texttotalvote);

        textEdit = view.findViewById(R.id.textEdit);
        btnLogout = view.findViewById(R.id.btnLogout);
        textUserName = view.findViewById( R.id.textUserName );
        textPhoneNumber = view.findViewById( R.id.textPhoneNumber );
        textEmail = view.findViewById( R.id.textEmail );
        textCity = view.findViewById( R.id.textCity );
        textState = view.findViewById( R.id.textState );
        textCountry = view.findViewById( R.id.textCountry );
        textPincode = view.findViewById( R.id.textPincode );
        imgUserProfilePhoto = view.findViewById( R.id.imgUserProfilePhoto );

        tabLayout = view.findViewById( R.id.tablayout_id );
        viewPager = view.findViewById( R.id.viewpager_id );


        toolbar=  view.findViewById(R.id.toolbar);
        toolbartext=  view.findViewById(R.id.toolbartext);
        toolbartext.setText( "Arch Daily" );









        initializeReference();
        initReference();

    }


    void initReference(){

        Api api = RetrofitClient.getApi().create(Api.class);
        Call<ApiResponse> call = api.getUserFiles(pd.getUserId());
        call.enqueue( new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ArrayList<TotalEventParticipate> totalEventParticipates = (ArrayList<TotalEventParticipate>) response.body().getResData().getTotalEventParticipate();
                ArrayList<UserTotalVote> userTotalVotes = (ArrayList<UserTotalVote>) response.body().getResData().getUserTotalVote();

                texteventCount.setText( totalEventParticipates.get( 0 ).getTotalParticipate() );
                texttotalvote.setText( userTotalVotes.get( 0 ).getSessionUserTotalVote() );
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        } );


        textEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), EditProfileActivity.class );
                startActivity(intent);
            }
        } );




        SharedPrefManager sfm = SharedPrefManager.getInstance(getActivity());
        ProfileDetail pd = sfm.getUser();


        Log.d( "game",""+pd.getUserId() );

        textUserName.setText( pd.getName() );
//        textPhoneNumber.setText( pd.getMobile() );
//        textEmail.setText( pd.getEmail() );
//        textCity.setText( pd.getCity() );
        Log.d("profile",""+pd.getEmail());
//        Picasso.with( context ).load( pd.getProfilePicPath() ).fit().centerCrop().into( imgUserProfilePhoto );



    }

    void initializeReference() {


        ViewPageAdapter adapter = new ViewPageAdapter( getChildFragmentManager() );

        //add fragment
//        PhotoListFragment photoListFragment = new PhotoListFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString( String.valueOf( pd.getUserId() ), String.valueOf( getArguments().getInt( String.valueOf( pd.getUserId() )) ) );
//        photoListFragment.setArguments( bundle );

        adapter.AddFragment(new SubFileListFragment(), "Photos" );
        adapter.AddFragment( new MainFileListFragment(), "File" );
        //adapter setup
        viewPager.setAdapter( adapter );
        tabLayout.setupWithViewPager( viewPager );
    }

}

