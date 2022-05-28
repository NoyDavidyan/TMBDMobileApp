package android.moviejson.model;

import java.util.List;

/**
 * Created by Noy davidyan on 25/05/2022.
 */

import com.google.gson.annotations.SerializedName;

public class MovieGenres {

    @SerializedName("genres")
    private List<Genre> genres = null;

    public List<Genre> getGenres() {
        return genres;
    }
}