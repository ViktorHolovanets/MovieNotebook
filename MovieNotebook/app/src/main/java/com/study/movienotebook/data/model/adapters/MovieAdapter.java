package com.study.movienotebook.data.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.study.movienotebook.R;
import com.study.movienotebook.data.lib.Library;
import com.study.movienotebook.data.model.DB.entities.Movie;
import com.study.movienotebook.data.model.DB.entities.UserView;
import com.study.movienotebook.data.repositories.Singleton;
import com.study.movienotebook.data.services.http.VolleyService;

import org.json.JSONException;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {
    private LayoutInflater inflater;
    private int layout;
    private List<Movie> movies;

    public MovieAdapter(@NonNull Context context, int resource, List<Movie> movies) {
        super(context, resource, movies);
        this.movies = movies;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        ImageView poster;
        TextView title;
        TextView type;
        TextView year;
        Button btnAdd;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.poster = convertView.findViewById(R.id.poster_movie);
            viewHolder.title = convertView.findViewById(R.id.title_film);
            viewHolder.type = convertView.findViewById(R.id.type_film);
            viewHolder.year = convertView.findViewById(R.id.year_film);
            viewHolder.btnAdd = convertView.findViewById(R.id.btn_addView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Movie movie = getItem(position);

        Glide.with(getContext()).load(movie.getPoster()).into(viewHolder.poster);
        viewHolder.title.setText(movie.getTitle());
        viewHolder.type.setText(movie.getType());
        viewHolder.year.setText(movie.getYear());
        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getContext().getString(R.string.server) + "/new/views";
                VolleyService volleyService = new VolleyService(getContext());
                try {
                    volleyService.loadPost(url, Library.getMovieJSONObject(movie), response -> {

                        Gson gson = new Gson();
                        UserView userView = gson.fromJson(response.toString(), UserView.class);

                        if (Singleton.getInstance().getUserViews().stream()
                                .noneMatch(i -> i.getViews().getMovie() == userView.getViews().getMovie())) {
                            Singleton.getInstance().getUserViews().add(userView);
                        }
                    });
                } catch (JSONException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }
}
