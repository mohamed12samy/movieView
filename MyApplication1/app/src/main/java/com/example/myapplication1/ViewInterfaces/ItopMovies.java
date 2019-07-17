package com.example.myapplication1.ViewInterfaces;

import com.example.myapplication1.Model.Movie;
import com.example.myapplication1.Model.movieList;

import java.util.ArrayList;

public interface ItopMovies {

    public interface Imtop {


        interface ImoviesInteractor {

            /*interface OnFinishedListener {

                void onFinished();
            }*/
            interface getList{
                void sendList(movieList mMovieList1);
            }
            void getCurrentMovies();
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

}
