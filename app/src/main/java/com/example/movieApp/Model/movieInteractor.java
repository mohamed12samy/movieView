package com.example.movieApp.Model;

import android.util.Log;

import com.example.movieApp.ViewInterfaces.Imovies;
import com.example.movieApp.Rest.Imovie;
import com.example.movieApp.Rest.RestClient;
import com.example.movieApp.database.AppDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class movieInteractor implements Imovies.ImoviesInteractor {

    private movieList mMovieList = new movieList();
    private movieList fav_movies = new movieList();
    private VideoList trailers = new VideoList();

    @Override
    public void getCurrentMovies(final Imovies.Ipresenter listener , final int page) {

        Imovie apiService = RestClient.getClient().create(Imovie.class);
        Call<movieList> calling = apiService.getCurMovies("94fd0367fe3409f7e819fa65831803ca",page);

        calling.enqueue(new Callback<movieList>() {
            @Override
            public void onResponse(Call<movieList> call, Response<movieList> response) {
                if(!response.isSuccessful()){
                    Log.d("TAGG","ERROR :  "+    response.raw().body().toString());

                    if(page == 1)
                        listener.NoCheckConnection();
                }

                else if(response.isSuccessful()) {
                    mMovieList =  response.body();
                    for(Movie m : mMovieList.getMovies())
                        m.setFavourite(getFavState(m.getTitle()));

                    listener.sendList(mMovieList , page);

                }
            }

            @Override
            public void onFailure(Call<movieList> call, Throwable t) {
                Log.e("TAG", "Got error : " + t.getLocalizedMessage());
                if(page == 1)
                    listener.NoCheckConnection();
            }
        });
    }





    /****************************************************************************************************************************************/




    @Override
    public void getTopMovies(final Imovies.Ipresenter listener, final int page) {
        Imovie apiService = RestClient.getClient().create(Imovie.class);
        Call<movieList> calling = apiService.getTopMovies("94fd0367fe3409f7e819fa65831803ca", page);

        calling.enqueue(new Callback<movieList>() {
            @Override
            public void onResponse(Call<movieList> call, Response<movieList> response) {
                if(!response.isSuccessful()){
                    Log.d("TAGG","ERROR :  "+    response.raw().body().toString());

                    if(page == 1)
                        listener.NoCheckConnection();
                }

                else if(response.isSuccessful()) {
                    mMovieList =  response.body();
                    for(Movie m : mMovieList.getMovies())
                        m.setFavourite(getFavState(m.getTitle()));
                    listener.sendList(mMovieList,page);

                }
            }

            @Override
            public void onFailure(Call<movieList> call, Throwable t) {
                if(page == 1)
                    listener.NoCheckConnection();
                Log.e("TAG", "Got error : " + t.getLocalizedMessage()+"   "+  page);
            }
        });
    }

    @Override
    public void getMovieTrailers(final Imovies.IMovieOverviewpresenter listener, final int id) {
        Imovie apiService = RestClient.getClient().create(Imovie.class);
        Call<VideoList> calling = apiService.getTrailer(id, "94fd0367fe3409f7e819fa65831803ca");

        calling.enqueue(new Callback<VideoList>() {
            @Override
            public void onResponse(Call<VideoList> call, Response<VideoList> response) {
                if(!response.isSuccessful()){
                    Log.d("TAGG","ERROR :  "+    response.raw().body().toString()+"   "+id);
                }

                else if(response.isSuccessful()) {
                    trailers =  response.body();
                    Log.d("frfff", trailers.getMovieTrailers().get(0).getKey());
                    listener.updateTrailerList(trailers);

                }
            }

            @Override
            public void onFailure(Call<VideoList> call, Throwable t) {

                Log.e("TAG", "Got error : " + t.getLocalizedMessage()+"   "+id);
            }
        });
    }

    /********************************************************************************************************/
@Override
public movieList getNowPlaying(){
    List<Movie> movies = AppDatabase.getInstance().dao().getNowPlaying();
    movieList m = new movieList();
    m.setMovies(movies);

    return m;
}

@Override
    public movieList getTop(){
        List <Movie> movies = AppDatabase.getInstance().dao().getTopMovies();
        movieList m = new movieList();
        m.setMovies(movies);

        return m;
    }

    @Override
    public void insertFavMovie(String title){
        AppDatabase.getInstance().dao().UpdateFav(title);
    }

    @Override
    public void DeleteFavMovie(String title){
        AppDatabase.getInstance().dao().DeleteFav(title);
    }

    @Override
    public movieList getFavmovies(Imovies.IFavPresenter presenter) {
        List<Movie> favMovies = AppDatabase.getInstance().dao().getFavMovies();
        Log.d("sert", favMovies.get(0).getTitle());
        fav_movies.setMovies(favMovies);

        presenter.sendList(fav_movies);
        return fav_movies;
    }

    @Override
    public boolean getFavState(String title){
        return AppDatabase.getInstance().dao().getFavState(title);
    }

    @Override
    public void InsertFavMovie(Movie movie){
        AppDatabase.getInstance().dao().insertFavMovie(movie);
    }

}