package com.cooey.common.vo;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.realm.RealmList;
import java.lang.reflect.Type;
import java.util.Iterator;

public class RealmStringListConverter implements JsonSerializer<RealmList<RealmString>>, JsonDeserializer<RealmList<RealmString>> {
    public JsonElement serialize(RealmList<RealmString> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray ja = new JsonArray();
        Iterator it = src.iterator();
        while (it.hasNext()) {
            ja.add(context.serialize(((RealmString) it.next()).getValue()));
        }
        return ja;
    }

    public RealmList<RealmString> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        RealmList<RealmString> tags = new RealmList();
        Iterator it = json.getAsJsonArray().iterator();
        while (it.hasNext()) {
            tags.add(new RealmString((String) context.deserialize((JsonElement) it.next(), String.class)));
        }
        return tags;
    }
}
