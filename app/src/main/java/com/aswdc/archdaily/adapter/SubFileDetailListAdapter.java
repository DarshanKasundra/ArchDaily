package com.aswdc.archdaily.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc.archdaily.Activity.EventDetailActivity;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.models.EventDetail;
import com.aswdc.archdaily.models.ListEvent;
import com.aswdc.archdaily.models.ProfileDetail;
import com.aswdc.archdaily.models.SubFile;
import com.aswdc.archdaily.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import net.simplifiedcoding.retrofitandroidtutorial.R;
//import net.simplifiedcoding.retrofitandroidtutorial.models.User;
//import com.aswdc.archdaily.models.UserDetail;

public class SubFileDetailListAdapter extends RecyclerView.Adapter<SubFileDetailListAdapter.UsersViewHolder> {

    private Activity context;
    private ArrayList<SubFile> subFiles;

    public SubFileDetailListAdapter(Activity context, ArrayList<SubFile> subFiles) {
        this.context = context;
        this.subFiles = subFiles;
    }
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.recyclerview_subfile_detail_list, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        SharedPrefManager sfm = SharedPrefManager.getInstance(context);
        ProfileDetail pd = sfm.getUser();
        Picasso.with( context ).load( subFiles.get( position ).getSubFilePath() ).fit().centerCrop().into( holder.ImgSubfile );
        holder.textUserName.setText( pd.getName() );
    }

    @Override
    public int getItemCount() {
        return subFiles.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView textUserName ;
        ImageView ImgSubfile;

        public UsersViewHolder(View itemView) {
            super(itemView);


            ImgSubfile = itemView.findViewById(R.id.ImgSubfile);
            textUserName = itemView.findViewById(R.id.textUserName);

        }
    }
}
