package com.example.movieApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.movieApp.Model.Movie;
import com.example.movieApp.Model.MovieTrailer;
import com.example.movieApp.Model.VideoList;
import com.example.movieApp.Model.movieInteractor;
import com.example.movieApp.Presenter.MovieOverviewPresenter;
import com.example.movieApp.ViewInterfaces.Imovies;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MovieOverview extends YouTubeBaseActivity implements Imovies.ImovieOverview {

    Toolbar toolbar;
    ImageView movie_poster;
    ImageView movie_backdrop;
    TextView movie_overview;
    RatingBar ratingBar;
    FloatingActionButton fav_button;


    Imovies.IMovieOverviewpresenter movieOverviewPresenter;
    Movie movie;

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    List<MovieTrailer> videoList = new ArrayList<>();

    String backdrop_path;
    String poster_path;
    float rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);


        rate = (float)getIntent().getDoubleExtra("vote",0)/2;
        int id = getIntent().getIntExtra("id",0);
        final boolean fav = getIntent().getBooleanExtra("favourite",false);
        movie = getIntent().getParcelableExtra("movie");
        backdrop_path = "https://image.tmdb.org/t/p/original"+movie.getBackdrop_path();
        poster_path = "https://image.tmdb.org/t/p/original"+movie.getPhotoPath();
        Log.d("bhytr",id+"  "+rate+"  "+fav);



        toolbar = findViewById(R.id.toolbar);
        movie_poster = findViewById(R.id.moviePoster);
        movie_backdrop = findViewById(R.id.back_poster);
        movie_overview = findViewById(R.id.overview);
        ratingBar = findViewById(R.id.rating_movie);
        fav_button = findViewById(R.id.floating_fav);

        movieOverviewPresenter = new MovieOverviewPresenter(new movieInteractor(), this);
        movieOverviewPresenter.getTrailers(id);


        updatingUI();

        /*setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movie.setVote_average((double) (rate * 2));
                if(!fav) {
                    movie.setFavourite(true);
                    movieOverviewPresenter.insertFav(movie);
                    Toast.makeText(MovieOverview.this, "Movie added to favourite", Toast.LENGTH_SHORT).show();
                }else if(fav){
                    movie.setFavourite(false);
                    movieOverviewPresenter.deleteFav(movie.getTitle());
                    Toast.makeText(MovieOverview.this, "Movie deleted to favourite", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void updatingUI(){
        toolbar.setTitle(movie.getTitle());
        movie_overview.setText(movie.getOverview());
        Glide.with(movie_backdrop.getContext())
                .load(backdrop_path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(movie_backdrop);

        Glide.with(movie_poster.getContext())
                .load(poster_path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(movie_poster);

        ratingBar.setRating(rate);
    }
    private void handlingYoutube(final String key){
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(key);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youTubePlayerView = findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(YoutubeConfig.getApiKey(),mOnInitializedListener);
    }
    @Override
    public void updateTrailerList(VideoList videoListt) {
        videoList = videoListt.getMovieTrailers();
        for (int i=0; i<videoList.size(); i++){
            MovieTrailer trailer = videoList.get(i);
            if(!"Trailer".equals(trailer.getType())){
                videoList.remove(trailer);
                i--;
            }
        }
        handlingYoutube(videoList.get(0).getKey());

    }
}
