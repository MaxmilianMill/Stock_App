package com.example.stock_app.watchlist;

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stock_app.overview.DetailActivity;
import com.example.stock_app.R;

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
    Integer[] indexOfStock;

    // methods that receives the arrays from the WatchlistActivity class and set the newly declared Arrays to the retrieved ones
    public WatchlistRecyclerAdapter(Context context, String[] companyNames, int[] companyLogos, String[] price,
                                    String[] companySymbols, String[] dailyChange, String[] open, String[] high,
                                    String[] low, Integer[] indexOfStock) {

        this.companyNames = companyNames;
        this.companySymbols = companySymbols;
        this.companyLogos = companyLogos;
        this.dailyChange = dailyChange;
        this.price = price;
        this.open = open;
        this.high = high;
        this.low = low;
        this.context = context;
        this.indexOfStock = indexOfStock;
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

        // get the position only of the stocks in watchlist
        int adjPosition = indexOfStock[position];

        System.out.println(adjPosition);

        // set the views to the according value
        holder.nameView.setText(companyNames[adjPosition]);
        holder.priceView.setText("$" + price[adjPosition]);
        holder.symbolView.setText(companySymbols[adjPosition]);
        holder.changeView.setText(dailyChange[adjPosition] + "%");
        holder.logoView.setImageResource(companyLogos[adjPosition]);

        // conditionally change the daily change text color depending on positive or negative change
        if (Float.parseFloat(dailyChange[adjPosition]) >= 0) {
            // change to green
            holder.changeView.setTextColor(Color.GREEN);
        } else if (Float.parseFloat(dailyChange[adjPosition]) < 0) {
            // change to red
            holder.changeView.setTextColor(Color.RED);
        }

        // set on click listener on the company name view
        holder.stockView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // output a small message on app
                Toast.makeText(context, "Clicked" + companyNames[adjPosition], Toast.LENGTH_SHORT).show();

                // new intent with reference to DetailActivity class
                Intent intent = new Intent(context, DetailActivity.class);
                // send data via intent to detail activity
                intent.putExtra("position", adjPosition);
                intent.putExtra("company_logo", companyLogos[adjPosition]);
                intent.putExtra("company_name", companyNames[adjPosition]);
                intent.putExtra("company_symbol", companySymbols[adjPosition]);
                intent.putExtra("price", price[adjPosition]);
                intent.putExtra("close", price[adjPosition]);
                intent.putExtra("open", open[adjPosition]);
                intent.putExtra("high", high[adjPosition]);
                intent.putExtra("low", low[adjPosition]);
                intent.putExtra("daily_change", dailyChange[adjPosition]);
                // start
                context.startActivity(intent);
            }
        });
    }

    // counts the amount of items to create
    @Override
    public int getItemCount() { return indexOfStock.length; }

    // class for the different elements
    public class ViewHolder extends RecyclerView.ViewHolder {

        // create textview and imageview objects
        TextView nameView;
        TextView priceView;
        TextView symbolView;
        TextView changeView;
        ImageView logoView;
        CardView stockView;

        // method to assign views the matching xml elements
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.company_name);
            symbolView = itemView.findViewById(R.id.company_symbol);
            logoView = itemView.findViewById(R.id.company_logo);
            priceView = itemView.findViewById(R.id.price);
            changeView = itemView.findViewById(R.id.daily_change);
            stockView = itemView.findViewById(R.id.cv_stock_item);
        }
    }
}
