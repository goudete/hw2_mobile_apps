package com.example.hw2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {
    // create the basic adapter extending from RecyclerView.Adapter

    List<Beer> beers;

    public BeerAdapter(List<Beer> beers) {
        this.beers = beers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate a layout from xml and return ViewHolder
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //inflate custom layout
        View beerView = inflater.inflate(R.layout.item_brew, parent, false);
        //return new view holder
        ViewHolder viewHolder = new ViewHolder(beerView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // populate data into the item through view holder

        //grab data model aka beer object
        Beer beer = beers.get(position);

        //set the view based on the data and view names
        holder.textView_name.setText(beer.getName());
        holder.textView_description.setText(beer.getDescription());

        //this is where i load image
    }

    @Override
    public int getItemCount() {
        // returns total number of items in list
        return beers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
       TextView textView_name;
       TextView textView_description;

       public ViewHolder (View itemView) {
           super(itemView);

           textView_name = itemView.findViewById(R.id.textView_name);
           textView_description = itemView.findViewById(R.id.textView_description);
       }
   }
}
