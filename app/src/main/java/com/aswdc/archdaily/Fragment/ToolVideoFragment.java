package com.aswdc.archdaily.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc.archdaily.R;
//import com.aswdc.autocadetutorial.Model.VideoModel;
//import com.aswdc.autocadetutorial.R;

import java.util.ArrayList;

//import com.aswdc.autocadetutorial.adapter.VideoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToolVideoFragment extends Fragment {
    ViewGroup viewGroup;
    RecyclerView rcvVideo;
//    ArrayList<VideoModel> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewGroup = (ViewGroup) inflater.inflate( R.layout.fragment_tool_video
                , container, false );
//        initReference();
        return viewGroup;
    }

//    void initReference() {
//        rcvVideo = viewGroup.findViewById( R.id.rcvVideo );
//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
//        rcvVideo.setLayoutManager(layoutManager);
//
//        rcvVideo.setLayoutManager(
//                new GridLayoutManager(getActivity(),
//                        1));
//
//
//        int toolsID = getArguments().getInt( Constants.TAG_TOOLS_ID );
//        list.addAll( new TblVideo( getActivity() ).selectVideoByToolsID( toolsID ) );
//        VideoAdapter adapter = new VideoAdapter( getActivity(), list );
//        rcvVideo.setAdapter( adapter );
//    }

}
