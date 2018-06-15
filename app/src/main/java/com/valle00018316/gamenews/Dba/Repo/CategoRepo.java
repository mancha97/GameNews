package com.valle00018316.gamenews.Dba.Repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.valle00018316.gamenews.Dba.Dao.DaoC;
import com.valle00018316.gamenews.Dba.Entidad.Categoria;
import com.valle00018316.gamenews.Dba.GameNDatabase;
import com.valle00018316.gamenews.GameNApi.Api;
import com.valle00018316.gamenews.GameNApi.Deserializer.ListG;
import com.valle00018316.gamenews.HomeActivity;
import com.valle00018316.gamenews.MainActivity;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class CategoRepo {

    private DaoC catDao;
    private String access;
    private Context mContext;

    public CategoRepo(Application application) {
        GameNDatabase db=GameNDatabase.getDatabase(application);
        this.catDao = db.categoriaDao();
        mContext = application.getApplicationContext();
        SharedPreferences sharedPreferences=application.getSharedPreferences("logbait",MODE_PRIVATE);
        access=sharedPreferences.getString("token","");

        cargarData();
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

    public void cargarData(){
        Gson gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new ListG()).create();

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Api.END_POINT).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();

        Api apiG = retrofit.create(Api.class);


        Call<List<String>> listaG = apiG.list("Beared "+ access);
        listaG.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                for (String name : response.body()){
                    insert(new Categoria(name));

                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                if (t instanceof SocketTimeoutException){
                    Toast.makeText(mContext, "Timed Out", Toast.LENGTH_SHORT).show();
                }else if (t instanceof Exception){
                    Toast.makeText(mContext, "UPS! \n Please try again later", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            }
        });
    }



}
