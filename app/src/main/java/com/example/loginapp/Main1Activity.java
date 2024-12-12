package com.example.loginapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Main1Activity extends AppCompatActivity {
    ImageView i1;
    TextView t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main1);

        i1=findViewById(R.id.img1);
        t1=findViewById(R.id.textView);


        i1.animate().alpha(0f).setDuration(0);
        t1.animate().alpha(0f).setDuration(0);


        i1.animate().alpha(1f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                t1.animate().alpha(1f).setDuration(800);

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Main1Activity.this,LoginforAdmin.class);
                startActivity(i);
                finish();

            }
        },3000);
    }
}