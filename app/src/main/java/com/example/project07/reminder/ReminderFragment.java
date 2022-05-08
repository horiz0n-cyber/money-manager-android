package com.example.project07.reminder;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.project07.R;
import com.example.project07.model.RemindModel;

import java.util.ArrayList;
import java.util.List;

public class ReminderFragment extends Fragment {

    View v;
    private RecyclerView myRecyclerView;
    ArrayList<RemindClass> listReminder;
    private int ID;

    public ReminderFragment(int ID) {
        // Required empty public constructor
        this.ID = ID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_reminder, container, false);
        myRecyclerView = (RecyclerView) v.findViewById(R.id.list_reminder);

        RemindModel remindModel = new RemindModel();
        listReminder = new ArrayList<>();
        listReminder = remindModel.getListNoti(this.ID, v.getContext());

        ReminderRecyclerViewAdapter reminderRecyclerViewAdapter = new ReminderRecyclerViewAdapter(getContext(), listReminder, ID);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(reminderRecyclerViewAdapter);

        Button addbutton = (Button) v.findViewById(R.id.imageButton3);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("test!!!");
                CreateReminderFragment createReminderFragment = new CreateReminderFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("AccID", ID);
                createReminderFragment.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, createReminderFragment,
                        createReminderFragment.getTag()).addToBackStack(null).commit();
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}