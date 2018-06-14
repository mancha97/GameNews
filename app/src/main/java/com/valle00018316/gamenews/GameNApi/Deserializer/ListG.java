package com.valle00018316.gamenews.GameNApi.Deserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListG implements JsonDeserializer<List<String>> {
    @Override
    public List<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonArray listJson = json.getAsJsonArray();
        List<String> lista = new ArrayList<>();
        for(JsonElement x:listJson){
            lista.add(x.getAsString());
        }

        return lista;
    }
}