package com.example.myapplication1.Model;

import com.example.myapplication1.Model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class movieList {

        @SerializedName("results")
        private List<Movie> movies = new ArrayList<>();

    public List<Movie> getMovies() {
        return movies;
    }

    // ...

}
