package com.example.movieApp.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.movieApp.ClickListener;
import com.example.movieApp.Model.Movie;
import com.example.movieApp.Model.movieInteractor;
import com.example.movieApp.MovieOverview;
import com.example.movieApp.Presenter.TopMoviesPresenter;
import com.example.movieApp.R;
import com.example.movieApp.RecyclerViewAdapter;
import com.example.movieApp.ViewInterfaces.Imovies;

import java.util.ArrayList;
import java.util.List;

public class TopMoviesFragment extends Fragment implements Imovies.InowPlaying, ClickListener {

    private Imovies.Ipresenter mTopMoviespresenter;

    private RecyclerView recyclerView;

    private int page;
    private List<Movie> moMvieList= new ArrayList<>() ;
    private RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(getActivity(),moMvieList, this);

    //private AppDatabase mAppDatabase;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_movies, container, false);

        //mAppDatabase = AppDatabase.getInstance(getActivity());
        page = 1;
        recyclerView = view.findViewById(R.id.top_movies_recycleerView);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));


//        gridView = view.findViewById(R.id.top_movies_list);
        mTopMoviespresenter = new TopMoviesPresenter(this , new movieInteractor());
        mTopMoviespresenter.getMovies(page);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(getActivity(), "Last", Toast.LENGTH_LONG).show();
                    mTopMoviespresenter.getMovies(++page);

                }
            }
        });
        return view;
    }

    @Override
    public void setMovies(List<Movie> movies) {

            if(movies != null) {

            moMvieList.addAll(movies);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void clickListen(Movie movie) {
        if(movie.isFavourite())
            mTopMoviespresenter.insertFav(movie);

        else if(!movie.isFavourite())
            mTopMoviespresenter.deleteFav(movie.getTitle());
    }

    @Override
    public void clickMovieListen(Movie movie, ImageView imageView) {
        Intent i = new Intent(getActivity(), MovieOverview.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                imageView, ViewCompat.getTransitionName(imageView));

        i.putExtra("vote",movie.getVote_average());
        i.putExtra("id",movie.getId());
        i.putExtra("favourite",movie.isFavourite());
        i.putExtra("movie",movie);
        Log.d("asasasas",movie.toString());
        startActivity(i, options.toBundle());
    }

    @Override
    public void setMoviesWithoutConnection( List<Movie> movies) {
        moMvieList.addAll(movies);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTopMoviespresenter.onDestroy();
    }
}