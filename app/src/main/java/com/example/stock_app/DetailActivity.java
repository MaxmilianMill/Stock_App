package com.example.stock_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the content view to activity_detail
        setContentView(R.layout.activity_detail);
        // hide upper app bar
        getSupportActionBar().hide();
        // call intent function
        getIncomingIntent();
    }

    // intent function
    private void getIncomingIntent() {

        // check if the intent has an assigned logo and name
        if (getIntent().hasExtra("company_logo") && getIntent().hasExtra("company_name")) {
            // assign the int the company logo element
            int companyLogo = getIntent().getIntExtra("company_logo", 0);
            // assign the string the company name
            String companyName = getIntent().getStringExtra("company_name");

            // call function to assign elements the defined values
            setDetails(companyLogo, companyName);
        }
    }

    // function to assign elements the values
    private void setDetails(int companyLogo, String companyName) {
        // find view and set the text to the company name
        TextView nameView = findViewById(R.id.iv_detail_name);
        nameView.setText(companyName);

        // Find image view and set it to the company logo
        ImageView logoView = findViewById(R.id.iv_detail_logo);
        logoView.setImageResource(Integer.parseInt(String.valueOf(companyLogo)));
    }
}