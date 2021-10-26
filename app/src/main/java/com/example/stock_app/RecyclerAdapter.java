package com.example.stock_app;

import android.content.Context;
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

    String cN[];
    String cS[];
    String p[];
    String dC[];
    int cL[];
    Context context;

    public RecyclerAdapter(Context context, String[] cN, int cL[], String[] p, String[] cS, String[] dC) {
        this.cN = cN;
        this.cS = cS;
        this.cL = cL;
        this.dC = dC;
        this.p = p;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.overview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        holder.nameView.setText(cN[position]);
        holder.priceView.setText("$" + p[position]);
        holder.symbolView.setText(cS[position]);
        holder.changeView.setText(dC[position] + "%");
        holder.logoView.setImageResource(cL[position]);

        if (Float.parseFloat(dC[position]) > 0) {
            holder.changeView.setTextColor(Color.GREEN);
        } else if (Float.parseFloat(dC[position]) < 0) {
            holder.changeView.setTextColor(Color.RED);
        }

        holder.nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked" + cN[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cN.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameView;
        TextView priceView;
        TextView symbolView;
        TextView changeView;
        ImageView logoView;

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
