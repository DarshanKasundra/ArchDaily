package com.aswdc.archdaily.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aswdc.archdaily.R;

import java.util.Calendar;
import java.util.Date;

public class PassbookActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_passbook );

        Toolbar toolbar;
        TextView toolbartext;
        toolbar=  findViewById( R.id.toolbar);
        toolbartext=  findViewById( R.id.toolbartext);
//        toolbartext.setText( "Add Ruppes Your Account" );

        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( "Passbook" );
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        Date currentTime = Calendar.getInstance().getTime();
        Log.d( "time", "" + currentTime);

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
