package com.cooey.api.client.models;

import com.cooey.api.client.models.Channel.TypeEnum;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class Channel$TypeEnum$Adapter extends TypeAdapter<TypeEnum> {
    public void write(JsonWriter jsonWriter, TypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
    }

    public TypeEnum read(JsonReader jsonReader) throws IOException {
        return TypeEnum.fromValue(String.valueOf(jsonReader.nextString()));
    }
}
