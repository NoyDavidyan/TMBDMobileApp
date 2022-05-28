package android.moviejson.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Noy davidyan on 25/05/2022.
 */

public class MovieDetails {
    @SerializedName("id")
    private Integer id;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("title")
    private String title;

    private List<Genre> genresList;

    public List<Genre> getGenresList() {
        return genresList;
    }

    public void setGenresList(List<Genre> genresList) {
        this.genresList = genresList;
    }

    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
