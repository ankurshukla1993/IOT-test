package com.cooey.common.converter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TagListConverter implements JsonDeserializer<Map<String, String>> {
    public Map<String, String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Map<String, String> result = new HashMap();
        Iterator it = json.getAsJsonArray().iterator();
        while (it.hasNext()) {
            for (Entry<String, JsonElement> entry : ((JsonElement) it.next()).getAsJsonObject().entrySet()) {
                result.put((String) entry.getKey(), ((JsonElement) entry.getValue()).getAsString());
            }
        }
        return result;
    }
}
