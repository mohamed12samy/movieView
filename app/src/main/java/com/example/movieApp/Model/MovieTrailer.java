package com.example.movieApp.Model;

import com.google.gson.annotations.SerializedName;

public class MovieTrailer {

    @SerializedName("id")
    String id;

    @SerializedName("key")
    String key;

    @SerializedName("type")
    String type;

    public MovieTrailer(String id, String key, String type) {
        this.id = id;
        this.key = key;
        this.type = type;
    }

    public MovieTrailer(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
