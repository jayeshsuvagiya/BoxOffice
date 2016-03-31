package com.adamant.boxoffice.Network;

import com.adamant.boxoffice.Model.Movie;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by jayesh on 20-03-2016.
 */
public class movieDeserializer implements JsonDeserializer<ArrayList<Movie>> {
    @Override
    public ArrayList<Movie> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement content = json.getAsJsonObject().get("results");
        Type listType = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        return new Gson().fromJson(content,listType);
    }
}
