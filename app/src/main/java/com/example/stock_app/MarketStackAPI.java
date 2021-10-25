package com.example.stock_app;

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

public class MarketStackAPI {

    // JSONObject for the data of the past 30 days
    private JSONObject chartData;
    // JSONObject for the raw Data
    private JSONObject rawData;

    // create empty String
    private String data = "";

    // open price
    private float open;
    // high price
    private float high;
    // low price
    private float low;
    // close price
    private float close;
    // volume
    private int volume;
    // symbol
    private String symbol;
    // daily price change in percent
    private float dailyChange;

    // main method
    public static void main(String[] args) throws JSONException {

    }

    public void apiConnectDaily(String ticker) {

        // dynamic url creation
        String addressLink = "http://api.marketstack.com/v1/eod?access_key=e83e62fb9537e31ee4398bec2c34162a&symbols=" + ticker + "&limit=30";

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
                this.chartData = jsonObject;
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
        this.high = (float) this.rawData.getDouble("high");
        return this.high;
    }

    public Float getLow() throws JSONException {
        // get open price from raw data object
        this.low = (float) this.rawData.getDouble("low");
        return this.low;
    }

    public Float getClose() throws JSONException {
        // get open price from raw data object

        this.close = (float) this.rawData.getDouble("close");
        return this.close;
    }

    public Integer getVolume() throws JSONException {
        // get open price from raw data object

        this.volume = this.rawData.getInt("volume");
        return this.volume;
    }

    public String getSymbol() throws JSONException {
        // get open price from raw data object
        this.symbol = this.rawData.getString("symbol");
        return this.symbol;
    }

    public BigDecimal getDailyChange() throws JSONException {
        // if close and open are none --> initialize both methods
        if (this.close == 0 || this.open == 0) {
            this.getClose();
            this.getOpen();
        }
        // calculate price change from open to close
        this.dailyChange = (this.close / this.open - 1) * 100;
        // round to two decimals
        BigDecimal bd = new BigDecimal(this.dailyChange).setScale(2, RoundingMode.HALF_UP);
        return bd;
    }
}