package id.co.imastudio.popularmovie4.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.co.imastudio.popularmovie4.MovieAdapter;
import id.co.imastudio.popularmovie4.R;
import id.co.imastudio.popularmovie4.database.MovieContract;
import id.co.imastudio.popularmovie4.model.ResultsItem;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    public FavoriteFragment() {
        // Required empty public constructor
    }

    RecyclerView recycler;
    List<ResultsItem> listMovie = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ambilDataFavorit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recycler = view.findViewById(R.id.recycler_view);
        // 0 layout item
        // 1 Dataset

        //fori
//        for (int i = 0; i < 20; i++) {
//            //pake constructor
//            ResultsItem movie1 = new ResultsItem("https://id.bmscdn.com/events/moviecard/ET00005344.jpg", "Dilan");
//            listMovie.add(movie1);
//
//            //pake setter
//            ResultsItem movie2 = new ResultsItem();
//            movie2.setJudulMovie("Dilan 2");
//            movie2.setPosterMovie("https://id.bmscdn.com/events/moviecard/ET00005344.jpg");
//            listMovie.add(movie2);
//        }

        ambilDataFavorit();


        // 2 Adapter
        recycler.setAdapter(new MovieAdapter(getActivity(), listMovie));

        // 3 Layout Manager
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        return view;
    }

    private void ambilDataFavorit() {
        getActivity().getSupportLoaderManager().initLoader(100, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case 100:
                return new CursorLoader(
                        getActivity(),
                        MovieContract.MovieEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
            default:
                throw new RuntimeException("Loader not Working");
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        listMovie.clear();
        if (data.getCount() > 0){
            if (data.moveToFirst()){
                do {
                    ResultsItem movie = new ResultsItem();
                    movie.setId(data.getInt(data.getColumnIndex(MovieContract.MovieEntry.COLUMN_ID)));
                    movie.setTitle(data.getString(data.getColumnIndex(MovieContract.MovieEntry.COLUMN_JUDUL)));
                    movie.setPosterPath(data.getString(data.getColumnIndex(MovieContract.MovieEntry.COLUMN_POSTER)));
                    movie.setOverview(data.getString(data.getColumnIndex(MovieContract.MovieEntry.COLUMN_OVERVIEW)));

                    listMovie.add(movie);
                    recycler.setAdapter(new MovieAdapter(getActivity(), listMovie));
                } while (data.moveToNext());
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    //onRes
    @Override
    public void onResume() {
        super.onResume();
        ambilDataFavorit();
        recycler.setAdapter(new MovieAdapter(getActivity(), listMovie));
    }

    @Override
    public void onStart() {
        super.onStart();
        ambilDataFavorit();
    }
}
