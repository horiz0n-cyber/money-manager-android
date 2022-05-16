package com.example.project07;


import static com.example.project07.nhom4.ProcPass.HashPass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project07.model.AccModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    private EditText regName;
    private EditText regPhone;
    private EditText regDate;
    private EditText regPass1;
    private EditText regPass2;
    private Button btnRegAcc;
    private Button btnBackLogin;
    final Calendar myCalendar= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regName = findViewById(R.id.regName);
        regPhone = findViewById(R.id.regPhone);
        regDate = findViewById(R.id.regDate);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        regDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterActivity.this,date,myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        regPass1 = findViewById(R.id.regPass1);
        regPass2 = findViewById(R.id.regPass2);
        btnRegAcc = findViewById(R.id.btnRegAcc);
        btnBackLogin = findViewById(R.id.btnBackLogin);

        btnRegAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(regName.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "Chua nhap ten", Toast.LENGTH_LONG).show();
                }
                else if (regPhone.getText().toString().equals(""))
                    Toast.makeText(RegisterActivity.this, "Chua nhap sdt", Toast.LENGTH_LONG).show();
                else if (!regPass1.getText().toString().equals(regPass2.getText().toString()))
                    Toast.makeText(RegisterActivity.this, "Khong trung Pass", Toast.LENGTH_LONG).show();
                else{
                    AccModel ac = new AccModel();
                    if(ac.  checkPhone(regPhone.getText().toString(), RegisterActivity.this))
                        Toast.makeText(RegisterActivity.this, "Tai khoan da ton tai", Toast.LENGTH_LONG).show();
                    else {
                        ac.addAcc(regName.getText().toString(), regPhone.getText().toString(), HashPass(regPass1.getText().toString()), regDate.getText().toString(), RegisterActivity.this);
                        Toast.makeText(RegisterActivity.this, "Dang ki thanh cong", Toast.LENGTH_LONG).show();
                        regName.setText("");
                        regPhone.setText("");
                        regDate.setText("");
                        regPass1.setText("");
                        regPass2.setText("");
                        Intent intent = new Intent();
                        intent.setClass(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });

        btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat);
        regDate.setText(dateFormat.format(myCalendar.getTime()));
    }
}