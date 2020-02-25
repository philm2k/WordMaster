package com.philm2k.wordmaster.view.word;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.philm2k.wordmaster.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditWordFragment extends Fragment {

    public EditWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_word, container, false);
    }
}
