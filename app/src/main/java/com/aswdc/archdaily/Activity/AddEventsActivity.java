package com.aswdc.archdaily.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aswdc.archdaily.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEventsActivity extends AppCompatActivity {

    Spinner SPPlanType;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_events );

        Toolbar toolbar;
        TextView toolbartext;
        toolbar=  findViewById( R.id.toolbar);
        toolbartext=  findViewById( R.id.toolbartext);
//        toolbartext.setText( "Add Ruppes Your Account" );

        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( "Add Ruppes Your Account" );
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        SPPlanType = findViewById(R.id.SPPlanType);

//        String dateStr = "04/05/2010";
//
//        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
//        Date dateObj = null;
//        try {
//            dateObj = curFormater.parse(dateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");
//
//        String newDateStr = postFormater.format(dateObj);
//        Log.d( "date", "" + newDateStr);


//        StateAdapter stateAdapter = new StateAdapter(this,stateList);
//        SPPlanType.setAdapter(stateAdapter);
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
