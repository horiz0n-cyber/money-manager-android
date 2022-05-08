package com.example.project07.expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project07.R;
import com.example.project07.income.AddIncomeFragment;
import com.example.project07.tracking.IERecyclerViewAdapter;
import com.example.project07.tracking.IncomeExpense;
import com.example.project07.income.IncomeDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class ExpenseFragment extends Fragment implements IERecyclerViewAdapter.OnIEListener {

    View v;
    private RecyclerView myRecyclerView;
    private List<IncomeExpense> listExpense;

    public ExpenseFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_expense, container, false);

        //button add income
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

        myRecyclerView = (RecyclerView) v.findViewById(R.id.list_expense);
        IERecyclerViewAdapter IERecyclerViewAdapter = new IERecyclerViewAdapter(getContext(), listExpense, this);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(IERecyclerViewAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listExpense = new ArrayList<>();
        listExpense.add(new IncomeExpense("ĂN UỐNG", "2.000.000", R.drawable.ic_utensils_solid));
        listExpense.add(new IncomeExpense("SỨC KHOẺ", "2.000.000", R.drawable.ic_heart_solid));
        listExpense.add(new IncomeExpense("HOÁ ĐƠN", "2.000.000", R.drawable.ic_file_invoice_solid));
        listExpense.add(new IncomeExpense("XĂNG XE", "2.000.000", R.drawable.ic_gas_pump_solid));
        listExpense.add(new IncomeExpense("MUA SẮM", "2.000.000", R.drawable.ic_cart_shopping_solid__1_));
        listExpense.add(new IncomeExpense("KHÁC", "2.000.000", R.drawable.ic_gamepad_solid));
    }

    @Override
    public void onIEClick(int position) {
//        Toast.makeText(getContext(),"XYZ",Toast.LENGTH_SHORT).show();
        //put argument
        String title = listExpense.get(position).getCategory();
        Bundle bundle = new Bundle();
        bundle.putString("category", title);

        ExpenseDetailFragment expenseDetailFragment = new ExpenseDetailFragment();
        expenseDetailFragment.setArguments(bundle);

        FragmentTransaction manager = getActivity().getSupportFragmentManager().beginTransaction();
        manager.replace(R.id.fragment_container,
                expenseDetailFragment).addToBackStack(null).commit();
    }
}
