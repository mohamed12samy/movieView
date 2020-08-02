package com.example.movieApp.Presenter;

import android.content.Context;

import com.example.movieApp.ViewInterfaces.Imovies;
import com.example.movieApp.Model.Movie;
import com.example.movieApp.Model.movieList;
import com.example.movieApp.database.AppDatabase;

import java.util.List;

public class nowPlayingPresenter implements Imovies.Ipresenter{

    private Imovies.ImoviesInteractor interactor;
    private Imovies.InowPlaying inowPlaying;
    public static movieList mMovieList = new movieList();
    private Context mContext;

    public nowPlayingPresenter(Imovies.InowPlaying inowPlaying, Imovies.ImoviesInteractor interactor/*, Context context*/) {
        this.inowPlaying = inowPlaying;
        this.interactor = interactor;
        //this.mContext = MyApplication.getInstance().getApplicationContext();
    }

    @Override
    public void onDestroy() {
        inowPlaying = null;
    }

    @Override
    public void getMovies(int page ) {
        interactor.getCurrentMovies(this , page);

    }


    @Override
    public void sendList(movieList mMovieList1 , int page) {
        mMovieList = mMovieList1;
        mMovieList.setFlag("Now_playing");
        if (inowPlaying != null) {
            inowPlaying.setMovies(mMovieList.getMovies());
            if(page == 1)
                AppDatabase.getInstance(/*mContext*/).dao().insertMovies(mMovieList.getMovies());
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

    @Override
    public void NoCheckConnection() {
        if (inowPlaying != null) {
            List<Movie> movies = AppDatabase.getInstance(/*mContext*/).dao().getNowPlaying();
            inowPlaying.setMoviesWithoutConnection(movies);

        }
    }

}