package com.example.stock_app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MarketStackAPI {

    // JSONObject for the data of the past 30 days
    private JSONArray chartData;
    // JSONObject for the raw Data
    private JSONObject rawData;

    // create empty String
    private String data = "";

    // open price
    private float open;
    // close price
    private float close;

    // main method
    public static void main(String[] args) throws JSONException {

    }

    public void apiConnectDaily(String ticker) {

        // api key
        String apiKey = "35abe21607f947d09c7aa76e44e167dd";

        // dynamic url creation
        String addressLink = "http://api.marketstack.com/v1/eod?access_key=" + apiKey + "&symbols=" + ticker + "&limit=30";

        // try catch statement
        try {
            // create new URL Object with the API URL
            URL url = new URL(addressLink);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            // load data into input stream
            InputStream is = http.getInputStream();
            // read data
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            // new String line
            String line;

            // while line is not null --> data stream is active
            while ((line = br.readLine()) != null) {

                // append data with line
                data = data + line;

            }

            // check if data string is empty
            if (!data.isEmpty()) {
                // parse data into JSONObject
                JSONObject jsonObject = new JSONObject(this.data);
                // paste data of jsonObject into chartData
                this.chartData = jsonObject.getJSONArray("data");
                // get json data of the current (last finished day with stock markets open) day
                this.rawData = jsonObject.getJSONArray("data").getJSONObject(0);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public Float getOpen() throws JSONException {
        // get open price from raw data object
        this.open = (float) this.rawData.getDouble("open");
        return this.open;
    }

    public Float getHigh() throws JSONException {
        // get open price from raw data object
        float high = (float) this.rawData.getDouble("high");
        return high;
    }

    public Float getLow() throws JSONException {
        // get open price from raw data object
        float low = (float) this.rawData.getDouble("low");
        return low;
    }

    public Float getClose() throws JSONException {
        // get open price from raw data object

        this.close = (float) this.rawData.getDouble("close");
        return this.close;
    }

    public Integer getVolume() throws JSONException {
        // get open price from raw data object

        int volume = this.rawData.getInt("volume");
        return volume;
    }

    public String getSymbol() throws JSONException {
        // get open price from raw data object
        String symbol = this.rawData.getString("symbol");
        return symbol;
    }

    public BigDecimal getDailyChange() throws JSONException {
        // if close and open are none --> initialize both methods
        if (this.close == 0 || this.open == 0) {
            this.getClose();
            this.getOpen();
        }
        // calculate price change from open to close
        float dailyChange = (this.close / this.open - 1) * 100;
        // round to two decimals
        BigDecimal bd = new BigDecimal(dailyChange).setScale(2, RoundingMode.HALF_UP);
        return bd;
    }

    public ArrayList<Object> getChartData() throws JSONException {
        // create array list
        ArrayList<Object> chartDataList = new ArrayList<>();
        // loop through json object chartData and paste every close price in array list
        for (int i = 0; i < this.chartData.length(); i++) {

            double chartData = this.chartData.getJSONObject(i).getDouble("close");

            chartDataList.add(i, chartData);
        }

        return chartDataList;
    }
}