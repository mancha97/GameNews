package com.valle00018316.gamenews.Dba.Repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.valle00018316.gamenews.Dba.Dao.DaoN;
import com.valle00018316.gamenews.Dba.Entidad.Noticia;
import com.valle00018316.gamenews.Dba.GameNDatabase;

import java.util.List;

public class NotiRepo {

    private DaoN mNotiDao;
    private LiveData<List<Noticia>> mAllNoti;

    public NotiRepo(Application application) {
        GameNDatabase db = GameNDatabase.getDatabase(application);
        mNotiDao = db.noticiaDao();
        mAllNoti = mNotiDao.getAllN();
    }

    public LiveData<List<Noticia>> getAllNoti() {
        return mAllNoti;
    }


    public void insert (Noticia noticia) {
        new insertAsyncTask(mNotiDao).execute(noticia);
    }

    private static class insertAsyncTask extends AsyncTask<Noticia, Void, Void> {

        private DaoN mAsyncTaskDao;

        insertAsyncTask(DaoN dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Noticia... params) {
            mAsyncTaskDao.insertnoti(params[0]);
            return null;
        }
    }
}