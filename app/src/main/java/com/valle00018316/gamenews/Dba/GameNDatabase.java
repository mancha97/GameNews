package com.valle00018316.gamenews.Dba;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.valle00018316.gamenews.Dba.Entity.Noticia;
import com.valle00018316.gamenews.Dba.Entity.Player;

@Database(entities = {Noticia.class, Player.class}, version = 1)
public abstract class DbaGameN extends RoomDatabase {

    public abstract WordDao wordDao();

    private static WordRoomDatabase INSTANCE;


    static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
