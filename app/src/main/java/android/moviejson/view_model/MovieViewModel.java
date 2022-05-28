package android.moviejson.view_model;

import static android.moviejson.Constants.API_PERSONAL_KEY;
import static android.moviejson.Constants.PAGE_NUMBER;
import static android.moviejson.Constants.YEAR_NUMBER;

import android.moviejson.model.MovieDetails;
import android.moviejson.model.MovieGenres;
import android.moviejson.model.api.APIService;
import android.moviejson.model.api.RetrofitInstance;
import android.moviejson.view.GenreAdapter;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Noy davidyan on 25/05/2022.
 */

public class MovieViewModel extends ViewModel {

    private MutableLiveData<List<MovieDetails>> movieList;
    final String TAG = "MovieViewModelTAG";
    private int i = 0;
    List<MovieDetails> movieDetails;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIService apiService;

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public MovieViewModel() {
        this.movieList = new MutableLiveData<>();
        this.movieDetails = new ArrayList<>();
    }

    public MutableLiveData<List<MovieDetails>> getMovieList() {
        return movieList;
    }

    public void fetchMovies() {
        apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);

        apiService.getAllMovies(API_PERSONAL_KEY, 1, YEAR_NUMBER)
                .concatMap(post1 -> {
                    movieDetails.addAll(post1.getResults());
                    return apiService.getAllMovies(API_PERSONAL_KEY, 2, YEAR_NUMBER);
                })
                .concatMap(post2 -> {
                    movieDetails.addAll(post2.getResults());
                    return apiService.getAllMovies(API_PERSONAL_KEY, 3, YEAR_NUMBER);
                })
                .concatMap(post3 -> {
                    movieDetails.addAll(post3.getResults());
                    return apiService.getAllMovies(API_PERSONAL_KEY, 4, YEAR_NUMBER);
                })
                .concatMap(post4 -> {
                    movieDetails.addAll(post4.getResults());
                    return apiService.getAllMovies(API_PERSONAL_KEY, 5, YEAR_NUMBER);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(finalPosts -> {
                    movieDetails.addAll(finalPosts.getResults());
                    setGenresByMovieId();
                });
    }


    private void setGenresByMovieId() {
        if (i == movieDetails.size()) {
            movieList.postValue(movieDetails);
            return; //loop is finished;
        }

        apiService.getMovieGenres(movieDetails.get(i).getId(), API_PERSONAL_KEY, PAGE_NUMBER)
                .enqueue(new Callback<MovieGenres>() {
                    @Override
                    public void onResponse(Call<MovieGenres> call, Response<MovieGenres> response) {
                        if (response.isSuccessful()) {
                            MovieGenres details = response.body();
                            movieDetails.get(i).setGenresList(details.getGenres());
                            ++i;
                            setGenresByMovieId();
                        } else
                            Log.e(TAG, response.message());
                    }

                    @Override
                    public void onFailure(Call<MovieGenres> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
