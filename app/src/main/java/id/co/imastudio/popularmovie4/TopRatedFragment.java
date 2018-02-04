package id.co.imastudio.popularmovie4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedFragment extends Fragment {


    public TopRatedFragment() {
        // Required empty public constructor
    }


    Button btnFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_top_rated, container, false);
        btnFragment = fragmentView.findViewById(R.id.btnFragment);
        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Context kalau di fragment pakai getActivity", Toast.LENGTH_SHORT).show();
            }
        });
        return fragmentView;
    }

    //1 Harus buat variabel viewnya dulu
    //2 Context kalau di fragment pakai getActivity()


}
