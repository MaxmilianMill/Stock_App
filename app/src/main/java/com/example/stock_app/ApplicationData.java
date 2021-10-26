package com.example.stock_app;

import android.app.Application;

public class ApplicationData extends Application {

    public String companyNames[] = {"Amazon"};
    public String companySymbols[] = {"AMZN"};
    public String price[] = new String[companySymbols.length];
    public String dailyChange[] = new String[companySymbols.length];
    public int companyLogos[] = {R.drawable.amazon, R.drawable.microsoft};

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
}
