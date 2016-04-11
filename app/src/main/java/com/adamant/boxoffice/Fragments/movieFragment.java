package com.adamant.boxoffice.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.adamant.boxoffice.Activities.DetailActivity;
import com.adamant.boxoffice.Adapters.movieListAdapter;
import com.adamant.boxoffice.Model.Movie;
import com.adamant.boxoffice.Network.MovieApiClient;
import com.adamant.boxoffice.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class movieFragment extends Fragment {

    @Bind(R.id.rc_movieList) RecyclerView rcMovieList;
    private List<Movie> popMovies;
    private List<Movie> topMovies;
    private movieListAdapter movieAdapter;
    private boolean ispopMovies=true;

    public movieFragment() {
        // Required empty public constructor
    }

    public static movieFragment newInstance() {
        return new movieFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
   //     popMovies=new ArrayList<>();
        movieAdapter = new movieListAdapter(popMovies, new movieListAdapter.movieClickListner() {
            @Override
            public void onMovieClick(View v, int position) {
                Intent i=new Intent(getContext(), DetailActivity.class);
                if(ispopMovies)
                i.putExtra("movie",popMovies.get(position));
                else i.putExtra("movie",topMovies.get(position));
                startActivity(i);
            }
        });
        rcMovieList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rcMovieList.setAdapter(movieAdapter);
        displayPopMovies();
    }

    private void displayPopMovies() {
        if(null==popMovies){
            MovieApiClient.getClient().getMovieApiService().getPopularMovies().enqueue(new Callback<ArrayList<Movie>>() {
                @Override
                public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                    Log.d("Mytag", "movie api response :- " + response.message());
                    popMovies = response.body();
                    Log.d("Mytag", "movie api response data :- "+popMovies.toString());
                    movieAdapter.setMovies(popMovies);
                    rcMovieList.setAdapter(movieAdapter);
                    ispopMovies=true;
                }

                @Override
                public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                    Log.d("Mytag", "movie api call failed"+t.getLocalizedMessage());
                    t.printStackTrace();
                }
            });
        }else {
            if(!ispopMovies){
                movieAdapter.setMovies(popMovies);
                ispopMovies=true;
            }

        }
    }

    private void displayTopMovies(){
        if(null==topMovies){
            MovieApiClient.getClient().getMovieApiService().getTopRatedMovies().enqueue(new Callback<ArrayList<Movie>>() {
                @Override
                public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                    Log.d("Mytag", "movie api response :- " + response.message());
                    topMovies = response.body();
                    movieAdapter.setMovies(topMovies);
                    ispopMovies=false;
                }

                @Override
                public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                    Log.d("Mytag", "movie api call failed");
                }
            });
        }else {
            if(ispopMovies) {
                movieAdapter.setMovies(topMovies);
                ispopMovies=false;
            }

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        hasOptionsMenu();
        inflater.inflate(R.menu.main_activity, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.popular:{
                displayPopMovies();
                break;
            }
            case R.id.toprated:{
                displayTopMovies();
                break;
            }
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
