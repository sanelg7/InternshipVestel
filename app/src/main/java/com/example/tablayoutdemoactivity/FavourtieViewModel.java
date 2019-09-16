package com.example.tablayoutdemoactivity;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FavourtieViewModel extends AndroidViewModel {

        private String TAG = this.getClass().getSimpleName();
        private restaurantObjectDbDao restaurantObjectDbDao;
        private AppDatabase appDatabase;
        private LiveData<List<RestaurantObjectDb>> restaurantObjectDb;

        public FavourtieViewModel(Application application) {
            super(application);

            appDatabase = AppDatabase.getDatabase(application);
            restaurantObjectDbDao = appDatabase.restaurantObjectDbDao();
            restaurantObjectDb = restaurantObjectDbDao.getAllRest();
        }

        public void insert(RestaurantObjectDb note) {
            new InsertAsyncTask(restaurantObjectDbDao).execute(note);
        }

        LiveData<List<RestaurantObjectDb>> getAllRest() {
            return restaurantObjectDb;
        }

        public void update(RestaurantObjectDb restaurantObjectDb) {
            new UpdateAsyncTask(restaurantObjectDbDao).execute(restaurantObjectDb);
        }

        public void delete(RestaurantObjectDb restaurantObjectDb) {
            new DeleteAsyncTask(restaurantObjectDbDao).execute(restaurantObjectDb);
        }

        @Override
        protected void onCleared() {
            super.onCleared();
            Log.i(TAG, "ViewModel Destroyed");
        }

        private class OperationsAsyncTask extends AsyncTask<RestaurantObjectDb, Void, Void> {

            restaurantObjectDbDao mAsyncTaskDao;

            OperationsAsyncTask(restaurantObjectDbDao dao) {
                this.mAsyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(RestaurantObjectDb... restaurantObjectDbs) {
                return null;
            }
        }

        private class InsertAsyncTask extends OperationsAsyncTask {

            InsertAsyncTask(restaurantObjectDbDao restaurantObjectDbDao) {
                super(restaurantObjectDbDao);
            }

            @Override
            protected Void doInBackground(RestaurantObjectDb... restaurantObjectDbs) {
                mAsyncTaskDao.insert(restaurantObjectDbs[0]);
                return null;
            }
        }

        private class UpdateAsyncTask extends OperationsAsyncTask {

            UpdateAsyncTask(restaurantObjectDbDao restaurantObjectDbDao) {
                super(restaurantObjectDbDao);
            }

            @Override
            protected Void doInBackground(RestaurantObjectDb... restaurantObjectDbs) {
                mAsyncTaskDao.update(restaurantObjectDbs[0]);
                return null;
            }
        }

        private class DeleteAsyncTask extends OperationsAsyncTask {

            public DeleteAsyncTask(restaurantObjectDbDao restaurantObjectDbDao) {
                super(restaurantObjectDbDao);
            }

            @Override
            protected Void doInBackground(RestaurantObjectDb... restaurantObjectDbs) {
                mAsyncTaskDao.delete(restaurantObjectDbs[0]);
                return null;
            }
        }
    }


