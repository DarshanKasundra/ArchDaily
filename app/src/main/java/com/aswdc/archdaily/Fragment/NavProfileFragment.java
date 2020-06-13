package com.aswdc.archdaily.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aswdc.archdaily.Activity.EditProfileActivity;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.adapter.ViewPageAdapter;
import com.aswdc.archdaily.models.ProfileDetail;
import com.aswdc.archdaily.storage.SharedPrefManager;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 */
public class NavProfileFragment extends  Fragment {
    TextView textUserName ,textPhoneNumber,textEmail,textCity,textState,textCountry,textPincode;
    ImageView textEdit;
    CircleImageView imgUserProfilePhoto;
    Button btnLogout;
    ViewPager viewPager;
    TabLayout tabLayout;
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
        initializeReference();
        initReference();

    }


    void initReference(){

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

