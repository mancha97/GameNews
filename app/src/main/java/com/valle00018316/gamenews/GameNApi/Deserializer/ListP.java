package com.valle00018316.gamenews.GameNApi.Deserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.valle00018316.gamenews.Dba.Entidad.Player;
import com.valle00018316.gamenews.Models.PlayerM;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListP implements JsonDeserializer<PlayerM> {
    @Override
    public PlayerM deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


        String id;
        String avatar, name, bio, game;
        JsonObject newsJson = json.getAsJsonObject();

        if (newsJson.get("_id") != null){
            id=newsJson.get("_id").getAsString();
        }else {
            id="";
        }

        if (newsJson.get("name") != null){
            avatar=newsJson.get("name").getAsString();
        }else {
            avatar="";
        }

        if (newsJson.get("biografia") != null){
            name=newsJson.get("biografia").getAsString();
        }else {
            name=("");
        }



        if (newsJson.get("avatar") != null){
            bio=newsJson.get("avatar").getAsString();
        }else {
            bio="";
        }

        if (newsJson.get("game") != null){
            game=newsJson.get("game").getAsString();
        }else {
            game="";
        }

       PlayerM player= new PlayerM(  id,  avatar,  name,  bio,  game);



        return player;
    }
}