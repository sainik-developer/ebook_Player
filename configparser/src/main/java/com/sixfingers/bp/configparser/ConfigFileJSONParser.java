package com.sixfingers.bp.configparser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.sixfingers.bp.model.Book;
import com.sixfingers.bp.model.Content;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by sainik on 09.11.18.
 */
public class ConfigFileJSONParser implements Parser<Book> {

    private Gson gson;
    private Parser<Content> contentParser;

    public ConfigFileJSONParser(final Parser<Content> contentParser) {

        this.contentParser = contentParser;

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(new TypeToken<List<Content>>() {
        }.getType(), new ContentJsonDeserialization<>());

        gson = gsonBuilder.create();
    }

    @Override
    public Book parser(InputStream inputStream) {

        return gson.fromJson(new InputStreamReader(inputStream), Book.class);

    }


    /***
     *
     * @param <Content>
     */
    private class ContentJsonDeserialization<Content> implements JsonDeserializationContext {
        @Override
        public Content deserialize(JsonElement json, Type typeOfT) throws JsonParseException {

            return null;
        }
    }

}
