package com.example.project07.reminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.project07.R;
import com.example.project07.model.RemindModel;

public class UpdateReminderFragment extends Fragment {
    private int NOTE_ID = 0;
    private int ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_reminder, container, false);

        EditText eTitle = (EditText) v.findViewById(R.id.edit_title_reminder);
        EditText eMoney = (EditText) v.findViewById(R.id.edit_money_reminder);
        EditText eDate = (EditText) v.findViewById(R.id.edit_date_reminder);
        Button eSave = (Button) v.findViewById(R.id.btnSaveUpdate);
        Button eDelete = (Button) v.findViewById(R.id.btnRemindDelete);

        //get arguments
        Bundle bundle = this.getArguments();
        if(bundle != null){
            eTitle.setText(bundle.getString("title").toString());
            eMoney.setText(bundle.getString("money").toString());
            eDate.setText(bundle.getString("date").toString());
            NOTE_ID = bundle.getInt("NOTE_ID");
            ID = bundle.getInt("AccID");
        }

        eSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemindModel remindModel = new RemindModel();
                remindModel.updateNoti(NOTE_ID ,eTitle.getText().toString(), eMoney.getText().toString(), eDate.getText().toString(), getContext() );

                ReminderFragment reminderFragment = new ReminderFragment(ID);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        reminderFragment).addToBackStack(null).commit();
            }
        });

        eDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Test!!!");
                RemindModel remindModel = new RemindModel();
                remindModel.delete(NOTE_ID, getContext());

                ReminderFragment reminderFragment = new ReminderFragment(ID);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        reminderFragment).addToBackStack(null).commit();
            }
        });

        return v;
    }
}
