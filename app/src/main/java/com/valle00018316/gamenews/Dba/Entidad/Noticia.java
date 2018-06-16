package com.valle00018316.gamenews.Dba.Entidad;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Noticia {

    @NonNull
    @PrimaryKey
    private String id;
    private String title, coverImage, description, body,createdDate, game;

    private int isFav;

    public Noticia(@NonNull String id, String title, String coverImage, String description, String body, String createdDate,String game ,  int isFav) {
        this.id = id;
        this.title = title;
        this.coverImage = coverImage;
        this.description = description;
        this.body = body;
        this.game = game;
        this.createdDate = createdDate;
        this.isFav = isFav;
    }



    @NonNull
    public String getId() {

        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getIsFav() {
        return isFav;
    }

    public void setIsFav(int isFav) {
        this.isFav = isFav;
    }
}
