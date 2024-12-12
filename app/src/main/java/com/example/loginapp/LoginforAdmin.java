package com.example.loginapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginforAdmin extends AppCompatActivity {
    Button btnalogin;
    EditText aeditTextAdmin,aeditTextPwd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginfor_admin);
        btnalogin=findViewById(R.id.abuttonLogin);
        aeditTextAdmin=findViewById(R.id.aeditTextadminname);
        aeditTextPwd=findViewById(R.id.aeditTextPassword);
        btnalogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String admin=aeditTextAdmin.getText().toString();
                String pwd=aeditTextPwd.getText().toString();
                if (admin.isEmpty() || pwd.isEmpty()) {
                    if (admin.isEmpty())
                        aeditTextAdmin.setError("Please fill the detail");
                    else if (pwd.isEmpty())
                        aeditTextPwd.setError("Please fill the detail");
                }
                else if(admin.equals("Parthsinh") && pwd.equals("parth@123")){
                    Toast.makeText(LoginforAdmin.this, "Login Successful for Parthsinh Bapu", Toast.LENGTH_SHORT).show();
                    //intent
                    Intent intent=new Intent(LoginforAdmin.this, Adminpanel.class);
                    startActivity(intent);
                    finish();
                    aeditTextAdmin.setError(null);
                    aeditTextPwd.setError(null);
                }
                else if(admin.equals("Jay") && pwd.equals("jay@123")){
                    Toast.makeText(LoginforAdmin.this, "Login Successful for Jay", Toast.LENGTH_SHORT).show();
                    //intent
                    Intent intent=new Intent(LoginforAdmin.this,Adminpanel.class);
                    startActivity(intent);
                    finish();
                    aeditTextAdmin.setError(null);
                    aeditTextPwd.setError(null);
                }
                else if(admin.equals("Vikas") && pwd.equals("vikas@123")){
                    Toast.makeText(LoginforAdmin.this, "Login Successful for Vikas", Toast.LENGTH_SHORT).show();
                    //intent
                    Intent intent=new Intent(LoginforAdmin.this,Adminpanel.class);
                    startActivity(intent);
                    finish();
                    aeditTextAdmin.setError(null);
                    aeditTextPwd.setError(null);
                }
                else{
                    Toast.makeText(LoginforAdmin.this, "Enter valid details", Toast.LENGTH_SHORT).show();
                }


            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}