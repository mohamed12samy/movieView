package com.example.myapplication1.Rest;


import com.example.myapplication1.Model.Movie;
import com.example.myapplication1.Model.movieList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Imovie {

    @GET("/{page}/discover/movie")
    Call<List<Movie>> getMovies(@Path("page") int pageNum,
                                @Query("api_key") String api_key,
                                @Query("sort_by") String s,
                                @Query("include_video") String ss,
                                @Query("year") int year
    );

    @GET("/3/movie/now_playing")
    Call<movieList> getCurMovies(@Query("api_key") String api_key);
}
