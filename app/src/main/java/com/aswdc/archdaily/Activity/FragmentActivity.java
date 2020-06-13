//package com.aswdc.archdaily.Activity;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.viewpager.widget.ViewPager;
//
//import com.aswdc.archdaily.Fragment.ToolDiscriptionFragment;
//import com.aswdc.archdaily.Fragment.ToolVideoFragment;
//import com.aswdc.archdaily.R;
//import com.aswdc.archdaily.adapter.ViewPageAdapter;
//import com.google.android.material.tabs.TabLayout;
//
//import java.util.ArrayList;
//
//public class FragmentActivity extends AppCompatActivity {
//    TextView tvtoolName;
//    ViewPager viewPager;
//    TabLayout tabLayout;
//    RecyclerView rcvDescription;
////    ArrayList<DescriptionModel> list = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate( savedInstanceState );
//        setContentView( R.layout.layout_profile_fragment );
//        getSupportActionBar().setTitle( "Tool Detail" );
//        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
//
////        tvtoolName = (TextView)findViewById( R.id.tvDescription );
////        DescriptionModel descriptionModel = new DescriptionModel();
////        tvtoolName.setText( descriptionModel.getTools_description() );
//        initializeReference();
////        initReference();
//
//    }
//
//    void initializeReference() {
//        tabLayout = findViewById( R.id.tablayout_id );
//        viewPager = findViewById( R.id.viewpager_id );
//        ViewPageAdapter adapter = new ViewPageAdapter( getSupportFragmentManager() );
//        //add fragment
//        ToolDiscriptionFragment toolDiscriptionFragment = new ToolDiscriptionFragment();
////        Bundle bundle = new Bundle();
////        bundle.putInt(Constants.TAG_TOOLS_ID, getIntent().getIntExtra( Constants.TAG_TOOLS_ID, 0 ) );
////        toolDiscriptionFragment.setArguments( bundle );
//
//        adapter.AddFragment(toolDiscriptionFragment, "Discription" );
//        adapter.AddFragment( new ToolVideoFragment(), "Videos" );
//        //adapter setup
//        viewPager.setAdapter( adapter );
//        tabLayout.setupWithViewPager( viewPager );
//    }
//
////    void initReference() {
////        rcvDescription = findViewById( R.id.rcvDescription );
////        rcvDescription.setLayoutManager(
////                new GridLayoutManager( getApplicationContext(),
////                        1 ) );
////        int toolsID = getIntent().getIntExtra( Constants.TAG_TOOLS_ID, 0 );
////        list.addAll( new TblDescription( getApplicationContext() ).selectDscrriptionByToolsID( toolsID ) );
////
////        DescriptionAdapter adapter = new DescriptionAdapter( this, list );
////        rcvDescription.setAdapter( adapter );
////    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case android.R.id.home:
//                finish();
//                return super.onOptionsItemSelected( item );
//            default:
//                return super.onOptionsItemSelected( item );
//        }
//    }
//}