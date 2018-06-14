package com.valle00018316.gamenews.Dba;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.valle00018316.gamenews.Dba.Dao.DaoC;
import com.valle00018316.gamenews.Dba.Dao.DaoN;
import com.valle00018316.gamenews.Dba.Dao.DaoP;
import com.valle00018316.gamenews.Dba.Entidad.Categoria;
import com.valle00018316.gamenews.Dba.Entidad.Noticia;
import com.valle00018316.gamenews.Dba.Entidad.Player;

@Database(entities = {Noticia.class, Player.class, Categoria.class}, version = 1, exportSchema = false)
public abstract class GameNDatabase extends RoomDatabase {

    public abstract DaoN noticiaDao();
    public abstract DaoP playerDao();
    public abstract DaoC categoriaDao();


    private static GameNDatabase INSTANCE;


    public static GameNDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (GameNDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            GameNDatabase.class, "GN_database")
                            .build();

                }
            }
        }
        return INSTANCE;
    }



    }
