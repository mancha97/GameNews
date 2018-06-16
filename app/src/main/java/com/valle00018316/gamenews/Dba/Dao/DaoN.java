package com.valle00018316.gamenews.Dba.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.valle00018316.gamenews.Dba.Entidad.Noticia;

import java.util.List;

@Dao
public interface DaoN {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertnoti(Noticia... noticia);

    @Query("SELECT*FROM Noticia")
    LiveData<List<Noticia>> getAllN();

    @Query("SELECT*FROM Noticia WHERE game=:game")
    LiveData<List<Noticia>> getNBGame(String game);

    @Query("SELECT*FROM Noticia WHERE title like :query")
    LiveData<List<Noticia>> getNBQuery(String query);


    @Update
    void update(Noticia... news);



}