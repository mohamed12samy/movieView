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

import com.example.movieApp.ClickListener;
import com.example.movieApp.Model.Movie;
import com.example.movieApp.Model.movieInteractor;
import com.example.movieApp.MovieOverview;
import com.example.movieApp.MyApplication;
import com.example.movieApp.Presenter.FavMoviesPresenter;
import com.example.movieApp.R;
import com.example.movieApp.RecyclerViewAdapter;
import com.example.movieApp.ViewInterfaces.Imovies;

import java.util.ArrayList;
import java.util.List;

public class FavMovies extends Fragment implements Imovies.InowPlaying, ClickListener {
    private Imovies.IFavPresenter favpresenter ;

    private RecyclerView recyclerView;

    private List<Movie> moMvieList = new ArrayList<>();

    private RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(getActivity(), moMvieList,this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav_movies, container, false);


        recyclerView = view.findViewById(R.id.fav_movies_recyclerView);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        //recyclerView.addItemDecoration(new ItemDecoration(23));

        favpresenter = new FavMoviesPresenter(this , new movieInteractor());
        favpresenter.getMovies();
        Log.d("asd", MyApplication.getInstance().getApplicationContext().toString());


        return view;
    }

            @Override
    public void clickListen(Movie movie) {
        if(movie.isFavourite())
            favpresenter.insertFav(movie);

        else if(!movie.isFavourite()){

            favpresenter.deleteFav(movie.getTitle());

            moMvieList.remove(movie);
            mAdapter.notifyDataSetChanged();

        }
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
    public void setMovies(List<Movie> movies) {
        if (movies != null) {
            moMvieList.addAll(movies);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setMoviesWithoutConnection(List<Movie> movies) {

    }
}
