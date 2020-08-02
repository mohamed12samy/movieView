package com.example.movieApp.ViewInterfaces;

import com.example.movieApp.Model.Movie;
import com.example.movieApp.Model.VideoList;
import com.example.movieApp.Model.movieList;

import java.util.List;

public interface Imovies {


    interface ImoviesInteractor {


        interface getList{

        }
        void getCurrentMovies(Imovies.Ipresenter l , int page);
        void getTopMovies(Imovies.Ipresenter listener, int page );
        void getMovieTrailers(Imovies.IMovieOverviewpresenter listener, int id);
        movieList getNowPlaying();
        movieList getTop();
        void insertFavMovie(String title);
        void DeleteFavMovie(String title);
        movieList getFavmovies(Imovies.IFavPresenter presenter);
        boolean getFavState(String title);
        void InsertFavMovie(Movie movie);
    }

    interface Ipresenter
    {
        void onDestroy();
        void getMovies(int page);
        void sendList(movieList mMovieList1 ,int page);
        void NoCheckConnection();
        void insertFav(Movie movie/*String title*/);
        void deleteFav(String title);
    }

    interface IMovieOverviewpresenter{
        void getTrailers(int id);
        void insertFav(Movie movie/*String title*/);
        void deleteFav(String title);
        void updateTrailerList(VideoList videoListt);
    }
    interface IFavPresenter
    {
        void onDestroy();
        void getMovies();
        void sendList(movieList mMovieList1);
        //void NoCheckConnection();
        void insertFav(Movie movie/*String title*/);
        void deleteFav(String title);
    }
    interface InowPlaying {

        void setMovies(List<Movie> movies);
        void setMoviesWithoutConnection(List<Movie> movies);
    }
    interface ImovieOverview{
        void updateTrailerList(VideoList videoListt);
    }
}
