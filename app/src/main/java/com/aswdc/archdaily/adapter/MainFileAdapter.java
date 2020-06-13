package com.aswdc.archdaily.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc.archdaily.R;
import com.aswdc.archdaily.models.MainFile;

import java.util.ArrayList;

//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import net.simplifiedcoding.retrofitandroidtutorial.R;
//import net.simplifiedcoding.retrofitandroidtutorial.models.User;

public class MainFileAdapter extends RecyclerView.Adapter<MainFileAdapter.UsersViewHolder> {

    private Activity activity;
    private ArrayList<MainFile> mainFiles;

    public MainFileAdapter(Activity activity, ArrayList<MainFile> mainFiles) {
        this.activity = activity;
        this.mainFiles = mainFiles;
    }

//    public HomeEventAdapter(HomeFragment mCtx, List<ListEvent> listEvents) {
//        this.mCtx = mCtx;
//        this.listEvents = listEvents;
//    }


    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate( R.layout.recyclerview_main_file_list, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.txtfileName.setText( mainFiles.get( position ).getMainFilePath() );
    }

    @Override
    public int getItemCount() {
        return mainFiles.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView txtfileName;
        public UsersViewHolder(View itemView) {
            super(itemView);
            txtfileName = itemView.findViewById( R.id.txtfileName );

        }
    }
}
