package com.example.project07.profile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project07.R;
import com.example.project07.RegisterActivity;
import com.example.project07.model.AccModel;
import com.example.project07.nhom4.AccClass;
import com.example.project07.reminder.CreateReminderFragment;
import com.example.project07.reminder.ReminderFragment;
import com.example.project07.reminder.ReminderRecyclerViewAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProfileFragment extends Fragment {

    View v;
    Button changePass;
    private int ID;
    private TextView profileName;
    private TextView profilePhone;
    private TextView profileDob;
    private Button btnSaveProfile;
    final Calendar myCalendar= Calendar.getInstance();

    AccClass accClass = new AccClass();

    public ProfileFragment(int ID) {
        this.ID = ID;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);

        profileName = v.findViewById(R.id.edit_name);
        profilePhone = v.findViewById(R.id.edit_phone);
        profileDob = v.findViewById(R.id.edit_dob);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        profileDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        btnSaveProfile = v.findViewById(R.id.btn_save_profile);


        AccModel accModel = new AccModel();

        accClass = accModel.getProfile(ID, v.getContext());

        profileName.setText(accClass.getName());
        profilePhone.setText(accClass.convertPhone(accClass.getPhone()));
        profileDob.setText(accClass.getDate());

        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(profileName.getText().toString().equals("")){
                    Toast.makeText(v.getContext(), "Chua nhap ten", Toast.LENGTH_LONG).show();
                }
                else if (profileDob.getText().toString().equals(""))
                    Toast.makeText(v.getContext(), "Ngay sinh", Toast.LENGTH_LONG).show();
                else {
                    accModel.updateProfile(ID, profileName.getText().toString(), profileDob.getText().toString(), v.getContext());

                    ReminderFragment reminderFragment = new ReminderFragment(ID);

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            reminderFragment).addToBackStack(null).commit();
                }
            }
        });

        changePass = v.findViewById(R.id.btnchange_pass);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("phone", accClass.getPhone());
                ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
                changePasswordFragment.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, changePasswordFragment,
                        changePasswordFragment.getTag()).addToBackStack(null).commit();
            }
        });

        return v;
    }

    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat);
        profileDob.setText(dateFormat.format(myCalendar.getTime()));
    }
}
