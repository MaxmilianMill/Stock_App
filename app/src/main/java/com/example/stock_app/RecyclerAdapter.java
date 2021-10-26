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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    // new String Arrays
    String cN[];
    String cS[];
    String p[];
    String dC[];
    // new int Array
    int cL[];
    // new context object
    Context context;

    // methods that receives the arrays from the OverviewActivity class and set the newly declared Arrays to the retrieved ones
    public RecyclerAdapter(Context context, String[] cN, int cL[], String[] p, String[] cS, String[] dC) {
        this.cN = cN;
        this.cS = cS;
        this.cL = cL;
        this.dC = dC;
        this.p = p;
        this.context = context;
    }

    // method to create a new item in the recyclerview element
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.overview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // method to set the elements inside every item
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        // set the views to the according value
        holder.nameView.setText(cN[position]);
        holder.priceView.setText("$" + p[position]);
        holder.symbolView.setText(cS[position]);
        holder.changeView.setText(dC[position] + "%");
        holder.logoView.setImageResource(cL[position]);

        // conditionally change the daily change text color depending on positive or negative change
        if (Float.parseFloat(dC[position]) > 0) {
            // change to green
            holder.changeView.setTextColor(Color.GREEN);
        } else if (Float.parseFloat(dC[position]) < 0) {
            // change to red
            holder.changeView.setTextColor(Color.RED);
        }

        // set on click listener on the company name view
        holder.nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // output a small message on app
                Toast.makeText(context, "Clicked" + cN[position], Toast.LENGTH_SHORT).show();

                // new intent with reference to DetailActivity class
                Intent intent = new Intent(context, DetailActivity.class);
                // give the intent the company logo
                intent.putExtra("company_logo", cL[position]);
                // give the intent the commpany name
                intent.putExtra("company_name", cN[position]);
                // start
                context.startActivity(intent);
            }
        });
    }

    // counts the amount of items to create
    @Override
    public int getItemCount() {
        return cN.length;
    }

    // class for the different elements
    public class ViewHolder extends RecyclerView.ViewHolder {

        // create textview and imageview objects
        TextView nameView;
        TextView priceView;
        TextView symbolView;
        TextView changeView;
        ImageView logoView;

        // method to assign views the matching xml element
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
