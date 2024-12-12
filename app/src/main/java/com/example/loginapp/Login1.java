package com.example.loginapp;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login1 extends AppCompatActivity {
    private FirebaseAuth auth;
   private EditText editTextUname,editTextPwd;
    private Button btnlogin,btnnothaveaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        //connect id
        editTextUname=findViewById(R.id.editTextUsername);
        editTextPwd=findViewById(R.id.editTextPassword);
        btnlogin=findViewById(R.id.buttonLogin);
        btnnothaveaccount=findViewById(R.id.buttonnothaveaccount);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get user input
                String uname=editTextUname.getText().toString();
                String pwd=editTextPwd.getText().toString();
                //perform validation
                if (uname.isEmpty()) {
                    editTextUname.setError("Username cannot be empty");
                }
                else if (pwd.isEmpty()) {
                    editTextPwd.setError("Password cannot be empty");
                }
                else{
                    // reistered user enter correct username and password then go to home page
                    auth.signInWithEmailAndPassword(uname,pwd)
                                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                        @Override
                                        public void onSuccess(AuthResult authResult) {
                                            //toast msg
                                            Toast.makeText(Login1.this, "Login Successful...", Toast.LENGTH_SHORT).show();

                                            //intent
                                            Intent intent=new Intent(Login1.this,Userpanel.class);
                                            startActivity(intent);
                                            finish();
                                            editTextUname.setError(null);
                                            editTextPwd.setError(null);

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Login1.this, "Login Failed" , Toast.LENGTH_SHORT).show();
                                }
                            });
                    }
                }
            });
        //do not have account then go to  register
        btnnothaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent
                Intent intent=new Intent(Login1.this,Register.class);
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}