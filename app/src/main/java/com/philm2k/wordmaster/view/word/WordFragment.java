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
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.philm2k.wordmaster.R;
import com.philm2k.wordmaster.adapter.WordAdapter;
import com.philm2k.wordmaster.databinding.FragmentWordBinding;

/**
 * Base: Coding in Flow Room + ViewModel + LiveData + RecyclerView (MVVM) Part 9: EDIT NOTES ON ITEM CLICK - Android
 * https://codinginflow.com/tutorials/android/room-viewmodel-livedata-recyclerview-mvvm/part-9-onitemclicklistener-update-functionality 참고
 */
public class WordFragment extends Fragment implements SearchView.OnQueryTextListener{

    private WordViewModel viewModel;
    private WordAdapter adapter;
    private FragmentWordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate (inflater, R.layout.fragment_word, container, false );

        setHasOptionsMenu ( true );

        RecyclerView recyclerView = InitRecyclerView();

        viewModel = new ViewModelProvider( this ).get ( WordViewModel.class );
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.getAllWords().observe(getViewLifecycleOwner(), words -> adapter.setWords(words));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback (0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete ( adapter.getWordAt ( viewHolder.getAdapterPosition () ) );
                Toast.makeText ( getContext (), R.string.deleted_word, Toast.LENGTH_SHORT ).show ();
            }
        } ).attachToRecyclerView ( recyclerView );

        /**
         * 손가락이나 펜으로 RecyclerView 항목을 터치했을 때 편집 실행
         */
        adapter.setOnItemClickListener (word -> {
            Bundle args = new Bundle();

            args.putInt("id", word.getId());
            args.putString("word", word.getWord());
            args.putString("meaning", word.getMeaning());

            // Toast.makeText(getContext(), word.getWord(),Toast.LENGTH_LONG).show();
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_word_to_navigation_word_detail, args);
        });

        return binding.getRoot();
    }

    private RecyclerView InitRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        RecyclerView recyclerView = binding.rvWord;
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize ( true );

        adapter = new WordAdapter();
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate ( R.menu.word_menu, menu );
        MenuItem searchItem = menu.findItem(R.id.action_word_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener ( this );
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_add_word){
            //Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_word_to_navigation_add_word);
            AddWordDialogFragment dialog = new AddWordDialogFragment();
            dialog.show(getChildFragmentManager(), "Hello");
        }
        return super.onOptionsItemSelected(item);
    }
}