package com.study.movienotebook.data.model.DB.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    @SerializedName("imdbID")
    private String imdbID;

    @SerializedName("title")
    private String title;

    @SerializedName("year")
    private String year;

    @SerializedName("type")
    private String type;

    @SerializedName("poster")
    private String poster;



    public Movie() {
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    // Конструктор для передачі об'єкта між активностями/фрагментами
    protected Movie(Parcel in) {
        imdbID = in.readString();
        title = in.readString();
        year = in.readString();
        type = in.readString();
        poster = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    // Записуємо значення полів класу в Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imdbID);
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(type);
        dest.writeString(poster);
    }
}
