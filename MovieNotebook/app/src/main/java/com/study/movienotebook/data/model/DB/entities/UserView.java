package com.study.movienotebook.data.model.DB.entities;

import com.google.gson.annotations.SerializedName;

public class UserView  {
    @SerializedName("seasons")
    private Integer seasons;

    @SerializedName("episode")
    private Integer episode;

    @SerializedName("myViews")
    private View views;

    @SerializedName("isView")
    private boolean isView;
    public boolean isView() {
        return isView;
    }

    public void setView(boolean view) {
        isView = view;
    }

    public Integer getSeasons() {
        return seasons;
    }

    public void setSeasons(Integer seasons) {
        this.seasons = seasons;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public View getViews() {
        return views;
    }

    public void setViews(View views) {
        this.views = views;
    }
}
