package com.example.stock_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WatchlistRecyclerAdapter extends RecyclerView.Adapter<WatchlistRecyclerAdapter.ViewHolder> {
    // new String Arrays
    String[] companyNames;
    String[] companySymbols;
    String[] price;
    String[] dailyChange;
    String[] open;
    String[] high;
    String[] low;
    // new int Array
    int[] companyLogos;
    // new context object
    Context context;
    boolean[] inWatchlist;

    // methods that receives the arrays from the OverviewActivity class and set the newly declared Arrays to the retrieved ones
    public WatchlistRecyclerAdapter(Context context, String[] companyNames, int[] companyLogos, String[] price,
                           String[] companySymbols, String[] dailyChange, String[] open, String[] high,
                           String[] low, boolean[] inWatchlist) {

        this.companyNames = companyNames;
        this.companySymbols = companySymbols;
        this.companyLogos = companyLogos;
        this.dailyChange = dailyChange;
        this.price = price;
        this.open = open;
        this.high = high;
        this.low = low;
        this.context = context;
        this.inWatchlist = inWatchlist;
    }

    // method to create a new item in the recyclerview element
    @NonNull
    @Override
    public WatchlistRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.overview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // method to set the elements inside every item
    @Override
    public void onBindViewHolder(@NonNull WatchlistRecyclerAdapter.ViewHolder holder, int position) {

        // set the views to the according value
        holder.nameView.setText(companyNames[position]);
        holder.priceView.setText("$" + price[position]);
        holder.symbolView.setText(companySymbols[position]);
        holder.changeView.setText(dailyChange[position] + "%");
        holder.logoView.setImageResource(companyLogos[position]);

        // conditionally change the daily change text color depending on positive or negative change
        if (Float.parseFloat(dailyChange[position]) >= 0) {
            // change to green
            holder.changeView.setTextColor(Color.GREEN);
        } else if (Float.parseFloat(dailyChange[position]) < 0) {
            // change to red
            holder.changeView.setTextColor(Color.RED);
        }

        // set on click listener on the company name view
        holder.nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // output a small message on app
                Toast.makeText(context, "Clicked" + companyNames[position], Toast.LENGTH_SHORT).show();

                // new intent with reference to DetailActivity class
                Intent intent = new Intent(context, DetailActivity.class);
                // get the position
                intent.putExtra("position", position);
                // give the intent the company logo
                intent.putExtra("company_logo", companyLogos[position]);
                // give the intent the company name
                intent.putExtra("company_name", companyNames[position]);
                intent.putExtra("company_symbol", companySymbols[position]);
                intent.putExtra("price", price[position]);
                // put all price data in extra
                intent.putExtra("close", price[position]);
                intent.putExtra("open", open[position]);
                intent.putExtra("high", high[position]);
                intent.putExtra("low", low[position]);
                intent.putExtra("daily_change", dailyChange[position]);
                // start
                context.startActivity(intent);
            }
        });
    }

    // counts the amount of items to create
    @Override
    public int getItemCount() {
        return companyNames.length;
    }

    // class for the different elements
    public class ViewHolder extends RecyclerView.ViewHolder {

        // create textview and imageview objects
        TextView nameView;
        TextView priceView;
        TextView symbolView;
        TextView changeView;
        ImageView logoView;

        // method to assign views the matching xml elements
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.company_name);
            symbolView = itemView.findViewById(R.id.company_symbol);
            logoView = itemView.findViewById(R.id.company_logo);
            priceView = itemView.findViewById(R.id.price);
            changeView = itemView.findViewById(R.id.daily_change);
        }
    }
}
