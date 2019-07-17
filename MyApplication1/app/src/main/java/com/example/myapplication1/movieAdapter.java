package com.example.myapplication1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication1.Model.Movie;
import com.example.myapplication1.View.NowPlayingFragment;

import java.util.ArrayList;
import java.util.List;

public class movieAdapter extends ArrayAdapter<Movie> {

    private final String path = "https://image.tmdb.org/t/p/original" ;
    public movieAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, parent, false);
        }
        ImageView moviePoster = convertView.findViewById(R.id.moviePoster);
        TextView movieTitle = convertView.findViewById(R.id.movieName);

        Movie movie = getItem(position);

        movieTitle.setText(movie.getTitle());

        String poster_path = path+movie.getPhotoPath();

        Glide.with(moviePoster.getContext())
                .load(poster_path)
                .into(moviePoster);

        return convertView;
    }
}
