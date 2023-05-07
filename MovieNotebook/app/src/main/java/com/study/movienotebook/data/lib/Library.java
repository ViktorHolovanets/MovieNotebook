package com.study.movienotebook.data.lib;

import com.study.movienotebook.data.model.DB.entities.Movie;

import org.json.JSONException;
import org.json.JSONObject;

public class Library {
    public static JSONObject getMovieJSONObject(Movie movie) throws JSONException {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("imdbID", movie.getImdbID());
            jsonBody.put("Title", movie.getTitle());
            jsonBody.put("Year", movie.getYear());
            jsonBody.put("Type", movie.getType());
            jsonBody.put("Poster", movie.getPoster());
        }catch (Exception ex){}

        return jsonBody;
    }
}
