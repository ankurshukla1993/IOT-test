package com.cooey.api.client.models;

import com.cooey.api.client.models.Artifact.PlatformEnum;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class Artifact$PlatformEnum$Adapter extends TypeAdapter<PlatformEnum> {
    public void write(JsonWriter jsonWriter, PlatformEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
    }

    public PlatformEnum read(JsonReader jsonReader) throws IOException {
        return PlatformEnum.fromValue(String.valueOf(jsonReader.nextString()));
    }
}
