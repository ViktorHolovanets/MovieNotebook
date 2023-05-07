package com.study.movienotebook.data.repositories;

import com.study.movienotebook.data.model.DB.entities.User;
import com.study.movienotebook.data.model.DB.entities.UserView;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static Singleton instance;
    private final List<UserView> userViews = new ArrayList<>();
    private String token;
    private User user;

    public String getToken() {
        return token;
    }

    public List<UserView> getUserViews() {
        return userViews;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Singleton() {

    }

    public static void destroyInstance() {
        if (instance != null) {
            instance = null;
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
