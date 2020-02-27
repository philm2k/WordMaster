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
import com.philm2k.wordmaster.databinding.UsageItemBinding;
import com.philm2k.wordmaster.model.entity.Usage;

import java.util.ArrayList;
import java.util.List;

public class UsageAdapter extends RecyclerView.Adapter<UsageAdapter.CustomViewHolder> implements Filterable {

    private List<Usage> usages;
    private List<Usage> filteredUsages;
    private String lastFilter = "";
    private OnItemClickListener listener;

    public UsageAdapter() {
        this.usages = new ArrayList<>(  );
        this.filteredUsages = new ArrayList<> (  );
    }

    public List<Usage> getFilteredUsages() {
        return filteredUsages;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        UsageItemBinding binding;

        TextView engStmt;
        TextView korTran;

        CustomViewHolder(UsageItemBinding binding) {
            super(binding.getRoot ());
            this.binding = binding;
            engStmt = binding.txtEngstmt;
            korTran = binding.txtKorTran;

            itemView.setOnClickListener( v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(filteredUsages.get (position));
                }
            } );
        }
        void bind(Usage usage){
            binding.setUsage ( usage );
        }
    }

    @NonNull
    @Override
    public UsageAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from ( parent.getContext () );
        UsageItemBinding binding = DataBindingUtil.inflate ( inflater, R.layout.usage_item,parent,false );

        return new UsageAdapter.CustomViewHolder( binding );
    }

    @Override
    public void onBindViewHolder(@NonNull UsageAdapter.CustomViewHolder holder, int position) {
        if(filteredUsages == null) return;
        Usage current = filteredUsages.get ( position );
        holder.bind(current);
    }

    @Override
    public int getItemCount() {
        return filteredUsages != null ? filteredUsages.size() : 0 ;
    }

    public void setUsages(List<Usage> usages){
        this.usages = usages;
        getFilter().filter(lastFilter);
    }

    public Usage getUsageAt(int position){
        return usages.get(position);
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    private Filter myFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            filteredUsages.clear ();
            lastFilter = constraint.toString();

            if(constraint.length () == 0){
                filteredUsages.addAll (usages);
            }else{
                filteredUsages = getFilteredResults(constraint.toString ().toLowerCase ());
            }
            FilterResults results = new FilterResults ();
            results.values = filteredUsages;
            results.count = filteredUsages.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredUsages = (List<Usage>)results.values;
            notifyDataSetChanged ();
        }
    };

    private List<Usage> getFilteredResults(String constraint) {
        List<Usage> results = new ArrayList<> (  );

        for(Usage item: usages){
            if(item.getEnglish_statement().toLowerCase ().contains ( constraint )){
                results.add(item);
            }
        }
        return results;
    }

    public interface OnItemClickListener {
        void onItemClick(Usage usage);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
