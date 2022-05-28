package android.moviejson.view;

import static android.moviejson.Constants.API_PERSONAL_KEY;
import static android.moviejson.Constants.PAGE_NUMBER;

import android.moviejson.R;
import android.moviejson.model.MovieDetails;
import android.moviejson.model.MovieGenres;
import android.moviejson.model.api.APIService;
import android.moviejson.model.api.RetrofitInstance;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Noy davidyan on 25/05/2022.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    List<MovieDetails> movieList;
    final String TAG = "MovieAdapterTAG";
    APIService apiService;

    public MovieAdapter(List<MovieDetails> movieList) {
        this.movieList = movieList;
        apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        MovieDetails movie = movieList.get(position);
        holder.nameTv.setText(movie.getTitle());
        holder.dateTv.setText(movie.getReleaseDate());

        GenreAdapter adapter = new GenreAdapter(movie.getGenresList());
        holder.genresRv.setLayoutManager(new LinearLayoutManager(holder.nameTv.getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.genresRv.setAdapter(adapter);

/*        apiService.getMovieGenres(movie.getId(), API_PERSONAL_KEY, PAGE_NUMBER)
                .enqueue(new Callback<MovieGenres>() {
                    @Override
                    public void onResponse(Call<MovieGenres> call, Response<MovieGenres> response) {
                        if (response.isSuccessful()) {
                            MovieGenres details = response.body();

                            GenreAdapter adapter = new GenreAdapter(details.getGenres());
                            holder.genresRv.setLayoutManager(new LinearLayoutManager(holder.nameTv.getContext(), LinearLayoutManager.HORIZONTAL, false));
                            holder.genresRv.setAdapter(adapter);
                        } else
                            Log.e(TAG, response.message());
                    }

                    @Override
                    public void onFailure(Call<MovieGenres> call, Throwable t) {
                        t.printStackTrace();
                    }
                });*/
    }

    @Override
    public int getItemCount() {
        if (movieList != null) return movieList.size();
        else return 0;
    }

    public void update(List<MovieDetails> movieList) {
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

        private TextView nameTv;
        private TextView dateTv;
        private RecyclerView genresRv;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.name_tv);
            dateTv = itemView.findViewById(R.id.date_tv);
            genresRv = itemView.findViewById(R.id.genres_rv);
        }
    }
}
