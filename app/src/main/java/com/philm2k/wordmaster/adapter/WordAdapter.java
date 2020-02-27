package com.philm2k.wordmaster.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.philm2k.wordmaster.R;
import com.philm2k.wordmaster.databinding.WordItemBinding;
import com.philm2k.wordmaster.model.entity.Word;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.CustomViewHolder> implements Filterable {

    private List<Word> words;
    private List<Word> filteredWords;
    private String lastFilter = "";
    private OnItemClickListener listener;

    public WordAdapter() {
        this.words = new ArrayList<> (  );
        this.filteredWords = new ArrayList<> (  );
    }

    public List<Word> getFilteredWords() {
        return filteredWords;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        WordItemBinding binding;

        TextView word;
        TextView meaning;

        CustomViewHolder(WordItemBinding binding) {
            super(binding.getRoot ());
            this.binding = binding;
            word = binding.txtWord;
            meaning = binding.txtMeaning;

            itemView.setOnClickListener( v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(filteredWords.get (position));
                }
            } );
        }
        void bind(Word word){
            binding.setWord ( word );
        }
    }

    @NonNull
    @Override
    public WordAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from ( parent.getContext () );
        WordItemBinding binding = DataBindingUtil.inflate ( inflater, R.layout.word_item,parent,false );

        return new CustomViewHolder ( binding );
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        if(filteredWords == null) return;
        Word current = filteredWords.get ( position );
        holder.bind(current);
    }

    @Override
    public int getItemCount() {
        return filteredWords != null ? filteredWords.size() : 0 ;
    }

    public void setWords(List<Word> words){
        this.words = words;
        getFilter().filter(lastFilter);
    }

    public Word getWordAt(int position){
        return words.get(position);
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    private Filter myFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            filteredWords.clear ();
            lastFilter = constraint.toString();

            if(constraint.length () == 0){
                filteredWords.addAll (words);
            }else{
                filteredWords = getFilteredResults(constraint.toString ().toLowerCase ());
            }
            FilterResults results = new FilterResults ();
            results.values = filteredWords;
            results.count = filteredWords.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredWords = (List<Word>)results.values;
            notifyDataSetChanged ();
        }
    };

    private List<Word> getFilteredResults(String constraint) {
        List<Word> results = new ArrayList<> (  );

        for(Word item: words){
            if(item.getWord ().toLowerCase ().contains ( constraint )){
                results.add(item);
            }
        }
        return results;
    }

    public interface OnItemClickListener {
        void onItemClick(Word word);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
