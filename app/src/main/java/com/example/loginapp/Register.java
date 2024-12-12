package com.example.loginapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Register extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText editTextname,editTextuname,editTextpwd,editTextcpwd;
    private Button btnregister;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        auth=FirebaseAuth.getInstance();
        //connect id
        editTextname=findViewById(R.id.editTextName);
        editTextuname=findViewById(R.id.editTextUsernameR);
        editTextpwd=findViewById(R.id.editTextPasswordR);
        editTextcpwd=findViewById(R.id.editTextConfirmPassword);
        btnregister=findViewById(R.id.buttonRegister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=editTextname.getText().toString();
                String UName=editTextuname.getText().toString();
                String Pwd=editTextpwd.getText().toString();
                String Cpwd=editTextcpwd.getText().toString();

                if (Name.isEmpty()){
                    editTextname.setError("Name cannot be empty");
                }
                else if (UName.isEmpty()){
                    editTextuname.setError("UserName cannot be empty");
                }
                else if (Pwd.isEmpty()) {
                    editTextpwd.setError("Password cannot be empty");
                }
                else if (Cpwd.isEmpty()) {
                    editTextcpwd.setError("ConfirmPassword cannot be empty");
                }
                else  if (Pwd.equals(Cpwd)) {
                    auth.createUserWithEmailAndPassword(UName,Pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Register.this,"Registration Successful...", Toast.LENGTH_SHORT).show();
                                // go to login page intent
                                Intent intent=new Intent(Register.this, Login1.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(Register.this, "Failed Register :" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else {
                        Toast.makeText(Register.this, "Password and Cofirm Password must be same", Toast.LENGTH_SHORT).show();
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