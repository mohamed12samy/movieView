package com.example.movieApp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "movies")
public class Movie  implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String photoPath;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String backdrop_path;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String overview;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private Double vote_average;


    @ColumnInfo(name = "flag")   //Top_movies --- Now_playing
    private String flag ;


    @ColumnInfo(name = "is_favourit")
    private boolean favourite ;

    protected Movie(Parcel in) {
        title = in.readString();
        photoPath = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        flag = in.readString();
        favourite = in.readByte() == 0;
        vote_average = in.readDouble();
        id = in.readInt();
    }



    public static final Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };







    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", photoPath='" + photoPath + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", overview='" + overview + '\'' +
                ", vote_average=" + vote_average +
                ", flag='" + flag + '\'' +
                ", favourite=" + favourite +
                '}';
    }

    @Ignore
    public Movie(String title, String photoPath, String backdrodp_path, String overview, Double vote_average, int id) {
        this.title = title;
        this.photoPath = photoPath;
        this.backdrop_path = backdrodp_path;
        this.overview = overview;
        this.vote_average = vote_average;
        this.id = id;
    }
    public Movie(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(photoPath);
        parcel.writeString(backdrop_path);
        parcel.writeString(overview);
        parcel.writeDouble(vote_average);
        parcel.writeString(flag);
        parcel.writeByte((byte) (favourite ? 1 : 0));
        parcel.writeInt(id);
    }


}
