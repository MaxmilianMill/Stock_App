package com.example.stock_app.other;

import android.app.Application;

import com.example.stock_app.R;

import java.util.ArrayList;

public class ApplicationData extends Application {

    // Stock Data Arrays
    public String[] companyNames = {"Amazon", "Microsoft", "Alphabet", "Disney",
                                    "Facebook", "JPM Chase", "Mastercard", "NVIDIA",
                                    "Paypal", "P&G", "Tesla", "Visa", "Apple", "UnitedHealth",
                                    "Johnson & Johnson"};
    public String[] companySymbols = {"AMZN", "MSFT", "GOOG", "DIS", "FB", "JPM", "MA", "NVDA",
                                        "PYPL", "PG", "TSLA", "V", "AAPL", "UNH", "JNJ"};
    public String[] price = new String[companySymbols.length];
    public String[] dailyChange = new String[companySymbols.length];
    public int[] companyLogos = {R.drawable.amazon, R.drawable.microsoft, R.drawable.alphabet,
                                R.drawable.disney, R.drawable.facebook, R.drawable.jpmorgan_chase,
                                R.drawable.mastercard, R.drawable.nvidia, R.drawable.paypal,
                                R.drawable.procter_gamble_logo, R.drawable.tesla, R.drawable.visa,
                                R.drawable.apple, R.drawable.unitedhealth, R.drawable.johnson_johnson};
    public String[] open = new String[companySymbols.length];
    public String[] high = new String[companySymbols.length];
    public String[] low = new String[companySymbols.length];
    public ArrayList chartData = new ArrayList<>();
    public boolean alreadyUpdated = false;

    // user data
    public String firstName = null;
    public String lastName;
    public String email;
    public int userID;

    // Getter, Setter and Add methods for all stock data arrays
    // ---------------------------------------------------------
    public String[] getCompanyNames() {
        return companyNames;
    }

    public void setCompanyNames(String[] companyNames) {
        this.companyNames = companyNames;
    }

    public void addCompanyNames(String companyNames, int index) {

        this.companyNames[index] = companyNames;
    }

    public String[] getCompanySymbols() {
        return companySymbols;
    }

    public void setCompanySymbols(String[] companySymbols) {
        this.companySymbols = companySymbols;
    }

    public void addCompanySymbols(String companySymbols, int index) {

        this.companySymbols[index] = companySymbols;
    }

    public String[] getPrice() {
        return price;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

    public void addPrice(String price, int index) {

        this.price[index] = price;
    }

    public String[] getDailyChange() {
        return dailyChange;
    }

    public void setDailyChange(String[] dailyChange) {
        this.dailyChange = dailyChange;
    }

    public void addDailyChange(String dailyChange, int index) {

        this.dailyChange[index] = dailyChange;
    }

    public int[] getCompanyLogos() {
        return companyLogos;
    }

    public void setCompanyLogos(int[] companyLogos) {
        this.companyLogos = companyLogos;
    }

    public void addCompanyLogos(Integer companyLogos, int index) {

        this.companyLogos[index] = companyLogos;
    }

    public String[] getOpen() {
        return open;
    }

    public void setOpen(String[] open) {
        this.open = open;
    }

    public void addOpen(String open, int index) {

        this.open[index] = open;
    }

    public String[] getHigh() {
        return high;
    }

    public void setHigh(String[] high) {
        this.high = high;
    }

    public void addHigh(String high, int index) {

        this.high[index] = high;
    }

    public String[] getLow() {
        return low;
    }

    public void setLow(String[] low) {
        this.low = low;
    }

    public void addLow(String low, int index) {

        this.low[index] = low;
    }

    public ArrayList getChartData() {
        return chartData;
    }

    public void setChartData(ArrayList chartData) {
        this.chartData = chartData;
    }

    public void addChartData(ArrayList chartData) {
        this.chartData.add(chartData);
    }
}
