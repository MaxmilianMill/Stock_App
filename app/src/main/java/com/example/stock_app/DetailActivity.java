package com.example.stock_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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

        getGraph();
    }

    // intent function
    private void getIncomingIntent() {

        // check if the intent has an assigned logo and name
        if (getIntent().hasExtra("company_logo") && getIntent().hasExtra("price")) {
            // assign the int the company logo element
            int companyLogo = getIntent().getIntExtra("company_logo", 0);
            // assign the string the company name
            String price = getIntent().getStringExtra("price");
            String dailyChange = getIntent().getStringExtra("daily_change");
            String close = getIntent().getStringExtra("close");
            String open = getIntent().getStringExtra("open");
            String high = getIntent().getStringExtra("high");
            String low = getIntent().getStringExtra("low");

            // call function to assign elements the defined values
            setDetails(companyLogo, price, dailyChange, close, open, high, low);
        }
    }

    // function to assign elements the values
    private void setDetails(int companyLogo, String price, String dailyChange, String close, String open,
                            String high, String low) {
        // find view and set the text to the company name
        TextView priceView = findViewById(R.id.tv_price);
        priceView.setText(price);

        // Find image view and set it to the company logo
        ImageView logoView = findViewById(R.id.iv_detail_logo);
        logoView.setImageResource(Integer.parseInt(String.valueOf(companyLogo)));

        TextView changeView = findViewById(R.id.tv_change);

        // change color of the daily change
        if (Float.parseFloat(dailyChange) >= 0) {

            changeView.setTextColor(Color.GREEN);
        } else {

            changeView.setTextColor(Color.RED);
        }

        changeView.setText(dailyChange + "%");

        TextView closeView = findViewById(R.id.tv_close);
        closeView.setText("$" + close);

        TextView openView = findViewById(R.id.tv_open);
        openView.setText("$" + open);

        TextView highView = findViewById(R.id.tv_high);
        highView.setText("$" + high);

        TextView lowView = findViewById(R.id.tv_low);
        lowView.setText("$" + low);
    }

    private void getGraph() {

        GraphView graphView = (GraphView) findViewById(R.id.gv_price_chart);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(1, 2),
                new DataPoint(2, 4),
                new DataPoint(3, 6),
                new DataPoint(4, 8)
        });

        graphView.addSeries(series);
    }
}