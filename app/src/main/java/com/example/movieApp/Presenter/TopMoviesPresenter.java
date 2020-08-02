package com.example.movieApp.Presenter;

import android.content.Context;

import com.example.movieApp.Model.Movie;
import com.example.movieApp.Model.movieList;
import com.example.movieApp.ViewInterfaces.Imovies;
import com.example.movieApp.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;


public class TopMoviesPresenter implements Imovies.Ipresenter , Imovies.ImoviesInteractor.getList{

    private Imovies.ImoviesInteractor interactor;
    private Imovies.InowPlaying itopmovies;
    public static movieList mMovieList = new movieList();
    private Context mContext;

    public TopMoviesPresenter(Imovies.InowPlaying itopmovies, Imovies.ImoviesInteractor interactor/*, Context context*/) {
        this.itopmovies = itopmovies;
        this.interactor = interactor;
    }

    @Override
    public void onDestroy() {
        itopmovies = null;
    }

    @Override
    public void getMovies(int page) {
        interactor.getTopMovies(this, page);
    }


    @Override
    public void sendList(movieList mMovieList1,int page) {
        mMovieList = mMovieList1;
        mMovieList.setFlag("Top_movies");

        if (itopmovies != null) {
            itopmovies.setMovies((ArrayList<Movie>) mMovieList.getMovies());
            if(page == 1)
                AppDatabase.getInstance(/*mContext*/).dao().insertMovies(mMovieList.getMovies());
        }
    }

    @Override
    public void NoCheckConnection() {
        if (itopmovies != null) {
            List<Movie> movies = AppDatabase.getInstance(/*mContext*/).dao().getTopMovies();
            itopmovies.setMoviesWithoutConnection(movies);
        }
    }

    @Override
    public void insertFav(Movie movie/*String title*/){
        interactor.InsertFavMovie(movie);
    }

    @Override
    public void deleteFav(String title){
        interactor.DeleteFavMovie(title);
    }


}
