package id.co.imastudio.popularmovie4;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by idn on 2/3/2018.
 */

public class ResponseModel {
    @SerializedName("results")
    @Expose
    List<MovieModel> results = new ArrayList<>();

    public List<MovieModel> getResults() {
        return results;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }
}
