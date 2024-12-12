package com.example.loginapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Adminpanel extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adminpanel);

        bottomNavigationView=findViewById(R.id.bottomNavView);
        frameLayout= findViewById(R.id.frameLayout);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int itemId=item.getItemId();

                if (itemId==R.id.navhome){
                    loadFragment(new fragment_home(),false);
                }   //menu folder add it so conatct jay
                else if (itemId ==R.id.navadd) {
                    loadFragment(new fragment_add(),false);
                } else if (itemId == R.id.navcontactus) {
                        loadFragment(new fragment_contactus(),false);
                }
                else
                    loadFragment(new fragment_logout(),false);


                    //return true;
            }
        });

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        if (isAppInitialized){
            fragmentTransaction.replace(R.id.frameLayout,fragment);
        }
        else{
            fragmentTransaction.replace(R.id.frameLayout,fragment);
        }
            fragmentTransaction.commit();
    }
}