package com.cooey.api.client.models;

import com.cooey.api.client.models.DietTemplate.OwnerTypeEnum;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class DietTemplate$OwnerTypeEnum$Adapter extends TypeAdapter<OwnerTypeEnum> {
    public void write(JsonWriter jsonWriter, OwnerTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
    }

    public OwnerTypeEnum read(JsonReader jsonReader) throws IOException {
        return OwnerTypeEnum.fromValue(String.valueOf(jsonReader.nextString()));
    }
}
