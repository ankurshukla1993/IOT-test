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

public class BooleanRealmListConverter implements JsonSerializer<RealmList<RealmBoolean>>, JsonDeserializer<RealmList<RealmBoolean>> {
    public JsonElement serialize(RealmList<RealmBoolean> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray ja = new JsonArray();
        Iterator it = src.iterator();
        while (it.hasNext()) {
            ja.add(context.serialize(Boolean.valueOf(((RealmBoolean) it.next()).getValue())));
        }
        return ja;
    }

    public RealmList<RealmBoolean> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        RealmList<RealmBoolean> tags = new RealmList();
        Iterator it = json.getAsJsonArray().iterator();
        while (it.hasNext()) {
            tags.add(new RealmBoolean(((Boolean) context.deserialize((JsonElement) it.next(), Boolean.class)).booleanValue()));
        }
        return tags;
    }
}
