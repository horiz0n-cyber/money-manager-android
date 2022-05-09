package com.example.project07;


import static com.example.project07.nhom4.ProcPass.HashPass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project07.model.AccModel;

public class RegisterActivity extends AppCompatActivity {

    private TextView regName;
    private TextView regPhone;
    private TextView regDate;
    private TextView regPass1;
    private TextView regPass2;
    private Button btnRegAcc;
    private Button btnBackLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regName = findViewById(R.id.regName);
        regPhone = findViewById(R.id.regPhone);
        regDate = findViewById(R.id.regDate);
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
}