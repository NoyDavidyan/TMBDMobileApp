package android.moviejson.model;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MovieResults {
    @SerializedName("results")
    private List<MovieDetails> results = null;

    public List<MovieDetails> getResults() {
        return results;
    }
}
