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
import com.valle00018316.gamenews.Dba.Dao.DaoC;
import com.valle00018316.gamenews.Dba.Dao.DaoP;
import com.valle00018316.gamenews.Dba.Entidad.Categoria;
import com.valle00018316.gamenews.Dba.Entidad.Player;
import com.valle00018316.gamenews.Dba.GameNDatabase;
import com.valle00018316.gamenews.GameNApi.Api;
import com.valle00018316.gamenews.GameNApi.Deserializer.ListG;
import com.valle00018316.gamenews.GameNApi.Deserializer.ListP;
import com.valle00018316.gamenews.Models.NoticiaM;
import com.valle00018316.gamenews.Models.PlayerM;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class PlayerRepo {
    private DaoP playDao;
    private String access;
    private Context mContext;

    public PlayerRepo(Application application) {
        GameNDatabase db=GameNDatabase.getDatabase(application);
        playDao = db.playerDao();
        mContext = application.getApplicationContext();
        SharedPreferences sharedPreferences=application.getSharedPreferences("logbait",MODE_PRIVATE);
        access=sharedPreferences.getString("logbait","");
//        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED){
//
//        }else {
//            Toast.makeText(mContext, "UPS! \n Please try again later", Toast.LENGTH_SHORT).show();
//        }
        cargarData();
    }


    public void cargarData(){
        Log.d("SIII", "cargarData: ");
        Gson gson = new GsonBuilder().registerTypeAdapter(PlayerM.class, new ListP()).create();

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Api.END_POINT).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();

        Api apiG = retrofit.create(Api.class);

        Call<List<PlayerM>> playerlista = apiG.getPlayers("Beared " + access);
        playerlista.enqueue(new Callback<List<PlayerM>>() {
            @Override
            public void onResponse(Call<List<PlayerM>> call, Response<List<PlayerM>> response) {
                if (response.code() == 200){
                    Log.d("desdesda", "ENTRO YEAH ");
                    for (PlayerM players : response.body()){

                        Log.d("Desesp", players.getName().toString());
                        insert(new Player(players.getId(), players.getAvatar(), players.getName(),players.getBio(),players.getGame()));
                    }
                }else if (response.code() == 401){
                }
            }

            @Override
            public void onFailure(Call<List<PlayerM>> call, Throwable t) {
                if (t instanceof SocketTimeoutException){
                    Toast.makeText(mContext, "Timed Out", Toast.LENGTH_SHORT).show();
                }else if (t instanceof Exception){
                    Toast.makeText(mContext, "Something went wrong \n Please try again later", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }

    public LiveData<List<Player>> getAllPlayers(){
        return playDao.getAllPlayers();
    }

    public LiveData<List<Player>> getPlayersByGame(String game){
        return playDao.getPlayersByGame(game);
    }


    public void insert(Player player){
        new InsertAsyncTask(playDao).execute(player);
    }

    private static class InsertAsyncTask extends AsyncTask<Player, Void, Void>{

        private DaoP myAsyncTaskPlayerDAO;

        InsertAsyncTask(DaoP dao){
            myAsyncTaskPlayerDAO = dao;
        }

        @Override
        protected Void doInBackground(Player... playerEntities) {
            myAsyncTaskPlayerDAO.insert(playerEntities);
            return null;
        }
    }



}
