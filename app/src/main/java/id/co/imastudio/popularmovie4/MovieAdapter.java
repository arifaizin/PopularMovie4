package id.co.imastudio.popularmovie4;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.co.imastudio.popularmovie4.model.ResultsItem;

/**
 * Created by idn on 2/3/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    Context context;
    List<ResultsItem> listMovie = new ArrayList<>();

    //constructor
    public MovieAdapter(Context context, List<ResultsItem> listMovie) {
        this.context = context;
        this.listMovie = listMovie;
    }

    //sambungin layout item
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.movie_item, parent, false);

        return new MyViewHolder(itemView);
    }

    //set data
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textJudul.setText(listMovie.get(position).getTitle());
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+listMovie.get(position).getPosterPath())
                .into(holder.imagePoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(context, DetailMovieActivity.class);
                //kirim data
                pindah.putParcelableArrayListExtra("DATA_MOVIE", (ArrayList<? extends Parcelable>) listMovie);
                pindah.putExtra("POSISI", position);
                context.startActivity(pindah);
            }
        });
    }

    //jumlah list
    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    //sambungin komponen dalam layout
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textJudul;
        ImageView imagePoster;
        public MyViewHolder(View itemView) {
            super(itemView);
            textJudul = itemView.findViewById(R.id.text_item_judul);
            imagePoster = itemView.findViewById(R.id.image_item_poster);
        }
    }
}
