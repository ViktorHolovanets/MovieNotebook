package com.study.movienotebook.data.services.http;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.study.movienotebook.R;
import com.study.movienotebook.data.repositories.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class VolleyService {
    private Context context;
    private RequestQueue volleyQueue;

    public VolleyService(Context context) {
        this.context = context;
        volleyQueue = Volley.newRequestQueue(context);
    }

    public void load(String url, int method, JSONObject jsonRequest, final VolleyResponseListener listener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                method,
                url,
                jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Singleton.getInstance().getToken());
                return headers;
            }
        };

        int initialTimeoutMs = 20000;
        int maxNumRetries = 0;
        float backoffMultiplier = 1.0f;
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(initialTimeoutMs, maxNumRetries, backoffMultiplier));
        volleyQueue.add(jsonObjectRequest);
    }

    public void loadGet(String url, JSONObject jsonRequest, Consumer<JSONObject> onSuccess) {
        load(url, Request.Method.GET, jsonRequest, getVolleyResponseListener(onSuccess));
    }

    public void loadPost(String url, JSONObject jsonRequest, Consumer<JSONObject> onSuccess) {
        load(url, Request.Method.POST, jsonRequest, getVolleyResponseListener(onSuccess));
    }

    public interface VolleyResponseListener {
        void onError(VolleyError error);

        void onResponse(JSONObject response);
    }

    private VolleyResponseListener getVolleyResponseListener(Consumer<JSONObject> onSuccess) {
        return new VolleyResponseListener() {
            @Override
            public void onError(VolleyError error) {
                String errorMessage = "";
                if (error instanceof NetworkError) {
                    errorMessage = "Network Error";
                } else if (error instanceof ServerError) {
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null && networkResponse.data != null) {
                        String errorResponse = new String(networkResponse.data);
                        try {
                            JSONObject responseJson = new JSONObject(errorResponse);
                            errorMessage = responseJson.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        errorMessage = "Server Error";
                    }
                } else if (error instanceof AuthFailureError) {
                    errorMessage = "Authentication Failure Error";
                } else if (error instanceof ParseError) {
                    errorMessage = "Parse Error";
                } else if (error instanceof TimeoutError) {
                    errorMessage = "Timeout Error";
                } else if (error instanceof NoConnectionError) {
                    errorMessage = "No Connection Error";
                }
                Toast.makeText(context, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                ProgressBar progressBar = ((Activity) context).findViewById(R.id.loading);
                if (progressBar != null) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onResponse(JSONObject response) {
                onSuccess.accept(response);
            }
        };
    }


    public void loadArray(String url, int method, JSONArray jsonRequest, final VolleyResponseArrayListener listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                method,
                url,
                jsonRequest,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Singleton.getInstance().getToken());
                return headers;
            }
        };
        volleyQueue.add(jsonArrayRequest);
    }

    public void loadArrayGet(String url, JSONArray jsonRequest, Consumer<JSONArray> onSuccess) {
        loadArray(url, Request.Method.GET, jsonRequest, getVolleyResponseArrayListener(onSuccess));
    }

    public void loadArrayPost(String url, JSONArray jsonRequest, Consumer<JSONArray> onSuccess) {
        loadArray(url, Request.Method.POST, jsonRequest, getVolleyResponseArrayListener(onSuccess));
    }

    public interface VolleyResponseArrayListener {
        void onError(VolleyError error);

        void onResponse(JSONArray response);
    }

    private VolleyResponseArrayListener getVolleyResponseArrayListener(Consumer<JSONArray> onSuccess) {
        return new VolleyResponseArrayListener() {
            @Override
            public void onError(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT);
            }

            @Override
            public void onResponse(JSONArray response) {
                onSuccess.accept(response);
            }
        };
    }

}
