package com.cooey.api.client.models;

import com.cooey.api.client.models.TrackingAlertMessage.CommandIdEnum;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class TrackingAlertMessage$CommandIdEnum$Adapter extends TypeAdapter<CommandIdEnum> {
    public void write(JsonWriter jsonWriter, CommandIdEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
    }

    public CommandIdEnum read(JsonReader jsonReader) throws IOException {
        return CommandIdEnum.fromValue(String.valueOf(jsonReader.nextString()));
    }
}
