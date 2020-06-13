//package com.aswdc.archdaily.Activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.aswdc.archdaily.R;
//
//public class CreateUserName extends AppCompatActivity {
//    Button btncreateUserName;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate( savedInstanceState );
//        setContentView( R.layout.activity_create_user );
//
//        btncreateUserName = findViewById( R.id.btncreateUserName );
//
//        btncreateUserName.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent( CreateUserName.this,CreateUserName.class );
//                startActivity( intent );
//                finish();
//            }
//        } );
//    }
//}
