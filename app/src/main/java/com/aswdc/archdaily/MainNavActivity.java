package com.aswdc.archdaily;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.aswdc.archdaily.Fragment.NavHomeFragment;
import com.aswdc.archdaily.Fragment.NavEventFragment;
import com.aswdc.archdaily.Fragment.NavProfileFragment;
//import com.aswdc.archdaily.Fragment.ProjectsFragment;
import com.aswdc.archdaily.Fragment.NavWinnerListFragment;
//import com.aswdc.archdaily.ui.dashboard.DashboardFragment;
//import com.aswdc.archdaily.ui.notifications.NotificationsFragment;
import com.aswdc.archdaily.storage.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class MainNavActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_nav );
        Toolbar toolbar;
        toolbar = findViewById( R.id.toolbar );

        BottomNavigationView navigationView = findViewById( R.id.bottom_nav );
        navigationView.setOnNavigationItemSelectedListener( this );
        displayFragment( new NavHomeFragment() );
        Log.d( "Use:L::::",""+ SharedPrefManager.getInstance( this ).getUser().getUserId() );
    }

    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.relativeLayout, fragment )
                .commit();
    }


//    com.aswdc.archdaily.Fragment.HomeFragment homeFragment = new com.aswdc.archdaily.Fragment.HomeFragment();

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.menu_home:
                fragment = new NavHomeFragment();
                break;
            case R.id.menu_event:
                fragment = new NavEventFragment();
                break;
            case R.id.menu_users:
                fragment = new NavWinnerListFragment();
                break;
            case R.id.menu_settings:
                fragment = new NavProfileFragment();
                break;
        }

        if (fragment != null) {
            displayFragment( fragment );
        }

        return false;
    }
}


