package com.philm2k.wordmaster.view.word;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.philm2k.wordmaster.R;
import com.philm2k.wordmaster.databinding.FragmentAddWordDialogBinding;

public class AddWordDialogFragment extends DialogFragment {


    private WordViewModel viewModel;

    private FragmentAddWordDialogBinding binding;


    public AddWordDialogFragment() {
        // Required empty public constructor
    }

    public static AddWordDialogFragment newInstance() {
        AddWordDialogFragment fragment = new AddWordDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(WordViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_word_dialog, container, false);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }
}
