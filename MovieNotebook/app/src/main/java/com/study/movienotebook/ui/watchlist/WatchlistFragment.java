package com.study.movienotebook.ui.watchlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.study.movienotebook.R;
import com.study.movienotebook.data.model.DB.entities.UserView;
import com.study.movienotebook.data.model.adapters.WatchlistAdapter;
import com.study.movienotebook.data.repositories.Singleton;
import com.study.movienotebook.data.services.http.VolleyService;
import com.study.movienotebook.databinding.FragmentWatchlistBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WatchlistFragment extends Fragment {

    private List<UserView> userViews;
    private WatchlistAdapter watchlistAdapter;
    private FragmentWatchlistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WatchlistViewModel homeViewModel =
                new ViewModelProvider(this).get(WatchlistViewModel.class);

        binding = FragmentWatchlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        final ListView movieView = binding.myWatchlist;
        userViews = new ArrayList<>();
        watchlistAdapter = new WatchlistAdapter(getContext(), R.layout.item_list_my_views, userViews);

        movieView.setAdapter(watchlistAdapter);
        if (Singleton.getInstance().getUserViews().size() < 1 && Singleton.getInstance().getToken() != null) {
            String url = getContext().getString(R.string.server) + "/views";
            VolleyService volleyService = new VolleyService(getContext());

            volleyService.loadArrayPost(url, null, response -> {
                try {
                    Gson gson = new Gson();
                    UserView[] userViewsArray = gson.fromJson(response.toString(), UserView[].class);
                    List<UserView> allWatchlist = Arrays.asList(userViewsArray);
                    Singleton.getInstance().getUserViews().addAll(allWatchlist);
                    updateListView();
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            updateListView();
        }

    }

    private void updateListView() {
        userViews.addAll(Singleton.getInstance().getUserViews().stream().filter(view -> view.isView()).collect(Collectors.toList()));
        watchlistAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}