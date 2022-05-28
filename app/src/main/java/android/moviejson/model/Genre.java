package android.moviejson.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Noy davidyan on 25/05/2022.
 */

public class Genre {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}