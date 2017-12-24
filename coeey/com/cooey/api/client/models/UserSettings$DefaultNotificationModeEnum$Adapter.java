package com.cooey.api.client.models;

import com.cooey.api.client.models.UserSettings.DefaultNotificationModeEnum;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class UserSettings$DefaultNotificationModeEnum$Adapter extends TypeAdapter<DefaultNotificationModeEnum> {
    public void write(JsonWriter jsonWriter, DefaultNotificationModeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
    }

    public DefaultNotificationModeEnum read(JsonReader jsonReader) throws IOException {
        return DefaultNotificationModeEnum.fromValue(String.valueOf(jsonReader.nextString()));
    }
}
