package com.example.tokenlab_challenge.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tokenlab_challenge.models.Movies;
import com.example.tokenlab_challenge.repositories.MoviesClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {
    MutableLiveData<List<Movies>> mMovies = new MutableLiveData<>();

    public MutableLiveData<List<Movies>> getmMovies() {
        return mMovies;
    }

    public void getMovies() {
        MoviesClient.getINSTANCE().getMovies().enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
                mMovies.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {

            }
        });
    }
}