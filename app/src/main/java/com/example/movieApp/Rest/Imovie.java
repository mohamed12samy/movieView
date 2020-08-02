package com.example.movieApp.Rest;


import com.example.movieApp.Model.VideoList;
import com.example.movieApp.Model.movieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Imovie {

    @GET("/3/movie/now_playing")
    Call<movieList> getCurMovies(@Query("api_key") String api_key,
                                 @Query("page") int page
                                 );


    @GET("/3/movie/top_rated")
    Call<movieList> getTopMovies(@Query("api_key") String api_key,
                                 @Query("page") int page
    );

    @GET("/3/movie/{id}/videos")
    Call<VideoList> getTrailer( @Path("id") int id,
                                @Query("api_key") String api_key

    );

}
