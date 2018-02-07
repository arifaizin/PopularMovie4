package id.co.imastudio.popularmovie4.connection;

import id.co.imastudio.popularmovie4.model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by idn on 2/3/2018.
 */

public interface ApiService {
    @GET("3/movie/popular?api_key=b08e3495841838f530552c2b261e00b1&language=en-US&page=1")
    Call<ResponseModel> ambilDataPopularMovie();

//    @GET("3/movie/popular?api_key=b08e3495841838f530552c2b261e00b1&language=en-US&page=1")
//    Call<ResponseModel> ambilDataTopRated();

}
