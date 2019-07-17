package com.example.myapplication1.Model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String photoPath;

    private movieList CurrentMovies = new movieList();

    public Movie(String title, String photoPath) {
        this.title = title;
        this.photoPath = photoPath;
    }
    public Movie(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }


}
