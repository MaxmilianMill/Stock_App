package com.example.stock_app;

import static android.graphics.Color.RED;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    int recyclerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the content view to activity_detail
        setContentView(R.layout.activity_detail_item);
        // hide upper app bar
        getSupportActionBar().hide();
        // call intent function
        getIncomingIntent();

        getGraph(this.recyclerPosition);
    }

    // intent function
    private void getIncomingIntent() {

        // check if the intent has an assigned logo and name
        if (getIntent().hasExtra("company_logo") && getIntent().hasExtra("price")) {
            // assign the int the company logo element
            int companyLogo = getIntent().getIntExtra("company_logo", 0);
            // assign the string the company name
            int position = getIntent().getIntExtra("position", 0);
            String price = getIntent().getStringExtra("price");
            String dailyChange = getIntent().getStringExtra("daily_change");
            String close = getIntent().getStringExtra("close");
            String open = getIntent().getStringExtra("open");
            String high = getIntent().getStringExtra("high");
            String low = getIntent().getStringExtra("low");

            this.recyclerPosition = position;

            // call function to assign elements the defined values
            setDetails(companyLogo, price, dailyChange, close, open, high, low);
        }
    }

    // function to assign elements the values
    private void setDetails(int companyLogo, String price, String dailyChange, String close, String open,
                            String high, String low) {
        // find view and set the text to the company name
        TextView priceView = findViewById(R.id.tv_price);
        priceView.setText("$" + price);

        // Find image view and set it to the company logo
        ImageView logoView = findViewById(R.id.iv_detail_logo);
        logoView.setImageResource(Integer.parseInt(String.valueOf(companyLogo)));

        TextView changeView = findViewById(R.id.tv_change);

        // change color of the daily change
        if (Float.parseFloat(dailyChange) >= 0) {

            changeView.setTextColor(Color.GREEN);
        } else {

            changeView.setTextColor(RED);
        }

        changeView.setText(dailyChange + "%");

        TextView closeView = findViewById(R.id.close);
        closeView.setText("$" + close);

        TextView openView = findViewById(R.id.open);
        openView.setText("$" + open);

        TextView highView = findViewById(R.id.high);
        highView.setText("$" + high);

        TextView lowView = findViewById(R.id.low);
        lowView.setText("$" + low);
    }

    private void getGraph(int recyclerPosition) {

        // access the ApplicationData class where all important values are stored
        ApplicationData appData = ((ApplicationData)getApplicationContext());

        ArrayList data = (ArrayList) appData.chartData.get(recyclerPosition);

        GraphView graphView = (GraphView) findViewById(R.id.gv_price_chart);

        // change the grid color to fully transparent
        graphView.getGridLabelRenderer().setGridColor(Color.argb(0, 0, 0, 0));

        // set legend text color to white
        graphView.getLegendRenderer().setTextColor(Color.WHITE);

        DataPoint[] dataPoints = new DataPoint[data.size()];

        int start = data.size() - 1;

        for (int i = 0; i < data.size(); i++) {
            dataPoints[i] = new DataPoint(i, (Double) data.get((start - i)));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);

        // change the line color to white
        series.setColor(Color.WHITE);

        graphView.addSeries(series);
    }
}