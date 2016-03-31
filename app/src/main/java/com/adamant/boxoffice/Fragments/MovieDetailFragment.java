package com.adamant.boxoffice.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adamant.boxoffice.BoxOfficeApp;
import com.adamant.boxoffice.Model.Movie;
import com.adamant.boxoffice.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {

    private Movie movie;

    @Bind(R.id.backdrop)
    ImageView backdrop;
    @Bind(R.id.movietitle)
    TextView movietitle;
    @Bind(R.id.movierating)
    TextView movierating;
    @Bind(R.id.moviedate)
    TextView moviedate;
    @Bind(R.id.movieoverview)
    TextView movieoverview;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public static MovieDetailFragment newInstance() {
        return new MovieDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("mytag",getArguments().getParcelable("movie").toString());
        movie=getArguments().getParcelable("movie");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Picasso.with(BoxOfficeApp.getAppContext()).load("http://image.tmdb.org/t/p/w342"+movie.backdropPath).into(backdrop);
        movietitle.setText(movie.title);
        moviedate.setText(movie.releaseDate);
        movierating.setText(""+(int) movie.voteAverage);
        movieoverview.setText(movie.overview);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
