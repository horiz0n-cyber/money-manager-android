package com.example.project07.expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project07.R;

public class UpdateExpenseFragment extends Fragment {
    public UpdateExpenseFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update_expense, container, false);

        //Spinner
        String[] exCategory =getResources().getStringArray(R.array.listExpense);
        Spinner spinner = (Spinner) v.findViewById(R.id.edit_expense_spiner_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item, exCategory);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Button save
        Button btn_save = v.findViewById(R.id.btnedit_expense_save);
        Button btn_delete = v.findViewById(R.id.delete_expense);

        return v;
    }
}
