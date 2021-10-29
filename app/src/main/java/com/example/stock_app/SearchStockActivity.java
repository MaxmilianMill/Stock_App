package com.example.stock_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchStockActivity extends AppCompatActivity {
    // new searchview object
    SearchView searchView;
    // new listview object
    ListView listView;
    // new Arraylist
    ArrayList<String> list;
    // new Arrayadapter
    ArrayAdapter<String > adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stock);
        // new searchview is connected with searchview in xml by id
        searchView = (SearchView) findViewById(R.id.searchStocks);
        // new listview is connected with listview in xml by id
        listView = (ListView) findViewById(R.id.listStocks);

        // create new arrayList and add new Listitems
        list = new ArrayList<>();
        list.add("Apple");
        list.add("Microsoft");
        list.add("Amazon");
        list.add("Facebook");
        list.add("Alphabet");
        list.add("Tesla");
        list.add("Berkshire Hathaway");
        list.add("NVIDIA");
        list.add("JPMorgan Chase");
        list.add("Johnson & Johnson");
        list.add("Visa");
        list.add("UnitedHealth Group");
        list.add("Paypal");
        list.add("Home Depot");
        list.add("Procter & Gamble");
        list.add("Mastercard");
        list.add("Walt Disney");
        list.add("Bank of America");
        // create new Adapter for ArrayAdapter and insert the list items
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        // set the adapter to the ListView
        listView.setAdapter(adapter);
        // OnQuery function
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // check if the list items contains the query
                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                // else show text on SearchStockActivity
                }else{
                    Toast.makeText(SearchStockActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });


    }
}