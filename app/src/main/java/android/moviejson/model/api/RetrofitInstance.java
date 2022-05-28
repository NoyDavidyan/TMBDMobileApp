package android.moviejson.model.api;

import static android.moviejson.Constants.API_BASE_URL;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Noy davidyan on 25/05/2022.
 */

public class RetrofitInstance {

    private static Retrofit retrofit;

    public static Retrofit getRetrofitClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //creating observables, Adding this class to Retrofit allows you to return an Observable
                    .build();
        }

        return retrofit;
    }
}