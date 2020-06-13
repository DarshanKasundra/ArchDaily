package com.aswdc.archdaily.adapter;

import android.app.Activity;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import net.simplifiedcoding.retrofitandroidtutorial.R;
//import net.simplifiedcoding.retrofitandroidtutorial.models.User;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc.archdaily.Activity.EventDetailActivity;
import com.aswdc.archdaily.R;
import com.aswdc.archdaily.models.EventDetail;
import com.aswdc.archdaily.models.ListEvent;
import com.aswdc.archdaily.models.ProfileDetail;
//import com.aswdc.archdaily.models.UserDetail;
import com.aswdc.archdaily.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NavEventAdapter extends RecyclerView.Adapter<NavEventAdapter.UsersViewHolder> {

    private Activity context;
    private ArrayList<ListEvent> listEvents;

    public NavEventAdapter(Activity context, ArrayList<ListEvent> listEvents) {
        this.context = context;
        this.listEvents = listEvents;
    }
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.recyclerview_nav_event, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        EventDetail eventDetail = new EventDetail();
//        UserDetail userDetail = new UserDetail();




        holder.btnHomeView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPrefManager sfm = SharedPrefManager.getInstance(context);
                ProfileDetail pd = sfm.getUser();

                Intent intent = new Intent( context, EventDetailActivity.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra( eventDetail.getEventId(), listEvents.get(position).getEventId());
//                intent.putExtra( userDetail.getUserId(), listEvents.get(position).getEventId());
                intent.putExtra( String.valueOf( pd.getUserId() ), listEvents.get(position).getEventId());
                context.startActivity(intent);


            }
        } );




        holder.textViewProjectName.setText( listEvents.get( position ).getEventName() );
//        holder.textAmount.setText(listEvents.get( position ).getFees());
        holder.textStatus.setText(listEvents.get(position).getStatus());
//        holder.textMaxuser.setText(listEvents.get(position).getMaxContestant());

        Picasso.with( context ).load( listEvents.get( position ).getMainBannerPath() ).fit().centerCrop().into( holder.imgProjHome );
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView textViewProjectName , textAmount ,textStatus ,textMaxuser ;
        ImageView imgProjHome;
        Button btnHomeView;
        public UsersViewHolder(View itemView) {
            super(itemView);

            btnHomeView = itemView.findViewById(R.id.btnHomeView);

            textViewProjectName = itemView.findViewById(R.id.textViewProjectName);
//            textAmount = itemView.findViewById(R.id.textAmount);
            textStatus = itemView.findViewById(R.id.textStatus);
//            textMaxuser = itemView.findViewById(R.id.textMaxuser);
            imgProjHome = itemView.findViewById( R.id.imgProjHome );

        }
    }
}
