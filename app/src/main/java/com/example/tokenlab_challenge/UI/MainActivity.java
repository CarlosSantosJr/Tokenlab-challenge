package com.example.tokenlab_challenge.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.tokenlab_challenge.R;
import com.example.tokenlab_challenge.adapters.MoviesAdapter;
import com.example.tokenlab_challenge.models.Movies;
import com.example.tokenlab_challenge.viewmodels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.OnMovieListener {
    private static final String TAG = "MainActivity";

    private MainActivityViewModel mMainActivityViewModel;
    private MoviesAdapter mAdapter;
    private RecyclerView recyclerView;
    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressbar = findViewById(R.id.progress_bar);
        showProgressBar();

        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.getMovies();

        recyclerView = findViewById(R.id.recycler_movies);
        mAdapter = new MoviesAdapter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        mMainActivityViewModel.getmMovies().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                mAdapter.setList(movies);
                hideProgressBar();
            }
        });
    }

    private void showProgressBar() {
        mProgressbar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void onMovieClick(int position) {
        Log.d(TAG, "onMovieClick: ");

        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra("movie_position", position);
        startActivity(intent);
    }
}