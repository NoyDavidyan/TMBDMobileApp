package android.moviejson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.moviejson.model.MovieDetails;
import android.moviejson.view.MovieAdapter;
import android.moviejson.view_model.MovieViewModel;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mMovieRv;
    private MovieAdapter mAdapter;
    private MovieViewModel mViewModel;
    private List<MovieDetails> mMovies = new ArrayList<>();
    private TextView mNoResultTv;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoResultTv = findViewById(R.id.no_result_tv);
        mMovieRv = findViewById(R.id.movies_rv);
        mProgressBar = findViewById(R.id.progress_bar);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_divider));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMovieRv.setLayoutManager(linearLayoutManager);
        mMovieRv.addItemDecoration(itemDecorator);

        mAdapter = new MovieAdapter(mMovies);
        mMovieRv.setAdapter(mAdapter);

        mViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mViewModel.getMovieList().observe(this, new Observer<List<MovieDetails>>() {
            @Override
            public void onChanged(List<MovieDetails> movies) {

                mProgressBar.setVisibility(View.GONE);

                if (movies != null) {
                    mAdapter.update(movies);
                    mNoResultTv.setVisibility(View.GONE);
                } else
                    mNoResultTv.setVisibility(View.VISIBLE);
            }
        });

        mViewModel.fetchMovies();
    }
}