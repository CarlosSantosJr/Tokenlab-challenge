package com.example.tokenlab_challenge.repositories;

import com.example.tokenlab_challenge.models.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesInterface {
    @GET("movies")
    public Call<List<Movies>> getMovies();
}