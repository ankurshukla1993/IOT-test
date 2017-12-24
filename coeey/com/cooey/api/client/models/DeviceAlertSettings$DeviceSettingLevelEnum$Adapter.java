package com.cooey.api.client.models;

import com.cooey.api.client.models.DeviceAlertSettings.DeviceSettingLevelEnum;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class DeviceAlertSettings$DeviceSettingLevelEnum$Adapter extends TypeAdapter<DeviceSettingLevelEnum> {
    public void write(JsonWriter jsonWriter, DeviceSettingLevelEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
    }

    public DeviceSettingLevelEnum read(JsonReader jsonReader) throws IOException {
        return DeviceSettingLevelEnum.fromValue(String.valueOf(jsonReader.nextString()));
    }
}
