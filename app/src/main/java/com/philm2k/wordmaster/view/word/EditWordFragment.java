package com.philm2k.wordmaster.view.word;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.philm2k.wordmaster.R;
import com.philm2k.wordmaster.adapter.UsageAdapter;
import com.philm2k.wordmaster.databinding.FragmentEditWordBinding;
import com.philm2k.wordmaster.model.entity.Word;
import com.philm2k.wordmaster.view.word.usage.UsageViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditWordFragment extends Fragment {

    private WordViewModel wordViewModel;
    private int wid;
    private String word;
    private String meaning;

    private UsageViewModel usageViewModel;
    private UsageAdapter adapter;

    private FragmentEditWordBinding binding;

    public EditWordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_edit_word, container, false);

        wordViewModel = new ViewModelProvider( this ).get(WordViewModel.class);

        assert getArguments () != null;
        wid = getArguments().getInt("id");
        word = getArguments().getString("word");
        meaning = getArguments().getString("meaning");
        binding.setWord(new Word(word,meaning));

        Toast.makeText(getContext(),word,Toast.LENGTH_LONG).show();

        binding.btnEditWord.setOnClickListener(v -> {
            Word current = binding.getWord();
            current.setId(wid);
            wordViewModel.update(current);
            Toast.makeText(getContext(),word + " is updated!!!",Toast.LENGTH_LONG).show();
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( binding.getRoot().getContext ());
        RecyclerView recyclerView = binding.rvUseage;
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize ( true );

        adapter = new UsageAdapter();
        recyclerView.setAdapter(adapter);

        usageViewModel = new ViewModelProvider(this).get(UsageViewModel.class);
        usageViewModel.findUsagesForWord(wid).observe(getViewLifecycleOwner(), usages -> adapter.setUsages(usages));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback (0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                usageViewModel.delete ( adapter.getUsageAt ( viewHolder.getAdapterPosition () ) );
                Toast.makeText ( getContext (), "Usage deleted", Toast.LENGTH_SHORT ).show ();
            }
        } ).attachToRecyclerView ( recyclerView );

        adapter.setOnItemClickListener( usage -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_edit_word_to_navigation_edit_usage);
        });

        return binding.getRoot();
    }
}
