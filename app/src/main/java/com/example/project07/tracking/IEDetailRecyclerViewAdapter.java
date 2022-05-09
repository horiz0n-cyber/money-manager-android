package com.example.project07.tracking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project07.R;
import com.example.project07.expense.ExpenseClass;
import com.example.project07.income.IncomeClass;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class IEDetailRecyclerViewAdapter extends RecyclerView.Adapter<IEDetailRecyclerViewAdapter.MyViewHolder2> {

    private Context mContext;
    private ArrayList<IncomeExpenseDetail> mData;
    private OnIEDetailListener mOnIEDetailListener;

    public IEDetailRecyclerViewAdapter(Context mContext, ArrayList<IncomeExpenseDetail> mData, OnIEDetailListener mOnIEDetailListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mOnIEDetailListener = mOnIEDetailListener;
    }


    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.ie_detail_row,parent,false);
        MyViewHolder2 vHolder = new MyViewHolder2(v, mOnIEDetailListener);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.ie_money_detail.setText(formatter.format(Double.parseDouble(mData.get(position).getMoney())));
        holder.ie_date_detail.setText(mData.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout ie_detail_row;
        private TextView ie_money_detail;
        private TextView ie_date_detail;
        OnIEDetailListener onIEDetailListener;

        public MyViewHolder2(@NonNull View itemView, OnIEDetailListener onIEDetailListener) {
            super(itemView);

            ie_detail_row = (LinearLayout) itemView.findViewById(R.id.ie_detail_row);
            ie_money_detail = (TextView) itemView.findViewById(R.id.ie_money_detail);
            ie_date_detail = (TextView) itemView.findViewById(R.id.ie_date_detail);
            this.onIEDetailListener = onIEDetailListener;

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            onIEDetailListener.onIEDetailClick(getAdapterPosition());
        }
    }

    public interface OnIEDetailListener{
        void onIEDetailClick(int position);
    }
}
