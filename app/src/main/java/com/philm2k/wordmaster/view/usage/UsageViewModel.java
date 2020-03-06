package com.philm2k.wordmaster.view.usage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.philm2k.wordmaster.model.entity.Usage;
import com.philm2k.wordmaster.model.repository.UsageRepository;

import java.util.List;

public class UsageViewModel  extends AndroidViewModel {

    private UsageRepository repository;
    private LiveData<List<Usage>> allUsages;

    public UsageViewModel(@NonNull Application application) {
        super (application);
        repository = new UsageRepository(application);
        allUsages = repository.getAllUsages();
    }

    public void insert(Usage usage){
        repository.insertUsage ( usage );
    }

    public void update(Usage usage){
        repository.updateUsage ( usage );
    }

    public void delete(Usage usage){
        repository.deleteUsage ( usage );
    }

    public Usage getUsageById(int id){
        return repository.getUsageById(id);
    }

    public Usage getUsageByWord(String query){
        return repository.getUsageByWord(query);
    }

    public LiveData<List<Usage>> getAllUsages(){
        return allUsages;
    }


    public LiveData<List<Usage>> findUsagesForWord(int id){

        return repository.findUsagesForWord(id);
    }
}