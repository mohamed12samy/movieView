package com.example.myapplication1.Model;

import android.util.Log;

import com.example.myapplication1.ViewInterfaces.INowPlaying;
import com.example.myapplication1.Presenter.nowPlayingPresenter;
import com.example.myapplication1.Rest.Imovie;
import com.example.myapplication1.Rest.RestClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class movieInteractor implements INowPlaying.ImoviesInteractor {

    private  movieList mMovieList = new movieList();
    private INowPlaying.Ipresenter mNowPlayingPresenter = new nowPlayingPresenter();


    @Override
    public void getCurrentMovies(final INowPlaying.ImoviesInteractor.getList listener) {
        getCurMovies(listener);

    }


    public void getCurMovies(final INowPlaying.ImoviesInteractor.getList f) {


        Imovie apiService = RestClient.getClient().create(Imovie.class);
        Call<movieList> calling = apiService.getCurMovies("94fd0367fe3409f7e819fa65831803ca");/*getMovies(1,"94fd0367fe3409f7e819fa65831803ca",
                "release_date.desc","false",2000);*/

        calling.enqueue(new Callback<movieList>() {
            @Override
            public void onResponse(Call<movieList> call, Response<movieList> response) {
                if(!response.isSuccessful()){
                    Log.d("TAGG","ERROR :  "+    response.raw().body().toString());
                    return;
                }

                else if(response.isSuccessful()) {
                    mMovieList =  response.body();

                    f.sendList(mMovieList);

                }
            }

            @Override
            public void onFailure(Call<movieList> call, Throwable t) {
                Log.e("TAG", "Got error : " + t.getLocalizedMessage());
            }
        });
    }
}