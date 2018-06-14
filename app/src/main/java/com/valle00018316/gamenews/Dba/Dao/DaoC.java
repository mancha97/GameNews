package com.valle00018316.gamenews.Dba.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.valle00018316.gamenews.Dba.Entidad.Categoria;

import java.util.List;

@Dao
public interface DaoC {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Categoria categoryEntity);

    @Query("SELECT*FROM Categoria")
    LiveData<List<Categoria>> getAllCategories();

}

