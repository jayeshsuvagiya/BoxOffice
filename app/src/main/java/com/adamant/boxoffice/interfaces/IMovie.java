package com.adamant.boxoffice.interfaces;

import com.adamant.boxoffice.Model.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jayesh on 20-03-2016.
 */
public interface IMovie {
    @GET("movie/popular")
    Call<ArrayList<Movie>> getPopularMovies();

    @GET("movie/top_rated")
    Call<ArrayList<Movie>> getTopRatedMovies();
}
