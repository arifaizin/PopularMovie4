package id.co.imastudio.popularmovie4;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by idn on 2/3/2018.
 */

public class MovieModel {
    @SerializedName("poster_path")
    @Expose
    String posterMovie;

    @SerializedName("title")
    @Expose
    String judulMovie;

    // 1 constructor
    // 2 setter and getter

    //klik kanan > generate

    public MovieModel(String posterMovie, String judulMovie) {
        this.posterMovie = posterMovie;
        this.judulMovie = judulMovie;
    }

    public MovieModel() {

    }

    public String getPosterMovie() {
        return posterMovie;
    }

    public void setPosterMovie(String posterMovie) {
        this.posterMovie = posterMovie;
    }

    public String getJudulMovie() {
        return judulMovie;
    }

    public void setJudulMovie(String judulMovie) {
        this.judulMovie = judulMovie;
    }
}
