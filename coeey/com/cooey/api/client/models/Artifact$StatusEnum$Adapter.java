package com.cooey.api.client.models;

import com.cooey.api.client.models.Artifact.StatusEnum;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class Artifact$StatusEnum$Adapter extends TypeAdapter<StatusEnum> {
    public void write(JsonWriter jsonWriter, StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
    }

    public StatusEnum read(JsonReader jsonReader) throws IOException {
        return StatusEnum.fromValue(String.valueOf(jsonReader.nextString()));
    }
}
