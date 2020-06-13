//package com.aswdc.archdaily.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.aswdc.archdaily.R;
//import com.example.metrimony.R;
//import com.example.metrimony.model.StateModel;
//
//import java.util.ArrayList;
//
//public class PlanTypeAdapter extends BaseAdapter {
//    Context context;
//    ArrayList<StateModel> stateList;
//    public PlanTypeAdapter(Context context, ArrayList<StateModel> stateList){
//        this.context=context;
//        this.stateList=stateList;
//    }
//    @Override
//    public int getCount() {
//        return stateList.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View contentView, ViewGroup parent) {
//        View v = contentView;
//        ViewHolder viewHolder;
//        if(v == null)
//        {
//            v= LayoutInflater.from(context).inflate( R.layout.view_row_spinner_plan_type,null);
//            viewHolder=new ViewHolder();
//            viewHolder.tvViewRow=v.findViewById(R.id.tvViewRow);
//            v.setTag(viewHolder);
//        }
//        else {
//            viewHolder= (ViewHolder) v.getTag();
//        }
//        viewHolder.tvViewRow.setText(stateList.get(position).getStateName());
//        return v;
//    }
//
//    class ViewHolder{
//        TextView tvViewRow;
//    }
//}
