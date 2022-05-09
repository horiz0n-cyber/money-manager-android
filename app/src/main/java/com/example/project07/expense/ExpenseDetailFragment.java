package com.example.project07.expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project07.R;
import com.example.project07.income.IncomeClass;
import com.example.project07.income.UpdateIncomeFragment;
import com.example.project07.model.ExpenseModel;
import com.example.project07.tracking.IEDetailRecyclerViewAdapter;
import com.example.project07.tracking.IncomeExpenseDetail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExpenseDetailFragment extends Fragment implements IEDetailRecyclerViewAdapter.OnIEDetailListener {
    View v;
    private RecyclerView myRecyclerView;
    private ArrayList<IncomeExpenseDetail> listExpenseDetail;
    private int ID;
    private int cate_id;
    private String cateTitle;
    public ExpenseDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_expense_detail, container, false);

        ExpenseModel expenseModel = new ExpenseModel();
        listExpenseDetail = expenseModel.getListExpenseDetail(ID, cate_id, v.getContext());

        Button addExpense = (Button) v.findViewById(R.id.add_expense);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddExpenseFragment addExpenseFragment = new AddExpenseFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, addExpenseFragment,
                        addExpenseFragment.getTag()).addToBackStack(null).commit();
            }
        });
        TextView tv_Category = v.findViewById(R.id.expense_detail_category);
        //get arguments

        tv_Category.setText(cateTitle);
        //RecyclerView of fragment income detail
        myRecyclerView = (RecyclerView) v.findViewById(R.id.list_expense_detail);
        IEDetailRecyclerViewAdapter ieDetailRecyclerViewAdapter = new IEDetailRecyclerViewAdapter(getContext(),listExpenseDetail,this);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(ieDetailRecyclerViewAdapter);

        return v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bind data for recycler view
        Bundle bundle = this.getArguments();
        if(bundle != null){
            cateTitle = bundle.getString("category");
            ID = bundle.getInt("AccID");
            cate_id = bundle.getInt("Cate_id");
        }
    }

    @Override
    public void onIEDetailClick(int position) {
        //Toast.makeText(getContext(),"âsasasasas",Toast.LENGTH_SHORT).show();

        UpdateExpenseFragment updateExpenseFragment = new UpdateExpenseFragment();

        FragmentTransaction manager = getActivity().getSupportFragmentManager().beginTransaction();
        manager.replace(R.id.fragment_container,
                updateExpenseFragment).addToBackStack(null).commit();
    }
}
