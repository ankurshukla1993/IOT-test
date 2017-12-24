package com.cooey.android.vitals.converters;

import android.arch.persistence.room.TypeConverter;
import com.cooey.android.vitals.Field;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class Converter {
    static Gson gson = new GsonBuilder().create();

    static class C08151 extends TypeToken<List<Field>> {
        C08151() {
        }
    }

    @TypeConverter
    public static List<Field> convertStringToFieldList(String fieldJson) {
        return (List) gson.fromJson(fieldJson, new C08151().getType());
    }

    @TypeConverter
    public static String convertFieldListToString(List<Field> fields) {
        return gson.toJson((Object) fields);
    }
}
