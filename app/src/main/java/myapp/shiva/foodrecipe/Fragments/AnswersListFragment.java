package myapp.shiva.foodrecipe.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import myapp.shiva.foodrecipe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnswersListFragment extends Fragment {


    public AnswersListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_answers_list, container, false);
    }

}
