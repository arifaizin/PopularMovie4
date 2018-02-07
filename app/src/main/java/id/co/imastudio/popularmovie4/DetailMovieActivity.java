package id.co.imastudio.popularmovie4;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.co.imastudio.popularmovie4.database.MovieContract;
import id.co.imastudio.popularmovie4.model.ResultsItem;

public class DetailMovieActivity extends AppCompatActivity {

    List<ResultsItem> listMovie = new ArrayList<>();
    int posisi;
    private ImageView imageDetailPoster;
    private TextView textDetailSinopsis;

    Boolean statusFavorit = false;
    private FloatingActionButton fab;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //terima data
        listMovie = getIntent().getParcelableArrayListExtra("DATA_MOVIE");
        posisi = getIntent().getIntExtra("POSISI", 0);
        //harus sama persis

        //baca pref
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        statusFavorit = pref.getBoolean("STATUSFAVORIT"+listMovie.get(posisi).getId(), false);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        updateStatus(statusFavorit);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //variabel boolean statusFavorit default false
                // kalau false jadi true dan hati penuh
                // kalau true jadi false dan hati kosong

                //ganti status
                if(statusFavorit == false){
                    statusFavorit = true;
                    //insert
                    insertDatabase();

                } else if(statusFavorit == true){
                    statusFavorit = false;
                    //delete
                    hapusDatabase();

                }
                //ganti icon
                updateStatus(statusFavorit);

                //tulis data
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("STATUSFAVORIT"+listMovie.get(posisi).getId(), statusFavorit);
                editor.commit();
            }
        });

        getSupportActionBar().setTitle(listMovie.get(posisi).getTitle());

        imageDetailPoster = findViewById(R.id.image_detail_poster);
        textDetailSinopsis = findViewById(R.id.text_detail_sinopsis);

        textDetailSinopsis.setText(listMovie.get(posisi).getOverview());
        Picasso.with(DetailMovieActivity.this).load("https://image.tmdb.org/t/p/w500/"+listMovie.get(posisi).getPosterPath()).into(imageDetailPoster);
    }

    private void hapusDatabase() {
        int id = getContentResolver().delete(
                MovieContract.MovieEntry.CONTENT_URI.buildUpon().appendPath(String.valueOf(listMovie.get(posisi).getId())).build(),
                null,
                null
        );

        if (id > 0) {
            Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
        }
    }

    private void insertDatabase() {
        ContentValues values = new ContentValues();
        values.put(MovieContract.MovieEntry.COLUMN_ID, listMovie.get(posisi).getId());
        values.put(MovieContract.MovieEntry.COLUMN_JUDUL, listMovie.get(posisi).getTitle());
        values.put(MovieContract.MovieEntry.COLUMN_POSTER, listMovie.get(posisi).getPosterPath());
        values.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, listMovie.get(posisi).getOverview());

        Uri uri = getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, values);

        if (ContentUris.parseId(uri) > 0) {
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateStatus(Boolean status) {
        if(status == false){
            fab.setImageResource(R.drawable.ic_not_favorite);
        } else if(status == true){
            fab.setImageResource(R.drawable.ic_favorite);
        }
    }


}
