package com.aswdc.archdaily.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc.archdaily.Activity.ActivitySubFileDetailList;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.models.AllWinnerList;
import com.aswdc.archdaily.models.SubFile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import net.simplifiedcoding.retrofitandroidtutorial.R;
//import net.simplifiedcoding.retrofitandroidtutorial.models.User;

public class AllWinnerListAdapter extends RecyclerView.Adapter<AllWinnerListAdapter.UsersViewHolder> {

    private Activity activity;
    private ArrayList<AllWinnerList> allWinnerLists;

    public AllWinnerListAdapter(Activity activity, ArrayList<AllWinnerList> allWinnerLists) {
        this.activity = activity;
        this.allWinnerLists = allWinnerLists;
    }

//    public HomeEventAdapter(HomeFragment mCtx, List<ListEvent> listEvents) {
//        this.mCtx = mCtx;
//        this.listEvents = listEvents;
//    }


    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate( R.layout.recyclerview_all_winner_list, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.textUserNameinWinner.setText( allWinnerLists.get( position ).getName() );
    }

    @Override
    public int getItemCount() {
        return allWinnerLists.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView textUserNameinWinner;
        public UsersViewHolder(View itemView) {
            super(itemView);
            textUserNameinWinner = itemView.findViewById( R.id.textUserNameinWinner );

        }
    }
}
