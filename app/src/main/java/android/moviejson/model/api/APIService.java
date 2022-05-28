package android.moviejson.model.api;

import static android.moviejson.Constants.API_KEY;
import static android.moviejson.Constants.DISCOVER;
import static android.moviejson.Constants.MOVIE_DETAILS;
import static android.moviejson.Constants.MOVIE_ID_KEY;
import static android.moviejson.Constants.PAGE_KEY;
import static android.moviejson.Constants.PRIMARY_RELEASE_YEAR_KEY;

import android.moviejson.model.MovieGenres;
import android.moviejson.model.MovieResults;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Noy davidyan on 25/05/2022.
 */

public interface APIService {

    @GET(DISCOVER)
    Observable<MovieResults> getAllMovies(@Query(API_KEY) String api_key,
                                                @Query(PAGE_KEY) int page,
                                                @Query(PRIMARY_RELEASE_YEAR_KEY) int year);

    @GET(MOVIE_DETAILS)
    Call<MovieGenres> getMovieGenres(@Path(MOVIE_ID_KEY) int movie_id,
                                     @Query(API_KEY) String api_key,
                                     @Query(PAGE_KEY) int page);
}
