package com.example.myapplication1.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.myapplication1.ViewInterfaces.INowPlaying;
import com.example.myapplication1.Model.Movie;
import com.example.myapplication1.Presenter.nowPlayingPresenter;
import com.example.myapplication1.R;
import com.example.myapplication1.movieAdapter;
import com.example.myapplication1.Model.movieInteractor;

import java.util.ArrayList;

public class NowPlayingFragment extends Fragment implements INowPlaying.InowPlaying {


    private INowPlaying.Ipresenter mNowPlayingPresenter;

    private GridView gridView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_now_playing, container, false);

        gridView = view.findViewById(R.id.now_playing_list);
        mNowPlayingPresenter = new nowPlayingPresenter(this , new movieInteractor());
        mNowPlayingPresenter.getMovies();

        return  view;
    }

    @Override
    public void setMovies(ArrayList<Movie> movies) {

        if(movies != null) {
            gridView.setAdapter(new movieAdapter(getActivity(), 0, movies));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNowPlayingPresenter.onDestroy();
    }

}