package com.example.myapplication1.Presenter;

import com.example.myapplication1.ViewInterfaces.INowPlaying;
import com.example.myapplication1.Model.Movie;
import com.example.myapplication1.Model.movieList;

import java.util.ArrayList;

public class nowPlayingPresenter implements INowPlaying.Ipresenter, INowPlaying.ImoviesInteractor.getList {

    private INowPlaying.ImoviesInteractor interactor;
    private INowPlaying.InowPlaying inowPlaying;
    public static movieList mMovieList = new movieList();

    public nowPlayingPresenter(INowPlaying.InowPlaying inowPlaying, INowPlaying.ImoviesInteractor interactor) {
        this.inowPlaying = inowPlaying;
        this.interactor = interactor;
    }

    public nowPlayingPresenter(){}
/*
    @Override
    public void onFinished(void curMovies) {

    }*/

    @Override
    public void onDestroy() {
        inowPlaying = null;
    }

    @Override
    public void getMovies() {
        interactor.getCurrentMovies(this);
    }


    @Override
    public void sendList(movieList mMovieList1) {
        mMovieList = mMovieList1;
        if (inowPlaying != null) {
            inowPlaying.setMovies((ArrayList<Movie>) mMovieList.getMovies());
        }
    }
}