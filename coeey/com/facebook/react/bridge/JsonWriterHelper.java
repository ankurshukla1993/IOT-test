package com.facebook.react.bridge;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class JsonWriterHelper {
    JsonWriterHelper() {
    }

    public static void value(JsonWriter writer, Object value) throws IOException {
        if (value instanceof Map) {
            mapValue(writer, (Map) value);
        } else if (value instanceof List) {
            listValue(writer, (List) value);
        } else {
            objectValue(writer, value);
        }
    }

    private static void mapValue(JsonWriter writer, Map<?, ?> map) throws IOException {
        writer.beginObject();
        for (Entry entry : map.entrySet()) {
            writer.name(entry.getKey().toString());
            value(writer, entry.getValue());
        }
        writer.endObject();
    }

    private static void listValue(JsonWriter writer, List<?> list) throws IOException {
        writer.beginArray();
        for (Object item : list) {
            objectValue(writer, item);
        }
        writer.endArray();
    }

    private static void objectValue(JsonWriter writer, Object value) throws IOException {
        if (value == null) {
            writer.nullValue();
        } else if (value instanceof String) {
            writer.value((String) value);
        } else if (value instanceof Number) {
            writer.value((Number) value);
        } else if (value instanceof Boolean) {
            writer.value(((Boolean) value).booleanValue());
        } else {
            throw new IllegalArgumentException("Unknown value: " + value);
        }
    }
}
