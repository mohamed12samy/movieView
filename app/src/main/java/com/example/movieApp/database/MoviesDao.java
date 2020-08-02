package com.example.movieApp.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.movieApp.Model.Movie;

import java.util.List;


@androidx.room.Dao
public interface MoviesDao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovies(List<Movie> movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavMovie(Movie movies);

    @Delete
    void deleteMovie(Movie movie);

    @Query("UPDATE movies SET is_favourit = 1 WHERE title = :Title")
    void UpdateFav(String Title);

    @Query("UPDATE movies SET is_favourit = 0 WHERE title = :Title")
    void DeleteFav(String Title);

    @Query("SELECT is_favourit FROM movies WHERE title =:Title")
    boolean getFavState(String Title);

    @Query("SELECT * FROM movies WHERE flag = 'Now_playing' ")
    List<Movie> getNowPlaying();

    @Query("SELECT * FROM movies  WHERE flag = 'Top_movies'")
    List<Movie> getTopMovies();

    @Query("SELECT * FROM movies WHERE is_favourit = 1 ")
    List<Movie> getFavMovies();

}