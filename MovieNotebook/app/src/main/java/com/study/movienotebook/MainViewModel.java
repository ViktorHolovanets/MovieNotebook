package com.study.movienotebook;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.study.movienotebook.data.model.DB.AppDatabase;
import com.study.movienotebook.data.model.DB.entities.User;
import com.study.movienotebook.data.model.DB.interfaces.UserDao;

public class MainViewModel extends AndroidViewModel {

    private UserDao userDao;
    private MutableLiveData<User> _user = new MutableLiveData<User>();
    public LiveData<User> user = _user;

    public MainViewModel(Application application) {
        super(application);
        userDao = AppDatabase.getInstance(application).userDao();
    }

    public void getUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    User user = userDao.getAllUsers().get(0);
                    _user.postValue(user);
                } catch (IndexOutOfBoundsException exception) {
                    _user.postValue(null);
                }
            }
        }).start();
    }
}


