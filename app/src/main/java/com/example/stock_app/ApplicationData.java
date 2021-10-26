package com.example.stock_app;

import android.app.Application;

public class ApplicationData extends Application {

    // create Arrays for every important value
    public String companyNames[] = {"Amazon", "Microsoft"};
    public String companySymbols[] = {"AMZN", "MSFT"};
    public String price[] = new String[companySymbols.length];
    public String dailyChange[] = new String[companySymbols.length];
    public int companyLogos[] = {R.drawable.amazon, R.drawable.microsoft};
    public float open[] = new float[companySymbols.length];
    public float high[] = new float[companySymbols.length];
    public float low[] = new float[companySymbols.length];

    /*
    Every Array has 3 different methods:
    1. Getter to retrieve the array from anywhere in the project
    2. Setter to change the array from anywhere in the project
    3. Add to add a value into the array
     */

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

    public float[] getOpen() {
        return open;
    }

    public void setOpen(float[] open) {
        this.open = open;
    }

    public void addOpen(float open, int index) {

        this.open[index] = open;
    }

    public float[] getHigh() {
        return high;
    }

    public void setHigh(float[] high) {
        this.high = high;
    }

    public void addHigh(float high, int index) {

        this.high[index] = high;
    }

    public float[] getLow() {
        return low;
    }

    public void setLow(float[] low) {
        this.low = low;
    }

    public void addLow(float low, int index) {

        this.low[index] = low;
    }


}
