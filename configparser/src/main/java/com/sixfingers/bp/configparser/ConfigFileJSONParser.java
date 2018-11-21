package com.sixfingers.bp.configparser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.sixfingers.bp.model.Book;
import com.sixfingers.bp.model.Content;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sainik on 09.11.18.
 */
public class ConfigFileJSONParser implements Parser<InputStream, Book> {

    private Gson gson;
    private Parser<String, Content> contentParser;


    public ConfigFileJSONParser() {
        this.contentParser = new ContentXMLParser();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Type type = new TypeToken<List<Content>>() {
        }.getType();
        gsonBuilder.registerTypeAdapter(type, new ContentJsonDeserialization());
        gson = gsonBuilder.create();
    }

    @Override
    public Book parser(InputStream inputStream) {
        return gson.fromJson(new InputStreamReader(inputStream, Charset.forName("UTF-8")), Book.class);
    }

    /***
     *
     */
    private class ContentJsonDeserialization implements JsonDeserializer<List<Content>> {
        @Override
        public List<Content> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            final List<Content> contents = new LinkedList<>();
            if (json.isJsonArray()) {
                JsonArray jsonElements = json.getAsJsonArray();
                for (JsonElement jsonElement : jsonElements) {
                    contents.add(contentParser.parser(jsonElement.getAsString()));
                }
            }
            return contents;
        }
    }
}
