package com.example.mymovie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TvFragment extends Fragment {

    View tv;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Tv> tvList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tv = inflater.inflate(R.layout.fragment_tv,container,false);
        recyclerView = tv.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return tv;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = VolleySingleton.getmInstance(getActivity()).getRequestQueue();
        tvList = new ArrayList<>();
        fetchTv();
    }

    private void fetchTv() {

        String url = "https://api.themoviedb.org/3/tv/popular?api_key=84a504e1f5f349bcbcadf3c74b6fe1e2";
        String baseposter = "https://themoviedb.org/t/p/w500";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null ,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String title;
                        if (jsonObject1.has("title")){
                            title = jsonObject1.getString("title");
                        }else {
                            title = jsonObject1.getString("name");
                        }
                        String overview = jsonObject1.getString("overview");
                        String poster = baseposter + jsonObject1.getString("poster_path");
                        String release;
                        if (jsonObject1.has("release_date")){
                            release = jsonObject1.getString("release_date");
                        }else {
                            release = jsonObject1.getString("first_air_date");
                        }


                        Tv tv = new Tv(title , poster , overview , release);
                        tvList.add(tv);
                    }
                    TvAdapter adapter = new TvAdapter(getActivity() , tvList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}