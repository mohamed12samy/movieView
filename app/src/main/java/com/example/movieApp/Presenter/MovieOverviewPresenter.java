package com.example.movieApp.Presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.movieApp.Model.Movie;
import com.example.movieApp.Model.VideoList;
import com.example.movieApp.Model.movieList;
import com.example.movieApp.MovieOverview;
import com.example.movieApp.Rest.Imovie;
import com.example.movieApp.ViewInterfaces.Imovies;

public class MovieOverviewPresenter implements Imovies.IMovieOverviewpresenter{

    private Imovies.ImoviesInteractor interactor;
    private Imovies.ImovieOverview imovieOverview;

    public MovieOverviewPresenter(Imovies.ImoviesInteractor interactor, Imovies.ImovieOverview imovieOverview) {
        this.interactor = interactor;
        this.imovieOverview = imovieOverview;
    }



    @Override
    public void getTrailers(int id) {
        interactor.getMovieTrailers(this, id);
    }

    @Override
    public void insertFav(Movie movie) {
        interactor.insertFavMovie(movie.getTitle());
    }

    @Override
    public void deleteFav(String title) {
        interactor.DeleteFavMovie(title);
    }

    @Override
    public void updateTrailerList(VideoList videoList) {
        imovieOverview.updateTrailerList(videoList);
    }
}
