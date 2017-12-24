package com.cooey.api.client.models;

import com.cooey.api.client.models.ShockAlertMessage.CommandExtensionEnum;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class ShockAlertMessage$CommandExtensionEnum$Adapter extends TypeAdapter<CommandExtensionEnum> {
    public void write(JsonWriter jsonWriter, CommandExtensionEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
    }

    public CommandExtensionEnum read(JsonReader jsonReader) throws IOException {
        return CommandExtensionEnum.fromValue(String.valueOf(jsonReader.nextString()));
    }
}
