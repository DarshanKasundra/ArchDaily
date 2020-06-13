//package com.aswdc.archdaily.adapter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.provider.SyncStateContract;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.aswdc.archdaily.Fragment.ProfileFragment;
//import com.aswdc.archdaily.R;
//import com.aswdc.archdaily.models.ApiResponse;
//import com.aswdc.archdaily.models.EventDetail;
//import com.aswdc.archdaily.models.ListEvent;
//import com.aswdc.archdaily.models.ProfileDetail;
//import com.aswdc.archdaily.models.UserDetail;
//import com.squareup.picasso.Picasso;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Callback;
//
////import android.support.annotation.NonNull;
////import android.support.v7.widget.RecyclerView;
////import net.simplifiedcoding.retrofitandroidtutorial.R;
////import net.simplifiedcoding.retrofitandroidtutorial.models.User;
//
//public class HomeEventDetailAdapter extends RecyclerView.Adapter<HomeEventDetailAdapter.UsersViewHolder> {
//
//    private Context context;
//    private ArrayList<EventDetail> eventDetails;
//
//    public HomeEventDetailAdapter(Context context, ArrayList<EventDetail> eventDetails) {
//        this.context = context;
//        this.eventDetails = eventDetails;
//    }
//
////    public HomeEventAdapter(HomeFragment mCtx, List<ListEvent> listEvents) {
////        this.mCtx = mCtx;
////        this.listEvents = listEvents;
////    }
//
//
//    @NonNull
//    @Override
//    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate( R.layout.recyclerview_poroject_detail, parent, false);
//        return new UsersViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
//        EventDetail eventDetail = eventDetails.get(position);
////        holder.tvName.setText(moduleList.get(i).getModule_name());
//
//        holder.textStartDate.setText( eventDetails.get( position ).getStartDate() );
//
//
//
//        holder.textEventName.setText( eventDetails.get( position ).getEventName() );
//        holder.textFees.setText( eventDetails.get( position ).getFees() );
//        holder.textStatus.setText( eventDetails.get( position ).getStatus() );
//        holder.textEndDate.setText( eventDetails.get( position ).getEndDate() );
//        holder.textDescrotion.setText( eventDetails.get( position ).getDescription() );
////        holder.textUserName.setText( userDetails.get( position ).getName() );
//
//
//        Picasso.with( context ).load( eventDetails.get( position ).getMainBannerPath() ).fit().centerCrop().into( holder.imgProjHome );
//
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//        return eventDetails.size();
//    }
//
//    class UsersViewHolder extends RecyclerView.ViewHolder {
//
//        TextView textEventName,textFees,textStatus,textStartDate,textEndDate,textDescrotion,textUserName;
//        ImageView imgProjHome;
//        public UsersViewHolder(View itemView) {
//            super(itemView);
//
//            textEventName = itemView.findViewById(R.id.textEventName);
//            textFees = itemView.findViewById(R.id.textFees);
//            textStatus = itemView.findViewById(R.id.textStatus);
//            textStartDate = itemView.findViewById(R.id.textStartDate);
//            textEndDate = itemView.findViewById(R.id.textEndDate);
//            textDescrotion = itemView.findViewById(R.id.textDescrotion);
//            textUserName = itemView.findViewById(R.id.textUserName);
//            imgProjHome = itemView.findViewById( R.id.imgProjHome );
//
//        }
//    }
//}
