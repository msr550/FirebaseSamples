package com.msr.firebasesamples.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.msr.firebasesamples.R;

import java.util.List;

/**
 * Created by Sandeep on 2/22/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<DataSnapshot> moviesList;

    public RecyclerViewAdapter(List<DataSnapshot> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i("===Test", "Test" + position);
        DataSnapshot dataSnapshot = moviesList.get(position);
        holder.nameTV.setText(dataSnapshot.child("brand").getValue().toString());
        holder.quantityTV.setText(dataSnapshot.child("quantity").getValue().toString());
       /* Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());*/
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTV, quantityTV;

        public MyViewHolder(View view) {
            super(view);
            nameTV = (TextView) view.findViewById(R.id.nameTV);
            quantityTV = (TextView) view.findViewById(R.id.quantityTV);
        }
    }
}
