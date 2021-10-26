package com.example.stock_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getIncomingIntent();
    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("company_logo") && getIntent().hasExtra("company_name")) {

            int companyLogo = getIntent().getIntExtra("company_logo", 0);
            String companyName = getIntent().getStringExtra("company_name");

            setDetails(companyLogo, companyName);
        }
    }

    private void setDetails(int companyLogo, String companyName) {

        TextView nameView = findViewById(R.id.iv_detail_name);
        nameView.setText(companyName);

        ImageView logoView = findViewById(R.id.iv_detail_logo);
        logoView.setImageResource(Integer.parseInt(String.valueOf(companyLogo)));
    }
}