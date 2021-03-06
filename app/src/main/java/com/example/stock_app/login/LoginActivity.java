package com.example.stock_app.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.example.stock_app.R;
import com.example.stock_app.other.ApplicationData;
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

        //TabLayout gravity so weit wie möglich füllen
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //create connection to the viewpager with adapter
        //übergabe der arguments in adapter
        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(),this, tabLayout.getTabCount());
        //connect viewpager to the adapter
        viewPager.setAdapter(adapter);
        //connect viewpager to tablayout --> tab changes viewpager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //wenn tab ausgewählt, dann
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Set the currently selected page - connected to the tab position
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

    // Empfange Daten aus Interface in LoginTabFragment Klasse
    @Override
    public void passUserData(String firstName, String lastName, String email, int id) {
        
        // access the ApplicationData class where all important values are stored
        ApplicationData appData = ((ApplicationData) getApplicationContext());
        
        // insert user data --> active user
        appData.firstName = firstName; 
        appData.lastName = lastName; 
        appData.email = email; 
        appData.userID = id; 
        
        Log.d("PASS LOGIN DATA", "User Data was successfully inserted into ApplicationData");
    }
}