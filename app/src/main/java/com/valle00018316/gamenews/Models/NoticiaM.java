package com.valle00018316.gamenews.Models;

public class NoticiaM {


    private String id;
    private String title, coverImage, description, body,createdDate, game;

    private int isFav;

    public NoticiaM(String id, String title, String coverImage, String description, String body, String createdDate, String game, int isFav) {
        this.id = id;
        this.title = title;
        this.coverImage = coverImage;
        this.description = description;
        this.body = body;
        this.createdDate = createdDate;
        this.game = game;
        this.isFav = isFav;
    }

    public String getId() {

        return id;
    }

    public void setId( String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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


