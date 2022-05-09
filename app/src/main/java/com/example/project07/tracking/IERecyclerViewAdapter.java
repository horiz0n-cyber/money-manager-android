package com.example.project07.tracking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project07.R;

import java.text.DecimalFormat;
import java.util.List;

public class IERecyclerViewAdapter extends RecyclerView.Adapter<IERecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<IncomeExpense> mData;
    private  OnIEListener mOnIEListener;

    public IERecyclerViewAdapter(Context mContext, List<IncomeExpense> mData, OnIEListener mOnIEListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mOnIEListener = mOnIEListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.ie_row,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v, mOnIEListener);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");

        holder.tv_category.setText(mData.get(position).getCategory());
        holder.tv_money.setText(formatter.format(Double.parseDouble(mData.get(position).getMoney())));
        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_category;
        private TextView tv_money;
        private ImageView img;
        OnIEListener onIEListener;

        public MyViewHolder(View itemView, OnIEListener onIEListener){
            super(itemView);

            tv_category = (TextView) itemView.findViewById(R.id.ex_category);
            tv_money = (TextView) itemView.findViewById(R.id.ex_money);
            img = (ImageView) itemView.findViewById(R.id.ex_img_category);
            this.onIEListener = onIEListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onIEListener.onIEClick(getAdapterPosition());
        }
    }

    public interface OnIEListener{
        void onIEClick(int position);
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
