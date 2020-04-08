package com.example.tokenlab_challenge.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tokenlab_challenge.R;
import com.example.tokenlab_challenge.models.Movies;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{
    private static final String TAG = "MoviesAdapter";

    private List<Movies> mMovies;
    private Context mContext;
    private OnMovieListener onMovieListener;

    public MoviesAdapter(Context mContext, OnMovieListener onMovieListener) {
        this.mMovies = new ArrayList<>();
        this.mContext = mContext;
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_movies_item, parent, false);
        MoviesViewHolder holder = new MoviesViewHolder(view, onMovieListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Movies movie = mMovies.get(position);

        Glide.with(mContext)
                .load(movie.getPoster_url())
                .into(holder.movieImage);
        holder.movieName.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView movieImage;
        TextView movieName;
        RelativeLayout itemLayout;
        OnMovieListener onMovieListener;

        public MoviesViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.img_movie);
            movieName = itemView.findViewById(R.id.txt_movie);
            itemLayout = itemView.findViewById(R.id.movie_item_layout);
            this.onMovieListener = onMovieListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMovieListener.onMovieClick(getAdapterPosition());
        }
    }

    public interface OnMovieListener {
        void onMovieClick(int position);
    }

    public void setList(List<Movies> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }


}