package com.example.project07.expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.project07.R;
import com.example.project07.model.ExpenseModel;

public class AddExpenseFragment extends Fragment {

    private int ID;
    private TextView expenseMoney;
    private TextView expenseDate;
    private TextView expenseNote;
    private Button btnAddExpenseSave;

    public AddExpenseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_exepense, container, false);
        String[] exCategory = getResources().getStringArray(R.array.listExpense);
        Spinner spinner = (Spinner) v.findViewById(R.id.add_expense_spiner_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item, exCategory);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            ID = bundle.getInt("AccID");
        }
        expenseMoney = v.findViewById(R.id.add_expense_money);
        expenseDate = v.findViewById(R.id.add_expense_date);
        expenseNote = v.findViewById(R.id.add_expense_note);
        btnAddExpenseSave = v.findViewById(R.id.btnadd_expense_save);

        btnAddExpenseSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expenseMoney.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Money", Toast.LENGTH_LONG).show();
                } else if (expenseDate.getText().toString().equals(""))
                    Toast.makeText(getContext(), "Date", Toast.LENGTH_LONG).show();
//                else if (remind_date.getText().toString().equals(""))
//                    Toast.makeText(getContext(), "date", Toast.LENGTH_LONG).show();
                else {
                    ExpenseClass expenseClass = new ExpenseClass(expenseMoney.getText().toString(),spinner.getSelectedItemPosition() + 1, expenseNote.getText().toString(), expenseDate.getText().toString(), ID);
                    ExpenseModel expenseModel = new ExpenseModel();
                    expenseModel.addExpense(expenseClass, v.getContext());
                    Toast.makeText(getContext(), "saved", Toast.LENGTH_LONG).show();
                    expenseMoney.setText("");
                    expenseDate.setText("");
                    expenseNote.setText("");

                    ExpenseFragment expenseFragment = new ExpenseFragment(ID);

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            expenseFragment).addToBackStack(null).commit();
                }
            }
        });

        return v;
    }
}
