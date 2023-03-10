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
import com.example.project07.model.ExpenseModel;
import com.example.project07.model.IncomeModel;

public class LoginActivity extends AppCompatActivity {

    private TextView phone;
    private TextView passwd;
    private Button btnLogin;
    private Button btnRegLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone = findViewById(R.id.loginPhone);
        passwd = findViewById(R.id.loginPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegLogin = findViewById(R.id.btnRegLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.getText().toString().equals(""))
                    Toast.makeText(LoginActivity.this, "Chua nhap sdt", Toast.LENGTH_LONG).show();
                else if (passwd.getText().toString().equals(""))
                    Toast.makeText(LoginActivity.this, "Chua nhap Pass", Toast.LENGTH_LONG).show();
                else {
                    AccModel accModel = new AccModel();
                    System.out.println("do????");
                    if(accModel.checkPass(phone.getText().toString(),HashPass(passwd.getText().toString()), LoginActivity.this)){
                        Toast.makeText(LoginActivity.this, "Dang nhap thanh cong", Toast.LENGTH_LONG).show();
                        int ID = accModel.getAccID(phone.getText().toString(), LoginActivity.this);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        System.out.println("Id dang nhap" + ID);
//                        IncomeModel incomeModel = new IncomeModel();
//                        incomeModel.MakeId(LoginActivity.this);
//                        ExpenseModel expenseModel = new ExpenseModel();
//                        expenseModel.MakeId(LoginActivity.this);
                        intent.putExtra("AccId", ID);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Sai mat khau", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}