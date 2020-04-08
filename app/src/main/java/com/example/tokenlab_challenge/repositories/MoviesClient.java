package com.example.tokenlab_challenge.repositories;

import com.example.tokenlab_challenge.models.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesClient {
    private static final String BASE_URL = "https://desafio-mobile.nyc3.digitaloceanspaces.com/";
    private  MoviesInterface moviesInterface;
    private static MoviesClient INSTANCE;

    public MoviesClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        moviesInterface = retrofit.create(MoviesInterface.class);
    }

    public static MoviesClient getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MoviesClient();
        }
        return INSTANCE;
    }

    public Call<List<Movies>> getMovies() {
        return moviesInterface.getMovies();
    }
}