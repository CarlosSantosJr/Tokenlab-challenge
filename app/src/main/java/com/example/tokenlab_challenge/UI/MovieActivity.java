package com.example.tokenlab_challenge.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.tokenlab_challenge.R;
import com.example.tokenlab_challenge.models.Movies;
import com.example.tokenlab_challenge.viewmodels.MainActivityViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MovieActivity extends AppCompatActivity {
    private static final String TAG = "MovieActivity";

    private MainActivityViewModel mainActivityViewModel;
    private Movies movie;
    private int position;

    private ProgressBar progressBar;
    private ImageView imageView;
    private TextView txt_title;
    private TextView txt_nvote;
    private TextView txt_vote;
    private TextView txt_ndate;
    private TextView txt_date;
    private TextView txt_ngenres;
    private TextView txt_genres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        progressBar = findViewById(R.id.movie_progressBar);
        imageView = findViewById(R.id.imageView);
        txt_title = findViewById(R.id.txt_title);
        txt_nvote = findViewById(R.id.txt_nvote);
        txt_vote = findViewById(R.id.txt_vote);
        txt_ndate = findViewById(R.id.txt_ndate);
        txt_date = findViewById(R.id.txt_date);
        txt_ngenres = findViewById(R.id.txt_ngenres);
        txt_genres = findViewById(R.id.txt_genres);
        showProgressBar();

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getMovies();

        Intent intent = getIntent();
        position = intent.getIntExtra("movie_position", -1);

        if(position < 0) {
            Intent back_intent = new Intent(this, MainActivity.class);
            startActivity(back_intent);
        }

        mainActivityViewModel.getmMovies().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                if(movies.size() > position) {
                    movie = movies.get(position);

                    Glide.with(getBaseContext())
                            .load(movie.getPoster_url())
                            .into(imageView);

                    txt_title.setText(movie.getTitle());
                    txt_vote.setText(String.valueOf(movie.getVote_average()));

                    Date release;
                    DateFormat dateFormat;
                    try {
                        release = new SimpleDateFormat("yyyy/MM/dd").parse(movie.getRelease_date());
                        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        txt_date.setText(dateFormat.format(release));
                    } catch (ParseException e) {
                        release = null;
                        try {
                            release = new SimpleDateFormat("yyyy-MM-dd").parse(movie.getRelease_date());
                            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            txt_date.setText(dateFormat.format(release));
                        } catch (ParseException ex) {
                            txt_date.setText(movie.getRelease_date());
                        }
                    }

                    String genres = movie.getGenres().toString();
                    genres = genres.substring(1, genres.length() - 1);
                    txt_genres.setText(genres);

                    hideProgressBar();
                }
            }
        });
    }

    private void showProgressBar() {
        imageView.setVisibility(View.GONE);
        txt_title.setVisibility(View.GONE);
        txt_nvote.setVisibility(View.GONE);
        txt_vote.setVisibility(View.GONE);
        txt_ndate.setVisibility(View.GONE);
        txt_date.setVisibility(View.GONE);
        txt_ngenres.setVisibility(View.GONE);
        txt_genres.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        txt_title.setVisibility(View.VISIBLE);
        txt_nvote.setVisibility(View.VISIBLE);
        txt_vote.setVisibility(View.VISIBLE);
        txt_ndate.setVisibility(View.VISIBLE);
        txt_date.setVisibility(View.VISIBLE);
        txt_ngenres.setVisibility(View.VISIBLE);
        txt_genres.setVisibility(View.VISIBLE);
    }
}