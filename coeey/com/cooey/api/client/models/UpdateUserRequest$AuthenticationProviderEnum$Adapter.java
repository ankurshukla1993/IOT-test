package com.cooey.api.client.models;

import com.cooey.api.client.models.UpdateUserRequest.AuthenticationProviderEnum;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class UpdateUserRequest$AuthenticationProviderEnum$Adapter extends TypeAdapter<AuthenticationProviderEnum> {
    public void write(JsonWriter jsonWriter, AuthenticationProviderEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
    }

    public AuthenticationProviderEnum read(JsonReader jsonReader) throws IOException {
        return AuthenticationProviderEnum.fromValue(String.valueOf(jsonReader.nextString()));
    }
}
