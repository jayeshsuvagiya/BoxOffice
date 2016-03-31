package com.adamant.boxoffice.Network;

import android.util.Log;

import com.adamant.boxoffice.BoxOfficeApp;
import com.adamant.boxoffice.Model.Movie;
import com.adamant.boxoffice.R;
import com.adamant.boxoffice.interfaces.IMovie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jayesh on 14-03-2016.
 */
public class MovieApiClient {
    private static final String BASE_URL="https://api.themoviedb.org/3/";
    private static final String API_KEY= BoxOfficeApp.getAppContext().getString(R.string.Api_key);

    private static MovieApiClient movieApiClient;
    private static Retrofit mretrofit;
    private static IMovie movieApiService;

    public static MovieApiClient getClient(){
        if(movieApiClient==null)
            movieApiClient=new MovieApiClient();

        return movieApiClient;
    }

    public static IMovie getMovieApiService(){
        if(movieApiService==null)
            movieApiService=mretrofit.create(IMovie.class);

        Log.d("mytag","called");
        return movieApiService;
    }

    private MovieApiClient(){
        Type listType = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        Gson gson = new GsonBuilder()
                        .registerTypeAdapter(listType,new movieDeserializer())
                        .create();

        OkHttpClient client = new OkHttpClient.Builder()
                                    .addInterceptor(new Interceptor() {
                                        @Override
                                        public Response intercept(Chain chain) throws IOException {
                                            Request request = chain.request();
                                            HttpUrl url = request.url().newBuilder().addQueryParameter("api_key", API_KEY).build();
                                            request = request.newBuilder().url(url).build();
                                            return chain.proceed(request);
                                        }
                                    })
                                    .build();

        mretrofit=new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

        movieApiService=mretrofit.create(IMovie.class);
       // service = retrofit.create(APIService.class);
    }

}
