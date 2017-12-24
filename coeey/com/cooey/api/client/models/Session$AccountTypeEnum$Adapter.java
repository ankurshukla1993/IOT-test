package com.cooey.api.client.models;

import com.cooey.api.client.models.Session.AccountTypeEnum;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class Session$AccountTypeEnum$Adapter extends TypeAdapter<AccountTypeEnum> {
    public void write(JsonWriter jsonWriter, AccountTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
    }

    public AccountTypeEnum read(JsonReader jsonReader) throws IOException {
        return AccountTypeEnum.fromValue(String.valueOf(jsonReader.nextString()));
    }
}
