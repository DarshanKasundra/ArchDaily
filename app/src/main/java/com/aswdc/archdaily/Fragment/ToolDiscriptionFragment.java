package com.aswdc.archdaily.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.aswdc.archdaily.R;
//import com.aswdc.autocadetutorial.Model.DescriptionModel;
//import com.aswdc.autocadetutorial.R;
//import com.aswdc.autocadetutorial.adapter.DescriptionAdapter;
//import com.aswdc.autocadetutorial.dbhelper.TblDescription;
//import com.aswdc.autocadetutorial.utils.Constants;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToolDiscriptionFragment extends Fragment {

    ViewGroup viewGroup;
    TextView tvtoolName;
    ViewPager viewPager;
    TabLayout tabLayout;
    RecyclerView rcvDescription;
//    ArrayList<DescriptionModel> list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewGroup = (ViewGroup) inflater.inflate( R.layout.fragment_tool_discription
                , container, false );
//        initReference();
        return viewGroup;

    }


//    void initReference() {
//        rcvDescription = viewGroup.findViewById( R.id.rcvDescription );
//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        rcvDescription.setLayoutManager(layoutManager);
//
//        rcvDescription.setLayoutManager(
//                new GridLayoutManager(getActivity(),
//                        1));
//
//
//        int toolsID = getArguments().getInt( Constants.TAG_TOOLS_ID );
//        list.addAll( new TblDescription( getActivity() ).selectDscrriptionByToolsID( toolsID ) );
//        DescriptionAdapter adapter = new DescriptionAdapter( getActivity(), list );
//        rcvDescription.setAdapter( adapter );
//    }
}
