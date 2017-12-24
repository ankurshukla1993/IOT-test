package com.cooey.common.converter;

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

public class TagRealmListConverter implements JsonSerializer<RealmList<Tag>>, JsonDeserializer<RealmList<Tag>> {
    public JsonElement serialize(RealmList<Tag> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray ja = new JsonArray();
        Iterator it = src.iterator();
        while (it.hasNext()) {
            ja.add(context.serialize((Tag) it.next()));
        }
        return ja;
    }

    public RealmList<Tag> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        RealmList<Tag> tags = new RealmList();
        Iterator it = json.getAsJsonArray().iterator();
        while (it.hasNext()) {
            tags.add((Tag) context.deserialize((JsonElement) it.next(), Tag.class));
        }
        return tags;
    }
}
