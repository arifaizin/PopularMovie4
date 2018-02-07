package id.co.imastudio.popularmovie4.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by idn on 2/4/2018.
 */

public class MovieContract {

    //TODO 2 : Definisiin Uri di Contract
    public static final String AUTHORITY = "id.co.imastudio.popularmovie4";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_TASKS = "listfilm";

    public static final class MovieEntry implements BaseColumns {

        //Untuk Uri
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();

        //untuk sqlite
        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_JUDUL = "title";
        public static final String COLUMN_POSTER = "poster_path";
        public static final String COLUMN_OVERVIEW = "overview";

    }
}
