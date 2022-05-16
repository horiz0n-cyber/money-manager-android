package com.example.project07.income;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.telecom.ConnectionService;
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
import com.example.project07.model.AccModel;
import com.example.project07.model.IncomeModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateIncomeFragment extends Fragment {

    private int incomeID;
    private int ID;
    private TextView editIncomeMoney;
    private TextView editIncomeDate;
    private TextView editIncomeNote;
    final Calendar myCalendar= Calendar.getInstance();

    public UpdateIncomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update_income, container, false);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            incomeID = bundle.getInt("incomeID");
            ID = bundle.getInt("AccID");
        }
        IncomeModel incomeModel = new IncomeModel();
        IncomeClass incomeClass = incomeModel.getIncomeByInID(incomeID, v.getContext());

        //Spinner
        String[] exCategory =getResources().getStringArray(R.array.listIncome);
        Spinner spinner = (Spinner) v.findViewById(R.id.edit_income_spiner_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item, exCategory);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //text
        editIncomeMoney = v.findViewById(R.id.edit_income_money);
        editIncomeDate = v.findViewById(R.id.edit_income_date);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        editIncomeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editIncomeNote = v.findViewById(R.id.edit_income_note);

        //Button save
        Button btn_save = v.findViewById(R.id.btnedit_income_save);
        Button btn_delete = v.findViewById(R.id.delete_income);

        spinner.setSelection(incomeClass.getCate_id()-1);
        editIncomeMoney.setText(incomeClass.getMoney());
        editIncomeDate.setText(incomeClass.getDate());
        editIncomeNote.setText(incomeClass.getNote());

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncomeClass incomeClass1 = new IncomeClass(incomeID, editIncomeMoney.getText().toString(),
                        spinner.getSelectedItemPosition() + 1, editIncomeNote.getText().toString(),
                        editIncomeDate.getText().toString());
                incomeModel.updateIncome(incomeClass1, v.getContext());

                IncomeFragment incomeFragment = new IncomeFragment(ID);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        incomeFragment).addToBackStack(null).commit();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incomeModel.delete(incomeClass.getIn_id(), v.getContext());

                IncomeFragment incomeFragment = new IncomeFragment(ID);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        incomeFragment).addToBackStack(null).commit();
            }
        });

        return v;
    }

    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat);
        editIncomeDate.setText(dateFormat.format(myCalendar.getTime()));
    }
}
