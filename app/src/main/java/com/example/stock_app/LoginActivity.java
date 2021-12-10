package com.example.stock_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity implements PassUserData {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // hide the upper app bar
        getSupportActionBar().hide();

        tabLayout = findViewById(R.id.tab_Layout);
        viewPager = findViewById(R.id.view_pager);

        //create the shown Tabs (Login & Signup)
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //create connection to the viewpager with adapter
        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(),this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.i("TAG", "onTabSelected: " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabUnselected: " + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabReselected: " + tab.getPosition());
            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void passUserData(String firstName, String lastName, String email, int id) {
        
        // access the ApplicationData class where all important values are stored
        ApplicationData appData = ((ApplicationData) getApplicationContext());
        
        // insert user data of active user 
        appData.firstName = firstName; 
        appData.lastName = lastName; 
        appData.email = email; 
        appData.userID = id; 
        
        Log.d("PASS LOGIN DATA", "User Data was successfully inserted into ApplicationData");
    }
}