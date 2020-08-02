package com.example.movieApp.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.movieApp.Model.Movie;
import com.example.movieApp.MyApplication;

@Database(entities = Movie.class,version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract MoviesDao dao();

    public static AppDatabase getInstance(/*Context context*/)
    {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(MyApplication.getInstance().getApplicationContext(),
                    AppDatabase.class,"moviesDatabase").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }


}
