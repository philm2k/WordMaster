package com.philm2k.wordmaster.view.word;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.philm2k.wordmaster.R;
import com.philm2k.wordmaster.adapter.UsageAdapter;
import com.philm2k.wordmaster.databinding.FragmentAddWordBinding;
import com.philm2k.wordmaster.model.entity.Usage;
import com.philm2k.wordmaster.model.entity.Word;
import com.philm2k.wordmaster.view.word.usage.UsageViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddWordFragment extends Fragment {

    private WordViewModel wordViewModel;
    private UsageViewModel usageViewModel;
    private UsageAdapter adapter;
    private EditText word;
    private EditText meaning;

    LinearLayout container;

    private EditText engStmt;
    private EditText korTran;

    private FragmentAddWordBinding binding;

   public AddWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_word, container, false);

        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        usageViewModel = new ViewModelProvider(this).get(UsageViewModel.class);

        /*-----Add Word 부분--------*/
        word = binding.editWord;
        meaning = binding.editMeaning;
        //container = binding.lLayout;

        final int[] wid = new int[1];   // insert된 word의 id를 저장
        binding.btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(word.getText().toString().equals("") || meaning.getText().toString().equals("") ){
                    Toast.makeText(getContext(), "Input value is not sufficient. Word or Meaning is emply", Toast.LENGTH_LONG).show();
                    return;
                }
                //Word newWord = new Word(word.getText().toString(),meaning.getText().toString());
                Word newWord = binding.getWord();
                wordViewModel.insert(newWord);
                Word inserted = wordViewModel.getWordByWord(word.getText().toString());
                wid[0] = inserted.getId();
            }
        });

        ViewGroup finalContainer = binding.lLayout;
        binding.btnAddUsageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                final View addView = inflater.inflate(R.layout.add_usage_item, null);
                finalContainer.addView(addView);

                engStmt = addView.findViewById(R.id.edit_engstmt);
                korTran = addView.findViewById(R.id.edit_korTran);

                addView.findViewById(R.id.btn_add_usage).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(wid[0] == 0){
                            Toast.makeText(getContext(), "Insert word first!!!", Toast.LENGTH_LONG).show();
                            return;
                        }
                        Usage newUsage = new Usage(engStmt.getText().toString(), korTran.getText().toString(), wid[0]);
                        Toast.makeText(getContext(), "Usage inserted successfully!!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return binding.getRoot();
    }
}
