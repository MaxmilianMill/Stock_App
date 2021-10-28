package com.example.stock_app;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationData extends Application {

    // create Arrays for every important value
    public String[] companyNames = {"Amazon", "Microsoft"};
    public String[] companySymbols = {"AMZN", "MSFT"};
    public String[] price = new String[companySymbols.length];
    public String[] dailyChange = new String[companySymbols.length];
    public int[] companyLogos = {R.drawable.amazon, R.drawable.microsoft};
    public String[] open = new String[companySymbols.length];
    public String[] high = new String[companySymbols.length];
    public String[] low = new String[companySymbols.length];
    public ArrayList chartData = new ArrayList<>();

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
