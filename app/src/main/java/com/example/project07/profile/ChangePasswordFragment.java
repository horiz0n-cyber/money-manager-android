package com.example.project07.profile;

import static com.example.project07.nhom4.ProcPass.HashPass;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.project07.LoginActivity;
import com.example.project07.MainActivity;
import com.example.project07.R;
import com.example.project07.RegisterActivity;
import com.example.project07.model.AccModel;

public class ChangePasswordFragment extends Fragment {
    View v;
    private String phone;
    private TextView changeOldPass;
    private TextView changeNewPass;
    private TextView changeNewPass2;
    private Button btnSavaChangePass;
    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_change_password, container, false);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            phone = bundle.getString("phone");
        }

        changeOldPass = v.findViewById(R.id.change_old_pass);
        changeNewPass = v.findViewById(R.id.change_new_pass);
        changeNewPass2 = v.findViewById(R.id.change_new_pass_2);
        btnSavaChangePass = v.findViewById(R.id.btnsave_change_pass);

        btnSavaChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccModel accModel = new AccModel();
                if(!changeNewPass.getText().toString().equals(changeNewPass2.getText().toString()))
                    Toast.makeText(v.getContext(), "Khong trung Pass", Toast.LENGTH_LONG).show();
                else if(accModel.checkPass(phone,HashPass(changeOldPass.getText().toString()), v.getContext())){
                    accModel.updatePass(phone,HashPass(changeNewPass.getText().toString()), v.getContext() );
                    Intent intent = new Intent(v.getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        return v;
    }

}
