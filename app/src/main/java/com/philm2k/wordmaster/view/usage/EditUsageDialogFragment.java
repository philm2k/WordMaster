package com.philm2k.wordmaster.view.usage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.philm2k.wordmaster.R;
import com.philm2k.wordmaster.adapter.UsageAdapter;
import com.philm2k.wordmaster.databinding.FragmentEditUsageDialogBinding;
import com.philm2k.wordmaster.model.entity.Usage;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditUsageDialogFragment extends DialogFragment {

    public static final String TAG = "[Philm2k]" + EditUsageDialogFragment.class.getSimpleName();

    private UsageViewModel usageViewModel;
    private UsageAdapter adapter;
    private int id, wid;

    private FragmentEditUsageDialogBinding binding;

    public EditUsageDialogFragment() {
        // Required empty public constructor
    }

    public static EditUsageDialogFragment newInstance(){
        EditUsageDialogFragment fragment = new EditUsageDialogFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_edit_usage_dialog, container, false);

        usageViewModel = new ViewModelProvider(this).get(UsageViewModel.class);

        //전달받은 Args 화면에 Display
        ArgsSetToScreen();

        binding.btnEditUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usage current = new Usage(binding.editEngstmt.getText().toString(),
                        binding.editKorTran.getText().toString(),wid);
                current.setId(id);
                usageViewModel.update(current);
                //Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_edit_usage_to_navigation_word_detail);
                dismiss();
            }
        });

        return binding.getRoot();
    }

    private void ArgsSetToScreen() {
        assert getArguments () != null;
        id = getArguments().getInt("id");
        wid = getArguments().getInt("wid");
        binding.setUsage(new Usage(getArguments().getString("engstmt"),getArguments().getString("kortran"), wid));
    }
}
