package com.example.project07.income;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project07.R;
import com.example.project07.model.IncomeModel;
import com.example.project07.tracking.IEDetailRecyclerViewAdapter;
import com.example.project07.tracking.IncomeExpenseDetail;

import java.util.ArrayList;

public class IncomeDetailFragment extends Fragment implements IEDetailRecyclerViewAdapter.OnIEDetailListener {

    View v;
    private RecyclerView myRecyclerView;
    private ArrayList<IncomeExpenseDetail> listIncomeDetail;
    private int ID;
    private int cate_id;
    private String cateTitle;
    public IncomeDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_income_detail, container, false);

        IncomeModel inComeModel = new IncomeModel();
        listIncomeDetail = inComeModel.getListIncomeDetail(ID, cate_id, v.getContext());

        //button add income
        Button addBtn = (Button) v.findViewById(R.id.add_income);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddIncomeFragment addIncomeFragment = new AddIncomeFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, addIncomeFragment,
                        addIncomeFragment.getTag()).addToBackStack(null).commit();
            }
        });

        TextView tv_Category = v.findViewById(R.id.detail_category);
        //get arguments

        tv_Category.setText(cateTitle);

        //RecyclerView of fragment income detail
        myRecyclerView = (RecyclerView) v.findViewById(R.id.list_income_detail);
        IEDetailRecyclerViewAdapter ieDetailRecyclerViewAdapter = new IEDetailRecyclerViewAdapter(getContext(),listIncomeDetail, this);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(ieDetailRecyclerViewAdapter);

        return v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get data
        Bundle bundle = this.getArguments();
        if(bundle != null){
            cateTitle = bundle.getString("category");
            ID = bundle.getInt("AccID");
            cate_id = bundle.getInt("Cate_id");
        }
    }

    @Override
    public void onIEDetailClick(int position) {
        Toast.makeText(getContext(),"ABC",Toast.LENGTH_SHORT).show();
    }
}
