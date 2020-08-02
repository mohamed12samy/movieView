package com.example.movieApp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VideoList {

    @SerializedName("results")
    private List<MovieTrailer> movieTrailers = new ArrayList<>();


    public List<MovieTrailer> getMovieTrailers() {
        return movieTrailers;
    }

    /*public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }*/
    // ...

}
