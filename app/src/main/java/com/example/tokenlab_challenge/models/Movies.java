package com.example.tokenlab_challenge.models;

import java.util.List;

public class Movies {
    private int id;
    private double vote_average;
    private String title;
    private String poster_url;
    private List<String> genres;
    private String release_date;

    public Movies(int id, double vote_average, String title, String poster_url, List<String> genres, String release_date) {
        this.id = id;
        this.vote_average = vote_average;
        this.title = title;
        this.poster_url = poster_url;
        this.genres = genres;
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}