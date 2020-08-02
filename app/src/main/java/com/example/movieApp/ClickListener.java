package com.example.movieApp;


import android.widget.ImageView;

import com.example.movieApp.Model.Movie;

public interface ClickListener {

    void clickListen(Movie movie);
    void clickMovieListen(Movie movie, ImageView imageView);
}
