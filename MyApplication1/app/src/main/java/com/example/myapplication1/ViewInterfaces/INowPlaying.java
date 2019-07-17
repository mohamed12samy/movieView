package com.example.myapplication1.ViewInterfaces;

import com.example.myapplication1.Model.Movie;
import com.example.myapplication1.Model.movieList;

import java.util.ArrayList;

public interface INowPlaying {


    interface ImoviesInteractor {


        interface getList{

            void sendList(movieList mMovieList1);
        }
        void getCurrentMovies(INowPlaying.ImoviesInteractor.getList l);
    }

    interface Ipresenter
    {
        void onDestroy();
        void getMovies();
    }

    interface InowPlaying {

        void setMovies(ArrayList<Movie> movies);
    }
}
