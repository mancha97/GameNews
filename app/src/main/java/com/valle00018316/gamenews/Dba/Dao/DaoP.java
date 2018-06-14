package com.valle00018316.gamenews.Dba.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.valle00018316.gamenews.Dba.Entity.Noticia;

import java.util.List;

@Dao
public interface DaoN {

    @Query("SELECT*FROM Noticia")
    LiveData<List<Noticia>> getAllN();

    @Query("SELECT*FROM Noticia WHERE game=:game")
    LiveData<List<Noticia>> getNBGame(String game);

    @Query("SELECT*FROM Noticia WHERE title like :query")
    LiveData<List<Noticia>> getNBQuery(String query);


}