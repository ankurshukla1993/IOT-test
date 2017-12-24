package com.cooey.api.client.models;

import com.cooey.api.client.models.Allergy.LevelEnum;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class Allergy$LevelEnum$Adapter extends TypeAdapter<LevelEnum> {
    public void write(JsonWriter jsonWriter, LevelEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
    }

    public LevelEnum read(JsonReader jsonReader) throws IOException {
        return LevelEnum.fromValue(String.valueOf(jsonReader.nextString()));
    }
}
