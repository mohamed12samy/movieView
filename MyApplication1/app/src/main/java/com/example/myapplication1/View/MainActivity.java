package com.example.myapplication1.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.myapplication1.R;
import com.example.myapplication1.ViewInterfaces.ImainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {

    //Imovie apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*apiService = RestClient.getClient().create(Imovie.class);
        fetchMovies();*/

        navigate();
    }


    private void navigate() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,
                navHostFragment.getNavController());
    }

    /*private void fetchMovies() {
        Call<List<Movies>> calling = apiService.getCurMovies("94fd0367fe3409f7e819fa65831803ca");/*getMovies(1,"94fd0367fe3409f7e819fa65831803ca",
                "release_date.desc","false",2000);*/
      /*  calling.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
                Log.d("TAG", "size : " + response.toString());
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                Log.e("TAG", "Got error : " + t.getLocalizedMessage());
            }
        });
    }*/

}
