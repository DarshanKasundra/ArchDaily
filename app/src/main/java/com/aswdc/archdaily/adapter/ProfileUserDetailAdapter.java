//package com.aswdc.archdaily.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.aswdc.archdaily.R;
//import com.aswdc.archdaily.models.ProfileDetail;
//import com.aswdc.archdaily.models.UserDetail;
//import com.aswdc.archdaily.storage.SharedPrefManager;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
////import android.support.annotation.NonNull;
////import android.support.v7.widget.RecyclerView;
////import net.simplifiedcoding.retrofitandroidtutorial.R;
////import net.simplifiedcoding.retrofitandroidtutorial.models.User;
//
//public class ProfileUserDetailAdapter extends RecyclerView.Adapter<ProfileUserDetailAdapter.UsersViewHolder> {
//
//    private Context context;
//
//    private ArrayList<ProfileDetail> profileDetails;
//
//    public ProfileUserDetailAdapter(Context context, ArrayList<ProfileDetail> profileDetails) {
//        this.context = context;
//        this.profileDetails = profileDetails;
//    }
//
//    //    public HomeEventAdapter(HomeFragment mCtx, List<ListEvent> listEvents) {
////        this.mCtx = mCtx;
////        this.listEvents = listEvents;
////    }
//
//
//    @NonNull
//    @Override
//    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate( R.layout.recyclerview_profile_detail, parent, false);
//        return new UsersViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
//        SharedPrefManager sfm = SharedPrefManager.getInstance(context);
//        ProfileDetail pd = sfm.getUser();
//
//
//        holder.textProfileUserEmali.setText( pd.getEmail());
////        holder.textUserEmali.setText( pd.get( position ).getEmail() );
//
////        Picasso.with( context ).load( userDetails.get( position ).getProfilePicPath() ).fit().centerCrop().into( holder.imgUserList );
//    }
//    @Override
//    public int getItemCount() {
//        return profileDetails.size();
//    }
//
//    class UsersViewHolder extends RecyclerView.ViewHolder {
//
//        TextView textProfileUserEmali,textUserEmali;
//        CircleImageView imgUserList;
//        public UsersViewHolder(View itemView) {
//            super(itemView);
//
//
////            textUserEmali = itemView.findViewById(R.id.textUserEmali);
//            textProfileUserEmali = itemView.findViewById(R.id.textProfileUserEmali);
////            imgUserList = itemView.findViewById( R.id.imgUserList );
//
//        }
//    }
//}
