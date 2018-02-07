package id.co.imastudio.popularmovie4.connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by idn on 2/3/2018.
 */

public class RetrofitConfig {
    private static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static ApiService getApiService(){
        return getRetrofit().create(ApiService.class);
    }
}
