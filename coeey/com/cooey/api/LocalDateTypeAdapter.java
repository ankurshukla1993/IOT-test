package com.cooey.api;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/* compiled from: ApiClient */
class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {
    private final DateTimeFormatter formatter = ISODateTimeFormat.date();

    LocalDateTypeAdapter() {
    }

    public void write(JsonWriter out, LocalDate date) throws IOException {
        if (date == null) {
            out.nullValue();
        } else {
            out.value(this.formatter.print(date));
        }
    }

    public LocalDate read(JsonReader in) throws IOException {
        switch (in.peek()) {
            case NULL:
                in.nextNull();
                return null;
            default:
                return this.formatter.parseLocalDate(in.nextString());
        }
    }
}
