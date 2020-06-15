package com.aswdc.archdaily.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.aswdc.archdaily.Activity.AddEventsActivity;
import com.aswdc.archdaily.Activity.AddRuppesActivity;
import com.aswdc.archdaily.Activity.LoginActivity;
import com.aswdc.archdaily.Activity.PassbookActivity;
import com.aswdc.archdaily.Activity.WalletToBankAcountActivity;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.adapter.NavEventAdapter;
import com.aswdc.archdaily.adapter.NavHomeAdapter;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ListEvent;
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.models.SubfilesWithUserDetailHistory;
import com.aswdc.archdaily.storage.SharedPrefManager;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavHomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    RecyclerView rcvallSubFileList;
    Context context;
    SwipeRefreshLayout swipeHome;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView navUserName;
    ImageView backButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nav_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

        swipeHome = view.findViewById( R.id.swipeHome );
        toolbar = view.findViewById( R.id.toolbar );
        rcvallSubFileList = view.findViewById( R.id.rcvallSubFileList );
        navUserName  = view.findViewById( R.id.navUserName );
        LinearLayoutManager manager = new LinearLayoutManager( getActivity() );
        rcvallSubFileList.setLayoutManager( manager );

//        SharedPrefManager sfm = SharedPrefManager.getInstance(getActivity());
//        ProfileDetail pd = sfm.getUser();
//
//        navUserName.setText( pd.getCity() );

        // Toolbar

        TextView toolbartext;
        toolbar=  view.findViewById(R.id.toolbar);
        toolbartext=  view.findViewById(R.id.toolbartext);
        toolbartext.setText( "Arch Daily" );

//        FInd ID

        drawerLayout= view.findViewById(R.id.drawer_layout);
        navigationView= view.findViewById(R.id.nav_view);

//        set nevigation drawer

        actionBarDrawerToggle = new ActionBarDrawerToggle( getActivity(),drawerLayout,toolbar, R.string.open,R.string.close );
        drawerLayout.addDrawerListener( actionBarDrawerToggle );
        actionBarDrawerToggle.setDrawerIndicatorEnabled( true );
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener( this );


//        method calling
        initReference();

    }

//      create method

    void initReference() {

//        set Progress Dialog

        ProgressDialog progress = new ProgressDialog( getActivity() );
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        Api api = RetrofitClient.getApi().create(Api.class);
        Call<ApiResponse> call = api.getallsubfile();
        call.enqueue( new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getResCode() == 1) {
                    ArrayList<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistories = (ArrayList<SubfilesWithUserDetailHistory>) response.body().getResData().getSubfilesWithUserDetailHistory();
                    rcvallSubFileList.setAdapter(new NavHomeAdapter(getActivity(), subfilesWithUserDetailHistories));
                }
                else
                {
                    Toast.makeText(getActivity(), "Data not found", Toast.LENGTH_SHORT).show();
                }
                progress.dismiss();
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Z",""+t.getLocalizedMessage());
                Toast.makeText( getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG ).show();
                progress.dismiss();
            }
        } );

//        Set swipe Refrish

        swipeHome.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Api api = RetrofitClient.getApi().create(Api.class);
                Call<ApiResponse> call = api.getallsubfile();
                call.enqueue( new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.body().getResCode() == 1) {
                            ArrayList<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistories = (ArrayList<SubfilesWithUserDetailHistory>) response.body().getResData().getSubfilesWithUserDetailHistory();
                            rcvallSubFileList.setAdapter(new NavHomeAdapter(getActivity(), subfilesWithUserDetailHistories));
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Data not found", Toast.LENGTH_SHORT).show();
                        }
                        progress.dismiss();
                    }
                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Log.d("Z",""+t.getLocalizedMessage());
                        Toast.makeText( getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG ).show();
                        progress.dismiss();
                    }
                } );
                new Handler(  ).postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        swipeHome.setRefreshing( false );
                    }
                } ,400);
            }
        } );

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_add_rupees:
                Toast.makeText( getActivity(), "AddArchRuppes", Toast.LENGTH_LONG ).show();
                Intent intent = new Intent( getActivity(), AddRuppesActivity.class );
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
