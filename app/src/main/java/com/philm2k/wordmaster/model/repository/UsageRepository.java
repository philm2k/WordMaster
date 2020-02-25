package com.philm2k.wordmaster.model.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.philm2k.wordmaster.model.WordMasterDatabase;
import com.philm2k.wordmaster.model.dao.UsageDao;
import com.philm2k.wordmaster.model.entity.Usage;

import java.util.List;

public class UsageRepository {

    private final UsageDao usageDao;
    private final LiveData<List<Usage>> allUsages;

    public UsageRepository(Application application) {
        WordMasterDatabase db = WordMasterDatabase.getInstance(application);
        this.usageDao = db.usageDao();
        this.allUsages = usageDao.getAll();
    }

    public LiveData<List<Usage>> getAllUsages(){
        return allUsages;
    }



    public Usage getUsageById(int id){
        try {
            return new UsageRepository.GetUsageByIdAsyncTask(usageDao).execute(id).get();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Usage getUsageByWord(String word){
        String query = "%" + word + "%";
        try {
            return new UsageRepository.GetUsageByEStmtAsyncTask(usageDao).execute(query).get();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }public void insertUsage(Usage usage) {
        new UsageRepository.InsertUsageAsyncTask (usageDao).execute ( usage );
    }
    public void updateUsage(Usage usage) {
        new UsageRepository.UpdateUsageAsyncTask (usageDao).execute ( usage );
    }
    public void deleteUsage(Usage usage) {
        new UsageRepository.DeleteUsageAsyncTask (usageDao).execute ( usage );
    }

    private static class GetUsageByIdAsyncTask extends AsyncTask<Integer, Void, Usage>{

        private UsageDao usageDao;

        public GetUsageByIdAsyncTask(UsageDao wordDao) {
            this.usageDao = usageDao;
        }

        @Override
        protected Usage doInBackground(Integer... integers) {
            return usageDao.getUsageById(integers[0]);
        }
    }

    private static class GetUsageByEStmtAsyncTask extends AsyncTask<String, Void, Usage>{

        private UsageDao usageDao;

        public GetUsageByEStmtAsyncTask(UsageDao usageDao) {
            this.usageDao = usageDao;
        }

        @Override
        protected Usage doInBackground(String... strings) {
            return usageDao.getUsageByEStmt(strings[0]);
        }
    }


    private static class InsertUsageAsyncTask extends AsyncTask<Usage, Void, Void> {

        private UsageDao usageDao;

        public InsertUsageAsyncTask(UsageDao usageDao) {
            this.usageDao = usageDao;
        }

        @Override
        protected Void doInBackground(Usage... usages) {
            usageDao.insert ( usages[0] );
            return null;
        }
    }

    private static class UpdateUsageAsyncTask extends AsyncTask<Usage, Void, Void>{

        private UsageDao usageDao;

        public UpdateUsageAsyncTask(UsageDao usageDao) {
            this.usageDao = usageDao;
        }

        @Override
        protected Void doInBackground(Usage... usages) {
            usageDao.update ( usages[0] );
            return null;
        }
    }

    private static class DeleteUsageAsyncTask extends AsyncTask<Usage, Void, Void>{

        private UsageDao usageDao;

        public DeleteUsageAsyncTask(UsageDao usageDao) {
            this.usageDao = usageDao;
        }

        @Override
        protected Void doInBackground(Usage... usages) {
            usageDao.delete ( usages[0] );
            return null;
        }
    }
}
