package com.valle00018316.gamenews.Dba.Repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.valle00018316.gamenews.Dba.Dao.DaoC;
import com.valle00018316.gamenews.Dba.Entidad.Categoria;
import com.valle00018316.gamenews.Dba.GameNDatabase;
import com.valle00018316.gamenews.GameNApi.Api;
import com.valle00018316.gamenews.GameNApi.Deserializer.ListG;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class CategoRepo {

    private DaoC catDao;
    private String access;

    public CategoRepo(Application application) {
        GameNDatabase db=GameNDatabase.getDatabase(application);
        this.catDao = db.categoriaDao();

        SharedPreferences sharedPreferences=application.getSharedPreferences("logbait",MODE_PRIVATE);
        access=sharedPreferences.getString("token","");
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

    Gson gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new ListG()).create();

    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Api.END_POINT).addConverterFactory(GsonConverterFactory.create(gson));
    Retrofit retrofit = builder.build();

    Api apiG = retrofit.create(Api.class);

    Call<List<String>> listaG = apiG.list("Beared "+ access);

}
