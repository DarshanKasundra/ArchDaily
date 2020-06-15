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
import com.aswdc.archdaily.models.SubfilesWithUserDetailHistory;
import com.aswdc.archdaily.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import net.simplifiedcoding.retrofitandroidtutorial.R;
//import net.simplifiedcoding.retrofitandroidtutorial.models.User;
//import com.aswdc.archdaily.models.UserDetail;

public class NavHomeAdapter extends RecyclerView.Adapter<NavHomeAdapter.UsersViewHolder> {

    private Activity context;
    private ArrayList<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistories;

    public NavHomeAdapter(Activity context, ArrayList<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistories) {
        this.context = context;
        this.subfilesWithUserDetailHistories = subfilesWithUserDetailHistories;
    }
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.recyclerview_home, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        EventDetail eventDetail = new EventDetail();
//        UserDetail userDetail = new UserDetail();


        holder.textUserName.setText( subfilesWithUserDetailHistories.get( position ).getName() );

        Picasso.with( context ).load( subfilesWithUserDetailHistories.get( position ).getSubFilePath() ).fit().centerCrop().into( holder.imgProjHome );
    }

    @Override
    public int getItemCount() {
        return subfilesWithUserDetailHistories.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView textUserName ;
        ImageView imgProjHome;
//        Button btnHomeView;
        public UsersViewHolder(View itemView) {
            super(itemView);

//            btnHomeView = itemView.findViewById(R.id.btnHomeView);

            textUserName = itemView.findViewById(R.id.textUserName);
//            textAmount = itemView.findViewById(R.id.textAmount);
//            textStatus = itemView.findViewById(R.id.textStatus);
//            textMaxuser = itemView.findViewById(R.id.textMaxuser);
            imgProjHome = itemView.findViewById( R.id.imgProjHome );

        }
    }
}
