package com.philm2k.wordmaster.view.word;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.philm2k.wordmaster.R;
import com.philm2k.wordmaster.adapter.UsageAdapter;
import com.philm2k.wordmaster.databinding.FragmentDetailWordBinding;
import com.philm2k.wordmaster.model.entity.Usage;
import com.philm2k.wordmaster.model.entity.Word;
import com.philm2k.wordmaster.view.usage.AddUsageDialogFragment;
import com.philm2k.wordmaster.view.usage.EditUsageDialogFragment;
import com.philm2k.wordmaster.view.usage.UsageViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailWordFragment extends Fragment {

    public static final String TAG = "[Philm2k]" + DetailWordFragment.class.getSimpleName();
    private FragmentDetailWordBinding binding;

    private Word selWord;
    private int wid;

    private UsageViewModel viewModel;
    private UsageAdapter adapter;

    public DetailWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_word, container, false);
        setHasOptionsMenu(true);

        assert getArguments () != null;
        selWord = new Word(getArguments().getString("word"),getArguments().getString("meaning"));
        wid = getArguments().getInt("id");
        binding.setWord(selWord);

        RecyclerView recyclerView = InitRecyclerView();

        viewModel = new ViewModelProvider(this).get(UsageViewModel.class);
        viewModel.findUsagesForWord(wid).observe(getViewLifecycleOwner(), new Observer<List<Usage>>() {
            @Override
            public void onChanged(List<Usage> usages) {
                adapter.setUsages(usages);
            }
        });

        /**
         * 왼쪽이나 오른쪽으로 Swipe 하여 RecyclerView 항목 삭제
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback (0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete ( adapter.getUsageAt ( viewHolder.getAdapterPosition () ) );
                Toast.makeText ( getContext (), R.string.deleted_usage, Toast.LENGTH_SHORT ).show ();
            }
        } ).attachToRecyclerView ( recyclerView );

        /**
         * 손가락이나 펜으로 RecyclerView 항목을 터치했을 때 값을 전달하고, 편집화면을 실행
         */
        adapter.setOnItemClickListener(new UsageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Usage usage) {
                Bundle args = new Bundle();
                args.putInt("id", usage.getId());
                args.putString("engstmt", usage.getEnglish_statement());
                args.putString("kortran", usage.getKorean_translation());
                args.putInt("wid", wid);

                EditUsageDialogFragment dialog = new EditUsageDialogFragment();
                dialog.setArguments(args);
                dialog.show(getChildFragmentManager(), "Hello");

                //Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_word_detail_to_navigation_edit_usage_dialog, args);
            }
        });

        return binding.getRoot();
    }

    private RecyclerView InitRecyclerView() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( binding.getRoot().getContext ());
        RecyclerView recyclerView = binding.rvUsage;
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize ( true );

        adapter = new UsageAdapter();
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.word_detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_add_usage){
            //Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_word_to_navigation_add_word);
            AddUsageDialogFragment dialog = new AddUsageDialogFragment();
            dialog.show(getChildFragmentManager(), "Hello");
        }
        return super.onOptionsItemSelected(item);
    }
}
