package com.valle00018316.gamenews.Dba.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.valle00018316.gamenews.Dba.Entidad.Player;

import java.util.List;

@Dao
public interface DaoP {

    @Query("SELECT*FROM Player WHERE game=:game")
    LiveData<List<Player>> getPlayer(String game);


}