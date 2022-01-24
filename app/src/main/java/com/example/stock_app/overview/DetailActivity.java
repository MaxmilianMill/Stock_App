package com.example.stock_app.overview;

import static android.graphics.Color.RED;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stock_app.R;
import com.example.stock_app.database.RoomDB;
import com.example.stock_app.database.Stock;
import com.example.stock_app.other.ApplicationData;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    int recyclerPosition;
    private String companyName;
    private String companySymbol;
    Button addToWatchlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the content view to activity_detail
        setContentView(R.layout.activity_detail_item);
        // hide upper app bar
        getSupportActionBar().hide();

        // access the ApplicationData class where all important values are stored
        ApplicationData appData = ((ApplicationData) getApplicationContext());

        // call intent function --> retrieves stock data from recycler view
        getIncomingIntent();

        // create graph with price array for the particular stock
        getGraph(this.recyclerPosition);

        addToWatchlist = findViewById(R.id.btn_add_watchlist);

        // call db
        RoomDB db = RoomDB.getDbInstance(this.getApplicationContext());

        // when button is clicked --> create new stock object in db if stock is not already in db
        addToWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stock item = new Stock();
                item.companyName = companyName;
                item.symbol = companySymbol;
                item.userID = appData.userID;

                if (!addedToWatchlistCheck(db, item.symbol, item.userID)) {

                    item.addedToWatchlist = true;

                    db.stockDao().insertItem(item);
                    Toast.makeText(DetailActivity.this,companyName + " was added to your Watchlist", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    // intent function
    private void getIncomingIntent() {

        // check if the intent has an assigned logo and name
        if (getIntent().hasExtra("company_logo") && getIntent().hasExtra("price")) {
            // assign variables the data from recycler view
            int companyLogo = getIntent().getIntExtra("company_logo", 0);
            int position = getIntent().getIntExtra("position", 0);
            String price = getIntent().getStringExtra("price");
            String dailyChange = getIntent().getStringExtra("daily_change");
            String close = getIntent().getStringExtra("close");
            String open = getIntent().getStringExtra("open");
            String high = getIntent().getStringExtra("high");
            String low = getIntent().getStringExtra("low");

            // get position of stock in the recycler view --> access correct chart data array
            this.recyclerPosition = position;

            // data for watchlist table
            this.companyName = getIntent().getStringExtra("company_name");
            this.companySymbol = getIntent().getStringExtra("company_symbol");

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

        // set text view to the required data
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

    // create a graph with the chart data from application data class
    private void getGraph(int recyclerPosition) {

        // access the ApplicationData class where all important values are stored
        ApplicationData appData = ((ApplicationData)getApplicationContext());

        // get the array
        ArrayList data = (ArrayList) appData.chartData.get(recyclerPosition);

        GraphView graphView = (GraphView) findViewById(R.id.gv_price_chart);

        // change the grid color to fully transparent
        graphView.getGridLabelRenderer().setGridColor(Color.argb(0, 0, 0, 0));

        // set legend text color to white
        graphView.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graphView.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);

        // insert data from array into a graph series array
        DataPoint[] dataPoints = new DataPoint[data.size()];

        int start = data.size() - 1;

        for (int i = 0; i < data.size(); i++) {
            dataPoints[i] = new DataPoint(i, (Double) data.get((start - i)));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);

        // change the line color to white
        series.setColor(Color.WHITE);

        // add series to graph
        graphView.addSeries(series);
    }

    // check if stock was already added to watchlist
    public boolean addedToWatchlistCheck(RoomDB db, String symbol, int userID) {
        boolean alreadyAdded = false;
        List<Stock> watchlist = db.stockDao().stockList(userID);

        for (int i = 0; i < watchlist.size(); i++) {

            if (watchlist.get(i).symbol.equals(symbol)) {

                alreadyAdded = true;
                break;
            }
        }

        System.out.println("Check it is: " + alreadyAdded);
        return alreadyAdded;
    }
}