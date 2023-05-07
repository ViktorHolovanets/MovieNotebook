package com.study.movienotebook.data.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.study.movienotebook.R;
import com.study.movienotebook.data.lib.Library;
import com.study.movienotebook.data.model.DB.entities.Movie;
import com.study.movienotebook.data.model.DB.entities.UserView;
import com.study.movienotebook.data.repositories.Singleton;
import com.study.movienotebook.data.services.http.VolleyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class WatchlistAdapter extends ArrayAdapter<UserView> {
    private LayoutInflater inflater;
    private int layout;
    private List<UserView> userViews;

    public WatchlistAdapter(@NonNull Context context, int resource, List<UserView> userViews) {
        super(context, resource, userViews);
        this.userViews = userViews;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        TextView title;
        ImageView poster;
        Spinner season;
        Spinner episode;
        TextView plot;
        TextView year;
        Button btnUpdate;
        Button btnDelete;
        LinearLayout infoLinearLayout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        WatchlistAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new WatchlistAdapter.ViewHolder();
            viewHolder.poster = convertView.findViewById(R.id.poster_movie);
            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.season = convertView.findViewById(R.id.spinner_season);
            viewHolder.year = convertView.findViewById(R.id.year);
            viewHolder.episode = convertView.findViewById(R.id.spinner_episode);
            viewHolder.btnUpdate = convertView.findViewById(R.id.update);
            viewHolder.plot = convertView.findViewById(R.id.plot);
            viewHolder.btnDelete=convertView.findViewById(R.id.delete);
            viewHolder.infoLinearLayout=convertView.findViewById(R.id.info_mark);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (WatchlistAdapter.ViewHolder) convertView.getTag();
        }

        UserView userView = getItem(position);
        Movie movie = userView.getViews().getMovie();
        Glide.with(getContext()).load(movie.getPoster()).into(viewHolder.poster);
        viewHolder.title.setText(movie.getTitle());
        viewHolder.year.setText(movie.getYear());
        viewHolder.plot.setText(userView.getViews().getPlot());
        viewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getContext().getString(R.string.server) + "/update/serial";
                VolleyService volleyService = new VolleyService(getContext());
                try {
                    JSONObject jsonBody = new JSONObject();
                    int s_spinner=Integer.parseInt(viewHolder.season.getSelectedItem().toString());
                    int ep_spinner=Integer.parseInt(viewHolder.episode.getSelectedItem().toString());
                    jsonBody.put("serial", Library.getMovieJSONObject(movie));
                    jsonBody.put("season", s_spinner);
                    jsonBody.put("episode", ep_spinner);
                    volleyService.loadPost(url, jsonBody, response -> {
                        try {
                            boolean isUpdate = response.getBoolean("isUpdate");
                            if(isUpdate){
                                userView.setSeasons(s_spinner);
                                userView.setEpisode(ep_spinner);
                                userView.setView(isUpdate);
                            }
                        } catch (Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getContext().getString(R.string.server) + "/delete";
                VolleyService volleyService = new VolleyService(getContext());
                try {
                    volleyService.loadPost(url, Library.getMovieJSONObject(movie), response -> {
                        try {
                            boolean isDelete = response.getBoolean("isDelete");
                            if(isDelete){
                                Singleton.getInstance().getUserViews().remove(userView);
                                remove(userView);
                                notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(movie.getType().equals("series")){
            viewHolder.infoLinearLayout.setVisibility(View.VISIBLE);
            List<Integer> episodes = new ArrayList<>();
            ArrayAdapter<Integer> episodesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, episodes);
            viewHolder.episode.setAdapter(episodesAdapter);
            List<Integer> numbers = getListInteger(Integer.parseInt(userView.getViews().getTotalSeasons()));
            ArrayAdapter<Integer> seasonsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, numbers);
            seasonsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            viewHolder.season.setAdapter(seasonsAdapter);
            viewHolder.season.setSelection(numbers.indexOf(userView.getSeasons()));
            viewHolder.season.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String url = getContext().getString(R.string.server) + "/serial/season";
                    VolleyService volleyService = new VolleyService(getContext());
                    try {
                        Object item = parent.getItemAtPosition(position);
                        String selectedItem = item.toString();
                        JSONObject jsonBody = new JSONObject();
                        jsonBody.put("serial", Library.getMovieJSONObject(movie));
                        Integer selectSeason = Integer.parseInt(selectedItem);
                        jsonBody.put("season", selectSeason);

                        volleyService.loadPost(url, jsonBody, response -> {
                            try {
                                int episodesSeason = response.getInt("episodes");
                                episodes.clear();
                                List<Integer> ep = getListInteger(episodesSeason);
                                episodes.addAll(ep);
                                episodesAdapter.notifyDataSetChanged();
                                viewHolder.episode.setSelection(userView.getSeasons() == selectSeason ? episodes.indexOf(userView.getEpisode()) : 0);
                            } catch (JSONException e) {
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        return convertView;
    }

    public List<Integer> getListInteger(int startNumber) {

        List<Integer> numbersList = new ArrayList<>();
        for (int i = 1; i <= startNumber; i++) {
            numbersList.add(i);
        }
        return numbersList;
    }
}
