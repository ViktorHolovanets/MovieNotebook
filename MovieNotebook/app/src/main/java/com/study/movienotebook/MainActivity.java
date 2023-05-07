package com.study.movienotebook;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.study.movienotebook.data.model.DB.AppDatabase;
import com.study.movienotebook.data.model.DB.entities.User;
import com.study.movienotebook.data.repositories.Singleton;
import com.study.movienotebook.data.services.http.VolleyService;
import com.study.movienotebook.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = binding.navViewMain;
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_search, R.id.navigation_exit)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navViewMain, navController);

    }

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Singleton.getInstance().getUser() == null) {
                    try {
                        User userFromDb = AppDatabase.getInstance(MainActivity.this).userDao().getAllUsers().get(0);
                        JSONObject jsonRequest = new JSONObject();
                        jsonRequest.put("Email", userFromDb.getEmail());
                        jsonRequest.put("Password", userFromDb.getPassword());
                        String url = getString(R.string.server) + "/api/login";
                        VolleyService volleyService = new VolleyService(MainActivity.this);
                        volleyService.loadPost(url, jsonRequest, response -> {
                            try {
                                String token = response.getString("token");
                                Singleton.getInstance().setToken(token);
                                Singleton.getInstance().setUser(userFromDb);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    } catch (IndexOutOfBoundsException e) {
                        startActivity(new Intent(MainActivity.this, Auth.class));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}