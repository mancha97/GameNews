package com.valle00018316.gamenews.GameNApi.Deserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.valle00018316.gamenews.Dba.Entidad.Noticia;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NoticiaD implements JsonDeserializer<Noticia>{

    @Override
    public Noticia deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


        String id,title,coverimage,description,body,game,cdate,isfav;

        JsonObject newsJson = json.getAsJsonObject();


        if (newsJson.get("_id") != null){
            id=newsJson.get("_id").getAsString();
        }else {
            id="";
        }

        if (newsJson.get("title") != null){
            title=newsJson.get("title").getAsString();
        }else {
            title="";
        }

        if (newsJson.get("coverImage") != null){
            coverimage=newsJson.get("coverImage").getAsString();
        }else {
            coverimage=("");
        }



        if (newsJson.get("description") != null){
            description=newsJson.get("description").getAsString();
        }else {
            description="";
        }

        if (newsJson.get("body") != null){
            body=newsJson.get("body").getAsString();
        }else {
            body="";
        }

        if (newsJson.get("game") != null){
            game=newsJson.get("game").getAsString();
        }else {
            game="";
        }
        if (newsJson.get("create_date") != null){
            cdate=newsJson.get("created_date").getAsString();
        }else {
            cdate="";
        }

        Noticia news = new Noticia(id,title,coverimage,description,body,game,cdate,0);

        return news;
    }

}
