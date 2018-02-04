package id.co.imastudio.popularmovie4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {


    public PopularFragment() {
        // Required empty public constructor
    }

    RecyclerView recycler;
    List<MovieModel> listMovie = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        recycler = view.findViewById(R.id.recycler_view);
        // 0 layout item
        // 1 Dataset

        //fori
//        for (int i = 0; i < 20; i++) {
//            //pake constructor
//            MovieModel movie1 = new MovieModel("https://id.bmscdn.com/events/moviecard/ET00005344.jpg", "Dilan");
//            listMovie.add(movie1);
//
//            //pake setter
//            MovieModel movie2 = new MovieModel();
//            movie2.setJudulMovie("Dilan 2");
//            movie2.setPosterMovie("https://id.bmscdn.com/events/moviecard/ET00005344.jpg");
//            listMovie.add(movie2);
//        }

        ambilDataOnline();

        // 2 Adapter
        recycler.setAdapter(new MovieAdapter(getActivity(), listMovie));

        // 3 Layout Manager
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        return view;
    }

    private void ambilDataOnline() {
        Call<ResponseModel> requestData = RetrofitConfig.getApiService().ambilDataPopularMovie();
        requestData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()){
                    listMovie = response.body().getResults();
                    recycler.setAdapter(new MovieAdapter(getActivity(), listMovie));

                } else {
                    Toast.makeText(getActivity(), "Response is not successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Response Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
