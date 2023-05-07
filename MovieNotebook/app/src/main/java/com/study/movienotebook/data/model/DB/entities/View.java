package com.study.movienotebook.data.model.DB.entities;

import com.google.gson.annotations.SerializedName;

public class View {
    @SerializedName("movie")
    private Movie movie;

    @SerializedName("plot")
    private String plot;

    @SerializedName("actors")
    private String actors;

    @SerializedName("genre")
    private String genre;

    @SerializedName("totalSeasons")
    private String totalSeasons;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(String totalSeasons) {
        this.totalSeasons = totalSeasons;
    }
}

