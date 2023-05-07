package com.study.movienotebook.ui.search;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.movienotebook.R;
import com.study.movienotebook.data.model.DB.entities.Movie;
import com.study.movienotebook.data.model.adapters.MovieAdapter;
import com.study.movienotebook.data.model.request.ParamsSearch;
import com.study.movienotebook.data.services.http.VolleyService;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    public SearchFragment() {

    }

    private View view;
    private ParamsSearch paramsSearch;
    List<Movie> states;
    MovieAdapter stateAdapter;
    ListView listView;
    private boolean isOpenFilter = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        paramsSearch = new ParamsSearch();

        listView = view.findViewById(R.id.result_search);
        states = new ArrayList<Movie>();
        stateAdapter = new MovieAdapter(getContext(), R.layout.item_list_films, states);
        listView.setAdapter(stateAdapter);
        view.findViewById(R.id.send_search).setOnClickListener(this::sendSearch);
        view.findViewById(R.id.filter_search).setOnClickListener(this::viewFilter);
        TextInputEditText editText = view.findViewById(R.id.edittext_search);
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendSearch(editText);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                editText.clearFocus();
                return true;
            }
            return false;
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("MOVIES", (ArrayList<? extends Parcelable>) states);
        outState.putParcelableArrayList("PARAMS", paramsSearch);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            states = savedInstanceState.getParcelableArrayList("MOVIES");
            stateAdapter = new MovieAdapter(getContext(), R.layout.item_list_films, states);
            listView.setAdapter(stateAdapter);
            paramsSearch = (ParamsSearch) savedInstanceState.getParcelableArrayList("PARAMS");
        }
    }

    private void viewFilter(View view) {
        if(isOpenFilter){
            return;
        }
        isOpenFilter=true;
        final Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Material_Dialog);
        dialog.setContentView(R.layout.filter_dialog);
        dialog.setTitle(R.string.filter_title);
        Spinner spinner = dialog.findViewById(R.id.year_spinner);
        List<String> years = paramsSearch.getListYear(1956);
        ArrayAdapter<String> yearsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, years);
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(yearsAdapter);
        spinner.setSelection(years.indexOf(paramsSearch.getYear()));
        CheckBox series = dialog.findViewById(R.id.param_serial);
        series.setChecked(paramsSearch.isSerial());
        CheckBox movies = dialog.findViewById(R.id.param_movie);
        movies.setChecked(paramsSearch.isMovie());
        Button cancelButton = dialog.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                isOpenFilter=false;
            }
        });
        dialog.findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSerial = series.isChecked();
                boolean isMovie = movies.isChecked();
                String year = spinner.getSelectedItem().toString();
                if (isSerial && !isMovie) {
                    paramsSearch.setType("series");
                } else if (!isSerial && isMovie) {
                    paramsSearch.setType("movie");
                }
                paramsSearch.setYear(year);

                dialog.dismiss();
                isOpenFilter=false;
            }
        });
        dialog.show();
    }

    private void sendSearch(View view) {
        String search = ((EditText) this.view.findViewById(R.id.edittext_search)).getText().toString().trim();
        if (search == null || search.trim().isEmpty()) {
            return;
        }
        paramsSearch.setSearch(search.replaceAll("\\s+", "+"));
        String url = getString(R.string.server) + "/search";
        VolleyService volleyService = new VolleyService(getContext());
        volleyService.loadPost(url, paramsSearch.jsonRequest(), response -> {
            try {
                JSONArray movieArray = response.getJSONArray("value");
                Type movieListType = new TypeToken<ArrayList<Movie>>() {
                }.getType();
                ArrayList<Movie> movies = new Gson().fromJson(movieArray.toString(), movieListType);
                states.clear();
                states.addAll(movies);
                stateAdapter.notifyDataSetChanged();
                Button btnNext = this.view.findViewById(R.id.btn_next);
                Button btnBack = this.view.findViewById(R.id.btn_back);
                btnNext.setOnClickListener(this::navButton);
                btnBack.setOnClickListener(this::navButton);
                btnNext.setVisibility(View.VISIBLE);
                btnBack.setVisibility(View.VISIBLE);


            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void navButton(View view) {
        String btnNav = ((Button) view).getText().toString();
        boolean isSend = true;
        switch (btnNav) {
            case "Back":
                int page = paramsSearch.getPage();
                if (page != 1)
                    paramsSearch.setPage(page - 1);
                else isSend = false;
                break;
            case "Next":
                paramsSearch.setPage(paramsSearch.getPage() + 1);
                break;
        }
        if (isSend)
            sendSearch(view);
    }
}