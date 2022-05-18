package com.example.project07.reminder;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project07.R;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReminderRecyclerViewAdapter extends RecyclerView.Adapter<ReminderRecyclerViewAdapter.MyViewHolder1> {

    Context mContext;
    ArrayList<RemindClass> mData;
    private int ID;

    public ReminderRecyclerViewAdapter(Context mContext, ArrayList<RemindClass> mData, int ID) {
        this.mContext = mContext;
        this.mData = mData;
        this.ID = ID;
    }


    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.reminder_row,parent,false);
        MyViewHolder1 vHolder = new MyViewHolder1(v);

//        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
//        String date = df.format(Calendar.getInstance().getTime());
//
//        if(vHolder.tv_date.getText().toString() == date){
//
//        }
        vHolder.reminder_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //put Arguments
                String title = vHolder.tv_title.getText().toString();
                String money = vHolder.tv_money.getText().toString();
                String date = vHolder.tv_date.getText().toString();
                int NOTE_ID = vHolder.NOTI_ID;

                money = ReplaceSymbols(money);

                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("money",money);
                bundle.putString("date", date);
                bundle.putInt("NOTE_ID", NOTE_ID);
                bundle.putInt("AccID", ID);

                UpdateReminderFragment updateReminderFragment = new UpdateReminderFragment();
                updateReminderFragment.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        updateReminderFragment).addToBackStack(null).commit();
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.tv_title.setText(mData.get(position).getNote());
        holder.tv_money.setText(formatter.format(Double.parseDouble(mData.get(position).getMoney())));
        holder.tv_date.setText(mData.get(position).getDate());
        holder.NOTI_ID = mData.get(position).getNoti_id();
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder1 extends RecyclerView.ViewHolder{

        private RelativeLayout reminder_row;
        private TextView tv_title;
        private TextView tv_money;
        private TextView tv_date;
        private int NOTI_ID;

        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);

            reminder_row = (RelativeLayout) itemView.findViewById(R.id.reminder_row_id);
            tv_title = (TextView) itemView.findViewById(R.id.remider_title);
            tv_money = (TextView) itemView.findViewById(R.id.remider_money);
            tv_date = (TextView) itemView.findViewById(R.id.reminder_date);

        }
    }

    public String ReplaceSymbols(String str){
        String[] arr = str.split(",");
        String money = "";
        for ( int i=0; i< arr.length; i++){
            money += arr[i];
        }
        return money;
    }

}
