package com.study.movienotebook.ui.login;

import static com.study.movienotebook.R.id;
import static com.study.movienotebook.R.layout;
import static com.study.movienotebook.R.string;

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

public class RegisterFragment extends Fragment {

    public RegisterFragment() {
        // Required empty public constructor
    }

    EditText nameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(layout.fragment_register, container, false);
        Button btnSend = view.findViewById(id.btn_register);
        btnSend.setOnClickListener(this::send);
        nameEditText = view.findViewById(id.username);
        emailEditText = view.findViewById(id.register_email);
        passwordEditText = view.findViewById(id.password);
        confirmPasswordEditText = view.findViewById(id.confirm_password);

        return view;
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void send(View view) {
        if (!isValidRequest()) {
            return;
        }
        VisibilityProgramBar();
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("UserName", name);
            jsonRequest.put("Email", email);
            jsonRequest.put("Password", password);
            jsonRequest.put("ConfirmPassword", confirmPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = getString(string.server) + "/api/register";
        VolleyService volleyService = new VolleyService(getContext());
        volleyService.loadPost(url, jsonRequest, response -> {
            try {
                String token = response.getString("token");
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
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        if (!password.equals(confirmPassword) || password.isEmpty()) {
            confirmPasswordEditText.setError(getString(string.invalid_password));
            isValid = false;
        }
        if (!isValidEmail(emailEditText.getText().toString())) {
            emailEditText.setError(getString(R.string.invalid_email));
            isValid = false;
        }
        return isValid;
    }

    private void VisibilityProgramBar() {
        ProgressBar progressBar = getActivity().findViewById(R.id.loading);
        if (progressBar != null)
            progressBar.setVisibility(View.VISIBLE);
    }
}
