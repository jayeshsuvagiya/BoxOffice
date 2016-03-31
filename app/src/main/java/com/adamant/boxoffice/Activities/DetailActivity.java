package com.adamant.boxoffice.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.adamant.boxoffice.Fragments.MovieDetailFragment;
import com.adamant.boxoffice.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Bind(R.id.app_bar1)
    Toolbar appBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setSupportActionBar(appBar1);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.container_fragmentdetailmovie) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            MovieDetailFragment fragmentmovie = MovieDetailFragment.newInstance();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            fragmentmovie.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_fragmentdetailmovie, fragmentmovie).commit();
        }
    }
}
