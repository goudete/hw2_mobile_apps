package com.example.hw2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {
    // create the basic adapter extending from RecyclerView.Adapter

    List<Beer> beers;
    Context mContext;

    public BeerAdapter(Context context, List<Beer> beers) {
        this.beers = beers;
        mContext = context;
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

        //loading image
        Picasso.get().load(beer.getImage_url()).into(holder.imageView_beer);

        holder.imageView_beer.setOnClickListener((view) -> {
            Intent intent = new Intent(mContext, FourthActivity.class);
            intent.putExtra("name", beer.getName());
            intent.putExtra("description", beer.getDescription());
            intent.putExtra("abv", beer.getAbv());
            intent.putExtra("image", beer.getImage_url());
            intent.putExtra("food_pairing", beer.getFood_pairing());
            intent.putExtra("brewsters_tips", beer.getBrewers_tips());
            intent.putExtra("first_brewed", beer.getFirst_brewed());

            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        // returns total number of items in list
        return beers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
       TextView textView_name;
       TextView textView_description;
       ImageView imageView_beer;

       public ViewHolder (View itemView) {
           super(itemView);
           textView_name = itemView.findViewById(R.id.textView_name);
           textView_description = itemView.findViewById(R.id.textView_description);
           imageView_beer = itemView.findViewById(R.id.imageView_beer);
       }

   }
}
