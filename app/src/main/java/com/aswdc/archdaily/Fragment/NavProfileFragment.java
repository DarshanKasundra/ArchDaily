package com.aswdc.archdaily.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
public class NavProfileFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{
    TextView textUserName ,textPhoneNumber,textEmail,textCity,textState,textCountry,textPincode,texteventCount,texttotalvote;
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
        TextView toolbartext;
        toolbar=  view.findViewById(R.id.toolbar);
        toolbartext=  view.findViewById(R.id.toolbartext);
        toolbartext.setText( pd.getName() );

//        FInd ID
        drawerLayout= view.findViewById(R.id.drawer_layout);
        navigationView= view.findViewById(R.id.nav_view);

//        set nevigation drawer

        actionBarDrawerToggle = new ActionBarDrawerToggle( getActivity(),drawerLayout,toolbar, R.string.open,R.string.close );
        drawerLayout.addDrawerListener( actionBarDrawerToggle );
        actionBarDrawerToggle.setDrawerIndicatorEnabled( true );
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener( this );


        texteventCount = view.findViewById(R.id.texteventCount);
        texttotalvote = view.findViewById(R.id.texttotalvote);

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


//        toolbar=  view.findViewById(R.id.toolbar);
//        toolbartext=  view.findViewById(R.id.toolbartext);
//        toolbartext.setText( "Arch Daily" );






//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//                // Handle the back button event
//            }
//        });
//        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);


        initializeReference();
        initReference();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//                // Handle the back button event
//                getChildFragmentManager().beginTransaction().add( R.id.fragment_container ,new NavProfileFragment()).commit();
//
//            }
//        });
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
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

//        textEdit.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent( getActivity(), EditProfileActivity.class );
//                startActivity(intent);
////                getActivity().finish();
////
////                Fragment fragment = getChildFragmentManager().findFragmentByTag(  );
////                if(fragment != null)
//          //          getChildFragmentManager().beginTransaction().remove(new NavProfileFragment()).commit();
//
//                }
//        } );




        SharedPrefManager sfm = SharedPrefManager.getInstance(getActivity());
        ProfileDetail pd = sfm.getUser();


        Log.d( "game",""+pd.getUserId() );

        textUserName.setText( pd.getName() );
        Log.d("profile",""+pd.getEmail());
//        Picasso.with( context ).load( pd.getProfilePicPath() ).fit().centerCrop().into( imgUserProfilePhoto );



    }

    void initializeReference() {


        ViewPageAdapter adapter = new ViewPageAdapter( getChildFragmentManager() );
        adapter.AddFragment(new SubFileListFragment(), "Photos" );
        adapter.AddFragment( new MainFileListFragment(), "File" );
        //adapter setup
        viewPager.setAdapter( adapter );
        tabLayout.setupWithViewPager( viewPager );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_edit:
                Intent intent = new Intent( getActivity(), EditProfileActivity.class );
                startActivity( intent );
                /*FragmentManager fragmentManager = getActivity().getFragmentManager(); // For AppCompat use getSupportFragmentManager
                fragmentManager.beginTransaction().replace( R.id.fragment_container ,new AddArchRupeeFragment()).commit();*/
                break;
            case R.id.nav_wallet_to_bank:
                Toast.makeText( getActivity(), "Wallet to bank", Toast.LENGTH_LONG ).show();
                Intent intent1 = new Intent( getActivity(), WalletToBankAcountActivity.class );
                startActivity( intent1 );
                break;

            case R.id.nav_passbook:
                Toast.makeText( getActivity(), "Passbook", Toast.LENGTH_LONG ).show();
                Intent intent2 = new Intent( getActivity(), PassbookActivity.class );
                startActivity( intent2 );
                break;
            case R.id.nav_add_event:
                Toast.makeText( getActivity(), "Add Event", Toast.LENGTH_LONG ).show();
                Intent intent3 = new Intent( getActivity(), AddEventsActivity.class );
                startActivity( intent3 );
                break;
            case R.id.nav_logout:
                Toast.makeText( getActivity(), "Log Out", Toast.LENGTH_LONG ).show();
                SharedPrefManager sfm = SharedPrefManager.getInstance(context);
                sfm.clear();

                Intent i = new Intent( getActivity(), LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                break;
        }

        drawerLayout.closeDrawer( GravityCompat.START );
        return true;

    }

}

