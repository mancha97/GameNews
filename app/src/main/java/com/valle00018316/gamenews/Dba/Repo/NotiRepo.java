package com.valle00018316.gamenews.Dba.Repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.valle00018316.gamenews.Dba.Dao.DaoN;
import com.valle00018316.gamenews.Dba.Entidad.Noticia;
import com.valle00018316.gamenews.Dba.GameNDatabase;
import com.valle00018316.gamenews.GameNApi.Api;
import com.valle00018316.gamenews.GameNApi.Deserializer.NoticiaD;
import com.valle00018316.gamenews.Models.NoticiaM;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class NotiRepo {

    private final Context mContext;
    private String access;
    private DaoN mNotiDao;


    public NotiRepo(Application application) {
        GameNDatabase db = GameNDatabase.getDatabase(application);
        mNotiDao = db.noticiaDao();
        mContext = application.getApplicationContext();
        SharedPreferences sharedPreferences=application.getSharedPreferences("logbait",MODE_PRIVATE);
        access=sharedPreferences.getString("logbait","");

        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);


         fetchapi();
         Log.d("D", "Instanceando Repo... ");
    }


    public void fetchapi(){
        Gson gson = new GsonBuilder().registerTypeAdapter(NoticiaM.class, new NoticiaD()).create();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(Api.END_POINT).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = retrofitBuilder.build();
        Api apiG = retrofit.create(Api.class);

        Call<List<NoticiaM>> notilista = apiG.noticias("Beared " + access);
        notilista.enqueue(new Callback<List<NoticiaM>>() {
            @Override
            public void onResponse(Call<List<NoticiaM>> call, Response<List<NoticiaM>> response) {
                if(response.isSuccessful()){

                    for (NoticiaM noticiaM: response.body()){
                        insert(new Noticia(
                                noticiaM.getId(), noticiaM.getTitle(), noticiaM.getCoverImage(),
                                noticiaM.getDescription(),noticiaM.getBody(),noticiaM.getCreatedDate(),
                                noticiaM.getGame(),noticiaM.getIsFav()
                                )
                        );
                        Log.d("News",noticiaM.getCreatedDate().toString());
                    }
                }else
                    Log.d("News","Mancha la esta mordiendo");
            }

            @Override
            public void onFailure(Call<List<NoticiaM>> call, Throwable t) {
                if (t instanceof SocketTimeoutException){
                    Toast.makeText(mContext, "Timed Out", Toast.LENGTH_SHORT).show();
                }else if (t instanceof Exception){
                    Toast.makeText(mContext, "Something went wrong \n Please try again later", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            }
        });
    }

    public LiveData<List<Noticia>> getAllNoti() {
        return mNotiDao.getAllN();
    }
    public LiveData<List<Noticia>> getNBGame(String game) { return mNotiDao.getNBGame(game); }
    public LiveData<List<Noticia>> getNBQuery(String query) { return mNotiDao.getNBQuery(query); }

    public void insert (Noticia noticia) {
        new InsertAsyncTask(mNotiDao).execute(noticia);

    }

    private static class InsertAsyncTask extends AsyncTask<Noticia, Void, Void>{

        private DaoN myAsyncTask;

        InsertAsyncTask(DaoN dao){
            myAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(Noticia... noticias) {
            myAsyncTask.insertnoti(noticias);
            return null;
        }
    }

    public void update(Noticia noticia){
        new UpdateAsyncTask(mNotiDao).execute(noticia);
    }

    private static class UpdateAsyncTask extends AsyncTask<Noticia, Void, Void>{

        private DaoN myAsyncTask;

        UpdateAsyncTask(DaoN dao){
            myAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(Noticia... noticias) {
            myAsyncTask.update(noticias);
            return null;
        }
    }
}