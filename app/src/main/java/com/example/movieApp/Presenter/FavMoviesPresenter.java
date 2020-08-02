package com.example.movieApp.Presenter;

import com.example.movieApp.Model.Movie;
import com.example.movieApp.Model.movieList;
import com.example.movieApp.ViewInterfaces.Imovies;

public class FavMoviesPresenter implements Imovies.IFavPresenter {

    private Imovies.ImoviesInteractor interactor;
    private Imovies.InowPlaying iFav;
    public static movieList mMovieList = new movieList();

    public FavMoviesPresenter(Imovies.InowPlaying iFav, Imovies.ImoviesInteractor interactor/*, Context context*/) {
        this.iFav = iFav;
        this.interactor = interactor;
    }

    @Override
    public void onDestroy() {
        iFav = null;
    }

    @Override
    public void getMovies() {
        interactor.getFavmovies(this);
    }
    @Override
    public void sendList(movieList mMovieList1) {
        mMovieList = mMovieList1;
        iFav.setMovies(mMovieList.getMovies());
    }
    @Override
    public void insertFav(Movie movie) {
        interactor.insertFavMovie(movie.getTitle());
    }

    @Override
    public void deleteFav(String title) {
        interactor.DeleteFavMovie(title);
    }
}
