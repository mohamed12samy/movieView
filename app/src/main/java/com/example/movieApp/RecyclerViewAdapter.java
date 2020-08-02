package com.example.movieApp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.movieApp.Model.Movie;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<Movie> mMovies = new ArrayList<>();
    private Context context;
    private ClickListener listener;

    public RecyclerViewAdapter(Context context , List<Movie> movies, ClickListener listener) {

        this.mMovies = movies;
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent,false);
        ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String title = mMovies.get(position).getTitle();
        String poster_path = "https://image.tmdb.org/t/p/original"+mMovies.get(position).getPhotoPath();

        holder.movieTitle.setText(title);

        if(mMovies.get(position).isFavourite()){
            holder.fav.setImageResource(R.drawable.ic_favorite_black_24dp);
            Log.d("hhhh",mMovies.get(position).getTitle()+"    "+mMovies.get(position).isFavourite());
        }
        else if(!mMovies.get(position).isFavourite()){
            holder.fav.setImageResource(R.drawable.ic_favorite_border_black_24dp);

            Log.d("kkkkk",mMovies.get(position).getTitle()+"    "+mMovies.get(position).isFavourite());
        }
        Glide.with(holder.moviePoster.getContext())
                .load(poster_path)
                .placeholder(R.drawable.default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle;
        ImageView moviePoster;
        ImageButton fav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.movieName);
            moviePoster = itemView.findViewById(R.id.moviePoster);
            fav = itemView.findViewById(R.id.favButton);

            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean Fav = mMovies.get(getAdapterPosition()).isFavourite();
                    Log.d("hghgghf",Fav+"");

                    if(Fav) mMovies.get(getAdapterPosition()).setFavourite(false);
                    else mMovies.get(getAdapterPosition()).setFavourite(true);

                    notifyItemChanged(getAdapterPosition());

                    listener.clickListen(mMovies.get(getAdapterPosition()));
                }
            });

            moviePoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Asas","*/*/*/*/");
                    listener.clickMovieListen(mMovies.get(getAdapterPosition()), moviePoster);
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
