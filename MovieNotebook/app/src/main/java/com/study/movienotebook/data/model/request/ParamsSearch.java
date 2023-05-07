package com.study.movienotebook.data.model.request;

import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class ParamsSearch extends ArrayList<Parcelable> {
    private String search;
    private Integer page=1;
    private String type;
    private String plot;
    private String year="All";
    private String id;
    private int season;


    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public List<String> getListYear(int startYear) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        List<String> yearsList = new ArrayList<>();
        for (int i = startYear; i <= currentYear; i++) {
            yearsList.add(Integer.toString(i));
        }
        yearsList.add("All");
        Collections.reverse(yearsList);
        return yearsList;
    }

    public JSONObject jsonRequest() {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("search", search != null ? search : JSONObject.NULL);
            jsonRequest.put("page", page != null ? page : JSONObject.NULL);
            jsonRequest.put("type", type != null ? type : JSONObject.NULL);
            jsonRequest.put("plot", plot != null ? plot : JSONObject.NULL);
            jsonRequest.put("year", year != "All" ? year : JSONObject.NULL);
            jsonRequest.put("id", id != null ? id : JSONObject.NULL);
            jsonRequest.put("season", season != 0 ? season : JSONObject.NULL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonRequest;
    }
    public Boolean isSerial(){
        return type == "series";
    }
    public Boolean isMovie(){
        return type == "movie";
    }
}
