package com.adamant.boxoffice.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.adamant.boxoffice.BoxOfficeApp;
import com.adamant.boxoffice.Model.Movie;
import com.adamant.boxoffice.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jayesh on 11-03-2016.
 */
public class movieListAdapter extends RecyclerView.Adapter<movieListAdapter.MovieHolder> {
    private List<Movie> movies;
    private movieClickListner mcl;
    public movieListAdapter(List<Movie> movies,movieClickListner mcl){
        this.movies=movies;
        this.mcl=mcl;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_movie_listitem, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Picasso.with(BoxOfficeApp.getAppContext()).load("http://image.tmdb.org/t/p/w500"+movies.get(position).posterPath).into(holder.ivMoviePoster);
      //  holder.tvMovieTitle.setText(movies.get(position).title);
    }

    @Override
    public int getItemCount() {
        if(movies!=null)
            return movies.size();
        else return 0;
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        private int position;
        @Bind(R.id.iv_moviePoster)
        ImageView ivMoviePoster;
      /*  @Bind(R.id.tv_movieTitle)
        TextView tvMovieTitle;*/

        //private dealproductholderclick mlistener;
        //RelativeLayout bottm;
        public MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ivMoviePoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mcl.onMovieClick(v,getAdapterPosition());
                }
            });
        }


        public void bind(int position) {
            this.position = position;
        }


    }

    public interface movieClickListner{
        public void onMovieClick(View v,int position);
    }
}
