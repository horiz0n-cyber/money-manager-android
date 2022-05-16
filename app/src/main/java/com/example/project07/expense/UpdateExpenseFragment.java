package com.example.project07.expense;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.project07.R;
import com.example.project07.income.IncomeClass;
import com.example.project07.income.IncomeFragment;
import com.example.project07.model.ExpenseModel;
import com.example.project07.model.IncomeModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateExpenseFragment extends Fragment {
    private int expenseID;
    private int ID;
    private TextView editExpenseMoney;
    private TextView editExpenseDate;
    private TextView editExpenseNote;
    final Calendar myCalendar= Calendar.getInstance();

    public UpdateExpenseFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update_expense, container, false);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            expenseID = bundle.getInt("expenseID");
            ID = bundle.getInt("AccID");
        }
        ExpenseModel expenseModel = new ExpenseModel();
        ExpenseClass expenseClass = expenseModel.getExpenseByInID(expenseID, v.getContext());

        //Spinner
        String[] exCategory =getResources().getStringArray(R.array.listExpense);
        Spinner spinner = (Spinner) v.findViewById(R.id.edit_expense_spiner_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item, exCategory);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
        editExpenseMoney = v.findViewById(R.id.edit_expense_money);
        editExpenseDate = v.findViewById(R.id.edit_expense_date);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        editExpenseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editExpenseNote = v.findViewById(R.id.edit_expense_note);

        //Button save
        Button btn_save = v.findViewById(R.id.btnedit_expense_save);
        Button btn_delete = v.findViewById(R.id.delete_expense);

        spinner.setSelection(expenseClass.getCate_id()-1);
        editExpenseMoney.setText(expenseClass.getMoney());
        editExpenseDate.setText(expenseClass.getDate());
        editExpenseNote.setText(expenseClass.getNote());

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExpenseClass expenseClass1 = new ExpenseClass(expenseID, editExpenseMoney.getText().toString(),
                        spinner.getSelectedItemPosition() + 1, editExpenseNote.getText().toString(),
                        editExpenseDate.getText().toString());
                expenseModel.updateExpense(expenseClass1, v.getContext());

                ExpenseFragment expenseFragment = new ExpenseFragment(ID);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        expenseFragment).addToBackStack(null).commit();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expenseModel.delete(expenseClass.getOut_id(), v.getContext());

                ExpenseFragment expenseFragment = new ExpenseFragment(ID);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        expenseFragment).addToBackStack(null).commit();
            }
        });

        return v;
    }

    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat);
        editExpenseDate.setText(dateFormat.format(myCalendar.getTime()));
    }
}
