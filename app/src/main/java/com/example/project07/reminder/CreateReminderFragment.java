package com.example.project07.reminder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project07.R;
import com.example.project07.model.RemindModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateReminderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateReminderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView remind_note;
    private TextView remind_money;
    private TextView remind_date;
    private Button btnSaveRemind;
    private int ID;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateReminderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateReminderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateReminderFragment newInstance(String param1, String param2) {
        CreateReminderFragment fragment = new CreateReminderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_create_reminder, container, false);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            ID = bundle.getInt("AccID");
        }
        System.out.println(ID);

        remind_note = rootView.findViewById(R.id.remindCreateNote);
        remind_money = rootView.findViewById(R.id.remindCreateMoney);
        remind_date = rootView.findViewById(R.id.remindCreateDate);
        btnSaveRemind = (Button) rootView.findViewById(R.id.btnSaveCreate);

        btnSaveRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Test!!!!");
                if (remind_note.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "note", Toast.LENGTH_LONG).show();
                } else if (remind_money.getText().toString().equals(""))
                    Toast.makeText(getContext(), "money", Toast.LENGTH_LONG).show();
                else if (remind_date.getText().toString().equals(""))
                    Toast.makeText(getContext(), "date", Toast.LENGTH_LONG).show();
                else {
                    RemindModel nc = new RemindModel();
                    nc.addNoti(remind_note.getText().toString(), remind_money.getText().toString(), remind_date.getText().toString(), ID, getContext());
                    Toast.makeText(getContext(), "saved", Toast.LENGTH_LONG).show();
                    remind_note.setText("");
                    remind_money.setText("");
                    remind_date.setText("");

                    ReminderFragment reminderFragment = new ReminderFragment(ID);

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            reminderFragment).addToBackStack(null).commit();
                }

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

}