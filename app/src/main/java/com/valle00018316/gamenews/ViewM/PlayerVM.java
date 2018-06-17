package com.valle00018316.gamenews.ViewM;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.valle00018316.gamenews.Dba.Entidad.Noticia;
import com.valle00018316.gamenews.Dba.Entidad.Player;
import com.valle00018316.gamenews.Dba.Repo.NotiRepo;
import com.valle00018316.gamenews.Dba.Repo.PlayerRepo;
import com.valle00018316.gamenews.Models.PlayerM;

import java.util.List;

public class PlayerVM extends AndroidViewModel {

    private PlayerRepo mRepository;

    private LiveData<List<Player>> mPlayer;

    public PlayerVM (Application application) {
        super(application);
        mRepository = new PlayerRepo(application);
    }

    public LiveData<List<Player>> getAllNoti() { return mRepository.getAllPlayers(); }
    public LiveData<List<Player>>  getNBGame(String game){return mRepository.getPlayersByGame(game);}

    public void insert(Player player) { mRepository.insert(player); }
}