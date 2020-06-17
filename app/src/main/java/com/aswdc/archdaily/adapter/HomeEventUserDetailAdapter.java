package com.aswdc.archdaily.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc.archdaily.Activity.EventDetailActivity;
import com.aswdc.archdaily.Activity.ListOfEventOfUserDetail;
import com.aswdc.archdaily.Activity.PopActivity;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.api.Api;
import com.aswdc.archdaily.api.RetrofitClient;
import com.aswdc.archdaily.models.ApiResponse;
import com.aswdc.archdaily.models.EventDetail;
import com.aswdc.archdaily.models.ListUserEvent;
import com.aswdc.archdaily.models.ProfileDetail;
import com.aswdc.archdaily.models.TotalVote;
//import com.aswdc.archdaily.models.UserDetail;
import com.aswdc.archdaily.models.UserList;
import com.aswdc.archdaily.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import net.simplifiedcoding.retrofitandroidtutorial.R;
//import net.simplifiedcoding.retrofitandroidtutorial.models.User;

public class HomeEventUserDetailAdapter extends RecyclerView.Adapter<HomeEventUserDetailAdapter.UsersViewHolder> {

    private Context context;
    private ArrayList<UserList> userLists;
//    private ArrayList<TotalVote> totalVotes;
    int eventID;

    public HomeEventUserDetailAdapter(Context context, ArrayList<UserList> userLists, int eventID) {
        this.context = context;
        this.userLists = userLists;
//        this.totalVotes =  totalVotes;
        this.eventID = eventID;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.recyclerview_event_user_detail, parent, false );
        return new UsersViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.textUserName.setText( userLists.get( position ).getName() );
        holder.textUserEmali.setText( userLists.get( position ).getEmail() );
        holder.textVoteCount.setText( userLists.get( position ).getTotalVote() );
        Picasso.with( context ).load( userLists.get( position ).getProfilePicPath() ).fit().centerCrop().into( holder.imgUserList );

        holder.textUserName.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListUserEvent listUserEvent = new ListUserEvent();
                EventDetail eventDetail = new EventDetail();

                Intent intent = new Intent( context, ListOfEventOfUserDetail.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra( eventDetail.getEventId(),  eventID);
                intent.putExtra( listUserEvent.getUserId(), userLists.get(position).getUserId());
                context.startActivity(intent);
            }
        } );

        holder.linearlayoutvotebutton.setOnClickListener( view -> {
            SharedPrefManager sfm1 = SharedPrefManager.getInstance( context );
            ProfileDetail pd1 = sfm1.getUser();
            Api api = RetrofitClient.getApi().create( Api.class );
            Call<ApiResponse> call = api.addVote( String.valueOf( SharedPrefManager.getInstance( context ).getUser().getUserId() ),
                    String.valueOf( userLists.get( position ).getUserId() ),
                    String.valueOf( eventID ), 1 );
            call.enqueue( new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    ArrayList<TotalVote> totalVotes = (ArrayList<TotalVote>) response.body().getResData().getTotalVotes();
                    if (response.body().getResCode() == 1){
                        holder.textVoteCount.setText( totalVotes.get(0).getTotalVote());
                        Toast.makeText( context, response.body().getResMessage(), Toast.LENGTH_LONG ).show();
                    }
                    else {
                        Toast.makeText( context, response.body().getResMessage(), Toast.LENGTH_LONG ).show();
                    }
                }
                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText( context, "already voted", Toast.LENGTH_LONG ).show();
                }
            } );
        } );
    }

    @Override
    public int getItemCount() {
        return userLists.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView textUserName, textUserEmali, textVoteCount;
        LinearLayout linearlayoutvotebutton;
        CircleImageView imgUserList;

        public UsersViewHolder(View itemView) {
            super( itemView );
            textVoteCount = itemView.findViewById( R.id.textVoteCount );
            linearlayoutvotebutton = itemView.findViewById( R.id.linearlayoutvotebutton );
            textUserEmali = itemView.findViewById( R.id.textUserEmali );
            textUserName = itemView.findViewById( R.id.textUserName );
            imgUserList = itemView.findViewById( R.id.imgUserList );

        }
    }
}
