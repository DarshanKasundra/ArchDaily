package com.aswdc.archdaily.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc.archdaily.R;
import com.aswdc.archdaily.models.UserWinnerList;

import java.util.List;

//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import net.simplifiedcoding.retrofitandroidtutorial.R;
//import net.simplifiedcoding.retrofitandroidtutorial.models.User;

public class UserWinnerListAdapter extends RecyclerView.Adapter<UserWinnerListAdapter.UsersViewHolder> {

    private Activity activity;
    private List<UserWinnerList> userWinnerLists;

    public UserWinnerListAdapter(Activity activity, List<UserWinnerList> userWinnerLists) {
        this.activity = activity;
        this.userWinnerLists = userWinnerLists;
    }

//    public HomeEventAdapter(HomeFragment mCtx, List<ListEvent> listEvents) {
//        this.mCtx = mCtx;
//        this.listEvents = listEvents;
//    }


    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate( R.layout.recyclerview_user_winner_list, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.textUserNameinWinner.setText( userWinnerLists.get( position ).getWinningPrize() );
    }

    @Override
    public int getItemCount() {
        return userWinnerLists.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView textUserNameinWinner;
        public UsersViewHolder(View itemView) {
            super(itemView);
            textUserNameinWinner = itemView.findViewById( R.id.textUserNameinWinner );

        }
    }
}
