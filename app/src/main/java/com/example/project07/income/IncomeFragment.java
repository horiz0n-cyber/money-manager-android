package com.example.project07.income;

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
import com.example.project07.model.IncomeModel;
import com.example.project07.tracking.IERecyclerViewAdapter;
import com.example.project07.tracking.IncomeExpense;

import java.util.ArrayList;
import java.util.List;

public class IncomeFragment extends Fragment implements IERecyclerViewAdapter.OnIEListener {

    View v;
    private RecyclerView myRecyclerView;
    private List<IncomeExpense> listIncome;
    private int ID;

    public IncomeFragment(int ID) {
        this.ID = ID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_income, container, false);

        IncomeModel inComeModel = new IncomeModel();
        //bind data for recycler view
        String[] arr = getResources().getStringArray(R.array.listIncome);
        int[] arrImg = {R.drawable.ic_briefcase_solid, R.drawable.ic_bitcoin_brands};
        listIncome = new ArrayList<>();
        for (int i=0; i< arr.length; i++){
            listIncome.add(new IncomeExpense(arr[i], inComeModel.getAdvByCate(ID,i+1,v.getContext()), arrImg[i]));
        }

        //button add income
        Button addBtn = (Button) v.findViewById(R.id.add_income);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddIncomeFragment addIncomeFragment = new AddIncomeFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("AccID", ID);
                addIncomeFragment.setArguments(bundle);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, addIncomeFragment,
                        addIncomeFragment.getTag()).addToBackStack(null).commit();
            }
        });
        //RecyclerView of fragment income
        myRecyclerView = (RecyclerView) v.findViewById(R.id.list_income);
        IERecyclerViewAdapter IERecyclerViewAdapter = new IERecyclerViewAdapter(getContext(), listIncome, this);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(IERecyclerViewAdapter);

        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onIEClick(int position) {
//        Toast.makeText(getContext(),"Test Click"+String.valueOf(position),
//                Toast.LENGTH_SHORT).show();
        //put argument
        String title = listIncome.get(position).getCategory();
        Bundle bundle = new Bundle();
        bundle.putString("category", title);
        bundle.putInt("AccID", ID);
        bundle.putInt("Cate_id", position+1);

        IncomeDetailFragment incomeDetailFragment = new IncomeDetailFragment();
        incomeDetailFragment.setArguments(bundle);

        FragmentTransaction manager = getActivity().getSupportFragmentManager().beginTransaction();
        manager.replace(R.id.fragment_container,
                incomeDetailFragment).addToBackStack(null).commit();
    }
}
