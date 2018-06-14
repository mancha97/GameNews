package com.valle00018316.gamenews.Dba.Repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.valle00018316.gamenews.Dba.Dao.DaoC;
import com.valle00018316.gamenews.Dba.Entidad.Categoria;
import com.valle00018316.gamenews.Dba.GameNDatabase;

import java.util.List;

public class CategoRepo {

    private DaoC catDao;

    public CategoRepo(Application application) {
        GameNDatabase db=GameNDatabase.getDatabase(application);
        this.catDao = db.categoriaDao();
    }

    public LiveData<List<Categoria>> getCategories(){
        return catDao.getAllCategories();
    }

    public void insert(Categoria categoria){
        new InsertCategoryAsyncTask(catDao).execute(categoria);
    }

    private static class InsertCategoryAsyncTask extends AsyncTask<Categoria, Void, Void> {

        private DaoC catDao;

        private InsertCategoryAsyncTask(DaoC catDao) {
            this.catDao = catDao;
        }

        @Override
        protected Void doInBackground(Categoria... categoria) {
            catDao.insertCategory(categoria[0]);
            return null;
        }
    }

}
