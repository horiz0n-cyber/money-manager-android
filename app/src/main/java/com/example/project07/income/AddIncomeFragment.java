package com.example.project07.income;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.project07.R;
import com.example.project07.model.IncomeModel;

public class AddIncomeFragment extends Fragment {

    private int ID;
    private TextView incomeMoney;
    private TextView incomeDate;
    private TextView incomeNote;
    private Button btnAddIncomeSave;

    public AddIncomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_income, container, false);
        String[] exCategory =getResources().getStringArray(R.array.listIncome);
        Spinner spinner = (Spinner) v.findViewById(R.id.add_income_spiner_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item, exCategory);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            ID = bundle.getInt("AccID");
        }
        incomeMoney = v.findViewById(R.id.add_income_money);
        incomeDate = v.findViewById(R.id.add_income_date);
        incomeNote = v.findViewById(R.id.add_income_note);
        btnAddIncomeSave = v.findViewById(R.id.btnadd_income_save);

        btnAddIncomeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (incomeMoney.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Money", Toast.LENGTH_LONG).show();
                } else if (incomeDate.getText().toString().equals(""))
                    Toast.makeText(getContext(), "Date", Toast.LENGTH_LONG).show();
//                else if (remind_date.getText().toString().equals(""))
//                    Toast.makeText(getContext(), "date", Toast.LENGTH_LONG).show();
                else {
                    IncomeClass incomeClass = new IncomeClass(incomeMoney.getText().toString(),spinner.getSelectedItemPosition() + 1, incomeNote.getText().toString(), incomeDate.getText().toString(), ID);
                    IncomeModel inComeModel = new IncomeModel();
                    inComeModel.addIncome(incomeClass, v.getContext());
                    Toast.makeText(getContext(), "saved", Toast.LENGTH_LONG).show();
                    incomeMoney.setText("");
                    incomeDate.setText("");
                    incomeNote.setText("");

                    IncomeFragment incomeFragment = new IncomeFragment(ID);

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            incomeFragment).addToBackStack(null).commit();
                }
            }
        });

        return  v;
    }
}
