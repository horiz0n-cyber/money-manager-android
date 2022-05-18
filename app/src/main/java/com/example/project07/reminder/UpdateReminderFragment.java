package com.example.project07.reminder;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.project07.R;
import com.example.project07.model.RemindModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateReminderFragment extends Fragment {
    private int NOTE_ID = 0;
    private int ID;
    private EditText eDate;
    final Calendar myCalendar= Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_reminder, container, false);

        EditText eTitle = (EditText) v.findViewById(R.id.edit_title_reminder);
        EditText eMoney = (EditText) v.findViewById(R.id.edit_money_reminder);
        eDate = v.findViewById(R.id.edit_date_reminder);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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

                // create notification when push LƯU
                Intent intent = new Intent(getActivity(),ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),0,intent,0);
                AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

                String strDate = eDate.getText().toString();
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

    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat);
        eDate.setText(dateFormat.format(myCalendar.getTime()));
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
