package com.example.myapplication1.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.myapplication1.Model.movieInteractor;
import com.example.myapplication1.Presenter.TopMoviesPresenter;
import com.example.myapplication1.Presenter.nowPlayingPresenter;
import com.example.myapplication1.R;
import com.example.myapplication1.ViewInterfaces.INowPlaying;
import com.example.myapplication1.ViewInterfaces.ItopMovies;

public class TopMoviesFragment extends Fragment implements ItopMovies {

    private ItopMovies.Ipresenter mNowPlayingPresenter;

    private GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_movies, container, false);

        gridView = view.findViewById(R.id.now_playing_list);
        //mNowPlayingPresenter = new TopMoviesPresenter(this , new movieInteractor());
       // mNowPlayingPresenter.getMovies();
        return view;
    }
}