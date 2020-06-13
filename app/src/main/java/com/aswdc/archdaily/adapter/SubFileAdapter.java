package com.aswdc.archdaily.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc.archdaily.Activity.ActivitySubFileDetailList;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.models.SubFile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import net.simplifiedcoding.retrofitandroidtutorial.R;
//import net.simplifiedcoding.retrofitandroidtutorial.models.User;

public class SubFileAdapter extends RecyclerView.Adapter<SubFileAdapter.UsersViewHolder> {

    private Activity activity;
    private ArrayList<SubFile> subFiles;

    public SubFileAdapter(Activity activity, ArrayList<SubFile> subFiles) {
        this.activity = activity;
        this.subFiles = subFiles;
    }

//    public HomeEventAdapter(HomeFragment mCtx, List<ListEvent> listEvents) {
//        this.mCtx = mCtx;
//        this.listEvents = listEvents;
//    }


    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate( R.layout.recyclerview_sub_file_list, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        Picasso.with(activity).load( subFiles.get( position ).getSubFilePath() ).fit().centerCrop().into( holder.imageView );
        holder.imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( activity, ActivitySubFileDetailList.class );
                activity.startActivity( intent );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return subFiles.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public UsersViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById( R.id.imageView );

        }
    }
}
