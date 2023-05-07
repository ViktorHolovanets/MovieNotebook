package com.study.movienotebook.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.study.movienotebook.R;
import com.study.movienotebook.data.model.DB.AppDatabase;
import com.study.movienotebook.data.model.DB.entities.User;
import com.study.movienotebook.data.repositories.Singleton;
import com.study.movienotebook.data.services.http.VolleyService;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends Fragment {
    public LoginFragment() {

    }

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        Button btnSend = view.findViewById(R.id.login);
        btnSend.setOnClickListener(this::send);
        return view;
    }

    private void send(View view) {
        if (!isValidRequest()) {
            return;
        }
        VisibilityProgramBar();

        EditText emailEditText = this.view.findViewById(R.id.login_email);
        String email = emailEditText.getText().toString();

        EditText passwordEditText = this.view.findViewById(R.id.password);
        String password = passwordEditText.getText().toString();

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("Email", email);
            jsonRequest.put("Password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = getString(R.string.server) + "/api/login";
        VolleyService volleyService = new VolleyService(getContext());
        volleyService.loadPost(url, jsonRequest, response -> {
            try {
                String token = response.getString("token");
                String name = response.getString("name");
                Singleton.getInstance().setToken(token);
                User user=new User(name, email, password);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase.getInstance(getContext()).userDao().insertUser(user);
                    }
                }).start();
                Singleton.getInstance().setUser(user);
                getActivity().finish();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private boolean isValidRequest() {
        Boolean isValid = true;
        EditText emailEditText = view.findViewById(R.id.login_email);
        if (!isValidEmail(emailEditText.getText().toString())) {
            emailEditText.setError(getString(R.string.invalid_email));
            isValid = false;
        }
        return isValid;
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void VisibilityProgramBar() {
        ProgressBar progressBar = getActivity().findViewById(R.id.loading);
        if (progressBar != null)
            progressBar.setVisibility(View.VISIBLE);
    }
}
