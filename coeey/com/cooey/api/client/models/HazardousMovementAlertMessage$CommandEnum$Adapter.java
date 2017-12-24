package com.cooey.api.client.models;

import com.cooey.api.client.models.HazardousMovementAlertMessage.CommandEnum;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class HazardousMovementAlertMessage$CommandEnum$Adapter extends TypeAdapter<CommandEnum> {
    public void write(JsonWriter jsonWriter, CommandEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
    }

    public CommandEnum read(JsonReader jsonReader) throws IOException {
        return CommandEnum.fromValue(String.valueOf(jsonReader.nextString()));
    }
}
