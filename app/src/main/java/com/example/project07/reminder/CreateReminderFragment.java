package com.example.project07.reminder;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project07.R;
import com.example.project07.model.RemindModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateReminderFragment extends Fragment {

    private TextView remind_note;
    private TextView remind_money;
    private TextView remind_date;
    private Button btnSaveRemind;
    private int ID;
    final Calendar myCalendar= Calendar.getInstance();

    public CreateReminderFragment() {
        // Required empty public constructor
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

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        remind_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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

                    // create notification when push LƯU
                    Intent intent = new Intent(getActivity(),ReminderBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),0,intent,0);
                    AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

                    String strDate = remind_date.getText().toString();
                    Calendar cal = Calendar.getInstance();
                    cal = convertStringToCalendar(strDate, "dd-MM-yyyy");
                    cal.set(Calendar.HOUR_OF_DAY,8);
                    cal.set(Calendar.MINUTE,0);
                    cal.set(Calendar.SECOND,0);

                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                            cal.getTimeInMillis(), pendingIntent);

                    //
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            reminderFragment).addToBackStack(null).commit();
                }

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat);
        remind_date.setText(dateFormat.format(myCalendar.getTime()));
    }

    public static Calendar convertStringToCalendar(String time,
                                                   String format) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateformat.parse(time);
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            return calendar;
        }
    }
}