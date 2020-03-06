package com.philm2k.wordmaster.view.usage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.philm2k.wordmaster.R;
import com.philm2k.wordmaster.databinding.FragmentAddUsageDialogBinding;
import com.philm2k.wordmaster.model.entity.Usage;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddUsageDialogFragment extends DialogFragment {

    public static final String TAG = "[Philm2k]" + AddUsageDialogFragment.class.getSimpleName();

    private UsageViewModel viewModel;
    private int wid;
    private FragmentAddUsageDialogBinding binding;


    public AddUsageDialogFragment() {
        // Required empty public constructor
    }

    public static AddUsageDialogFragment newInstance(){
        AddUsageDialogFragment fragment = new AddUsageDialogFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_usage_dialog, container, false);

        viewModel = new ViewModelProvider(this).get(UsageViewModel.class);

        binding.btnAddUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.editEngstmt.getText().equals("") || binding.editKorTran.getText().equals("")){
                    Toast.makeText(getContext(), R.string.empty_usage, Toast.LENGTH_LONG).show();
                    return;
                }
                Usage newUsage = new Usage(binding.editEngstmt.getText().toString(), binding.editKorTran.getText().toString(), wid);
                viewModel.update(newUsage);
                Snackbar.make(binding.getRoot(), newUsage.toString(), Snackbar.LENGTH_LONG);
                //Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_add_usage_to_navigation_word_detail);
                dismiss();
            }
        });

        return binding.getRoot();
    }

    private void ArgsSetToScreen() {
        assert getArguments () != null;
        wid = getArguments().getInt("wid");
    }
}
